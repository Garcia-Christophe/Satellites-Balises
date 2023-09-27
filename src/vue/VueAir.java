package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class VueAir extends VueEspace {

	private List<VueSatellite> vuesSatellites;

	public VueAir(int x, int y, Dimension dimension) {
		super(x, y, dimension);
		this.setBackground(Color.white);

		this.vuesSatellites = new ArrayList<>();
	}

	public List<VueSatellite> getVuesSatellites() {
		return vuesSatellites;
	}

}
