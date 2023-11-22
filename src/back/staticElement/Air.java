package back.staticElement;

import java.util.Iterator;
import java.util.List;

import back.mobileElement.Beacon;
import back.mobileElement.Satellite;
import back.event.AvailableForSynchronisation;
import back.event.SynchronisationEnded;

public class Air extends Space {

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

			if (!satellite.isInSynchronisation()) {
				// Abonnement des balises pleines à l'évènement "Disponible pour
				// synchronisation" du satellite.
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
				Iterator<Beacon> itor = satellite.getBeaconsInSynchro().iterator();
				while (itor.hasNext()) {
					Beacon beacon = itor.next();
					if (!satellite.isInRangeOf(beacon)) {
						satellite.getEventHandler().registerListener(SynchronisationEnded.class, beacon);
						SynchronisationEnded synchroEndedEvent = new SynchronisationEnded(satellite);
						satellite.getEventHandler().send(synchroEndedEvent);
						satellite.getEventHandler().unregisterListener(SynchronisationEnded.class, beacon);
						itor.remove();
					}
				}

				if (satellite.getBeaconsInSynchro().isEmpty()) {
					satellite.removeSynchronisationState();
				}
			}
		}
	}

}
