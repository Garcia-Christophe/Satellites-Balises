package back.movement;

import back.mobileElement.Beacon;
import back.mobileElement.MobileElement;

public class MoveStrategySurface extends MoveStrategy {

	@Override
	public void move(MobileElement elem, int maxX, int minX, int maxY, int minY) {
		Beacon balise = (Beacon) elem;
		int direction = balise.getIndexDescente() != 0 ? 10 : -10;

		if ((elem.getTopRight().y > minY && direction < 0) || (elem.getBottomLeft().y < maxY && direction > 0)) {
			elem.getBottomLeft().setLocation(elem.getBottomLeft().x, elem.getBottomLeft().y + direction);
			elem.getTopRight().setLocation(elem.getTopRight().x, elem.getTopRight().y + direction);
		}

		if (balise.getIndexDescente() != 0 && direction > 0) {
			balise.setIndexDescente(balise.getIndexDescente() - 1);
			if (balise.getIndexDescente() == 0) {
				balise.setMoveStrategy(balise.getDefaultStrategy());
			}
		}
	}

}
