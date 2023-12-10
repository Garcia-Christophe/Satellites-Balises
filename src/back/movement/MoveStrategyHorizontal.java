package back.movement;

import java.util.Random;

import back.mobileElement.MobileElement;

/**
 * Stratégie de déplacement horizontal.
 */
public class MoveStrategyHorizontal extends MoveStrategy {

	@Override
	public void move(MobileElement elem, int maxX, int minX, int maxY, int minY) {
		// Définition aléatoire de la direction (droite ou gauche)
		int moveTo = new Random().nextInt(2) == 0 ? 10 : -10;

		// Si l'élément doit et peut aller à droite OU que l'élément doit et peut aller
		// à gauche, alors le déplace
		if ((moveTo > 0 && elem.getTopRight().getX() < maxX) || (moveTo < 0 && elem.getBottomLeft().getX() > minX)) {
			elem.getBottomLeft().setLocation(elem.getBottomLeft().getX() + moveTo, elem.getBottomLeft().getY());
			elem.getTopRight().setLocation(elem.getTopRight().getX() + moveTo, elem.getTopRight().getY());
		}
	}

}
