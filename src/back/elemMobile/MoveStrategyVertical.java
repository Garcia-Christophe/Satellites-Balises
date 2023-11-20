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
				elem.getBasGauche().setLocation(elem.getBasGauche().getX(), elem.getBasGauche().getY() + 10);
				elem.getHautDroit().setLocation(elem.getHautDroit().getX(), elem.getHautDroit().getY() + 10);
			}
		} else {
			// vers le haut
			if (elem.getBasGauche().getY() > minY) {
				elem.getBasGauche().setLocation(elem.getBasGauche().getX(), elem.getBasGauche().getY() - 10);
				elem.getHautDroit().setLocation(elem.getHautDroit().getX(), elem.getHautDroit().getY() - 10);
			}
		}
	}

}
