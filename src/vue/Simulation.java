package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import back.elemMobile.Balise;
import back.elemMobile.MoveStrategyHorizontal;
import back.elemMobile.MoveStrategySinusoidale;
import back.elemMobile.MoveStrategyVertical;
import back.elemMobile.Satellite;
import back.elemStatic.Air;
import back.elemStatic.Eau;
import nicellipse.component.NiRectangle;
import nicellipse.component.NiSpace;

public class Simulation {

	private NiSpace niSpace;

	private Air air;

	private Eau eau;

	//private VueAir vueAir;

	//private VueEau vueEau;

	public static void main(String[] args) {
		Simulation simulation = new Simulation();
		simulation.startGraphicAnimation();
	}

	public Simulation() {
		this.niSpace = new NiSpace("Simulation satellites balises", new Dimension(1024, 728));
		this.niSpace.setDoubleBuffered(true);
		this.niSpace.openInWindow();

		List<Satellite> listeSatellites = new ArrayList<Satellite>();
		listeSatellites.add(new Satellite());
		listeSatellites.add(new Satellite());
		listeSatellites.add(new Satellite());
		listeSatellites.add(new Satellite());
		listeSatellites.add(new Satellite());
		List<Balise> listeBalises = new ArrayList<Balise>();
		listeBalises.add(new Balise(new MoveStrategyHorizontal(), 100, 728, 364));
		listeBalises.add(new Balise(new MoveStrategyHorizontal(), 100, 728, 364));
		listeBalises.add(new Balise(new MoveStrategyHorizontal(), 100, 728, 364));
		listeBalises.add(new Balise(new MoveStrategyVertical(), 100, 728, 364));
		listeBalises.add(new Balise(new MoveStrategySinusoidale(), 100, 728, 364));
		this.air = new Air(1024, 0, 364, 0, listeSatellites);
		this.eau = new Eau(1024, 0, 728, 364, listeBalises);

		this.nouveauxEspaces();
	}

	public void nouveauxEspaces() {
//		this.vueAir = new VueAir(this.air);
//		this.vueEau = new VueEau(this.eau, this.niSpace.getHeight());
		NiRectangle vueAir = new NiRectangle();
		vueAir.setBounds(0, 0, 1024, 364);
		vueAir.setBackground(Color.white);
		NiRectangle vueEau = new NiRectangle();
		vueEau.setBounds(0, 364, 1024, 364);
		vueEau.setBackground(Color.blue);

		/**this.niSpace.add(vueAir);
		this.niSpace.add(vueEau);**/
		
		try {
			System.out.println("test");
			for (Satellite satellite : this.air.getSatellites()) {
				VueSatellite vueSatellite = new VueSatellite(satellite);
				int randX = new Random().nextInt(900 - 100) + 100;
				int randY = new Random().nextInt(180 - 40) + 10;
				System.out.println(vueSatellite.getImage().getWidth(null) + randX);
				satellite.setHautDroit(new Point(vueSatellite.getImage().getWidth(null) + randX, 0 + randY));
				satellite.setBasGauche(new Point(0 + randX, vueSatellite.getImage().getHeight(null) + randY));
				this.niSpace.add(vueSatellite);
			}
			for (Balise balise : this.eau.getBalises()) {
				VueBalise vueBalise = new VueBalise(balise);
				int randX = new Random().nextInt(900 - 100) + 100;
				int randY = new Random().nextInt(300 - 40) + 10 + 364;
				balise.setHautDroit(new Point(vueBalise.getImage().getWidth(null) + randX, 0 + randY));
				balise.setBasGauche(new Point(0 + randX, vueBalise.getImage().getHeight(null) + randY));
				this.niSpace.add(vueBalise);
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
		private int graphicAnimationDelay = 100;

		boolean sat = false;

		@Override
		public void actionPerformed(ActionEvent e) {
			// Étape de simulation
			Simulation.this.air.move();
			Simulation.this.eau.move();

//			Component[] vuesAir = Simulation.this.vueAir.getComponents();
//			Component[] vuesEau = Simulation.this.vueEau.getComponents();
//			Component[] views = new Component[vuesAir.length + vuesEau.length];
//			System.arraycopy(vuesAir, 0, views, 0, vuesAir.length);
//			System.arraycopy(vuesEau, 0, views, vuesAir.length, vuesEau.length);

			// Met à jour toutes les vues éléments
			for (Component vueMobile : Simulation.this.niSpace.getComponents()) {
				if (vueMobile instanceof VueElementMobile) {
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
