package view;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import back.mobileElement.Satellite;

/**
 * Vue représentant un satellite.
 */
public class ViewSatellite extends ViewMobileElement {

	/**
	 * ID de la version de série générée.
	 */
	private static final long serialVersionUID = 5831396417635822418L;

	/**
	 * Chemin de l'image représentant un satellite, qu'importe la balise.
	 */
	private static String satellitePath = "./src/assets/satellite.png";

	/**
	 * Chemin de l'image représentant un satellite en synchronisation, qu'importe le
	 * satellite.
	 */
	private static String synchronizingSatellitePath = "./src/assets/satelliteEnSynchro.png";

	/**
	 * Le satellite représenté.
	 */
	private Satellite satellite;

	/**
	 * True si à l'étape de simulation précédente, le satellite était en
	 * synchronisation, false sinon.
	 */
	private boolean wasSynchro;

	/**
	 * Crée une représentation d'un satellite.
	 * 
	 * @param satellite - Satellite représenté
	 * @throws IOException - Lorsqu'il n'est pas possible d'associer l'image
	 */
	public ViewSatellite(Satellite satellite) throws IOException {
		super(new File(satellitePath));

		this.satellite = satellite;
		this.redraw();
	}

	@Override
	public void redraw() {
		// Place l'image correctement en fonction du satellite
		int x = (int) (this.satellite.getTopRight().getX() - (this.getImage().getWidth(null) / 2));
		int y = (int) (this.satellite.getBottomLeft().getY() - (this.getImage().getHeight(null) / 2));
		this.setCenter(new Point(x, y));

		try {
			if (satellite.isInSynchronisation()) {
				// Si le satellite est en synchronisation alors qu'il ne l'était pas à l'étape
				// de simulation précédente, alors change l'image
				if (!wasSynchro) {
					changeImage(synchronizingSatellitePath);
					wasSynchro = true;
				}
			} else if (wasSynchro) {
				// Si le satellite n'est plus en synchronisation alors qu'il l'était à l'étape
				// de simulation précédente, alors change l'image
				changeImage(satellitePath);
				wasSynchro = false;
			}
		} catch (Exception e) {
			// Ne fait rien
		}
	}

}
