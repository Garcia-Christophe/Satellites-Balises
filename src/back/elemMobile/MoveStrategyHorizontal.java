package back.elemMobile;

import java.util.Random;

public class MoveStrategyHorizontal extends MoveStrategy {

	@Override
	public void move(ElementMobile elem, int maxX, int minX, int maxY, int minY) {
		Random rand = new Random();
		double direction = rand.nextInt(2);
		if (direction == 0) {
			// vers la droite
			if (elem.getHautDroit().getX() < maxX) {
				elem.getBasGauche().setX(elem.getBasGauche().getX() + 10);
				elem.getHautDroit().setX(elem.getHautDroit().getX() + 10);
			}
		} else {
			// vers la gauche
			if (elem.getBasGauche().getX() > minX) {
				elem.getBasGauche().setX(elem.getBasGauche().getX() - 10);
				elem.getHautDroit().setX(elem.getHautDroit().getX() - 10);
			}
		}
	}

}
