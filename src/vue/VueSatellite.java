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
		this.setCenter(new Point(this.satellite.getCoordonnee().getX(), this.satellite.getCoordonnee().getY()));
	}

}
