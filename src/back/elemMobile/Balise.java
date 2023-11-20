package back.elemMobile;

import java.awt.Point;
import java.util.Random;

public class Balise extends ElementMobile {

	private int capacite, stockage, nbDescente, indexDescente, attenteSynchro;

	private boolean estPleine, estSynchro;

	public Balise(Point hautDroit, Point basGauche, MoveStrategy moveStrategy, int capacite, int maxY, int minY) {
		super(hautDroit, basGauche, moveStrategy);
		Random rand = new Random();
		this.capacite = capacite;
		this.stockage = 0;
		this.estPleine = false;
		this.indexDescente = 0;
		this.nbDescente = rand.nextInt(maxY - minY) / 10;
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
}
