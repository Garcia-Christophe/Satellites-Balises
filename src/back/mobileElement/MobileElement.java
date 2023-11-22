package back.mobileElement;

import java.awt.Point;

import back.EventHandler;
import back.event.AbstractEvent;
import back.movement.MoveStrategy;

public abstract class MobileElement {

	protected final int waitingTimeSynchro = 5;

	protected Point topRight, bottomLeft;

	protected MoveStrategy moveStrategy;

	private EventHandler eventHandler;

	public MobileElement(MoveStrategy moveStrategy) {
		super();
		this.topRight = new Point(0, 0);
		this.bottomLeft = new Point(0, 0);
		this.moveStrategy = moveStrategy;
		this.eventHandler = new EventHandler();
	}

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

	public abstract void receive(AbstractEvent event);
}
