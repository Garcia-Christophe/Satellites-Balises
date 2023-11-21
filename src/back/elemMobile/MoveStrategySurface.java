package back.elemMobile;

import java.util.Random;

public class MoveStrategySurface extends MoveStrategy {

	@Override
	public void move(ElementMobile elem, int maxX, int minX, int maxY, int minY) {
		Balise balise = (Balise) elem;
		int direction = balise.getIndexDescente() != 0 ? 10 : -10;
		
		if (elem.getHautDroit().getY() > minY && elem.getBasGauche().getY() < maxY) {
			elem.getBasGauche().setLocation(elem.getBasGauche().getX(), elem.getBasGauche().getY() + direction);
			elem.getHautDroit().setLocation(elem.getHautDroit().getX(), elem.getHautDroit().getY() + direction);
		}
		if (balise.getIndexDescente() != 0 && direction > 0) {
			balise.setIndexDescente(balise.getIndexDescente() - 1);
			if (balise.getIndexDescente() == 0) {
				balise.setMoveStrategy(balise.getDefaultStrategy());
			}
		}
	}

}
