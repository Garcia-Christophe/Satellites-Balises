package test;

import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import back.event.AvailableForSynchronisation;
import back.event.SynchronisationEnded;
import back.event.SynchronisationInProgress;
import back.mobileElement.Beacon;
import back.mobileElement.Satellite;
import back.movement.MoveStrategyHorizontal;
import back.movement.MoveStrategySatellite;
import back.movement.MoveStrategySinusoidale;
import back.movement.MoveStrategySurface;
import back.movement.MoveStrategyVertical;
import back.staticElement.Air;
import back.staticElement.Space;
import back.staticElement.Water;

public class testBeaconSatellite {
	
	private int windowWidth = 1024;
	
	private int windowHeight = 728 ;
	
	@Test
	void testExistence() {
		List<Satellite> listSatellites = new ArrayList<Satellite>();
		Air air = new Air(windowWidth, 0, windowHeight/2, 0, listSatellites);
		assertTrue(air.getSatellites().size() == 0 );
		
		List<Beacon> listBeacons = new ArrayList<Beacon>();
		Water water = new Water(windowWidth, 0, windowHeight, windowHeight / 2, listBeacons);
		assertTrue(water.getBeacons().size() == 0 );
	}
	
	
	@Test
	void testIsExistAdd() {
		List<Satellite> listSatellites = new ArrayList<Satellite>();
		Air air = new Air(windowWidth, 0, windowHeight/2, 0, listSatellites);
		assertTrue(air.getSatellites().size() == 0 );
		
		List<Beacon> listBeacons = new ArrayList<Beacon>();
		Water water = new Water(windowWidth, 0, windowHeight, windowHeight / 2, listBeacons);
		assertTrue(water.getBeacons().size() == 0 );
		
		//--NEXT--//
		listSatellites.add(new Satellite());
		listSatellites.add(new Satellite());
		listSatellites.add(new Satellite());
		listSatellites.add(new Satellite());
		listSatellites.add(new Satellite());
		air.setSatellites(listSatellites);
		assertTrue(air.getSatellites().size() == 5 );
		
		listBeacons.add(new Beacon(new MoveStrategyHorizontal(), 100, windowHeight, windowHeight / 2));
		listBeacons.add(new Beacon(new MoveStrategyHorizontal(), 100, windowHeight, windowHeight / 2));
		listBeacons.add(new Beacon(new MoveStrategyHorizontal(), 100, windowHeight, windowHeight / 2));
		listBeacons.add(new Beacon(new MoveStrategyVertical(), 100, windowHeight, windowHeight / 2));
		listBeacons.add(new Beacon(new MoveStrategySinusoidale(), 100, windowHeight, windowHeight / 2));
		water.setBeacons(listBeacons);
		assertTrue(water.getBeacons().size() == 5);
	}
	
	private Air air;
	
	private Water water;
	
	@BeforeEach
	void Context() {
		List<Satellite> listSatellites = new ArrayList<Satellite>();
		this.air = new Air(windowWidth, 0, windowHeight/2, 0, listSatellites);
		
		List<Beacon> listBeacons = new ArrayList<Beacon>();
		this.water = new Water(windowWidth, 0, windowHeight, windowHeight / 2, listBeacons);
		
		listSatellites.add(new Satellite());
		listSatellites.add(new Satellite());
		listSatellites.add(new Satellite());
		listSatellites.add(new Satellite());
		listSatellites.add(new Satellite());
		this.air.setSatellites(listSatellites);
		
		listBeacons.add(new Beacon(new MoveStrategyHorizontal(), 100, windowHeight, windowHeight / 2));
		listBeacons.add(new Beacon(new MoveStrategyHorizontal(), 100, windowHeight, windowHeight / 2));
		listBeacons.add(new Beacon(new MoveStrategyHorizontal(), 100, windowHeight, windowHeight / 2));
		listBeacons.add(new Beacon(new MoveStrategyVertical(), 100, windowHeight, windowHeight / 2));
		listBeacons.add(new Beacon(new MoveStrategySinusoidale(), 100, windowHeight, windowHeight / 2));
		this.water.setBeacons(listBeacons);
	}
	
	@Test
	void testModifySatellites() {
		List<Satellite> listSatellites2 = new ArrayList<Satellite>();
		listSatellites2.add(new Satellite());
		listSatellites2.add(new Satellite());
		listSatellites2.add(new Satellite());
		this.air.setSatellites(listSatellites2);
		assertTrue(this.air.getSatellites().size() == 3 );
		assertTrue(this.water.getBeacons().size() == 5 );
	}
	
	
	@Test
	void testModifyBalises() {
		List<Beacon> listBeacons2 = new ArrayList<Beacon>();
		listBeacons2.add(new Beacon(new MoveStrategyHorizontal(), 100, windowHeight, windowHeight / 2));
		listBeacons2.add(new Beacon(new MoveStrategyHorizontal(), 100, windowHeight, windowHeight / 2));
		this.water.setBeacons(listBeacons2);
		assertTrue(this.water.getBeacons().size() == 2 );
		assertTrue(this.air.getSatellites().size() == 5 );
	}
	
	@Test
	void testModifyStrategySatellites() {
		List<Satellite> listeSatellites = new ArrayList<Satellite>();
		listeSatellites.add(new Satellite());
		Satellite satellite = listeSatellites.get(0);
		satellite.setMoveStrategy(new MoveStrategySatellite());
		listeSatellites.set(0, satellite);
		assertTrue(this.air.getSatellites().get(0).getMoveStrategy() instanceof MoveStrategySatellite);
	}
	
	@Test
	void testModifyStrategyBalises() {
		List<Beacon> listBeacons = this.water.getBeacons();
		Beacon beacon = listBeacons.get(0);
		assertTrue(beacon.getDefaultStrategy() instanceof MoveStrategyHorizontal);
		
		beacon.setMoveStrategy(new MoveStrategySinusoidale());
		listBeacons.set(0, beacon);
		this.water.setBeacons(listBeacons);
		assertTrue(this.water.getBeacons().get(0).getMoveStrategy() instanceof MoveStrategySinusoidale);
	}
	
	@Test
	void testSynchronisation() {
		// Tests balise est pleine
		Beacon beacon = this.water.getBeacons().get(0);
		beacon.setCapacity(1);
		beacon.storeData();
		Space.fullBeacons.add(beacon);
		assertTrue(this.water.getBeacons().get(0).isFull());
		assertTrue(this.water.getBeacons().get(0).getMoveStrategy() instanceof MoveStrategySurface);	
		// Test existence de la balise = balise pleine
		Set<Beacon> fullBeacons = this.water.getfullBeacons();
		assertTrue(fullBeacons.contains(beacon));
		// Reconstition du parcours d'un event
		// --balise.receive()
		AvailableForSynchronisation availableForSynchronisation = new AvailableForSynchronisation(beacon);
		Point position1Beacon = beacon.getBottomLeft();
		Point position2Beacon = beacon.getTopRight();
		List<Satellite> listSatellites = new ArrayList<Satellite>();
		listSatellites = this.air.getSatellites();
		Satellite satellite = listSatellites.get(0);
		satellite.setBottomLeft(position1Beacon);
		satellite.setTopRight(position2Beacon);
		assertTrue(availableForSynchronisation.toString().equals("Disponible pour synchronisation") && satellite.isInRangeOf(beacon)==true && !beacon.isInSynchro());
		// ---ok, en synchro
		beacon.getEventHandler().registerListener(SynchronisationInProgress.class, satellite);
		SynchronisationInProgress synchronisationInProgressEvent = new SynchronisationInProgress(beacon);
		beacon.getEventHandler().send(synchronisationInProgressEvent);
		// --satellite.receive()
		assertTrue(synchronisationInProgressEvent.toString().equals("Synchronisation en cours"));
		//---ok, en synchro
		satellite.getEventHandler().registerListener(SynchronisationEnded.class, beacon);
		SynchronisationEnded synchronisationEnded = new SynchronisationEnded(beacon);
		beacon.getEventHandler().unregisterListener(SynchronisationInProgress.class, this);
		// --balise.receive()
		assertTrue(synchronisationEnded.toString().equals("Synchronisation terminée"));
		Space.fullBeacons.remove(beacon);
		// Test existence = balise non pleines
		assertTrue(!Space.getfullBeacons().contains(beacon));
	}
}
