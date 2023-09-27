package vue;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import back.elemMobile.Balise;
import back.elemMobile.Coordonnee;
import back.elemMobile.ElementMobile.Direction;
import back.elemMobile.Satellite;
import back.elemStatic.Air;
import back.elemStatic.Eau;
import nicellipse.component.NiSpace;

public class Simulation {

	private NiSpace niSpace;

	private Air air;

	private Eau eau;

	private VueAir vueAir;

	private VueEau vueEau;

	public static void main(String[] args) {
		Simulation simulation = new Simulation();
		simulation.startGraphicAnimation();
	}

	public Simulation() {
		this.niSpace = new NiSpace("Simulation satellites balises", new Dimension(1024, 728));
		this.niSpace.setDoubleBuffered(true);
		this.niSpace.openInWindow();

		List<Satellite> listeSatellites = new ArrayList<Satellite>();
		listeSatellites.add(new Satellite(new Coordonnee(100, 50), Direction.HORIZONTAL));
		listeSatellites.add(new Satellite(new Coordonnee(650, 100), Direction.HORIZONTAL));
		listeSatellites.add(new Satellite(new Coordonnee(330, 150), Direction.HORIZONTAL));
		listeSatellites.add(new Satellite(new Coordonnee(250, 200), Direction.HORIZONTAL));
		listeSatellites.add(new Satellite(new Coordonnee(850, 250), Direction.HORIZONTAL));
		List<Balise> listeBalises = new ArrayList<Balise>();
		listeBalises.add(new Balise(new Coordonnee(150, 50), Direction.HORIZONTAL, 100, 324, 728));
		listeBalises.add(new Balise(new Coordonnee(600, 100), Direction.VERTICAL, 100, 324, 728));
		listeBalises.add(new Balise(new Coordonnee(280, 150), Direction.HORIZONTAL, 100, 324, 728));
		listeBalises.add(new Balise(new Coordonnee(750, 200), Direction.VERTICAL, 100, 324, 728));
		listeBalises.add(new Balise(new Coordonnee(920, 250), Direction.HORIZONTAL, 100, 324, 728));
		this.air = new Air(1024, 0, 364, 0, listeSatellites);
		this.eau = new Eau(1024, 0, 728, 364, listeBalises);

		this.nouveauxEspaces();
	}

	public void nouveauxEspaces() {
		this.vueAir = new VueAir(this.air);
		this.vueEau = new VueEau(this.eau);

		this.niSpace.add(this.vueAir);
		this.niSpace.add(this.vueEau);

		try {
			for (Satellite satellite : this.air.getSatellites()) {
				this.vueAir.add(new VueSatellite(satellite));
			}
			for (Balise balise : this.eau.getBalises()) {
				this.vueEau.add(new VueBalise(balise));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.niSpace.repaint();
	}

	public void startGraphicAnimation() {
		new GraphicAnimation().start();
	}

	class GraphicAnimation implements ActionListener {
		private int graphicAnimationDelay = 1000;

		boolean sat = false;

		@Override
		public void actionPerformed(ActionEvent e) {
			// Étape de simulation
			Simulation.this.air.move();
			Simulation.this.eau.move();

			Component[] vuesAir = Simulation.this.vueAir.getComponents();
			Component[] vuesEau = Simulation.this.vueEau.getComponents();
			Component[] views = new Component[vuesAir.length + vuesEau.length];
			System.arraycopy(vuesAir, 0, views, 0, vuesAir.length);
			System.arraycopy(vuesEau, 0, views, vuesAir.length, vuesEau.length);

			// Met à jour toutes les vues éléments
			for (Component vueMobile : views) {
				((VueElementMobile) vueMobile).mettreAJourVue();
			}
		}

		public void start() {
			Timer animation = new Timer(0, this);
			animation.setDelay(this.graphicAnimationDelay);
			animation.start();
		}

	}

}
