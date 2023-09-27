package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class VueEau extends VueEspace {

	private List<VueBalise> vuesBalises;

	public VueEau(int x, int y, Dimension dimension) {
		super(x, y, dimension);
		this.setBackground(Color.blue);

		this.vuesBalises = new ArrayList<>();
	}

	public List<VueBalise> getVuesBalises() {
		return vuesBalises;
	}

}
