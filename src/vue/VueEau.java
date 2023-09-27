package vue;

import java.awt.Color;
import java.awt.Dimension;

import back.elemStatic.Eau;

public class VueEau extends VueEspace {

	private Eau eau;

	public VueEau(Eau eau) {
		super(eau.getMinX(), eau.getMinY(), new Dimension(eau.getMaxX(), eau.getMaxY()));
		this.setBackground(Color.blue);

		this.eau = eau;
	}

}
