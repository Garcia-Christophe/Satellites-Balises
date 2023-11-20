package back.elemMobile;

import java.util.Random;

public class MoveStrategySatellite extends MoveStrategy {

	@Override
	public void move(ElementMobile elem, int maxX, int minX, int maxY, int minY) {
		int altitude = (int) elem.getBasGauche().getY();
		elem.getBasGauche().setLocation(elem.getBasGauche().getX() + (altitude / 20), elem.getBasGauche().getY());
		elem.getHautDroit().setLocation(elem.getHautDroit().getX() + (altitude / 20), elem.getBasGauche().getY());
		if (elem.getHautDroit().getX() > maxX) {
			int ecart = (int) (elem.getHautDroit().getX() - elem.getBasGauche().getX());
			elem.getBasGauche().setLocation(minX, elem.getBasGauche().getY());
			elem.getHautDroit().setLocation(minX + ecart, elem.getHautDroit().getY());
		}
	}

}
