package back.mobileElement;

import java.awt.Point;

import back.EventHandler;
import back.event.AbstractEvent;
import back.movement.MoveStrategy;

/**
 * Classe abstraite définissant un élément mobile.
 */
public abstract class MobileElement {

	/**
	 * Coordonnées en-haut à droite et en-bas à gauche de l'élément mobile.
	 */
	protected Point topRight, bottomLeft;

	/**
	 * Stratégie actuelle de déplacement de l'élément mobile.
	 */
	protected MoveStrategy moveStrategy;

	/**
	 * Gestionnaire d'évènements de l'élément mobile.
	 */
	protected EventHandler eventHandler;

	/**
	 * Crée un élément mobile avec une stratégie fournie.
	 * 
	 * @param moveStrategy - Stratégie par défaut de l'élément mobile
	 */
	public MobileElement(MoveStrategy moveStrategy) {
		super();
		this.topRight = new Point(0, 0);
		this.bottomLeft = new Point(0, 0);
		this.moveStrategy = moveStrategy;
		this.eventHandler = new EventHandler();
	}

	/**
	 * Méthode abstracte définissant le comportement à adopter lors d'une réception
	 * d'évènement.
	 * 
	 * @param event - Évènement reçu
	 */
	public abstract void receive(AbstractEvent event);

	/**
	 * Récupère le gestionnaire d'évènements.
	 * 
	 * @return le gestionnaire d'évènements
	 */
	public EventHandler getEventHandler() {
		return eventHandler;
	}

	/**
	 * Récupère les coordonnées haut-droite de l'élément.
	 * 
	 * @return les coordonnées haut-droite de l'élément
	 */
	public Point getTopRight() {
		return topRight;
	}

	/**
	 * Définit les coordonnées haut-droite de l'élément.
	 * 
	 * @param topRight - Les nouvelles coordonnées haut-droite de l'élément
	 */
	public void setTopRight(Point topRight) {
		this.topRight = topRight;
	}

	/**
	 * Récupère les coordonnées bas-gauche de l'élément.
	 * 
	 * @return les coordonnées bas-gauche de l'élément
	 */
	public Point getBottomLeft() {
		return bottomLeft;
	}

	/**
	 * Définit les coordonnées bas-gauche de l'élément.
	 * 
	 * @param bottomLeft - Les nouvelles coordonnées bas-gauche de l'élément
	 */
	public void setBottomLeft(Point bottomLeft) {
		this.bottomLeft = bottomLeft;
	}

	/**
	 * Récupère la stratégie actuelle de déplacement.
	 * 
	 * @return la stratégie actuelle de déplacement
	 */
	public MoveStrategy getMoveStrategy() {
		return moveStrategy;
	}

	/**
	 * Définit la stratégie actuelle de déplacement.
	 * 
	 * @param moveStrategy - la nouvelle stratégie actuelle de déplacement.
	 */
	public void setMoveStrategy(MoveStrategy moveStrategy) {
		this.moveStrategy = moveStrategy;
	}

}
