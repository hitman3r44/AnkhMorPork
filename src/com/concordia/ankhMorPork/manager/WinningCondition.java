package com.concordia.ankhMorPork.manager;

import java.util.ArrayList;
import java.util.List;

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
	 * @param board 
	 * @return
	 */
	public boolean lordVetinari(Board board){
		List<Integer> minionLocatedonBoard=new ArrayList<Integer>();
		boolean winStatus=false;
		String currentPlayerColor=board.getPlayerList().get(board.getPlayerTurn()-1).getColor();
		for(int index=0;index<12;index++){
				if(board.getArea().get(index).colorOfMinion.contains(currentPlayerColor) && board.getArea().get(index).getNoOfDemon()==0){
					minionLocatedonBoard.add(index+1);
				}
		}
		if(minionLocatedonBoard.size()==9){
			winStatus = true;
		}
		return winStatus;
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
	 * @param board 
	 *  
	 * @return
	 */
	public boolean lordSelachii(Board board){
		return false;
	}
	
	public boolean lordRust(Board board){
		return false;
	}
	
	public boolean lordDeWorde(Board board){
		return false;
	}
	
	/**
	 * # If the is 8 trouble makers in the game he win
	 * @param board 
	 * @return
	 */
	
	//Dragon King of Arms
	public boolean dragonKingOfArms(Board board){
		Integer troublemarker=0;
		boolean winStatus=false;
		for(int index=0;index<12;index++){
				if(board.getArea().get(index).getTroubleMaker()){
					troublemarker++;
				}
		}
		if(troublemarker>=8){
			winStatus = true;
		}
		return winStatus;
	}
	
	/**
	 * # Worth more then $50
	 * # If take loan it will cut -$12
	 * @param board 
	 * @return
	 */
	
	//Chrysoprase
	public boolean chrysoprase(Board board){
		Integer buidingCost=0;
		Integer totalCost=0;
		boolean winStatus=false;
		Player currentPlayer=board.getPlayerList().get(board.getPlayerTurn()-1);
		for(int index=0;index<12;index++){
				if(board.getArea().get(index).getBuildingHolderName().equalsIgnoreCase(currentPlayer.getName())){
					buidingCost+=BoardManager.cityAreaCardList.get(index-1).getCost();
				}
		}
		totalCost=buidingCost+currentPlayer.getPlayerMoney();
		if(totalCost>=50){
			winStatus = true;
		}
		return winStatus;
	}
	
	/**
	 * # If nobody win the games before finishing the playing cards
	 * @param board 
	 * @return
	 */
	//Commander Vimes
	public boolean commanderVimes(Board board){
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
	boolean generalRules(Player player) {
		pointCalculator(player);
		return false;
	}
	
	
	//Other Methods
	boolean isAreaControlled(){
		return false;
	}
	
	boolean isWorthMoreThen50Doller(){
		return false;
	}
	
	boolean isTheAllPalyingCardFinished(){
		return false;
	}
	
	Integer pointCalculator(Player player){
		return 0;
	}
	

}
