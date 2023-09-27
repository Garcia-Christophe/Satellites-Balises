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
			satellite.move(getMaxX(), getMinX());
			
			List<Balise> baliseNonSynchronise = new ArrayList<>();
			for (Balise balise : super.getBalisesPleine()) {
				if (balise.getCoordonnee().getX() == satellite.getCoordonnee().getX()) {
					balise.synchronisation();
					satellite.synchronisation();					
				} else {
					baliseNonSynchronise.add(balise);
				}
			}
			super.setBalisesPleine(baliseNonSynchronise);
		}
	}

}
