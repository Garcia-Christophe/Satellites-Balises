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
				if (balise.getCoordonnee().getY() < super.getMaxY()) {
					balise.getCoordonnee().setY(balise.getCoordonnee().getY()+1);
				} else {
					super.addBaliseToBalisesPleine(balise);
				}
			} else {
				balise.move(getMaxX(), getMinX(), getMaxY(), getMinY());
				balise.stockerDonnee();
			}
		}
	}
	
	
}
