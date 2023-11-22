package back.mobileElement;

import java.util.Random;

import back.staticElement.Space;
import back.event.AbstractEvent;
import back.event.SynchronisationInProgress;
import back.movement.MoveStrategy;
import back.movement.MoveStrategySurface;

public class Beacon extends MobileElement {

	private int capacity, storage, nbDescente, indexDescente;

	private boolean isFull, InSynchro;

	private MoveStrategy defaultStrategy;

	private int rangeSynchro = 20;

	public Beacon(MoveStrategy moveStrategy, int capacity, int maxY, int minY) {
		super(moveStrategy);
		Random rand = new Random();
		this.capacity = capacity;
		this.defaultStrategy = moveStrategy;
		this.storage = this.indexDescente = 0;
		this.isFull = this.InSynchro = false;
		this.nbDescente = rand.nextInt(maxY - minY - 1) / 10;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getStorage() {
		return storage;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

	public boolean isFull() {
		return isFull;
	}

	public void setIsFull(boolean isFull) {
		this.isFull = isFull;
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

	public boolean isInSynchro() {
		return InSynchro;
	}

	public void stockerDonnee() {
		Random rand = new Random();
		int nbdata = rand.nextInt(3) + 1;
		this.storage += nbdata;
		this.isFull = this.storage >= this.capacity;
		if (isFull) {
			this.setMoveStrategy(new MoveStrategySurface());
		}
	}

	@Override
	public void receive(AbstractEvent event) {
		// Définition de la présence (ou non) de la source dans le range de
		// synchronisation de la balise
		Satellite satellite = (Satellite) event.getSource();

		if (event.toString().equals("Disponible pour synchronisation") && satellite.isInRangeOf(this)
				&& !this.InSynchro) {
			// Reçoit "Disponible pour synchronisation" de tous les satellites uniquement si
			// pleine.
			this.getEventHandler().registerListener(SynchronisationInProgress.class, satellite);
			SynchronisationInProgress synchroInProgressEvent = new SynchronisationInProgress(this);
			this.getEventHandler().send(synchroInProgressEvent);
			this.InSynchro = true;
		} else if (event.toString().equals("Synchronisation terminée") && this.InSynchro) {
			// Reçoit "Synchronisation terminée" du satellite auquel la balise se
			// synchronise

			// Reset des données
			this.InSynchro = false;
			this.setStorage(0);
			this.setIsFull(false);
			this.setIndexDescente(this.getNbDescente());

			Space.removeBeaconFromFullBeacons(this);
		}
	}
}
