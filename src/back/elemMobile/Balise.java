package back.elemMobile;

import java.util.Random;

import back.elemStatic.Espace;
import back.event.AbstractEvent;
import back.event.SynchronisationEnCours;

public class Balise extends ElementMobile {

	private int capacite, stockage, nbDescente, indexDescente;

	private boolean estPleine, enSynchro;

	private MoveStrategy defaultStrategy;

	private int rangeSynchro = 20;

	public Balise(MoveStrategy moveStrategy, int capacite, int maxY, int minY) {
		super(moveStrategy);
		Random rand = new Random();
		this.capacite = capacite;
		this.defaultStrategy = moveStrategy;
		this.stockage = this.indexDescente = 0;
		this.estPleine = this.enSynchro = false;
		this.nbDescente = rand.nextInt(maxY - minY - 1) / 10;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public int getStockage() {
		return stockage;
	}

	public void setStockage(int stockage) {
		this.stockage = stockage;
	}

	public boolean estPleine() {
		return estPleine;
	}

	public void setEstPleine(boolean estPleine) {
		this.estPleine = estPleine;
	}

	public int getNbDescente() {
		return nbDescente;
	}

	public void setNbDescente(int nbDescente) {
		this.nbDescente = nbDescente;
	}

	public int getIndexDescente() {
		return indexDescente;
	}

	public void setIndexDescente(int indexDescente) {
		this.indexDescente = indexDescente;
	}

	public MoveStrategy getDefaultStrategy() {
		return defaultStrategy;
	}

	public int getRangeSynchro() {
		return this.rangeSynchro;
	}

	public boolean estEnSynchro() {
		return enSynchro;
	}

	public void stockerDonnee() {
		Random rand = new Random();
		int nbdonnee = rand.nextInt(3) + 1;
		this.stockage += nbdonnee;
		this.estPleine = this.stockage >= this.capacite;
		if (estPleine) {
			this.setMoveStrategy(new MoveStrategySurface());
		}
	}

	@Override
	public void receive(AbstractEvent event) {
		// Définition de la présence (ou non) de la source dans le range de
		// synchronisation de la balise
		Satellite satellite = (Satellite) event.getSource();

		if (event.toString().equals("Disponible pour synchronisation") && satellite.isInRangeOf(this)
				&& !this.enSynchro) {
			// Reçoit "Disponible pour synchronisation" de tous les satellites uniquement si
			// pleine.
			this.getEventHandler().registerListener(SynchronisationEnCours.class, satellite);
			SynchronisationEnCours synchroEnCoursEvent = new SynchronisationEnCours(this);
			this.getEventHandler().send(synchroEnCoursEvent);
			this.enSynchro = true;
		} else if (event.toString().equals("Synchronisation terminée") && this.enSynchro) {
			// Reçoit "Synchronisation terminée" du satellite auquel la balise se
			// synchronise

			// Reset des données
			this.enSynchro = false;
			this.setStockage(0);
			this.setEstPleine(false);
			this.setIndexDescente(this.getNbDescente());

			Espace.removeBaliseFromBalisesPleines(this);
		}
	}
}
