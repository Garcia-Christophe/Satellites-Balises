package back.elemStatic;

import java.util.ArrayList;
import java.util.List;

import back.elemMobile.Balise;
import back.elemMobile.Satellite;

public class Air extends Espace{
	
	
	private List<Satellite> satellites;

	public Air(int maxX, int minX, int maxY, int minY, List<Satellite> satellites) {
		super(maxX, minX, maxY, minY);
		this.satellites = satellites;
	}

	public List<Satellite> getSatellites() {
		return satellites;
	}

	public void setSatellites(List<Satellite> satellites) {
		this.satellites = satellites;
	}
	
	@Override
	public void move() {
		for (Satellite satellite : this.satellites) {
			satellite.getMoveStrategy().move(satellite, getMaxX(), getMinX(), getMaxY(), getMinY());
			
			boolean dejaSynchro = false;
			for (Balise balise : super.getBalisesPleine()) {
				double baliseX = (balise.getHautDroit().getX() + balise.getBasGauche().getX()) / 2;
				double satelliteX = (satellite.getHautDroit().getX() + satellite.getBasGauche().getX()) / 2;
				boolean inIntervalSynchro = (baliseX >= (satelliteX - 100))
						&& (baliseX <= (satelliteX + 100));
				if (inIntervalSynchro && !dejaSynchro) {
					balise.synchronisation();
					satellite.synchronisation();
					dejaSynchro = true;
				}
			}
			super.setBalisesPleine(new ArrayList<>());
		}
	}

}
