package back.movement;

import back.mobileElement.MobileElement;

/**
 * Stratégie de déplacement spécifique au satellite.
 */
public class MoveStrategySatellite extends MoveStrategy {

	@Override
	public void move(MobileElement elem, int maxX, int minX, int maxY, int minY) {
		// Altitude du satellite
		// Elle permet de définir la vitesse de déplacement sur l'axe X
		// Plus le satellite est bas, plus il va vite. Et inversement
		int altitude = (int) elem.getBottomLeft().getY();

		// Déplacement du satellite sur la droite proportionnellement à son altitude
		elem.getBottomLeft().setLocation(elem.getBottomLeft().getX() + (altitude / 20), elem.getBottomLeft().getY());
		elem.getTopRight().setLocation(elem.getTopRight().getX() + (altitude / 20), elem.getBottomLeft().getY());

		// Si le satellite atteint la limite de la fenêtre (à droite), alors il est
		// redessiné au début de la fenêtre (à gauche)
		if (elem.getTopRight().getX() > maxX) {
			int ecart = (int) (elem.getTopRight().getX() - elem.getBottomLeft().getX());
			elem.getBottomLeft().setLocation(minX, elem.getBottomLeft().getY());
			elem.getTopRight().setLocation(minX + ecart, elem.getTopRight().getY());
		}
	}

}
