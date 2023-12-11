package back.staticElement;

import java.util.HashSet;
import java.util.Set;

import back.mobileElement.Beacon;

/**
 * Classe abstraite représentant un espace dans lequel des éléments mobiles se
 * déplacent.
 */
public abstract class Space {

	/**
	 * Coordonnées minimales et maximales des axes X et Y de l'espace.
	 */
	private int maxX, minX, maxY, minY;

	/**
	 * Liste unique des balises pleines.
	 */
	public static Set<Beacon> fullBeacons = new HashSet<>();

	/**
	 * Crée un espace.
	 * 
	 * @param maxX - Coordonnée maximale sur l'axe X
	 * @param minX - Coordonnée minimale sur l'axe X
	 * @param maxY - Coordonnée maximale sur l'axe Y
	 * @param minY - Coordonnée minimale sur l'axe Y
	 */
	public Space(int maxX, int minX, int maxY, int minY) {
		this.maxX = maxX;
		this.minX = minX;
		this.maxY = maxY;
		this.minY = minY;
	}

	/**
	 * Méthode abstraite définissant l'étape de simulation.
	 */
	public abstract void move();

	/**
	 * Récupère la coordonnée maximale sur l'axe X.
	 * 
	 * @return - la coordonnée maximale sur l'axe X
	 */
	public int getMaxX() {
		return maxX;
	}

	/**
	 * Définit la coordonnée maximale sur l'axe X.
	 * 
	 * @param maxX - La coordonnée maximale sur l'axe X
	 */
	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	/**
	 * Récupère la coordonnée minimale sur l'axe X.
	 * 
	 * @return - la coordonnée minimale sur l'axe X
	 */
	public int getMinX() {
		return minX;
	}

	/**
	 * Définit la coordonnée minimale sur l'axe X.
	 * 
	 * @param minX - La coordonnée minimale sur l'axe X
	 */
	public void setMinX(int minX) {
		this.minX = minX;
	}

	/**
	 * Récupère la coordonnée maximale sur l'axe Y.
	 * 
	 * @return - la coordonnée maximale sur l'axe Y
	 */
	public int getMaxY() {
		return maxY;
	}

	/**
	 * Définit la coordonnée maximale sur l'axe Y.
	 * 
	 * @param maxY - La coordonnée maximale sur l'axe Y
	 */
	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

	/**
	 * Récupère la coordonnée minimale sur l'axe Y.
	 * 
	 * @return - la coordonnée minimale sur l'axe Y
	 */
	public int getMinY() {
		return minY;
	}

	/**
	 * Définit la coordonnée minimale sur l'axe Y.
	 * 
	 * @param minY - La coordonnée minimale sur l'axe Y
	 */
	public void setMinY(int minY) {
		this.minY = minY;
	}

	/**
	 * Récupère la liste de balises prêtes à la synchronisation.
	 * 
	 * @return - la liste des balises prêtes à la synchronisation
	 */
	public static Set<Beacon> getfullBeacons() {
		return Space.fullBeacons;
	}

}
