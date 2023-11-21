package back.elemStatic;

import java.util.List;

import back.elemMobile.Balise;

public class Eau extends Espace {

	private List<Balise> balises;

	public Eau(int maxX, int minX, int maxY, int minY, List<Balise> balises) {
		super(maxX, minX, maxY, minY);
		this.balises = balises;
	}

	public List<Balise> getBalises() {
		return balises;
	}

	public void setBalises(List<Balise> balises) {
		this.balises = balises;
	}

	@Override
	public void move() {
		for (Balise balise : this.balises) {
			if (!balise.estPleine()) {
				balise.getMoveStrategy().move(balise, getMaxX(), getMinX(), getMaxY(), getMinY());
				balise.stockerDonnee();
			} else if (balise.getHautDroit().y > this.getMinY() + 9) {
				balise.getMoveStrategy().move(balise, getMaxX(), getMinX(), getMaxY(), getMinY());
			} else {
				super.addBaliseToBalisesPleines(balise);
			}
		}
	}

}
