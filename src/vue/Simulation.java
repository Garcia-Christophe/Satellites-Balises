package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.Timer;

import back.ContexteDeSimulation;
import back.elemMobile.Balise;
import back.elemMobile.Satellite;
import nicellipse.component.NiRectangle;
import nicellipse.component.NiSpace;

public class Simulation {

	private NiSpace niSpace;

	private NiRectangle vueAir, vueEau;

	private ContexteDeSimulation contexteDeSimulation;

	private final int WINDOW_WIDTH = 1024, WINDOW_HEIGHT = 728;

	public static void main(String[] args) {
		Simulation simulation = new Simulation();
		simulation.startGraphicAnimation();
	}

	public Simulation() {
		this.contexteDeSimulation = new ContexteDeSimulation(WINDOW_WIDTH, WINDOW_HEIGHT);

		this.niSpace = new NiSpace("Simulation satellites balises", new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		this.niSpace.setDoubleBuffered(true);
		this.niSpace.openInWindow();

		this.nouveauxEspaces();
	}

	public void nouveauxEspaces() {
		try {
			for (Satellite satellite : this.contexteDeSimulation.getSatellites()) {
				VueSatellite vueSatellite = new VueSatellite(satellite);
				int randX = new Random().nextInt(900 - 100) + 100;
				int randY = new Random().nextInt(180 - 40) + 10;

				satellite.setHautDroit(new Point(vueSatellite.getImage().getWidth(null) + randX, 0 + randY));
				satellite.setBasGauche(new Point(0 + randX, vueSatellite.getImage().getHeight(null) + randY));
				this.niSpace.add(vueSatellite);
			}

			for (Balise balise : this.contexteDeSimulation.getBalises()) {
				VueBalise vueBalise = new VueBalise(balise);
				int randX = new Random().nextInt(900 - 100) + 100;
				int randY = new Random().nextInt(300 - 40) + 10 + WINDOW_HEIGHT / 2;

				balise.setHautDroit(new Point(vueBalise.getImage().getWidth(null) + randX, 0 + randY));
				balise.setBasGauche(new Point(0 + randX, vueBalise.getImage().getHeight(null) + randY));
				this.niSpace.add(vueBalise);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Séparation des vues de l'air et de l'eau
		vueAir = new NiRectangle();
		vueAir.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT / 2);
		vueAir.setBackground(Color.white);
		vueEau = new NiRectangle();
		vueEau.setBounds(0, WINDOW_HEIGHT / 2, WINDOW_WIDTH, WINDOW_HEIGHT / 2);
		vueEau.setBackground(Color.blue);

		this.niSpace.add(vueAir);
		this.niSpace.add(vueEau);

		this.niSpace.repaint();
	}

	public void startGraphicAnimation() {
		new GraphicAnimation().start();
	}

	class GraphicAnimation implements ActionListener {
		private int graphicAnimationDelay = 100;

		@Override
		public void actionPerformed(ActionEvent e) {
			// Étape de simulation
			Simulation.this.contexteDeSimulation.move();

			// Met à jour toutes les vues éléments
			for (Component vueMobile : Simulation.this.niSpace.getComponents()) {
				if (vueMobile != Simulation.this.vueAir && vueMobile != Simulation.this.vueEau) {
					((VueElementMobile) vueMobile).mettreAJourVue();
				}
			}
		}

		public void start() {
			Timer animation = new Timer(0, this);
			animation.setDelay(this.graphicAnimationDelay);
			animation.start();
		}
	}

}
