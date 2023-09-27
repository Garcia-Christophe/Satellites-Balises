package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import nicellipse.component.NiRectangle;

public class VueEspace extends NiRectangle {

	/**
	 * ID de la version de série générée.
	 */
	private static final long serialVersionUID = -8450696591338239544L;

	public VueEspace(int x, int y, Dimension dim, Color bgColor) {
		// TODO:
		// - utiliser les coordonnées du paramètre Espace espace) pour les lignes
		// suivantes
		Point position = new Point(x, y);
		Dimension dimension = dim;
		this.setBounds(position.x, position.y, dimension.width, dimension.height);
		Color tc = new Color(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue());
		this.setBackground(tc);
	}

}
