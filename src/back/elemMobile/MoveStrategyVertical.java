package back.elemMobile;

import java.util.Random;

public class MoveStrategyVertical extends MoveStrategy {

	@Override
	public void move(ElementMobile elem, int maxX, int minX, int maxY, int minY) {
		Random rand = new Random();
		double direction = rand.nextInt(2);
		if (direction == 0) {
			// vers le bas
			if (elem.getHautDroit().getY() < maxY) {
				elem.getBasGauche().setY(elem.getBasGauche().getY() + 10);
				elem.getHautDroit().setY(elem.getHautDroit().getY() + 10);
			}
		} else {
			// vers le haut
			if (elem.getBasGauche().getY() > minY) {
				elem.getBasGauche().setY(elem.getBasGauche().getY() - 10);
				elem.getHautDroit().setY(elem.getHautDroit().getY() - 10);
			}
		}
	}

}
