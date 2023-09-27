package vue;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import back.elemMobile.Balise;

public class VueBalise extends VueElementMobile {

	/**
	 * ID de la version de série générée.
	 */
	private static final long serialVersionUID = -6000578662312970019L;

	private Balise balise;

	public VueBalise(Balise bal) throws IOException {
		super(new File("./assets/balise.png"));

		this.balise = bal;
		this.redessine();
	}

	@Override
	public void redessine() {
		this.setCenter(new Point(this.balise.getCoordonnee().getX(), this.balise.getCoordonnee().getY()));
	}

}
