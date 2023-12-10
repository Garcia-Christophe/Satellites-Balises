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

	public int getMaxX() {
		return maxX;
	}

	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	public int getMinX() {
		return minX;
	}

	public void setMinX(int minX) {
		this.minX = minX;
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

	public int getMinY() {
		return minY;
	}

	public void setMinY(int minY) {
		this.minY = minY;
	}

	public static Set<Beacon> getfullBeacons() {
		return Space.fullBeacons;
	}

}
