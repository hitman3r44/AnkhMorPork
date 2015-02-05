package com.concordia.ankhMorPork.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	
	int playerID= 3;
	String playerName="Obama";
	String color;
	Player player= new Player(playerID, playerName, color);
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetPlayerId() {
		player.setPlayerId(playerID);
		int expected=3;
		assertEquals(expected, player.getPlayerId());
	}
/*
	@Test
	public void testSetPlayerId() {
		fail("Not yet implemented");
	}
*/
	@Test
	public void testGetName() {
	player.setName(playerName);
	String expected="Obama";
	assertEquals(expected, player.getName());
	}
/*
	@Test
	public void testSetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetColor() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetColor() {
		fail("Not yet implemented");
	}
*/
}