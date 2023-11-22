package back.movement;

import back.mobileElement.MobileElement;

/**
 * Stratégie de déplacements des éléments mobiles.
 */
public abstract class MoveStrategy {

	/**
	 * Méthode définissant le déplacement d'un élément mobile.
	 * 
	 * @param elem - L'élément mobile à déplacer
	 * @param maxX - Coordonée maximale sur l'axe X
	 * @param minX - Coordonée minimale sur l'axe X
	 * @param maxY - Coordonée maximale sur l'axe Y
	 * @param minY - Coordonée minimale sur l'axe Y
	 */
	public abstract void move(MobileElement elem, int maxX, int minX, int maxY, int minY);

}
