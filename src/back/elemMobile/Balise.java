package back.elemMobile;

import java.util.Random;

public class Balise extends ElementMobile {

	private int capacite, stockage, nbDescente, indexDescente, attenteSynchro;

	private boolean estPleine, estSynchro;

	public Balise(Coordonnee hautDroit, Coordonnee basGauche, Direction direction, int capacite, int maxY, int minY) {
		super(hautDroit, basGauche, direction);
		Random rand = new Random();
		this.capacite = capacite;
		this.stockage = 0;
		this.estPleine = false;
		this.indexDescente = 0;
		this.nbDescente = rand.nextInt(maxY - minY);
		this.estSynchro = false;
		this.attenteSynchro = 0;
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
		Random rand = new Random();
		int nbdonnee = rand.nextInt(3) + 1;
		this.stockage += nbdonnee;
		this.estPleine = this.stockage >= this.capacite;
	}

	@Override
	public void synchronisation() {
		if (this.attenteSynchro < super.tempsAttenteSynchro) {
			this.estSynchro = true;
			this.attenteSynchro++;
		} else {
			System.out.println("synchronisation effectu�");
			this.setStockage(0);
			this.setEstPleine(false);
			this.setIndexDescente(this.getNbDescente());
			this.attenteSynchro = 0;
			this.estSynchro = false;
		}
	}

	public void move(int maxX, int minX, int maxY, int minY) {
		if (indexDescente != 0) {
			if (this.getBasGauche().getY() < maxY) {
				this.getBasGauche().setY(this.getBasGauche().getY() + 10);
				this.getHautDroit().setY(this.getHautDroit().getY() + 10);
			}
			this.indexDescente--;
		} else {
			Random rand = new Random();
			double random = rand.nextInt(2);
			switch (this.getDirection()) {
			case HORIZONTAL:
				this.moveHorizontale(random, maxX, minX);
				break;
			case VERTICAL:
				this.moveVerticale(random, maxY, minY);
				break;
			}
			stockerDonnee();
		}
	}

	private void moveHorizontale(double direction, int maxX, int minX) {
		if (direction == 0) {
			// vers la droite
			if (this.getHautDroit().getX() < maxX) {
				this.getBasGauche().setX(this.getBasGauche().getX() + 10);
				this.getHautDroit().setX(this.getHautDroit().getX() + 10);
			}
		} else {
			// vers la gauche
			if (this.getBasGauche().getX() > minX) {
				this.getBasGauche().setX(this.getBasGauche().getX() - 10);
				this.getHautDroit().setX(this.getHautDroit().getX() - 10);
			}
		}
	}

	private void moveVerticale(double direction, int maxY, int minY) {
		if (direction == 0) {
			// vers le bas
			if (this.getBasGauche().getY() < maxY) {
				this.getBasGauche().setY(this.getBasGauche().getY() + 10);
				this.getHautDroit().setY(this.getHautDroit().getY() + 10);
			}
		} else {
			// vers le haut
			if (this.getHautDroit().getY() > minY) {
				this.getBasGauche().setY(this.getBasGauche().getY() - 10);
				this.getHautDroit().setY(this.getHautDroit().getY() - 10);
			}
		}
	}

}
