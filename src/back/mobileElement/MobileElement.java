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

	public EventHandler getEventHandler() {
		return eventHandler;
	}

	public Point getTopRight() {
		return topRight;
	}

	public void setTopRight(Point topRight) {
		this.topRight = topRight;
	}

	public Point getBottomLeft() {
		return bottomLeft;
	}

	public void setBottomLeft(Point bottomLeft) {
		this.bottomLeft = bottomLeft;
	}

	public MoveStrategy getMoveStrategy() {
		return moveStrategy;
	}

	public void setMoveStrategy(MoveStrategy moveStrategy) {
		this.moveStrategy = moveStrategy;
	}

}
