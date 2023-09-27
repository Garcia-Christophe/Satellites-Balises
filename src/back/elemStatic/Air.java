package back.elemStatic;

import java.util.ArrayList;
import java.util.List;
import back.elemMobile.Satellite;

public class Air {
	
	private int maxX, minX, maxY, minY;
	
	private List<Satellite> satellites;

	public Air(int maxX, int minX, int maxY, int minY, List<Satellite> satellites) {
		super();
		this.maxX = maxX;
		this.minX = minX;
		this.maxY = maxY;
		this.minY = minY;
		this.satellites = satellites;
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

	public List<Satellite> getSatellites() {
		return satellites;
	}

	public void setSatellites(List<Satellite> satellites) {
		this.satellites = satellites;
	}
	
	public void move() {
		for (Satellite satellite : this.satellites) {
			satellite.getCoordonnee().setX(satellite.getCoordonnee().getX()+1);
			if (satellite.getCoordonnee().getX() > this.maxX) satellite.getCoordonnee().setX(minX);
		}
	}

}
