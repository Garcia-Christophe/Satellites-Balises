package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.function.Function;

import javax.imageio.ImageIO;

import nicellipse.component.NiImage;

/**
 * Classe abstraite, héritant d'un JComponent Image simplifié, représentant la
 * vue d'un élément mobile.
 */
public abstract class ViewMobileElement extends NiImage {

	/**
	 * ID de la version de série générée.
	 */
	private static final long serialVersionUID = 8314462284805791832L;
	
	protected static String jarPath;

	/**
	 * Crée la vue d'un élément mobile.
	 * 
	 * @param path - Chemin de l'image à associer à l'élément mobile
	 * @throws IOException - Lorsqu'il n'est pas possible d'associer l'image
	 */
	public ViewMobileElement(File path) throws IOException {
		super(path);
		ProtectionDomain protectionDomain = Simulation.class.getProtectionDomain();
        CodeSource codeSource = protectionDomain.getCodeSource();

        if (codeSource != null) {
            java.net.URL jarUrl = codeSource.getLocation();
            jarPath = jarUrl.getPath();
            System.out.println(path.getAbsolutePath());
            System.out.println("Chemin absolu de l'application : " + jarPath);
        } else {
            System.out.println("Impossible de r�cup�rer le chemin absolu de l'application.");
        }
	}

	/**
	 * Met à jour le contenu.
	 */
	public void updateView() {
		this.redraw();
	}

	/**
	 * Permet de changer l'image de l'élément mobile.
	 * 
	 * @param path - Nouveau chemin de fichier à associer à l'élément mobile
	 */
	public void changeImage(String path) {
		try {
			BufferedImage rawImage = null;
			rawImage = ImageIO.read(new File(path));
			this.setImage(rawImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode abstraite permettant de définir le redessinage.
	 */
	public abstract void redraw();

}
