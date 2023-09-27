package vue;

import java.awt.Color;
import java.awt.Dimension;

import back.elemStatic.Air;

public class VueAir extends VueEspace {

	private Air air;

	public VueAir(Air air) {
		super(air.getMinX(), air.getMinY(), new Dimension(air.getMaxX(), air.getMaxY()));
		this.setBackground(Color.white);

		this.air = air;
	}

}
