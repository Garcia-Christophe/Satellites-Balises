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
			if (balise.estPleine()) {
				if (balise.getHautDroit().getY() > super.getMinY() + 9) {
					balise.getBasGauche().setLocation(balise.getBasGauche().getX(), balise.getBasGauche().getY() - 10);
					balise.getHautDroit().setLocation(balise.getHautDroit().getX(), balise.getHautDroit().getY() - 10);
				} else {
					super.addBaliseToBalisesPleine(balise);
				}
			} else {
				balise.getMoveStrategy().move(balise, getMaxX(), getMinX(), getMaxY(), getMinY());
			}
		}
	}

}
