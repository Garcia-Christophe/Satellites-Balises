package back.elemStatic;

import java.util.HashSet;
import java.util.Set;

import back.elemMobile.Balise;

public abstract class Espace {

	private int maxX, minX, maxY, minY;

	private static Set<Balise> balisesPleines = new HashSet<>();

	public Espace(int maxX, int minX, int maxY, int minY) {
		this.maxX = maxX;
		this.minX = minX;
		this.maxY = maxY;
		this.minY = minY;
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

	public static Set<Balise> getBalisesPleines() {
		return Espace.balisesPleines;
	}

	public static boolean addBaliseToBalisesPleines(Balise balise) {
		return Espace.balisesPleines.add(balise);
	}

	public static boolean removeBaliseFromBalisesPleines(Balise balise) {
		return Espace.balisesPleines.remove(balise);
	}

	public abstract void move();
}
