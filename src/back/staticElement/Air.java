package back.staticElement;

import java.util.Iterator;
import java.util.List;

import back.event.AvailableForSynchronisation;
import back.event.SynchronisationEnded;
import back.mobileElement.Beacon;
import back.mobileElement.Satellite;

/**
 * L'air est l'espace dans lequel se déplacent des satellites.
 */
public class Air extends Space {

	/**
	 * Liste des satellites.
	 */
	private List<Satellite> satellites;

	/**
	 * Crée l'espace d'eau.
	 * 
	 * @param maxX       - Coordonnée maximale sur l'axe X
	 * @param minX       - Coordonnée minimale sur l'axe X
	 * @param maxY       - Coordonnée maximale sur l'axe Y
	 * @param minY       - Coordonnée minimale sur l'axe Y
	 * @param satellites - Liste des satellites qui s'y déplaceront
	 */
	public Air(int maxX, int minX, int maxY, int minY, List<Satellite> satellites) {
		super(maxX, minX, maxY, minY);
		this.satellites = satellites;
	}

	@Override
	public void move() {
		// Exécute une étape de simulation pour chaque satellite
		for (Satellite satellite : this.satellites) {
			satellite.getMoveStrategy().move(satellite, getMaxX(), getMinX(), getMaxY(), getMinY());

			if (!satellite.isInSynchronisation()) {
				// Abonnement des balises pleines à l'évènement "Disponible pour
				// synchronisation" du satellite, si le satellite n'est pas encore en
				// synchronisation
				for (Beacon balise : Space.getfullBeacons()) {
					satellite.getEventHandler().registerListener(AvailableForSynchronisation.class, balise);
				}

				// Envoi de l'évènement "Disponible pour synchronisation"
				AvailableForSynchronisation event = new AvailableForSynchronisation(satellite);
				satellite.getEventHandler().send(event);

				// Désabonnement des balises pleines pour l'évènement "Disponible pour
				// synchronisation"
				for (Beacon beacon : Space.getfullBeacons()) {
					satellite.getEventHandler().unregisterListener(AvailableForSynchronisation.class, beacon);
				}
			} else {
				// Le satellite est en synchronisation avec une ou plusieurs balises
				Iterator<Beacon> itor = satellite.getBeaconsInSynchro().iterator();

				while (itor.hasNext()) {
					Beacon beacon = itor.next();

					if (!satellite.isInRangeOf(beacon)) {
						// Si le satellite n'est plus dans la zone de synchronisation de cette balise,
						// alors termine la synchronisation et sa liaison avec la balise
						satellite.getEventHandler().registerListener(SynchronisationEnded.class, beacon);
						SynchronisationEnded synchroEndedEvent = new SynchronisationEnded(satellite);
						satellite.getEventHandler().send(synchroEndedEvent);
						satellite.getEventHandler().unregisterListener(SynchronisationEnded.class, beacon);
						itor.remove();
					}
				}

				// Si le satellite n'est plus en synchronisation avec aucune balise, alors met à
				// jour l'état de synchronisation
				if (satellite.getBeaconsInSynchro().isEmpty()) {
					satellite.removeSynchronisationState();
				}
			}
		}
	}

	public List<Satellite> getSatellites() {
		return satellites;
	}

	public void setSatellites(List<Satellite> satellites) {
		this.satellites = satellites;
	}

}
