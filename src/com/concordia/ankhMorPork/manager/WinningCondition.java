package com.concordia.ankhMorPork.manager;

/**
 * @author elDiablo
 * Mar 31, 2015
 * 9:40:40 PM
 * 2015
 * @email: sumitsarkarbd@gmail.com
 */
public class WinningCondition {
	
	/**
	 * 
	 * 	Game's General Rules for Winning
	 *  # According to personality card
	 *  # Commander Vines win if nobody declare themself winner
	 *  # For the last condition most point gained player win
	 */
	
	// Lord Vetinari
	
	/**
	 * # For 2 player he need at least minions in 11 different areas
	 * # For 3 players he need at least minions in 10 different areas
	 * # For 4 players he need at least minions in 9 different areas
	 * 
	 * # Those areas must be free of demons
	 * @return
	 */
	boolean lordVetinari(){
		return false;
	}
	
	//All those have a same Winning condition
	
	/**
	 * # For 2 player he need control at least 7 areas 
	 * # For 3 player he need control at least 5 areas
	 * # For 4 player he need control at least 4 areas
	 * 
	 * # Control Means  = Have most playing pieces (MInions and building)
	 * # You would also have to have more pieces there than the total number of trolls in the area
	 * # If demons in there you can't control the area
	 * # Trouble Maker doesn't effect on controlling
	 * 
	 * # Summary: Playing pieces have to more then the trolls and demon free
	 *  
	 * @return
	 */
	boolean lordSelachii(){
		return false;
	}
	
	boolean lordRust(){
		return false;
	}
	
	boolean lordDeWorde(){
		return false;
	}
	
	/**
	 * # If the is 8 trouble makers in the game he win
	 * @return
	 */
	
	//Dragon King of Arms
	boolean dragonKingOfArms(){
		return false;
	}
	
	/**
	 * # Worth more then $50
	 * # If take loan it will cut -$12
	 * @return
	 */
	
	//Chrysoprase
	boolean chrysoprase(){
		return false;
	}
	
	/**
	 * # If nobody win the games before finishing the playing cards
	 * @return
	 */
	//Commander Vimes
	boolean commanderVimes(){
		return false;
	}
	
	/**
	 * # The player have the most points he win the game
	 * # Points Calculation:
	 * 		# per minions = 5 points
	 * 		# per buildings = Monetary Cost take as points
	 * 		# per dollar = 1 points
	 * 		# If dent card and bank card  = -15 points
	 * 		# For tie Highest Monetary value wins
	 * @return
	 */
	//General Rules
	boolean generalRules() {
		return false;
	}
	

}
