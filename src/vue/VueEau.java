package vue;

import java.awt.Color;
import java.awt.Dimension;

import back.elemStatic.Eau;

public class VueEau extends VueEspace {

	/**
	 * ID de la version de série générée.
	 */
	private static final long serialVersionUID = 5851936924569437189L;

	private Eau eau;

	public VueEau(Eau eau, int height) {
		super(eau.getMinX(), eau.getMinY() + height / 2, new Dimension(eau.getMaxX(), eau.getMaxY() + height / 2));
		this.setBackground(Color.blue);

		this.setEau(eau);
	}

	public Eau getEau() {
		return eau;
	}

	public void setEau(Eau eau) {
		this.eau = eau;
	}

}
