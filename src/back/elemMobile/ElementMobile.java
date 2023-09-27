package back.elemMobile;

public abstract class ElementMobile {
	
	protected final int tempsAttenteSynchro = 5;
	
	protected Coordonnee hautDroit, basGauche;
	
	protected Direction direction;
	
	public ElementMobile(Coordonnee hautDroit, Coordonnee basGauche, Direction direction) {
		super();
		this.hautDroit = hautDroit;
		this.basGauche = basGauche;
		this.direction = direction;
	}


	public Coordonnee getHautDroit() {
		return hautDroit;
	}


	public void setHautDroit(Coordonnee hautDroit) {
		this.hautDroit = hautDroit;
	}


	public Coordonnee getBasGauche() {
		return basGauche;
	}


	public void setBasGauche(Coordonnee basGauche) {
		this.basGauche = basGauche;
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
