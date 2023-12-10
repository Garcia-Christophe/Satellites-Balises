package back.movement;

import back.mobileElement.Beacon;
import back.mobileElement.MobileElement;

/**
 * Stratégie de déplacement spécifique à l'aller-retour de la balise à la
 * surface.
 */
public class MoveStrategySurface extends MoveStrategy {

	@Override
	public void move(MobileElement elem, int maxX, int minX, int maxY, int minY) {
		Beacon balise = (Beacon) elem;
		// Définition aléatoire de la direction (vers le haut ou vers le bas)
		int moveTo = balise.getCurrentDepth() != 0 ? 10 : -10;

		// Si l'élément doit et peut aller en-haut OU que l'élément doit et peut aller
		// en-bas, alors le déplace
		if ((elem.getTopRight().y > minY && moveTo < 0) || (elem.getBottomLeft().y < maxY && moveTo > 0)) {
			elem.getBottomLeft().setLocation(elem.getBottomLeft().x, elem.getBottomLeft().y + moveTo);
			elem.getTopRight().setLocation(elem.getTopRight().x, elem.getTopRight().y + moveTo);
		}

		// Si la profondeur actuelle est différente de la profondeur par défaut de la
		// balise, alors la met à jour
		if (balise.getCurrentDepth() != 0 && moveTo > 0) {
			balise.setCurrentDepth(balise.getCurrentDepth() - 1);

			// Si après mise à jour, la balise a retrouvé sa profondeur par défaut, alors
			// remet la stratégie par défaut
			if (balise.getCurrentDepth() == 0) {
				balise.setMoveStrategy(balise.getDefaultStrategy());
			}
		}
	}

}
