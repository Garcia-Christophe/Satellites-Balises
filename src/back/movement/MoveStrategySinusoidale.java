package back.movement;

import java.util.Random;

import back.mobileElement.MobileElement;

public class MoveStrategySinusoidale extends MoveStrategy {

	@Override
	public void move(MobileElement elem, int maxX, int minX, int maxY, int minY) {
		Random rand = new Random();
		int direction = rand.nextInt(4);
		switch (direction) {
		case (0):
			// vers le bas
			if (elem.getBottomLeft().getY() < maxY) {
				elem.getBottomLeft().setLocation(elem.getBottomLeft().getX(), elem.getBottomLeft().getY() + 10);
				elem.getTopRight().setLocation(elem.getTopRight().getX(), elem.getTopRight().getY() + 10);
			}
			break;
		case (1):
			// vers le haut
			if (elem.getTopRight().getY() > minY) {

				elem.getBottomLeft().setLocation(elem.getBottomLeft().getX(), elem.getBottomLeft().getY() - 10);
				elem.getTopRight().setLocation(elem.getTopRight().getX(), elem.getTopRight().getY() - 10);
			}
			break;
		case (2):
			// vers la droite
			if (elem.getTopRight().getX() < maxX) {
				elem.getBottomLeft().setLocation(elem.getBottomLeft().getX() + 10, elem.getBottomLeft().getY());
				elem.getTopRight().setLocation(elem.getTopRight().getX() + 10, elem.getTopRight().getY());
			}
			break;
		case (3):
			// vers la gauche
			if (elem.getBottomLeft().getX() > minX) {
				elem.getBottomLeft().setLocation(elem.getBottomLeft().getX() - 10, elem.getBottomLeft().getY());
				elem.getTopRight().setLocation(elem.getTopRight().getX() - 10, elem.getTopRight().getY());
			}
			break;
		}
	}
}
