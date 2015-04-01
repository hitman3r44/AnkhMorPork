package com.concordia.ankhMorPork.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import com.concordia.ankhMorPork.common.Common;
import com.concordia.ankhMorPork.common.Global;

public class ActionItemImpl {
	private Scanner userInputForLoadOrNewGameScanner = new Scanner(System.in);
	/**
	 * The method placeTheMinion manipulates the Board object to place a minion on the board
	 * @param board : Contains current status of board
	 * @return board: contains updated status of board after placing minion
	 */
	public Board placeTheMinion(Board board) {
		String input=null;
		Scanner sc = new Scanner(System.in);
		String currentPlayerColor=board.getPlayerList().get(board.getPlayerTurn()-1).getColor();
		Integer noOfMinions=board.getPlayerList().get(board.getPlayerTurn()-1).getNoOfMinions();
		List<Integer> minionLocatedonBoard=new ArrayList<Integer>();
		for(int index=0;index<12;index++){
		if(board.getArea().get(index).colorOfMinion.contains(currentPlayerColor)){
			minionLocatedonBoard.add(index+1);
		}
		}
		 Set<Integer> adjacentAreaSet = new TreeSet<Integer>(minionLocatedonBoard);
		 System.out.println("\nYour Minions ON Board are located at the following Area :\n");
		 System.out.println("\t\tAreaNo.\t\t\tAreaName");
		for(int i=0;i<minionLocatedonBoard.size();i++){
			System.out.println("\t\t"+minionLocatedonBoard.get(i)+"\t\t\t"+BoardManager.cityAreaCardList.get(minionLocatedonBoard.get(i)-1).getName());
			List<Integer> temporaryList=BoardManager.AdjacentAreaMap.get(minionLocatedonBoard.get(i));
			adjacentAreaSet.addAll(temporaryList);
		}
		 System.out.println("\nYou are allowed to place the minion in the following Area :\n");
		 System.out.println("\t\tAreaNo.\t\t\tAreaName");
		 if(adjacentAreaSet.size()!=0){
				 for (Integer integer : adjacentAreaSet) {
					 System.out.println("\t\t"+integer+"\t\t\t"+BoardManager.cityAreaCardList.get(integer-1).getName());
				}
		 }else{//when no minions on board then player can place anywhere
			 	  for(int k=0;k<12;k++){
			 		 System.out.println("\t\t"+(k+1)+"\t\t\t"+BoardManager.cityAreaCardList.get(k).getName());
			 	  }
		 }
		 System.out.println("\nEnter AreaNo. : \n");	
		 try{
		 input = sc.nextLine();
			if(adjacentAreaSet.contains(Integer.parseInt(input))){
			
				System.out.println("Before Updating : "+board.getArea().get(Integer.parseInt(input)-1).getColorOfMinion());
				board.UpdateAreaDetails(board.getArea().get(Integer.parseInt(input)-1),currentPlayerColor);
				System.out.println("After Updating : "+board.getArea().get(Integer.parseInt(input)-1).getColorOfMinion());
				board.getPlayerList().get(board.getPlayerTurn()-1).setNoOfMinions(noOfMinions - 1);
			}
			else{
				System.out.println("Invalid input !");
				board=placeTheMinion(board);
			}
		 }catch(NumberFormatException e){
			 System.out.println("Invalid input ! Give Number as Input !");
				board=placeTheMinion(board);
		 }
		return board;
		
	}
	
	/**
	 * The method placeTheBuilding manipulates the Board object to place a building on the board
	 * @param board : Contains current status of board
	 * @return board: contains updated status of board after placing a building
	 */
	public Board placeTheBuilding(Board board) {
		String input=null;
		Scanner sc = new Scanner(System.in);
		Player player=board.getPlayerList().get(board.getPlayerTurn()-1);
		String currentPlayerColor=player.getColor();
		Integer noOfBuilding=player.getNoOfBuilding();
		String playerName = player.getName();
		Integer playerMoney=player.getPlayerMoney();
		List<Integer> buildingAllowedonBoard=new ArrayList<Integer>();
		for(int index=0;index<12;index++){
		if(board.getArea().get(index).colorOfMinion.contains(currentPlayerColor) && (!board.getArea().get(index).getTroubleMaker()) && (!board.getArea().get(index).getBuilding())){
			buildingAllowedonBoard.add(index+1);
		}
		}
		if(buildingAllowedonBoard.size()==0 && noOfBuilding==0){//Already six building on the board
			System.out.println("\nRemove any one of your Building and then place anywhere else\n");
		}else{
			System.out.println("\nYou are allowed to place the minion in the following Area :\n");
			System.out.println("\t\tAreaNo.\t\t\tCost\t\t\tAreaName");
			for(int i=0;i<buildingAllowedonBoard.size();i++){
				System.out.println("\t\t"+buildingAllowedonBoard.get(i)+"\t\t\t"+BoardManager.cityAreaCardList.get(buildingAllowedonBoard.get(i)-1).getCost()+"\t\t\t"+BoardManager.cityAreaCardList.get(buildingAllowedonBoard.get(i)-1).getName());
			}
		}
		if(buildingAllowedonBoard.size()!=0){
		System.out.println("\nEnter Area No. : \n");	
		 try{
		 input = sc.nextLine();
		 Integer costOfBuilding=BoardManager.cityAreaCardList.get(Integer.parseInt(input)-1).getCost();
		 List<Integer> cityAreaCardofPlayer=player.getCityAreaCard();
			if(playerMoney >= costOfBuilding){
				System.out.println("Before Updating : "+board.getArea().get(Integer.parseInt(input)-1).getBuilding());
				board.UpdateAreaDetailsWithBuilding(playerName,board.getArea().get(Integer.parseInt(input)-1));
				System.out.println("After Updating : "+board.getArea().get(Integer.parseInt(input)-1).getBuilding());
				player.setNoOfBuilding(noOfBuilding - 1);
				player.setPlayerMoney(playerMoney - costOfBuilding);
				cityAreaCardofPlayer.add(Integer.parseInt(input));
				player.setCityAreaCard(cityAreaCardofPlayer);
			}
			else{
				System.out.println("You dont have enough Money to afford !");
			}
		 }catch(NumberFormatException e){
			 System.out.println("Invalid input ! Give Number as Input !");
				board=placeTheBuilding(board);
		 }
		}else{
			System.out.println("You dont have Minion on board for building.");
		}
		return board;
		
	}
	/**
	 * The method assassination manipulates the Board object to remove a minion on the board
	 * @param board : Contains current status of board
	 * @return board: contains updated status of board after removing a minion
	 */
	public Board assassination(Board board) {
		String input=null,color=null;
		Scanner sc = new Scanner(System.in);
		Player player =board.getPlayerList().get(board.getPlayerTurn()-1);
		String colorOfPlayer=board.getPlayerList().get(board.getPlayerTurn()-1).getColor();
		List<Integer> demononBoard=new ArrayList<Integer>();
		List<Integer> trollonBoard=new ArrayList<Integer>();
		List<Integer> miniononBoard=new ArrayList<Integer>();
		for(int index=0;index<12;index++){
		if(board.getArea().get(index).getNoOfDemon()!=0 && (board.getArea().get(index).getTroubleMaker())){
			demononBoard.add(index+1);
		}
		if(board.getArea().get(index).getNoOfTroll()!=0 && (board.getArea().get(index).getTroubleMaker())){
			trollonBoard.add(index+1);
		}
		if(board.getArea().get(index).getColorOfMinion().size()!=0 && (board.getArea().get(index).getTroubleMaker())){
			miniononBoard.add(index+1);
		}
		}
		System.out.println("\nYou can remove the following pieces : \n\n\t\t1.Demon\n\t\t2.Troll\n\t\t3.Minion");
		System.out.println("\nEnter your Choice : \n");	
		 try{
		 input = sc.nextLine();
		 if(Integer.parseInt(input)==1){
			 if(demononBoard.size()==0){
				 System.out.println("No Area Availabe to remove demon !");
			 }else{
			 System.out.println("\nYou are allowed to Remove the Demon in the following Area :\n");
			 System.out.println("\t\tAreaNo.\t\t\tNoOfDemon\t\t\tAreaName");
				for(int i=0;i<demononBoard.size();i++){
					System.out.println("\t\t"+demononBoard.get(i)+"\t\t\t"+board.getArea().get(demononBoard.get(i)-1).getNoOfDemon()+"\t\t\t"+BoardManager.cityAreaCardList.get(demononBoard.get(i)-1).getName());
				}
				System.out.println("\nEnter AreaNo. : \n");
				input = sc.nextLine();
				board.RemoveDemon(board.getArea().get(Integer.parseInt(input)));
			 }
		 }
		 else if(Integer.parseInt(input)==2){
			 if(trollonBoard.size()==0){
				 System.out.println("No Area Availabe to remove Troll !");
			 }else{
			 System.out.println("\nYou are allowed to Remove the Troll in the following Area :\n");
			 System.out.println("\t\tAreaNo.\t\t\tNoOfTroll\t\t\tAreaName");
				for(int i=0;i<trollonBoard.size();i++){
					System.out.println("\t\t"+trollonBoard.get(i)+"\t\t\t"+board.getArea().get(trollonBoard.get(i)-1).getNoOfTroll()+"\t\t\t"+BoardManager.cityAreaCardList.get(trollonBoard.get(i)-1).getName());
				}
				System.out.println("\nEnter AreaNo. : \n");
				input = sc.nextLine();
				board.RemoveTroll(board.getArea().get(Integer.parseInt(input)));
			 }
		 }
		 else if(Integer.parseInt(input)==3){
			 System.out.println("\nYou are allowed to Remove the Minion in the following Area(Don't remove your's) :\n");
			 System.out.println("\t\tAreaNo.\t\t\tColorOfMinion\t\t\tAreaName");
				for(int i=0;i<miniononBoard.size();i++){
					System.out.println("\t\t"+miniononBoard.get(i)+"\t\t\t"+board.getArea().get(miniononBoard.get(i)-1).getColorOfMinion()+"\t\t\t"+BoardManager.cityAreaCardList.get(miniononBoard.get(i)-1).getName());
				}
				System.out.println("\nEnter AreaNo. : \n");
				input = sc.nextLine();
				System.out.println("\nEnter the Color ID to be removed : \n");
				List<String> colorList=board.getArea().get(Integer.parseInt(input)-1).getColorOfMinion();
				int i=0;
				for (String string : colorList) {
					i++;
					if(!(string.equals(colorOfPlayer))){
					System.out.println("\t\t"+i+"."+string);
					
					}
				}
				color = sc.nextLine();
				board.RemoveMinion(board.getArea().get(Integer.parseInt(input)-1),colorList.get(Integer.parseInt(color)-1),player);
				
		 }
		 }catch(NumberFormatException e){
			 System.out.println("Invalid input ! Give Number as Input !");
				board=assassination(board);
		 }
		return board;
	}
	/**
	 * The method removeOneTroubleMarker manipulates the Board object to remove a trouble marker on the board.
	 * @param board : Contains current status of board.
	 * @return board: contains updated status of board after removing a trouble marker.
	 */
	public Board removeOneTroubleMarker(Board board) {
		String input=null;
		Scanner sc = new Scanner(System.in);
		List<Integer> troubleMakerOnBoard=new ArrayList<Integer>();
		for(int index=0;index<12;index++){
		if(board.getArea().get(index).getTroubleMaker()){
			troubleMakerOnBoard.add(index+1);
		}
		}
		try{
		if(troubleMakerOnBoard.size()==0){
			System.out.println("\nNo Trouble Maker Available on the board\n");
		}
		else{
			System.out.println("\nYou are allowed to Remove trouble Maker in the following Area's : \n");
			System.out.println("\t\tAreaNo.\t\t\tAreaName");
			for(int i=0;i<troubleMakerOnBoard.size();i++){
				System.out.println("\t\t"+troubleMakerOnBoard.get(i)+"\t\t\t"+BoardManager.cityAreaCardList.get(troubleMakerOnBoard.get(i)-1).getName());
			}
			System.out.println("\nEnter the AreaNo.\n");
			input=sc.nextLine();
			board.removeOneTroubleMarker(board.getArea().get(Integer.parseInt(input)-1));
		}
		}catch(NumberFormatException e){
			 System.out.println("Invalid input ! Give Number as Input !");
				board=removeOneTroubleMarker(board);
		}
		return board;
	}

	/**
	 * This method manipulates the Board object to draw money from the bank.
	 * @param board : Contains current status of board, which has details of bank as well.
	 * @return board: contains updated status of board after drawing cash from the bank.
	 */
	public Board takeMoneyFromBank(Board board) {
		String input=null;
		boolean found=false;
		Integer money=0;
		PlayerCard playerCard=null;
		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter the Player Card_ID for which money required : \n");
		try{
				input=sc.nextLine();
				if(!(board.getPlayerList().get(board.getPlayerTurn()-1).getGreenPlayerCards().contains(Integer.parseInt(input)))){
					System.out.println("\n You dont hold the given Player Card. Try Again!");
				}else{
				playerCard = BoardManager.playerCardMap.get(Integer.parseInt(input));
				for (int l=0;l<playerCard.getActionItem().length;l++){
					if(playerCard.getActionItem()[l].contains("$")){
						System.out.println("ActionItem : "+playerCard.getActionItem()[l]);
						money=Integer.parseInt(String.valueOf(playerCard.getActionItem()[l].charAt(playerCard.getActionItem()[l].indexOf('$')+1)));
						System.out.println("money : "+money);
						found=true;
						break;
					}
				}
				if(found){
					board.takeMoneyFromBank(board,money);
					System.out.println("\nYour AnkhMorporkh Dollar balance is credited with $"+money);
					System.out.println("\n\t\t Available Balance : "+board.getPlayerList().get(board.getPlayerTurn()-1).getPlayerMoney());
				}else{
					System.out.println("\nFor the given Card there is no such Action item which demands money from the bank. Try Again!\n");
				}
				}
			}catch(NumberFormatException e){
				 System.out.println("Invalid input ! Give Number as Input !");
					board=takeMoneyFromBank(board);
			}
		return board;
	}
	/**
	 * The method manipulates the Board object to read the description on the card.
	 * @param board : Contains current status of board.
	 */

	public Board scroll(Board board) {
		String input=null;
		PlayerCard playerCard=null;
		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter the Player Card_ID for which this action is played : \n");
		try{
			input=sc.nextLine();
			if(!(board.getPlayerList().get(board.getPlayerTurn()-1).getGreenPlayerCards().contains(Integer.parseInt(input)))){
				System.out.println("\n You dont hold the given Player Card. Try Again!");
			}else{
			playerCard = BoardManager.playerCardMap.get(Integer.parseInt(input));
			System.out.println("\n"+playerCard.getDescription()+"\n");
			}
			
		}catch(NumberFormatException e){
			 System.out.println("Invalid input ! Give Number as Input !");
				board=scroll(board);
		}
		return board;
	}

	/**
	 * The method manipulates the Board object to play a random event card on the board.
	 * @param board : Contains current status of board.
	 * @return board: contains updated status of board after the random event has happened.
	 */
	public Board playRandomEventCard(Board board) {
		if(Global.existingRandomEventCard.size()==12){
			System.out.println("\n\t\t All Random Event Cards are utilized.");
		}
		else{
		Integer cardNumber=Common.generateRandom(1, 12, Global.existingRandomEventCard);
		HashMap<String, String> card=BoardManager.randomEventCards.get(cardNumber);
		System.out.println("\nyou have been allocated the below Random Event Card.Proceed with the Description!\n");
		for (Entry<String, String> key : card.entrySet()) {
			System.out.println("\n\t\tCardName : "+key.getKey());
			System.out.println("\n\t\tDescription : "+key.getValue());
		}
		Global.existingRandomEventCard.add(cardNumber);
		}
		return board;
	}

	public Board passTheMove(Board board) {
		System.out.println("\n\t\t\tPush the Played Card to the trash in order to refill your hands");
		System.out.println("\n\t\t\t Would you like to refill your hands?       Yes(Y)   No(N)");
		Integer playerTurn=(board.getPlayerTurn())%(board.getNoOfPlayer());
		playerTurn=playerTurn+1;
		String input=userInputForLoadOrNewGameScanner
				.nextLine();
		if ("Y".equalsIgnoreCase(input)) {
			board=Trash(board);
			board=Refill(board,board.getPlayerTurn());
			board.setPlayerTurn(playerTurn);
		}
		else if ("N".equalsIgnoreCase(input)) {
			board.setPlayerTurn(playerTurn);
		}
		else{
			System.out.println("\nInvalid Input !! lets Get back to Menu\n");
		}
		return board;
	}

	private Board Refill(Board board, Integer playerTurn) {
		List<Integer> player_Card = board.getPlayerList().get(board.getPlayerTurn()-1).getGreenPlayerCards();
		Integer randomNumber=0;
		if(player_Card.size()>4){
			System.out.println("\nYou already have more than or equal to 5 cards!\n");
		}else{
			while(player_Card.size()<5){
				randomNumber = Common.generateRandom(1, 48, Global.existingGreenCards);
				player_Card.add(randomNumber);
				Global.existingGreenCards.add(randomNumber);
				System.out.println("\n\t\t\tNew Card Added : "+BoardManager.playerCardMap.get(randomNumber).getName());
			}
		}
		
		return board;
	}

	private Board Trash(Board board) {
		Scanner sc = new Scanner(System.in);
		String input=null;
		List<Integer> player_Card = board.getPlayerList().get(board.getPlayerTurn()-1).getGreenPlayerCards();
		System.out.println("\n\t\t\tEnter the Player Card_ID to be moved to trash:   (Press E to exit once u moved all your played card to trash)\n");
		try{
				input=sc.nextLine();
				if((input.equalsIgnoreCase("E")))
				{
					return board;
				}else{
					if(!(board.getPlayerList().get(board.getPlayerTurn()-1).getGreenPlayerCards().contains(Integer.parseInt(input)))){
						System.out.println("\n You dont hold the given Player Card. Try Again!");
					}else{
						Integer a =Integer.parseInt(input);
						player_Card.remove(a);
					}
					board.getPlayerList().get(board.getPlayerTurn()-1).setGreenPlayerCards(player_Card);
					board=Trash(board);
				}
			}catch(NumberFormatException e){
				 System.out.println("Invalid input ! Give Number as Input !");
					board=Trash(board);
			 }
		return board;
	}
}
