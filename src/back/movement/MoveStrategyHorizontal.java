package back.movement;

import java.util.Random;

import back.mobileElement.MobileElement;

public class MoveStrategyHorizontal extends MoveStrategy {

	@Override
	public void move(MobileElement elem, int maxX, int minX, int maxY, int minY) {
		Random rand = new Random();
		double direction = rand.nextInt(2);
		if (direction == 0) {
			// vers la droite
			if (elem.getTopRight().getX() < maxX) {
				elem.getBottomLeft().setLocation(elem.getBottomLeft().getX() + 10, elem.getBottomLeft().getY());
				elem.getTopRight().setLocation(elem.getTopRight().getX() + 10, elem.getTopRight().getY());
			}
		} else {
			// vers la gauche
			if (elem.getBottomLeft().getX() > minX) {
				elem.getBottomLeft().setLocation(elem.getBottomLeft().getX() - 10, elem.getBottomLeft().getY());
				elem.getTopRight().setLocation(elem.getTopRight().getX() - 10, elem.getTopRight().getY());
			}
		}
	}

}
