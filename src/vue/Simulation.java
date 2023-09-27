package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;

import nicellipse.component.NiSpace;

public class Simulation {

	private NiSpace space = new NiSpace("Simulation satellites balises", new Dimension(1024, 728));

	public static void main(String[] args) {
		Simulation simulation = new Simulation();
//		simulation.setGraphicAnimation();
	}

	public Simulation() {
		this.space.setDoubleBuffered(true);
		this.space.openInWindow();
		try {
			this.nouveauxEspaces();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void nouveauxEspaces() throws IOException {
		// TODO:
		// - importer Espace
		// - créer 2 instances avec les bonnes dimensions et la bonne position
//		Espace air = new Espace(0, 0);
//		Espace mer = new Espace(0, 512);
		VueEspace vueAir = new VueEspace(0, 0, new Dimension(1024, 364), Color.white);
		VueEspace vueMer = new VueEspace(0, 364, new Dimension(1024, 364), Color.blue);

		VueSatellite vueSat = new VueSatellite();
		vueSat.setCenter(new Point(512, 50));
		VueBalise vueBal = new VueBalise();

		this.space.add(vueAir);
		this.space.add(vueMer);
		vueAir.add(vueSat);
		vueMer.add(vueBal);
		this.space.repaint();
	}

}
