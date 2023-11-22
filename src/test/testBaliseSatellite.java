package test;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import back.elemMobile.Balise;
import back.elemMobile.MoveStrategyHorizontal;
import back.elemMobile.MoveStrategySatellite;
import back.elemMobile.MoveStrategySinusoidale;
import back.elemMobile.MoveStrategySurface;
import back.elemMobile.MoveStrategyVertical;
import back.elemMobile.Satellite;
import back.elemStatic.Air;
import back.elemStatic.Eau;

class testBaliseSatellite {
	
	private int windowWidth = 1024;
	
	private int windowHeight = 728 ;
	
	@Test
	void testExistence() {
		List<Satellite> listeSatellites = new ArrayList<Satellite>();
		Air air = new Air(windowWidth, 0, windowHeight/2, 0, listeSatellites);
		assertTrue(air.getSatellites().size() == 0 );
		
		List<Balise> listeBalises = new ArrayList<Balise>();
		Eau eau = new Eau(windowWidth, 0, windowHeight, windowHeight / 2, listeBalises);
		assertTrue(eau.getBalises().size() == 0 );
	}
	
	@Test
	void testIsExistAdd() {
		List<Satellite> listeSatellites = new ArrayList<Satellite>();
		Air air = new Air(windowWidth, 0, windowHeight/2, 0, listeSatellites);
		assertTrue(air.getSatellites().size() == 0 );
		
		List<Balise> listeBalises = new ArrayList<Balise>();
		Eau eau = new Eau(windowWidth, 0, windowHeight, windowHeight / 2, listeBalises);
		assertTrue(eau.getBalises().size() == 0 );
		
		//--NEXT--//
		listeSatellites.add(new Satellite());
		listeSatellites.add(new Satellite());
		listeSatellites.add(new Satellite());
		listeSatellites.add(new Satellite());
		listeSatellites.add(new Satellite());
		air.setSatellites(listeSatellites);
		assertTrue(air.getSatellites().size() == 5 );
		
		listeBalises.add(new Balise(new MoveStrategyHorizontal(), 100, windowHeight, windowHeight / 2));
		listeBalises.add(new Balise(new MoveStrategyHorizontal(), 100, windowHeight, windowHeight / 2));
		listeBalises.add(new Balise(new MoveStrategyHorizontal(), 100, windowHeight, windowHeight / 2));
		listeBalises.add(new Balise(new MoveStrategyVertical(), 100, windowHeight, windowHeight / 2));
		listeBalises.add(new Balise(new MoveStrategySinusoidale(), 100, windowHeight, windowHeight / 2));
		eau.setBalises(listeBalises);
		assertTrue( eau.getBalises().size() == 5);
	}
	
	private Air air;
	
	private Eau eau;
	
	@BeforeEach
	void Context() {
		List<Satellite> listeSatellites = new ArrayList<Satellite>();
		this.air = new Air(windowWidth, 0, windowHeight/2, 0, listeSatellites);
		
		List<Balise> listeBalises = new ArrayList<Balise>();
		this.eau = new Eau(windowWidth, 0, windowHeight, windowHeight / 2, listeBalises);
		
		listeSatellites.add(new Satellite());
		listeSatellites.add(new Satellite());
		listeSatellites.add(new Satellite());
		listeSatellites.add(new Satellite());
		listeSatellites.add(new Satellite());
		this.air.setSatellites(listeSatellites);
		
		listeBalises.add(new Balise(new MoveStrategyHorizontal(), 100, windowHeight, windowHeight / 2));
		listeBalises.add(new Balise(new MoveStrategyHorizontal(), 100, windowHeight, windowHeight / 2));
		listeBalises.add(new Balise(new MoveStrategyHorizontal(), 100, windowHeight, windowHeight / 2));
		listeBalises.add(new Balise(new MoveStrategyVertical(), 100, windowHeight, windowHeight / 2));
		listeBalises.add(new Balise(new MoveStrategySinusoidale(), 100, windowHeight, windowHeight / 2));
		this.eau.setBalises(listeBalises);
	}
	
	@Test
	void testModifySatellites() {
		List<Satellite> listeSatellites2 = new ArrayList<Satellite>();
		listeSatellites2.add(new Satellite());
		listeSatellites2.add(new Satellite());
		listeSatellites2.add(new Satellite());
		this.air.setSatellites(listeSatellites2);
		assertTrue(this.air.getSatellites().size() == 3 );
		assertTrue(this.eau.getBalises().size() == 5 );
	}
	
	
	@Test
	void testModifyBalises() {
		List<Balise> listeBalises2 = new ArrayList<Balise>();
		listeBalises2.add(new Balise(new MoveStrategyHorizontal(), 100, windowHeight, windowHeight / 2));
		listeBalises2.add(new Balise(new MoveStrategyHorizontal(), 100, windowHeight, windowHeight / 2));
		this.eau.setBalises(listeBalises2);
		assertTrue(this.eau.getBalises().size() == 2 );
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
		List<Balise> listeBalise = this.eau.getBalises();
		Balise balise = listeBalise.get(0);
		assertTrue(balise.getDefaultStrategy() instanceof MoveStrategyHorizontal);
		
		balise.setMoveStrategy(new MoveStrategySinusoidale());
		listeBalise.set(0, balise);
		this.eau.setBalises(listeBalise);
		assertTrue(this.eau.getBalises().get(0).getMoveStrategy() instanceof MoveStrategySinusoidale);
	}
	
	@Test
	void testSynchro() {
		// Test balise est pleine
		Balise balise = this.eau.getBalises().get(0);
		balise.setCapacite(1);
		balise.stockerDonnee();    //VA_stockData?
		this.eau.addBaliseToBalisesPleines(balise);
		assertTrue(this.eau.getBalises().get(0).estPleine());
		assertTrue(this.eau.getBalises().get(0).getMoveStrategy() instanceof MoveStrategySurface);	
		boolean isExist = false;
		Set<Balise> balisesPleines = this.eau.getBalisesPleines();
		for (Balise balise2 : balisesPleines) {
			if(balise2.equals(balise))
				isExist=true;
		}
		assertTrue(isExist);
		// Test synchro
	}
}
