package com.concordia.ankhMorPork.manager;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerCardTest {

	PlayerCard playerCard=new PlayerCard();
	@Test
	public void testSetIdentifier() {
		Integer identifier=5;
		Integer expected=5;
		playerCard.setIdentifier(identifier);
		assertEquals(expected,playerCard.getIdentifier() );
	}

	@Test
	public void testGetName() {
		 String name="Doctor Hen";
		 playerCard.setName(name);
		 String expected="Doctor Hen";
		 String actual= playerCard.getName();
		 assertEquals(expected,actual);
	}

/**	@Test
	public void testSetName() {
		fail("Not yet implemented");
	}
*/

	@Test
	public void testGetActionItem() {
		String[] actionItem={"Place a Minion","Interrupt"};
		playerCard.setActionItem(actionItem);
		String[] expected= {"Place a Minion","Interrupt"};
		assertArrayEquals(expected, playerCard.getActionItem());
	}

	@Test
	public void testGetDescription() {
		String description="Take $2 from Bank";
		String expected="Take $2 from Bank";
		playerCard.setDescription(description);
		assertEquals(expected, playerCard.getDescription());
	}
	@Test
	public void testSetName() {
		 String name="Doctor Hen";
		 playerCard.setName(name);
		 String expected="Doctor Hen";
		 assertEquals(expected,playerCard.getName());
	}

}
