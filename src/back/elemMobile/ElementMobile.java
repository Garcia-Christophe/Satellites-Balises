package back.elemMobile;

public abstract class ElementMobile {
	
	protected final int tempsAttenteSynchro = 5;
	
	protected Coordonnee hautDroit, basGauche;
	
	protected MoveStrategy moveStrategy;
	
	public ElementMobile(Coordonnee hautDroit, Coordonnee basGauche, MoveStrategy moveStrategy) {
		super();
		this.hautDroit = hautDroit;
		this.basGauche = basGauche;
		this.moveStrategy = moveStrategy;
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


	public MoveStrategy getMoveStrategy() {
		return moveStrategy;
	}


	public void setMoveStrategy(MoveStrategy moveStrategy) {
		this.moveStrategy = moveStrategy;
	}


	public abstract void synchronisation();
}
