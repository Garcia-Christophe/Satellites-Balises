package back.mobileElement;

import java.util.Random;

import back.event.AbstractEvent;
import back.event.SynchronisationInProgress;
import back.movement.MoveStrategy;
import back.movement.MoveStrategySurface;
import back.staticElement.Space;

/**
 * Classe représentant une balise. Une balise est un élément mobile qui récolte
 * des données au fond de l'océan.
 */
public class Beacon extends MobileElement {

	/**
	 * Capacité de stockage des données à récolter.
	 */
	private int capacity;

	/**
	 * Stockage actuel des données.
	 */
	private int storage;

	/**
	 * Profondeur par défaut de la balise dans l'océan.
	 */
	private int defaultDepth;

	/**
	 * Profondeur actuelle redéfinit à chaque aller-retour à la surface.
	 */
	private int currentDepth;

	/**
	 * True si la balise a atteint sa capacité de stockage de récolte de données,
	 * false sinon.
	 */
	private boolean isFull;

	/**
	 * True si la balise est en synchronisation, false sinon.
	 */
	private boolean InSynchro;

	/**
	 * Stratégie par défaut de déplacement de la balise.
	 */
	private MoveStrategy defaultStrategy;

	/**
	 * Zone de synchronisation autour de la balise.
	 */
	private final int RANGE_SYNCHRO = 20;

	/**
	 * Crée une balise.
	 * 
	 * @param moveStrategy - Stratégie de déplacement par défaut
	 * @param capacity     - Capacité de stockage des données à récolter
	 * @param maxY         - Profondeur maximale à ne pas franchir
	 * @param minY         - Profondeur minimale à ne pas franchir
	 */
	public Beacon(MoveStrategy moveStrategy, int capacity, int maxY, int minY) {
		super(moveStrategy);
		this.capacity = capacity;
		this.defaultStrategy = moveStrategy;
		this.storage = this.currentDepth = 0;
	}

	/**
	 * Stockage d'une donnée.
	 */
	public void storeData() {
		// Génération aléatoire d'une donnée : 1 | 2 | 3
		int nbData = new Random().nextInt(3) + 1;

		// Stockage
		this.storage += nbData;
		this.isFull = this.storage >= this.capacity;
		if (isFull) {
			// Si la balise est pleine, change de stratégie pour faire un aller-retour à la
			// surface
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
			// la balise est pleine.
			this.eventHandler.registerListener(SynchronisationInProgress.class, satellite);
			SynchronisationInProgress synchroInProgressEvent = new SynchronisationInProgress(this);
			this.eventHandler.send(synchroInProgressEvent);
			this.InSynchro = true;
		} else if (event.toString().equals("Synchronisation terminée") && this.InSynchro) {
			// Reçoit "Synchronisation terminée" du satellite auquel la balise se
			// synchronise

			// Remise à zéro des données
			this.InSynchro = false;
			this.setStorage(0);
			this.setIsFull(false);
			this.setCurrentDepth(this.defaultDepth);

			// Suppression de la balise de la liste de balises pleines
			Space.fullBeacons.remove(this);
		}
	}

	/**
	 * Récupère la capacité de la balise.
	 * 
	 * @return la capacité de la balise
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Définit la capacité de la balise.
	 * 
	 * @param capacity - La nouvelle capacité de la balise
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * Récupère la quantitié stockable par la balise.
	 * 
	 * @return la quantité stockable par la balise
	 */
	public int getStorage() {
		return storage;
	}

	/**
	 * Définit la quantité stockable par la balise.
	 * 
	 * @param storage - La nouvelle quantité stockable par la balise
	 */
	public void setStorage(int storage) {
		this.storage = storage;
	}

	/**
	 * Récupère l'état de stockage de la balise.
	 * 
	 * @return true si la balise est pleine de données, false sinon
	 */
	public boolean isFull() {
		return isFull;
	}

	/**
	 * Définit l'état de stockage de la balise.
	 * 
	 * @param isFull - True si la balise est pleine de données, false sinon
	 */
	public void setIsFull(boolean isFull) {
		this.isFull = isFull;
	}

	/**
	 * Définit la profondeur par défaut de la balise.
	 * 
	 * @param defaultDepth - la profondeur par défaut de la balise
	 */
	public void setDefaultDepth(int defaultDepth) {
		this.defaultDepth = defaultDepth;
	}

	/**
	 * Récupère la profondeur actuelle de la balise.
	 * 
	 * @return la profondeur actuelle de la balise
	 */
	public int getCurrentDepth() {
		return currentDepth;
	}

	/**
	 * Définit la profondeur actuelle de la balise.
	 * 
	 * @param indexDescente - La nouvelle profondeur actuelle de la balise
	 */
	public void setCurrentDepth(int indexDescente) {
		this.currentDepth = indexDescente;
	}

	/**
	 * Récupère la stratégie de déplacement par défaut de la balise.
	 * 
	 * @return la stratégie de déplacement par défaut de la balise
	 */
	public MoveStrategy getDefaultStrategy() {
		return defaultStrategy;
	}

	/**
	 * Récupère la zone de synchronisation de la balise.
	 * 
	 * @return la zone de synchronisation de la balise
	 */
	public int getRangeSynchro() {
		return this.RANGE_SYNCHRO;
	}

	/**
	 * Récupère l'état de la balise.
	 * 
	 * @return true si la balise est en cours de synchronisation, false sinon
	 */
	public boolean isInSynchro() {
		return InSynchro;
	}
}
