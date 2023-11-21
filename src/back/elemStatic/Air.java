package back.elemStatic;

import java.util.Iterator;
import java.util.List;

import back.elemMobile.Balise;
import back.elemMobile.Satellite;
import back.event.DisponiblePourSynchronisation;
import back.event.SynchronisationTerminee;

public class Air extends Espace {

	private List<Satellite> satellites;

	public Air(int maxX, int minX, int maxY, int minY, List<Satellite> satellites) {
		super(maxX, minX, maxY, minY);
		this.satellites = satellites;
	}

	public List<Satellite> getSatellites() {
		return satellites;
	}

	public void setSatellites(List<Satellite> satellites) {
		this.satellites = satellites;
	}

	@Override
	public void move() {
		for (Satellite satellite : this.satellites) {
			satellite.getMoveStrategy().move(satellite, getMaxX(), getMinX(), getMaxY(), getMinY());

			if (!satellite.estEnSynchronisation()) {
				// Abonnement des balises pleines à l'évènement "Disponible pour
				// synchronisation" du satellite.
				for (Balise balise : Espace.getBalisesPleines()) {
					satellite.getEventHandler().registerListener(DisponiblePourSynchronisation.class, balise);
				}

				// Envoi de l'évènement "Disponible pour synchronisation"
				DisponiblePourSynchronisation event = new DisponiblePourSynchronisation(satellite);
				satellite.getEventHandler().send(event);

				// Désabonnement des balises pleines pour l'évènement "Disponible pour
				// synchronisation"
				for (Balise balise : Espace.getBalisesPleines()) {
					satellite.getEventHandler().unregisterListener(DisponiblePourSynchronisation.class, balise);
				}
			} else {
				Iterator<Balise> itor = satellite.getBalisesEnSynchro().iterator();
				while (itor.hasNext()) {
					Balise balise = itor.next();
					if (!satellite.isInRangeOf(balise)) {
						satellite.getEventHandler().registerListener(SynchronisationTerminee.class, balise);
						SynchronisationTerminee synchroTermineeEvent = new SynchronisationTerminee(satellite);
						satellite.getEventHandler().send(synchroTermineeEvent);
						satellite.getEventHandler().unregisterListener(SynchronisationTerminee.class, balise);
						itor.remove();
					}
				}

				if (satellite.getBalisesEnSynchro().isEmpty()) {
					satellite.removeSynchronisationState();
				}
			}
		}
	}

}
