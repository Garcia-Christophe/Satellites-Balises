package vue;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import back.elemMobile.Balise;

public class VueBalise extends VueElementMobile {

	/**
	 * ID de la version de série générée.
	 */
	private static final long serialVersionUID = -6000578662312970019L;
	private static String pathBalise = "./assets/balise.png";
	private static String pathBaliseEnSynchro = "./assets/baliseEnSynchro.png";

	private Balise balise;
	private boolean etaitSynchro;

	public VueBalise(Balise bal) throws IOException {
		super(new File("./assets/balise.png"));

		this.balise = bal;
		this.redessine();
	}

	@Override
	public void redessine() {
		try {
			int x = (int) (this.balise.getHautDroit().getX() - (this.getImage().getWidth(null) / 2));
			int y = (int) (this.balise.getBasGauche().getY() - (this.getImage().getHeight(null) / 2));
			this.setCenter(new Point(x, y));
			
			if(balise.estEnSynchro()) {
				if(!etaitSynchro) {
					changeImage(pathBaliseEnSynchro);
					etaitSynchro=true;
				}
			}else if(etaitSynchro){
				changeImage(pathBalise);
				etaitSynchro=false;
			}
		} catch (Exception e) {
		}
	}
	

	public Balise getBalise() {
		return balise;
	}

}
