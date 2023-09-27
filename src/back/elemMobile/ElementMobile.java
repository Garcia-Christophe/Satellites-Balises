package back.elemMobile;

public abstract class ElementMobile {
	protected Coordonnee coordonnee;
	
	protected Direction direction;
	
	public ElementMobile(Coordonnee coordonnee, Direction direction) {
		super();
		this.coordonnee = coordonnee;
		this.direction = direction;
	}


	public Coordonnee getCoordonnee() {
		return coordonnee;
	}


	public void setCoordonnee(Coordonnee coordonnee) {
		this.coordonnee = coordonnee;
	}


	public Direction getDirection() {
		return direction;
	}


	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public abstract void synchronisation();


	public enum Direction{HORIZONTAL, VERTICAL}
}
