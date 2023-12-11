package view;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import back.mobileElement.Beacon;

/**
 * Vue représentant une balise.
 */
public class ViewBeacon extends ViewMobileElement {

	/**
	 * ID de la version de série générée.
	 */
	private static final long serialVersionUID = -6000578662312970019L;

	/**
	 * Chemin de l'image représentant une balise, qu'importe la balise.
	 */
	private static String beaconPath = "assets/balise.png";

	/**
	 * Chemin de l'image représentant une balise en synchronisation, qu'importe la
	 * balise.
	 */
	private static String synchronizingBeaconPath = "assets/baliseEnSynchro.png";

	/**
	 * La balise représentée.
	 */
	private Beacon beacon;

	/**
	 * True si à l'étape de simulation précédente, la balise était en
	 * synchronisation, false sinon.
	 */
	private boolean wasSynchro;

	/**
	 * Crée une représentation d'une balise.
	 * 
	 * @param beacon - Balise représentée
	 * @throws IOException - Lorsqu'il n'est pas possible d'associer l'image
	 */
	public ViewBeacon(Beacon beacon) throws IOException {
		super(new File(beaconPath));

		this.beacon = beacon;
		this.redraw();
	}

	@Override
	public void redraw() {
		try {
			// Place l'image correctement en fonction de la balise
			int x = (int) (this.beacon.getTopRight().getX() - (this.getImage().getWidth(null) / 2));
			int y = (int) (this.beacon.getBottomLeft().getY() - (this.getImage().getHeight(null) / 2));
			this.setCenter(new Point(x, y));

			if (beacon.isInSynchro()) {
				if (!wasSynchro) {
					// Si la balise est en synchronisation alors qu'elle ne l'était pas à l'étape
					// de simulation précédente, alors change l'image
					changeImage(synchronizingBeaconPath);
					wasSynchro = true;
				}
			} else if (wasSynchro) {
				// Si la balise n'est plus en synchronisation alors qu'elle l'était à l'étape
				// de simulation précédente, alors change l'image
				changeImage(beaconPath);
				wasSynchro = false;
			}
		} catch (Exception e) {
			// Ne fait rien
		}
	}

}
