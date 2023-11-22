package vue;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import back.mobileElement.Satellite;

public class ViewSatellite extends ViewMobileElement {

	/**
	 * ID de la version de série générée.
	 */
	private static final long serialVersionUID = 5831396417635822418L;
	private static String pathSatellite = "./assets/satellite.png";
	private static String pathSatelliteInSynchro = "./assets/satelliteEnSynchro.png";

	private Satellite satellite;
	private boolean wasSynchro;

	public ViewSatellite(Satellite sat) throws IOException {
		super(new File(pathSatellite));

		this.satellite = sat;
		this.redraw();
	}

	@Override
	public void redraw() {
		if (this.satellite.getTopRight() != null) {
			int x = (int) (this.satellite.getTopRight().getX() - (this.getImage().getWidth(null) / 2));
			int y = (int) (this.satellite.getBottomLeft().getY() - (this.getImage().getHeight(null) / 2));
			try {
				this.setCenter(new Point(x, y));
				if(satellite.isInSynchronisation()) {
					if(!wasSynchro) {
						changeImage(pathSatelliteInSynchro);
						wasSynchro=true;
					}
				}else if(wasSynchro){
					changeImage(pathSatellite);
					wasSynchro=false;
				}
			} catch (Exception e) {
			}
		}
	}

}
