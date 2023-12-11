package back.mobileElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import back.event.AbstractEvent;
import back.event.SynchronisationEnded;
import back.event.SynchronisationInProgress;
import back.movement.MoveStrategySatellite;

/**
 * Classe représentant un satellite. Un satellite est un élément mobile se
 * déplaçant dans l'espace, capable de se synchroniser avec une balise plein de
 * données à récolter, afin de les stocker à son tour.
 */
public class Satellite extends MobileElement {

	/**
	 * True si le satellite est en cours de synchronisation, false sinon.
	 */
	private boolean inSynchro;

	/**
	 * Liste de balises en synchronisation.
	 */
	private Set<Beacon> beaconsInSynchro;

	/**
	 * Liste des données récoltées à chaque synchronisation avec une balise.
	 */
	private List<Integer> collectedData;

	/**
	 * Crée un satellite.
	 */
	public Satellite() {
		super(new MoveStrategySatellite());
		this.collectedData = new ArrayList<>();
		this.beaconsInSynchro = new HashSet<>();
	}

	/**
	 * @param beacon - Balise avec laquelle se synchroniser
	 * @return True si le satellite est dans la zone de synchronisation de la balise
	 */
	public boolean isInRangeOf(Beacon beacon) {
		return (this.topRight.x > beacon.bottomLeft.x - beacon.getRangeSynchro()
				&& this.topRight.x < beacon.topRight.x + beacon.getRangeSynchro())
				|| (this.bottomLeft.x > beacon.bottomLeft.x - beacon.getRangeSynchro()
						&& this.bottomLeft.x < beacon.topRight.x + beacon.getRangeSynchro());
	}

	@Override
	public void receive(AbstractEvent event) {
		if (event.toString().equals("Synchronisation en cours")) {
			// Reçoit "Synchronisation en cours" d'une balise si le satellite est présent
			// dans sa zone de synchronisation

			// Récupère les données de la balise
			this.inSynchro = true;
			Beacon beacon = (Beacon) event.getSource();
			int beaconStorage = beacon.getStorage();
			this.collectedData.add(beaconStorage);

			// Enregistre la balise avec laquelle il faudra se désynchroniser
			this.beaconsInSynchro.add(beacon);

			// Abonnement de la balise à l'évènement "Synchronisation terminée"
			this.eventHandler.registerListener(SynchronisationEnded.class, beacon);

			// Désabonnement de la balise pour l'évènement "Synchronisation en cours"
			beacon.eventHandler.unregisterListener(SynchronisationInProgress.class, this);
		}
	}

	/**
	 * Met fin à l'état de synchronisation du satellite.
	 */
	public void removeSynchronisationState() {
		inSynchro = false;
	}

	/**
	 * Récupère l'état du satellite.
	 * 
	 * @return true si le satellite est en cours de synchronisation, false sinon
	 */
	public boolean isInSynchronisation() {
		return inSynchro;
	}

	/**
	 * Récupère la liste de balises prêtes à la synchronisation.
	 * 
	 * @return la liste de balises prêtes à la synchronisation
	 */
	public Set<Beacon> getBeaconsInSynchro() {
		return beaconsInSynchro;
	}

}
