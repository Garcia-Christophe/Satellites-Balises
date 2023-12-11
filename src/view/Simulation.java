package view;

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

/**
 * Classe contenant la méthode main. Lance la simulation de synchronisation
 * entre satellites et balises.
 */
public class Simulation {

	/**
	 * JPanel simplifié dans lequel se déroule la simulation.
	 */
	private NiSpace niSpace;

	/**
	 * JPanel simplifié représentant un rectangle pour décrire les espaces Air et
	 * Eau.
	 */
	private NiRectangle viewAir, viewEau;

	/**
	 * Le contexte de la simulation.
	 */
	private SimulationContext simulationContext;

	/**
	 * Tailles de la fenêtre de simulation.
	 */
	private final int WINDOW_WIDTH = 1024, WINDOW_HEIGHT = 728;

	/**
	 * Méthode main. Lance la simulation.
	 * 
	 * @param args - Éventuels arguments au lancement
	 */
	public static void main(String[] args) {
		new Simulation().startGraphicAnimation();
	}

	/**
	 * Crée la simulation.
	 */
	public Simulation() {
		// Crée le contexte de simulation
		this.simulationContext = new SimulationContext(WINDOW_WIDTH, WINDOW_HEIGHT);

		// Définit le panel de la simulation
		this.niSpace = new NiSpace("Simulation satellites balises", new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		this.niSpace.setDoubleBuffered(true);
		this.niSpace.openInWindow();

		// Crée le contenu de la simulation
		this.niewSpaces();
	}
 
	/**
	 * Crée le contenu de la simulation.
	 */
	public void niewSpaces() {
		try {
			for (Satellite satellite : this.simulationContext.getSatellites()) {
				// Pour chaque satellite, crée une vue associée
				ViewSatellite vueSatellite = new ViewSatellite(satellite);
				int randX = new Random().nextInt(900 - 100) + 100;
				int randY = new Random().nextInt(180 - 40) + 10;

				// Définit la taille du satellite en fonction de son image
				satellite.setTopRight(new Point(vueSatellite.getImage().getWidth(null) + randX, 0 + randY));
				satellite.setBottomLeft(new Point(0 + randX, vueSatellite.getImage().getHeight(null) + randY));

				// Ajoute le satellite dans le panel de simulation
				this.niSpace.add(vueSatellite);
			}

			for (Beacon balise : this.simulationContext.getBeacons()) {
				// Pour chaque balise, crée une vue associée
				ViewBeacon vueBalise = new ViewBeacon(balise);
				int randX = new Random().nextInt(900 - 100) + 100;
				int randY = new Random().nextInt(300 - 40) + 10 + WINDOW_HEIGHT / 2;

				// Définit la taille de la balise en fonction de son image
				balise.setTopRight(new Point(vueBalise.getImage().getWidth(null) + randX, 0 + randY));
				balise.setBottomLeft(new Point(0 + randX, vueBalise.getImage().getHeight(null) + randY));
				balise.setDefaultDepth((randY - WINDOW_HEIGHT / 2) / 10);

				// Ajoute la balise dans le panel de simulation
				this.niSpace.add(vueBalise);
			}
		} catch (IOException e) {
			// L'erreur peut survenir s'il n'est pas possible de définir une image
			e.printStackTrace();
		}

		// Séparation des vues de l'air et de l'eau
		viewAir = new NiRectangle();
		viewAir.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT / 2);
		viewAir.setBackground(Color.white);
		viewEau = new NiRectangle();
		viewEau.setBounds(0, WINDOW_HEIGHT / 2, WINDOW_WIDTH, WINDOW_HEIGHT / 2);
		viewEau.setBackground(Color.blue);

		// Ajout des représentations des espaces dans le panel de simulation
		this.niSpace.add(viewAir);
		this.niSpace.add(viewEau);

		// Redessine le panel de simulation
		this.niSpace.repaint();
	}

	/**
	 * Lance les étapes de simulation.
	 */
	public void startGraphicAnimation() {
		new GraphicAnimation().start();
	}

	/**
	 * Classe permettant de performer une action à chaque temps donné.
	 * 
	 * Ici, redéssine la simulation toutes les 100ms.
	 */
	class GraphicAnimation implements ActionListener {

		/**
		 * Délai entre chaque action performée.
		 */
		private int graphicAnimationDelay = 100;

		@Override
		public void actionPerformed(ActionEvent e) {
			// Étape de simulation
			Simulation.this.simulationContext.move();

			// Met à jour les vues éléments
			for (Component vueMobile : Simulation.this.niSpace.getComponents()) {
				if (vueMobile != Simulation.this.viewAir && vueMobile != Simulation.this.viewEau) {
					((ViewMobileElement) vueMobile).updateView();
				}
			}
		}

		/**
		 * Lance l'animation.
		 */
		public void start() {
			Timer animation = new Timer(0, this);
			animation.setDelay(this.graphicAnimationDelay);
			animation.start();
		}
	}

}
