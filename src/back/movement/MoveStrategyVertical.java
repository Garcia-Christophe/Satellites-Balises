package back.movement;

import java.util.Random;

import back.mobileElement.MobileElement;

public class MoveStrategyVertical extends MoveStrategy {

	@Override
	public void move(MobileElement elem, int maxX, int minX, int maxY, int minY) {
		Random rand = new Random();
		double direction = rand.nextInt(2);
		if (direction == 0) {
			// vers le bas
			if (elem.getBottomLeft().getY() < maxY) {
				elem.getBottomLeft().setLocation(elem.getBottomLeft().getX(), elem.getBottomLeft().getY() + 10);
				elem.getTopRight().setLocation(elem.getTopRight().getX(), elem.getTopRight().getY() + 10);
			}
		} else {
			// vers le haut
			if (elem.getTopRight().getY() > minY) {
				elem.getBottomLeft().setLocation(elem.getBottomLeft().getX(), elem.getBottomLeft().getY() - 10);
				elem.getTopRight().setLocation(elem.getTopRight().getX(), elem.getTopRight().getY() - 10);
			}
		}
	}

}
