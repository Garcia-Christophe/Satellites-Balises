package vue;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import back.mobileElement.Beacon;

public class ViewBeacon extends ViewMobileElement {

	/**
	 * ID de la version de série générée.
	 */
	private static final long serialVersionUID = -6000578662312970019L;
	private static String pathBalise = "./assets/balise.png";
	private static String pathBaliseEnSynchro = "./assets/baliseEnSynchro.png";

	private Beacon beacon;
	private boolean wasSynchro;

	public ViewBeacon(Beacon bal) throws IOException {
		super(new File("./assets/balise.png"));

		this.beacon = bal;
		this.redraw();
	}

	@Override
	public void redraw() {
		try {
			int x = (int) (this.beacon.getTopRight().getX() - (this.getImage().getWidth(null) / 2));
			int y = (int) (this.beacon.getBottomLeft().getY() - (this.getImage().getHeight(null) / 2));
			this.setCenter(new Point(x, y));
			
			if(beacon.isInSynchro()) {
				if(!wasSynchro) {
					changeImage(pathBaliseEnSynchro);
					wasSynchro=true;
				}
			}else if(wasSynchro){
				changeImage(pathBalise);
				wasSynchro=false;
			}
		} catch (Exception e) {
		}
	}
	

	public Beacon getBeacon() {
		return beacon;
	}

}
