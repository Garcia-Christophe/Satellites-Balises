package back.elemMobile;

public class Balise extends ElementMobile {
	
	private int capacite, stockage;

	public Balise(Coordonnee coordonnee, Direction direction, int capacite) {
		super(coordonnee, direction);
		this.capacite = capacite;
		this.stockage = 0;
	}

	public boolean stockerDonnee() {
		int nbdonnee = (int) Math.random() * 3;
		this.stockage += nbdonnee;
		return this.stockage <= this.capacite;
	}

	@Override
	public void synchronisation() {
				
	}

}
