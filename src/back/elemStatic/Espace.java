package back.elemStatic;

import java.util.ArrayList;
import java.util.List;

import back.elemMobile.Balise;

public abstract class Espace {
	
	private int maxX, minX, maxY, minY;
	
	private List<Balise> balisesPleine;
	

	public Espace(int maxX, int minX, int maxY, int minY) {
		this.maxX = maxX;
		this.minX = minX;
		this.maxY = maxY;
		this.minY = minY;
		this.balisesPleine = new ArrayList<>();
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

	public List<Balise> getBalisesPleine() {
		return balisesPleine;
	}

	public void setBalisesPleine(List<Balise> balisesPleine) {
		this.balisesPleine = balisesPleine;
	}
	
	public boolean addBaliseToBalisesPleine(Balise balise) {
		return this.balisesPleine.add(balise);
	}
	
	public boolean removeBaliseToBalisesPleine(Balise balise) {
		return this.balisesPleine.remove(balise);
	}
	
	public abstract void move();
}
