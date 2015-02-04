package com.concordia.ankhMorPork.manager;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.junit.Before;
import org.junit.Test;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

public class AreaTest {
	Boolean troubleMaker=false;
	Boolean building=false;
	Integer noOfDemon=0;
	Integer noOfTroll=0;
	Area area = new Area(troubleMaker, building, noOfDemon, noOfTroll);
	@Before
	public void setUp() throws Exception {
		
		List<String> colorOfMinion=new ArrayList<String>();
		colorOfMinion.add("Red");
		area.setColorOfMinion(colorOfMinion);
		Integer identifier= 10;
        area.setIdentifier(identifier);
        @SuppressWarnings("unused")
		Boolean troubleMaker= true;
        area.setTroubleMaker(troubleMaker);
        
	}

/*	@Test
	public void testArea() {
		fail("Not yet implemented");
	}
*/
	@Test
	public void testGetIdentifier() {
		Integer expected=10;
		assertEquals(expected, area.getIdentifier());
		
	}

/*	@Test
	public void testSetIdentifier() {
		fail("Not yet implemented");
	}
*/
	@Test
	public void testGetColorOfMinion() {
		List<String> expected=new ArrayList<String>();
		expected.add("Red");
		assertEquals(expected, area.getColorOfMinion());
		
	}

/*	@Test
	public void testSetColorOfMinion() {
		fail("Not yet implemented");
	}
*/
	@Test
	public void testGetTroubleMaker() {
	Boolean expected=true;
	assertEquals(expected, area.getTroubleMaker());
	}

/*	@Test
	public void testSetTroubleMaker() {
		fail("Not yet implemented");
	}
*/
}
