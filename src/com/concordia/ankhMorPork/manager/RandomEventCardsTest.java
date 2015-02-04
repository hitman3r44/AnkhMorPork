package com.concordia.ankhMorPork.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RandomEventCardsTest {
	RandomEventCards abc;
	@Before
	public void setUp() throws Exception {
		abc = new RandomEventCards();
		abc.setName("khurram");
		
		
		
	}

	@Test
	public void testGetIdentifier() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetIdentifier() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetName() {
		//fail("Not yet implemented");
		String expected = "khurram";
		assertEquals(expected, abc.getName());
	}

	@Test
	public void testSetName() {
		fail("Not yet implemented");		
	}

	@Test
	public void testGetDescription() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDescription() {
		fail("Not yet implemented");
	}

}
