package back.elemMobile;

import java.awt.Point;

public abstract class ElementMobile {
	
	protected final int tempsAttenteSynchro = 5;
	
	protected Point hautDroit, basGauche;
	
	protected MoveStrategy moveStrategy;
	
	public ElementMobile(MoveStrategy moveStrategy) {
		super();
		this.hautDroit = new Point(0, 0);
		this.basGauche = new Point(0, 0);
		this.moveStrategy = moveStrategy;
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


	public abstract void synchronisation();
}
