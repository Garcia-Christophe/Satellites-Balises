package back.elemStatic;

import java.util.List;

import back.elemMobile.Balise;

public class Eau {
	private int maxX, minX, maxY, minY;
	
	private List<Balise> balises;
	
	private List<Balise> balisesPleine;

	public Eau(int maxX, int minX, int maxY, int minY, List<Balise> balises) {
		super();
		this.maxX = maxX;
		this.minX = minX;
		this.maxY = maxY;
		this.minY = minY;
		this.balises = balises;
	}

	public int getMaxX() {
		return maxX;
	}

	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	public int getMinX() {
		return minX;
	}

	public void setMinX(int minX) {
		this.minX = minX;
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

	public int getMinY() {
		return minY;
	}

	public void setMinY(int minY) {
		this.minY = minY;
	}

	public List<Balise> getBalises() {
		return balises;
	}

	public void setBalises(List<Balise> balises) {
		this.balises = balises;
	}
	
	public void move() {
		for (Balise balise : this.balises) {
			double random = Math.random();
			if (random < 0.5) {
				switch(balise.getDirection()) {
					case HORIZONTAL:
						if (balise.getCoordonnee().getX() < this.maxX)
							balise.getCoordonnee().setX(balise.getCoordonnee().getX()+1);
					case VERTICAL:
						if (balise.getCoordonnee().getY() < this.maxY)
							balise.getCoordonnee().setY(balise.getCoordonnee().getY()+1);
				}
			} else {
				switch(balise.getDirection()) {
					case HORIZONTAL:
						if (balise.getCoordonnee().getX() > this.minX)
							balise.getCoordonnee().setX(balise.getCoordonnee().getX()-1);
					case VERTICAL:
						if (balise.getCoordonnee().getY() > this.minY)
							balise.getCoordonnee().setY(balise.getCoordonnee().getY()-1);
				}
			}
		}
	}
	
	
}
