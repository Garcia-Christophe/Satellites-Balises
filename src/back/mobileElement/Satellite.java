package back.mobileElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import back.event.AbstractEvent;
import back.event.SynchronisationInProgress;
import back.event.SynchronisationEnded;
import back.movement.MoveStrategySatellite;

public class Satellite extends MobileElement {

	private boolean inSynchro;

	private Set<Beacon> beaconsInSynchro;

	public Set<Beacon> getBeaconsInSynchro() {
		return beaconsInSynchro;
	}

	private List<Integer> collectedData;

	public Satellite() {
		super(new MoveStrategySatellite());
		this.collectedData = new ArrayList<>();
		this.beaconsInSynchro = new HashSet<>();
	}

	public void removeSynchronisationState() {
		inSynchro = false;
	}

	public boolean isInSynchronisation() {
		return inSynchro;
	}

	public boolean isInRangeOf(Beacon balise) {
		return (this.topRight.x > balise.bottomLeft.x - balise.getRangeSynchro()
				&& this.topRight.x < balise.topRight.x + balise.getRangeSynchro())
				|| (this.bottomLeft.x > balise.bottomLeft.x - balise.getRangeSynchro()
						&& this.bottomLeft.x < balise.topRight.x + balise.getRangeSynchro());
	}

	@Override
	public void receive(AbstractEvent event) {
		if (event.toString().equals("Synchronisation en cours")) {
			// Reçoit "Synchronisation en cours" d'une balise si présent dans sa zone de
			// synchronisation

			// Récupère les données de la balise
			this.inSynchro = true;
			Beacon beacon = (Beacon) event.getSource();
			int BeaconStorage = beacon.getStorage();
			this.collectedData.add(BeaconStorage);

			// Enregistre la limite de la zone de synchronisation
			this.beaconsInSynchro.add(beacon);

			// Abonnement de la balise à l'évènement "Synchronisation terminée"
			this.getEventHandler().registerListener(SynchronisationEnded.class, beacon);

			// Désabonnement de la balise pour l'évènement "Synchronisation en cours"
			beacon.getEventHandler().unregisterListener(SynchronisationInProgress.class, this);
		}
	}

}
