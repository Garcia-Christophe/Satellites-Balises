package back.staticElement;

import java.util.List;

import back.mobileElement.Beacon;

public class Water extends Space {

	private List<Beacon> beacons;

	public Water(int maxX, int minX, int maxY, int minY, List<Beacon> beacons) {
		super(maxX, minX, maxY, minY);
		this.beacons = beacons;
	}

	public List<Beacon> getBeacons() {
		return beacons;
	}

	public void setBeacons(List<Beacon> beacons) {
		this.beacons = beacons;
	}

	@Override
	public void move() {
		for (Beacon beacon : this.beacons) {
			if (!beacon.isFull()) {
				beacon.getMoveStrategy().move(beacon, getMaxX(), getMinX(), getMaxY(), getMinY());
				beacon.storeData();
			} else if (beacon.getTopRight().y > this.getMinY() + 9) {
				beacon.getMoveStrategy().move(beacon, getMaxX(), getMinX(), getMaxY(), getMinY());
			} else {
				super.addBeaconToFullBeacons(beacon);
			}
		}
	}

}
