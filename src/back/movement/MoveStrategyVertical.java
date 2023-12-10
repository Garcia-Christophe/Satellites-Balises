package back.movement;

import java.util.Random;

import back.mobileElement.MobileElement;

/**
 * Stratégie de déplacement vertical.
 */
public class MoveStrategyVertical extends MoveStrategy {

	@Override
	public void move(MobileElement elem, int maxX, int minX, int maxY, int minY) {
		// Définition aléatoire de la direction (vers le haut ou vers le bas)
		int moveTo = new Random().nextInt(2) == 0 ? 10 : -10;

		// Si l'élément doit et peut aller à droite OU que l'élément doit et peut aller
		// à gauche, alors le déplace
		if ((moveTo > 0 && elem.getBottomLeft().getY() < maxY) || (moveTo < 0 && elem.getTopRight().getY() > minY)) {
			elem.getBottomLeft().setLocation(elem.getBottomLeft().getX(), elem.getBottomLeft().getY() + moveTo);
			elem.getTopRight().setLocation(elem.getTopRight().getX(), elem.getTopRight().getY() + moveTo);
		}
	}

}
