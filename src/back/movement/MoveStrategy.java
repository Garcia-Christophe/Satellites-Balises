package back.movement;

import back.mobileElement.MobileElement;

public abstract class MoveStrategy {
	
	public abstract void move(MobileElement elem, int maxX, int minX, int maxY, int minY);

}
