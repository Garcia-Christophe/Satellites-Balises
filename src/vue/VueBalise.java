package vue;

import java.io.File;
import java.io.IOException;

public class VueBalise extends VueElementMobile {

	/**
	 * ID de la version de série générée.
	 */
	private static final long serialVersionUID = -6000578662312970019L;

	public VueBalise() throws IOException {
		super(new File("./assets/balise.png"));
	}

	@Override
	public void redessine() {
	}

}
