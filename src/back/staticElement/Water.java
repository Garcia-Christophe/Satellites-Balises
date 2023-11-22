package back.staticElement;

import java.util.List;

import back.mobileElement.Beacon;

/**
 * L'océan est l'espace dans lequel se déplacent des balises.
 */
public class Water extends Space {

	/**
	 * Liste des balises.
	 */
	private List<Beacon> beacons;

	/**
	 * Crée l'espace d'eau.
	 * 
	 * @param maxX    - Coordonnée maximale sur l'axe X
	 * @param minX    - Coordonnée minimale sur l'axe X
	 * @param maxY    - Coordonnée maximale sur l'axe Y
	 * @param minY    - Coordonnée minimale sur l'axe Y
	 * @param beacons - Liste des balises qui s'y déplaceront
	 */
	public Water(int maxX, int minX, int maxY, int minY, List<Beacon> beacons) {
		super(maxX, minX, maxY, minY);
		this.beacons = beacons;
	}

	@Override
	public void move() {
		// Exécute une étape de simulation pour chaque balise
		for (Beacon beacon : this.beacons) {
			if (!beacon.isFull()) {
				// Si la balise n'est pas encore pleine, alors fait déplacer la balise selon sa
				// stratégie, et récolte des données
				beacon.getMoveStrategy().move(beacon, getMaxX(), getMinX(), getMaxY(), getMinY());
				beacon.storeData();
			} else if (beacon.getTopRight().y > this.getMinY() + 9) {
				// Sinon si la balise n'est pas encore en surface, alors fait déplacer la balise
				beacon.getMoveStrategy().move(beacon, getMaxX(), getMinX(), getMaxY(), getMinY());
			} else {
				// Sinon, ajoute la balise à la liste des balises pleines
				Space.fullBeacons.add(beacon);
			}
		}
	}

	public List<Beacon> getBeacons() {
		return beacons;
	}

	public void setBeacons(List<Beacon> beacons) {
		this.beacons = beacons;
	}

}
