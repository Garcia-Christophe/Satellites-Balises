package vue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import nicellipse.component.NiImage;

public abstract class VueElementMobile extends NiImage {

	/**
	 * ID de la version de série générée.
	 */
	private static final long serialVersionUID = 8314462284805791832L;

	public VueElementMobile(File path) throws IOException {
		super(path);
	}

	public void mettreAJourVue() {
		this.redessine();
	}
	
	public void changeImage(String path) {
		try {
			BufferedImage rawImage = null;
			rawImage = ImageIO.read(new File(path));
			this.setImage(rawImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public abstract void redessine();

}
