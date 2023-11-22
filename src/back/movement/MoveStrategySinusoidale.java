package back.movement;

import java.util.Random;

import back.mobileElement.MobileElement;

/**
 * Stratégie de déplacement sinusoïdale.
 */
public class MoveStrategySinusoidale extends MoveStrategy {

	@Override
	public void move(MobileElement elem, int maxX, int minX, int maxY, int minY) {
		// Définition de la direction de déplacement (gauche, haut, bas ou droite)
		int direction = new Random().nextInt(4);
		int moveTo = 10;

		switch (direction) {
		case (0):
			// Déplacement vers le bas
			if (elem.getBottomLeft().getY() < maxY) {
				elem.getBottomLeft().setLocation(elem.getBottomLeft().getX(), elem.getBottomLeft().getY() + moveTo);
				elem.getTopRight().setLocation(elem.getTopRight().getX(), elem.getTopRight().getY() + moveTo);
			}
			break;
		case (1):
			// Déplacement vers le haut
			if (elem.getTopRight().getY() > minY) {

				elem.getBottomLeft().setLocation(elem.getBottomLeft().getX(), elem.getBottomLeft().getY() - moveTo);
				elem.getTopRight().setLocation(elem.getTopRight().getX(), elem.getTopRight().getY() - moveTo);
			}
			break;
		case (2):
			// Déplacement vers la droite
			if (elem.getTopRight().getX() < maxX) {
				elem.getBottomLeft().setLocation(elem.getBottomLeft().getX() + moveTo, elem.getBottomLeft().getY());
				elem.getTopRight().setLocation(elem.getTopRight().getX() + moveTo, elem.getTopRight().getY());
			}
			break;
		case (3):
			// Déplacement vers la gauche
			if (elem.getBottomLeft().getX() > minX) {
				elem.getBottomLeft().setLocation(elem.getBottomLeft().getX() - moveTo, elem.getBottomLeft().getY());
				elem.getTopRight().setLocation(elem.getTopRight().getX() - moveTo, elem.getTopRight().getY());
			}
			break;
		}
	}
}
