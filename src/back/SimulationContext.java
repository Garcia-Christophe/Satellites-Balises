package back;

import java.util.ArrayList;
import java.util.List;

import back.mobileElement.Beacon;
import back.mobileElement.Satellite;
import back.movement.MoveStrategyHorizontal;
import back.movement.MoveStrategySinusoidale;
import back.movement.MoveStrategyVertical;
import back.staticElement.Air;
import back.staticElement.Water;

/**
 * Contexte de la simulation.
 * 
 * Permet de définir les satellites et les balises de la simulation.
 */
public class SimulationContext {

	/**
	 * Espace pour les satellites.
	 */
	private Air air;

	/**
	 * Espace pour les balises.
	 */
	private Water water;

	/**
	 * Crée un contexte de simulation.
	 * 
	 * Crée une liste de satellites et une liste de balises, et définit les limites
	 * des espaces Air et Eau.
	 * 
	 * @param windowWidth  - Largeur de la fenêtre de simulation
	 * @param windowHeight - Hauteur de la fenêtre de simulation
	 */
	public SimulationContext(int windowWidth, int windowHeight) {
		// Création des satellites
		List<Satellite> satellitesList = new ArrayList<Satellite>();
		satellitesList.add(new Satellite());
		satellitesList.add(new Satellite());
		satellitesList.add(new Satellite());
		satellitesList.add(new Satellite());
		satellitesList.add(new Satellite());

		// Création des balises
		List<Beacon> beaconsList = new ArrayList<Beacon>();
		beaconsList.add(new Beacon(new MoveStrategyHorizontal(), 100, windowHeight, windowHeight / 2));
		beaconsList.add(new Beacon(new MoveStrategyHorizontal(), 100, windowHeight, windowHeight / 2));
		beaconsList.add(new Beacon(new MoveStrategyHorizontal(), 100, windowHeight, windowHeight / 2));
		beaconsList.add(new Beacon(new MoveStrategyVertical(), 100, windowHeight, windowHeight / 2));
		beaconsList.add(new Beacon(new MoveStrategySinusoidale(), 100, windowHeight, windowHeight / 2));

		// Définition des espaces Air et Eau
		this.air = new Air(windowWidth, 0, windowHeight / 2, 0, satellitesList);
		this.water = new Water(windowWidth, 0, windowHeight, windowHeight / 2, beaconsList);
	}

	public List<Satellite> getSatellites() {
		return this.air.getSatellites();
	}

	public List<Beacon> getBeacons() {
		return this.water.getBeacons();
	}

	public void move() {
		this.air.move();
		this.water.move();
	}

}
