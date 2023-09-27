package vue;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;

import nicellipse.component.NiSpace;

public class Simulation {

	private NiSpace space = new NiSpace("Simulation satellites balises", new Dimension(1024, 728));

	private VueAir vueAir;

	private VueEau vueEau;

	public static void main(String[] args) {
		Simulation simulation = new Simulation();
		simulation.startGraphicAnimation();
	}

	public Simulation() {
		this.space.setDoubleBuffered(true);
		this.space.openInWindow();

		this.nouveauxEspaces();
	}

	public void nouveauxEspaces() {
		this.vueAir = new VueAir(0, 0, new Dimension(1024, 364));
		this.vueEau = new VueEau(0, 364, new Dimension(1024, 364));

		this.space.add(this.vueAir);
		this.space.add(this.vueEau);
		this.space.repaint();
	}

	public void startGraphicAnimation() {
		new GraphicAnimation().start();
	}

	class GraphicAnimation implements ActionListener {
		private int graphicAnimationDelay = 1000;

		boolean sat = false;

		@Override
		public void actionPerformed(ActionEvent e) {
//			Component[] views = Simulation.this.space.getComponents();
//			for (Component component : views) {
//				// Met à jour toutes les vues éléments
//				if (component instanceof VueElementMobile) {
//					((VueElementMobile) component).mettreAJourVue();
//				}
//			}

			try {
				Simulation.this.vueAir.removeAll();
				Simulation.this.vueAir.add(sat ? new VueSatellite() : new VueBalise());
				sat = !sat;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Simulation.this.space.repaint();

			// Lancement d'une étape de simulation
			// ContexteDeSimulation contexteDeSimulation = new
			// ContexteDeSimulation(Simulation.this);
//			vueAir.etapeDeSimulation(contexteDeSimulation);
//			vueEau.etapeDeSimulation(contexteDeSimulation);
		}

		public void start() {
			Timer animation = new Timer(0, this);
			animation.setDelay(this.graphicAnimationDelay);
			animation.start();
		}

	}

}
