package com.concordia.ankhMorPork.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

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

	/*@Test
	public void testSetBankMoney() {
		Integer result= board.setBankMoney(bankMoney);
		
		assertEquals(expected,result);
	}
	*/

	/*	@Test
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
	Integer noOfPlayers=4;
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
