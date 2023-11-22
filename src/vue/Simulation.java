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

import back.SimulationContext;
import back.mobileElement.Beacon;
import back.mobileElement.Satellite;
import nicellipse.component.NiRectangle;
import nicellipse.component.NiSpace;

public class Simulation {

	private NiSpace niSpace;

	private NiRectangle viewAir, viewEau;

	private SimulationContext simulationContext;

	private final int WINDOW_WIDTH = 1024, WINDOW_HEIGHT = 728;

	public static void main(String[] args) {
		Simulation simulation = new Simulation();
		simulation.startGraphicAnimation();
	}

	public Simulation() {
		this.simulationContext = new SimulationContext(WINDOW_WIDTH, WINDOW_HEIGHT);

		this.niSpace = new NiSpace("Simulation satellites balises", new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		this.niSpace.setDoubleBuffered(true);
		this.niSpace.openInWindow();

		this.niewSpaces();
	}

	public void niewSpaces() {
		try {
			for (Satellite satellite : this.simulationContext.getSatellites()) {
				ViewSatellite vueSatellite = new ViewSatellite(satellite);
				int randX = new Random().nextInt(900 - 100) + 100;
				int randY = new Random().nextInt(180 - 40) + 10;

				satellite.setTopRight(new Point(vueSatellite.getImage().getWidth(null) + randX, 0 + randY));
				satellite.setBottomLeft(new Point(0 + randX, vueSatellite.getImage().getHeight(null) + randY));
				this.niSpace.add(vueSatellite);
			}

			for (Beacon balise : this.simulationContext.getBeacons()) {
				ViewBeacon vueBalise = new ViewBeacon(balise);
				int randX = new Random().nextInt(900 - 100) + 100;
				int randY = new Random().nextInt(300 - 40) + 10 + WINDOW_HEIGHT / 2;

				balise.setTopRight(new Point(vueBalise.getImage().getWidth(null) + randX, 0 + randY));
				balise.setBottomLeft(new Point(0 + randX, vueBalise.getImage().getHeight(null) + randY));
				this.niSpace.add(vueBalise);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Séparation des vues de l'air et de l'eau
		viewAir = new NiRectangle();
		viewAir.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT / 2);
		viewAir.setBackground(Color.white);
		viewEau = new NiRectangle();
		viewEau.setBounds(0, WINDOW_HEIGHT / 2, WINDOW_WIDTH, WINDOW_HEIGHT / 2);
		viewEau.setBackground(Color.blue);

		this.niSpace.add(viewAir);
		this.niSpace.add(viewEau);

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
			Simulation.this.simulationContext.move();

			// Met à jour toutes les vues éléments
			for (Component vueMobile : Simulation.this.niSpace.getComponents()) {
				if (vueMobile != Simulation.this.viewAir && vueMobile != Simulation.this.viewEau) {
					((ViewMobileElement) vueMobile).updateView();
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
