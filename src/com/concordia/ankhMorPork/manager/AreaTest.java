package com.concordia.ankhMorPork.manager;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AreaTest {
	Boolean troubleMaker=false;
	Boolean building=false;
	Integer noOfDemon=0;
	Integer noOfTroll=0;
	Area area = new Area(troubleMaker, building, noOfDemon, noOfTroll);
	@Before
	public void setUp() throws Exception {
		
		Integer identifier= 10;
        area.setIdentifier(identifier);
        @SuppressWarnings("unused")
		Boolean troubleMaker= true;
        area.setTroubleMaker(troubleMaker);
        
	}

	@Test
	public void testGetIdentifier() {
		Integer expected=10;
		assertEquals(expected, area.getIdentifier());
		
	}
	@Test
	public void testGetColorOfMinion() {
		List<String> colorOfMinion=new ArrayList<String>();
		colorOfMinion.add("Red");
		area.setColorOfMinion(colorOfMinion);
		
		List<String> expected=new ArrayList<String>();
		expected.add("Red");
		List<String> result=new ArrayList<String>();
		result=area.getColorOfMinion();
		assertEquals(expected,result );
		
	}

	@Test
	public void testSetColorOfMinion() {
	    List<String> colorOfMinion=new ArrayList<String>();
		colorOfMinion.add("green");
		List<String> expected=new ArrayList<String>();
		expected.add("green");
		
		area.setColorOfMinion(colorOfMinion);
		assertEquals(expected, area.getColorOfMinion());
		
	}

	@Test
	public void testGetTroubleMaker() {
	Boolean expected=true;
	assertEquals(expected, area.getTroubleMaker());
	}

	@Test
	public void testSetTroubleMaker() {
		troubleMaker=false;
		area.setTroubleMaker(troubleMaker);
		Boolean expected=false;
		assertEquals(expected,area.getTroubleMaker());
	
	}

}
