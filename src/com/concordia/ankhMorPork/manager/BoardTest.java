package com.concordia.ankhMorPork.manager;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
		
	Integer bankMoney=23;
	Integer expected=23;
	Board board=new Board();
	@Before
	public void setUp() throws Exception {
 		
	}

	@Test
	public void testGetBankMoney() {

		board.setBankMoney(bankMoney);
 		assertEquals(expected,board.getBankMoney());
		
	}


	@Test
	public void testGetNoOfPlayer() {
	Integer noOfPlayers=4;
	Integer expected= 4;
	board.setNoOfPlayer(noOfPlayers);
	assertEquals(expected, board.getNoOfPlayer());
	
	}
	@Test
	public void testGetPlayerTurn() {
	Integer playerTurn=4;
	Integer expected= 4;
	board.setPlayerTurn(playerTurn);
	assertEquals(expected, board.getPlayerTurn());
	
	}
	

}
