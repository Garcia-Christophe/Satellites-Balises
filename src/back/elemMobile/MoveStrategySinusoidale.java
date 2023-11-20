package back.elemMobile;

import java.util.Random;

public class MoveStrategySinusoidale extends MoveStrategy {

	@Override
	public void move(ElementMobile elem, int maxX, int minX, int maxY, int minY) {
		Random rand = new Random();
		int direction = rand.nextInt(4);
		switch (direction) {
		case (0):
			// vers le bas
			if (elem.getHautDroit().getY() < maxY) {
				elem.getBasGauche().setLocation(elem.getBasGauche().getX(), elem.getBasGauche().getY() + 10);
				elem.getHautDroit().setLocation(elem.getHautDroit().getX(), elem.getHautDroit().getY() + 10);
			}
			break;
		case (1):
			// vers le haut
			if (elem.getBasGauche().getY() > minY) {

				elem.getBasGauche().setLocation(elem.getBasGauche().getX(), elem.getBasGauche().getY() - 10);
				elem.getHautDroit().setLocation(elem.getHautDroit().getX(), elem.getHautDroit().getY() - 10);
			}
			break;
		case (2):
			// vers la droite
			if (elem.getHautDroit().getX() < maxX) {
				elem.getBasGauche().setLocation(elem.getBasGauche().getX() + 10, elem.getBasGauche().getY());
				elem.getHautDroit().setLocation(elem.getHautDroit().getX() + 10, elem.getHautDroit().getY());
			}
			break;
		case (3):
			// vers la gauche
			if (elem.getBasGauche().getX() > minX) {
				elem.getBasGauche().setLocation(elem.getBasGauche().getX() - 10, elem.getBasGauche().getY());
				elem.getHautDroit().setLocation(elem.getHautDroit().getX() - 10, elem.getHautDroit().getY());
			}
			break;
		}
	}
}
