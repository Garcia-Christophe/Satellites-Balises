package vue;

import java.awt.Dimension;

import nicellipse.component.NiRectangle;

public abstract class VueEspace extends NiRectangle {

	/**
	 * ID de la version de série générée.
	 */
	private static final long serialVersionUID = -8450696591338239544L;

	public VueEspace(int x, int y, Dimension dimension) {
		this.setBounds(x, y, dimension.width, dimension.height);
	}

}
