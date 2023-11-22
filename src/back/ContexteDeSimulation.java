package back;

import java.util.ArrayList;
import java.util.List;

import back.elemMobile.Balise;
import back.elemMobile.MoveStrategyHorizontal;
import back.elemMobile.MoveStrategySinusoidale;
import back.elemMobile.MoveStrategyVertical;
import back.elemMobile.Satellite;
import back.elemStatic.Air;
import back.elemStatic.Eau;

public class ContexteDeSimulation {

	private Air air;

	private Eau eau;

	public ContexteDeSimulation(int windowWidth, int windowHeight) {
		// Création des satellites
		List<Satellite> listeSatellites = new ArrayList<Satellite>();
		listeSatellites.add(new Satellite());
		listeSatellites.add(new Satellite());
		listeSatellites.add(new Satellite());
		listeSatellites.add(new Satellite());
		listeSatellites.add(new Satellite());

		// Création des balises
		List<Balise> listeBalises = new ArrayList<Balise>();
		listeBalises.add(new Balise(new MoveStrategyHorizontal(), 100, windowHeight, windowHeight / 2));
		listeBalises.add(new Balise(new MoveStrategyHorizontal(), 100, windowHeight, windowHeight / 2));
		listeBalises.add(new Balise(new MoveStrategyHorizontal(), 100, windowHeight, windowHeight / 2));
		listeBalises.add(new Balise(new MoveStrategyVertical(), 100, windowHeight, windowHeight / 2));
		listeBalises.add(new Balise(new MoveStrategySinusoidale(), 100, windowHeight, windowHeight / 2));

		// Définition des espaces Air et Eau
		this.air = new Air(windowWidth, 0, windowHeight / 2, 0, listeSatellites);
		this.eau = new Eau(windowWidth, 0, windowHeight, windowHeight / 2, listeBalises);
	}

	public List<Satellite> getSatellites() {
		return this.air.getSatellites();
	}

	public List<Balise> getBalises() {
		return this.eau.getBalises();
	}

	public void move() {
		this.air.move();
		this.eau.move();
	}

}
