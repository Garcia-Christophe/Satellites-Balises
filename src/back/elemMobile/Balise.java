package back.elemMobile;

public class Balise extends ElementMobile {
	
	private int capacite, stockage, nbDescente, indexDescente;
	
	private boolean estPleine;

	public Balise(Coordonnee coordonnee, Direction direction, int capacite, int maxY, int minY) {
		super(coordonnee, direction);
		this.capacite = capacite;
		this.stockage = 0;
		this.estPleine = false;
		this.indexDescente = 0;
		this.nbDescente = (int) Math.random() * (maxY - minY);
	}
	
	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public int getStockage() {
		return stockage;
	}

	public void setStockage(int stockage) {
		this.stockage = stockage;
	}

	public boolean estPleine() {
		return estPleine;
	}

	public void setEstPleine(boolean estPleine) {
		this.estPleine = estPleine;
	}

	public int getNbDescente() {
		return nbDescente;
	}

	public void setNbDescente(int nbDescente) {
		this.nbDescente = nbDescente;
	}

	public int getIndexDescente() {
		return indexDescente;
	}

	public void setIndexDescente(int indexDescente) {
		this.indexDescente = indexDescente;
	}

	public void stockerDonnee() {
		int nbdonnee = (int) Math.random() * 3;
		this.stockage += nbdonnee;
		this.estPleine = this.stockage >= this.capacite;
	}

	@Override
	public void synchronisation() {
			System.out.println("synchronisation effectué");
			this.setStockage(0);
			this.setEstPleine(false);
			this.setIndexDescente(this.getNbDescente());
	}
	
	public void move(int maxX, int minX, int maxY, int minY) {
		if (indexDescente != 0) {
			if (this.getCoordonnee().getY() > minY)
				this.getCoordonnee().setY(this.getCoordonnee().getY()-1);
			this.indexDescente--;
		} else {
			double random = Math.random();
			switch(this.getDirection()) {
				case HORIZONTAL:
					this.moveHorizontale(random, maxX, minX);
				case VERTICAL:
					this.moveVerticale(random, maxY, minY);
			}
		}
	}
	
	private void moveHorizontale(double direction, int maxX, int minX) {
		if (direction < 0.5) {
			//vers la droite
			if (this.getCoordonnee().getX() < maxX)
				this.getCoordonnee().setX(this.getCoordonnee().getX()+1);
		} else {
			//vers la gauche
			if (this.getCoordonnee().getX() > minX)
				this.getCoordonnee().setX(this.getCoordonnee().getX()-1);
		}
	}
	
	private void moveVerticale(double direction, int maxY, int minY) {
		if (direction < 0.5) {
			//vers le haut
			if (this.getCoordonnee().getY() < maxY)
				this.getCoordonnee().setY(this.getCoordonnee().getY()+1);
		} else {
			//vers le bas
			if (this.getCoordonnee().getY() > minY)
				this.getCoordonnee().setY(this.getCoordonnee().getY()-1);
		}
	}

}
