package com.concordia.ankhMorPork.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

public class BoardTest {
	Integer bankMoney=23;
	Integer noOfPlayers=4;

	Board board=new Board();
	@Before
	public void setUp() throws Exception {
 		
	}

	@Test
	public void testGetBankMoney() {
		Integer expected=23;
		board.setBankMoney(bankMoney);
 		assertEquals(expected,board.getBankMoney());
		
	}
/*
	@Test
	public void testSetBankMoney() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPersonalityCardDetails() {
		fail("Not yet implemented");
	}

/*	@Test
	public void testSetPersonalityCardDetails() {
		fail("Not yet implemented");
	}

*/	
	@Test
	public void testGetNoOfPlayer() {
	Integer expected= 4;
	board.setNoOfPlayer(noOfPlayers);
	assertEquals(expected, board.getNoOfPlayer());
	
	}

/*	@Test
	public void testSetNoOfPlayer() {
		fail("Not yet implemented");
	}
*/
}
