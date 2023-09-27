package vue;

import java.io.File;
import java.io.IOException;

public class VueSatellite extends VueElementMobile {

	/**
	 * ID de la version de série générée.
	 */
	private static final long serialVersionUID = 5831396417635822418L;

	public VueSatellite() throws IOException {
		super(new File("./assets/satellite.png"));
	}

	@Override
	public void redessine() {
	}

}
