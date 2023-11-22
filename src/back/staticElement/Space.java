package back.staticElement;

import java.util.HashSet;
import java.util.Set;

import back.mobileElement.Beacon;

public abstract class Space {

	private int maxX, minX, maxY, minY;

	private static Set<Beacon> fullBeacons = new HashSet<>();

	public Space(int maxX, int minX, int maxY, int minY) {
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

	public static Set<Beacon> getfullBeacons() {
		return Space.fullBeacons;
	}

	public static boolean addBeaconToFullBeacons(Beacon beacon) {
		return Space.fullBeacons.add(beacon);
	}

	public static boolean removeBeaconFromFullBeacons(Beacon beacon) {
		return Space.fullBeacons.remove(beacon);
	}

	public abstract void move();
}
