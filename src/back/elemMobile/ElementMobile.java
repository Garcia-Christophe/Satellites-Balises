package back.elemMobile;

import java.awt.Point;

import back.EventHandler;
import back.event.AbstractEvent;

public abstract class ElementMobile {

	protected final int tempsAttenteSynchro = 5;

	protected Point hautDroit, basGauche;

	protected MoveStrategy moveStrategy;

	private EventHandler eventHandler;

	public ElementMobile(MoveStrategy moveStrategy) {
		super();
		this.hautDroit = new Point(0, 0);
		this.basGauche = new Point(0, 0);
		this.moveStrategy = moveStrategy;
		this.eventHandler = new EventHandler();
	}

	public EventHandler getEventHandler() {
		return eventHandler;
	}

	public Point getHautDroit() {
		return hautDroit;
	}

	public void setHautDroit(Point hautDroit) {
		this.hautDroit = hautDroit;
	}

	public Point getBasGauche() {
		return basGauche;
	}

	public void setBasGauche(Point basGauche) {
		this.basGauche = basGauche;
	}

	public MoveStrategy getMoveStrategy() {
		return moveStrategy;
	}

	public void setMoveStrategy(MoveStrategy moveStrategy) {
		this.moveStrategy = moveStrategy;
	}

	public abstract void receive(AbstractEvent event);
}
