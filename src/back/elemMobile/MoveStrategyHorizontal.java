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
				elem.getBasGauche().setLocation(elem.getBasGauche().getX() + 10, elem.getBasGauche().getY());
				elem.getHautDroit().setLocation(elem.getHautDroit().getX() + 10, elem.getHautDroit().getY());
			}
		} else {
			// vers la gauche
			if (elem.getBasGauche().getX() > minX) {
				elem.getBasGauche().setLocation(elem.getBasGauche().getX() - 10, elem.getBasGauche().getY());
				elem.getHautDroit().setLocation(elem.getHautDroit().getX() - 10, elem.getHautDroit().getY());
			}
		}
	}

}
