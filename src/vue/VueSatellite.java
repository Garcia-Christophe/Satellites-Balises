package vue;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import back.elemMobile.Satellite;

public class VueSatellite extends VueElementMobile {

	/**
	 * ID de la version de série générée.
	 */
	private static final long serialVersionUID = 5831396417635822418L;

	private Satellite satellite;

	public VueSatellite(Satellite sat) throws IOException {
		super(new File("./assets/satellite.png"));

		this.satellite = sat;
		this.redessine();
	}

	@Override
	public void redessine() {
		if (this.satellite.getHautDroit() != null) {
			int x = (int) (this.satellite.getHautDroit().getX() - (this.getImage().getWidth(null) / 2));
			int y = (int) (this.satellite.getBasGauche().getY() - (this.getImage().getHeight(null) / 2));
			try {
				this.setCenter(new Point(x, y));
			} catch (Exception e) {
			}
		}
	}

}
