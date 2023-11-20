package back.elemMobile;

import java.util.Random;

public class MoveStrategySatellite extends MoveStrategy {

	@Override
	public void move(ElementMobile elem, int maxX, int minX, int maxY, int minY) {
		elem.getBasGauche().setX(elem.getBasGauche().getX() + 10);
		elem.getHautDroit().setX(elem.getHautDroit().getX() + 10);
		if (elem.getHautDroit().getX() > maxX) {
			int ecart = elem.getHautDroit().getX() - elem.getBasGauche().getX();
			elem.getBasGauche().setX(minX);
			elem.getHautDroit().setX(minX + ecart);
		}
	}

}
