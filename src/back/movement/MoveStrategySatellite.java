package back.movement;

import java.util.Random;

import back.mobileElement.MobileElement;

public class MoveStrategySatellite extends MoveStrategy {

	@Override
	public void move(MobileElement elem, int maxX, int minX, int maxY, int minY) {
		int altitude = (int) elem.getBottomLeft().getY();
		elem.getBottomLeft().setLocation(elem.getBottomLeft().getX() + (altitude / 20), elem.getBottomLeft().getY());
		elem.getTopRight().setLocation(elem.getTopRight().getX() + (altitude / 20), elem.getBottomLeft().getY());
		if (elem.getTopRight().getX() > maxX) {
			int ecart = (int) (elem.getTopRight().getX() - elem.getBottomLeft().getX());
			elem.getBottomLeft().setLocation(minX, elem.getBottomLeft().getY());
			elem.getTopRight().setLocation(minX + ecart, elem.getTopRight().getY());
		}
	}

}
