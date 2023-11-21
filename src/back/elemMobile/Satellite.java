package back.elemMobile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import back.event.AbstractEvent;
import back.event.SynchronisationEnCours;
import back.event.SynchronisationTerminee;

public class Satellite extends ElementMobile {

	private boolean enSynchro;

	private Set<Balise> balisesEnSynchro;

	public Set<Balise> getBalisesEnSynchro() {
		return balisesEnSynchro;
	}

	private List<Integer> donneesRecuperees;

	public Satellite() {
		super(new MoveStrategySatellite());
		this.donneesRecuperees = new ArrayList<>();
		this.balisesEnSynchro = new HashSet<>();
	}

	public void removeSynchronisationState() {
		enSynchro = false;
	}

	public boolean estEnSynchronisation() {
		return enSynchro;
	}

	public boolean isInRangeOf(Balise balise) {
		return (this.hautDroit.x > balise.basGauche.x - balise.getRangeSynchro()
				&& this.hautDroit.x < balise.hautDroit.x + balise.getRangeSynchro())
				|| (this.basGauche.x > balise.basGauche.x - balise.getRangeSynchro()
						&& this.basGauche.x < balise.hautDroit.x + balise.getRangeSynchro());
	}

	@Override
	public void receive(AbstractEvent event) {
		if (event.toString().equals("Synchronisation en cours")) {
			// Reçoit "Synchronisation en cours" d'une balise si présent dans sa zone de
			// synchronisation

			// Récupère les données de la balise
			this.enSynchro = true;
			Balise balise = (Balise) event.getSource();
			int stockageBalise = balise.getStockage();
			this.donneesRecuperees.add(stockageBalise);

			// Enregistre la limite de la zone de synchronisation
			this.balisesEnSynchro.add(balise);

			// Abonnement de la balise à l'évènement "Synchronisation terminée"
			this.getEventHandler().registerListener(SynchronisationTerminee.class, balise);

			// Désabonnement de la balise pour l'évènement "Synchronisation en cours"
			balise.getEventHandler().unregisterListener(SynchronisationEnCours.class, this);
		}
	}

}
