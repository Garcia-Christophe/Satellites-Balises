package vue;

import java.awt.Color;
import java.awt.Dimension;

import back.elemStatic.Air;

public class VueAir extends VueEspace {

	/**
	 * ID de la version de série générée.
	 */
	private static final long serialVersionUID = 6893212399291262450L;

	private Air air;

	public VueAir(Air air) {
		super(air.getMinX(), air.getMinY(), new Dimension(air.getMaxX(), air.getMaxY()));
		this.setBackground(Color.white);

		this.setAir(air);
	}

	public Air getAir() {
		return air;
	}

	public void setAir(Air air) {
		this.air = air;
	}

}
