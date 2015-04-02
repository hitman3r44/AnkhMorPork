package com.concordia.ankhMorPork.manager;
/**
 * 
 * @author VarunPattiah
 * Feb 3, 2015
 * 10:38:54 AM
 * 2015
 * @email: varunpattiah@gmail.com
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.concordia.ankhMorPork.common.Common;
import com.concordia.ankhMorPork.common.Global;

/**
 * 
 * Class contains the information about the game including details of each player
 * Complete Information about area,personalityCardDetails,cityAreaCard,randomEventCard,playerCard
 */
public class Board {
	//instance variable
	private List<Area> area;
	private Integer bankMoney;
	private Map<String,String> personalityCardDetails;
	private List<CityAreaCard> cityAreaCard;
	private HashMap<Integer, HashMap<String, String>> randomEventCard;
	private Integer noOfPlayer;
	private List<Player> playerList;
	private List<Integer> existingPlayercards;
	private Integer playerTurn;
	public Scanner sc=new Scanner(System.in);
	public ActionItemImpl ActionItemImpl=new ActionItemImpl();
	/**
	 * This function is used to retreieve player turn
	 * @return: Integer value is returned as player turn
	 */
	public Integer getPlayerTurn() {
		return playerTurn;
	}
	/**
	 * This function sets the player turn
	 * @param  playerTurn  : Integer values is passed to set the player turn
	 */
	
	public void setPlayerTurn(Integer playerTurn) {
		this.playerTurn = playerTurn;
	}
	//setters and getters
	/**
	 * It retrieves the area list
	 * @return: list containing areas is returned
	 * 
	 */
		public List<Area> getArea() {
		return area;
	}
	/**
	 * This function sets the area
	 * @param  area  : List of areas is passed in order to define the areas
	 */
		
	public void setArea(List<Area> area) {
		this.area = area;
	}
	/**
	 * It fetches the bank money
	 * @return: bank money as integer
	 */
	
	public Integer getBankMoney() {
		return bankMoney;
	}
	/**
	 * This function is sets the money amount in bank
	 */
	
	public void setBankMoney(Integer bankMoney) {
		this.bankMoney = bankMoney;
	}
	

	/**
	 * It fetches the details of personality cards
	 * @return: personalityCard details
	 */
	
	public Map<String, String> getPersonalityCardDetails() {
		return personalityCardDetails;
	}
	public void setPersonalityCardDetails(Map<String, String> personalityCardDetails) {
		this.personalityCardDetails = personalityCardDetails;
	}

	/**
	 * It fetches the list of city area cards on the board
	 * @return: city area cards in a list
	 */
	public List<CityAreaCard> getCityAreaCard() {
		return cityAreaCard;
	}

	/**
	 * It fetches the bank money
	 * @return: bank money as integer
	 */
	public void setCityAreaCard(List<CityAreaCard> cityAreaCard) {
		this.cityAreaCard = cityAreaCard;
	}
	public HashMap<Integer, HashMap<String, String>> getRandomEventCard() {
		return randomEventCard;
	}
	public void setRandomEventCard(HashMap<Integer, HashMap<String, String>> randomEventCards) {
		this.randomEventCard = randomEventCards;
	}
	

	/**
	 * This function works to retrieve number of players in game
	 * @return: number of players
	 */
	public Integer getNoOfPlayer() {
		return noOfPlayer;
	}

	/**
	 * Sets the no. of players playing the game
	 */
	public void setNoOfPlayer(Integer noOfPlayer) {
		this.noOfPlayer = noOfPlayer;
	}

	/**
	 * Retrieves the list of players playing the game
	 * @return: player list containing player names is returned
	 */
	public List<Player> getPlayerList() {
		return playerList;
	}

	/**
	 * Sets the player names playing the game in a list
	 */
	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}

	/**
	 * It fetches the player card available on board
	 * @return: bank money as integer
	 */
	
	public List<Integer> getExistingPlayercards() {
		return existingPlayercards;
	}
	public void setExistingPlayercards(List<Integer> existingPlayercards) {
		this.existingPlayercards = existingPlayercards;
	}
	public void UpdateAreaDetails(Area area,String color) {
		List<String> colorOfMinion=area.getColorOfMinion();
		if(colorOfMinion.size()!=0 && (!area.getTroubleMaker())){
			area.setTroubleMaker(true);
		}
		colorOfMinion.add(color);
		area.setColorOfMinion(colorOfMinion);
	}
	public void UpdateAreaDetailsWithBuilding(String playerName, Area area) {
		area.setBuilding(true);
		area.setBuildingHolderName(playerName);
		
	}
	public void RemoveDemon(Area area) {
		area.setNoOfDemon(area.getNoOfDemon()-1);
		area.setTroubleMaker(false);
		
	}
	public void RemoveTroll(Area area) {
		area.setNoOfTroll(area.getNoOfTroll()-1);
		area.setTroubleMaker(false);
	}
	public void RemoveMinion(Area area, String color, Player player) {
		List<String> colorOfMinion=area.getColorOfMinion();
		colorOfMinion.remove(color);
		area.setColorOfMinion(colorOfMinion);
		area.setTroubleMaker(false);
		player.setNoOfMinions(player.getNoOfMinions()+1);
	}
	public void removeOneTroubleMarker(Area area) {
		area.setTroubleMaker(false);
		
	}
	public void takeMoneyFromBank(Board board, Integer money) {
		Integer playerMoney=board.getPlayerList().get(board.getPlayerTurn()-1).getPlayerMoney();
		playerMoney=playerMoney+money;
		board.getPlayerList().get(board.getPlayerTurn()-1).setPlayerMoney(playerMoney);
	}
	public Board applyCityAreaCard(CityAreaCard cityAreaCard2, Board board) {
		System.out.println(cityAreaCard2.getIdentifier());
		String currentPlayerColor=board.getPlayerList().get(board.getPlayerTurn()-1).getColor();
		Integer noOfMinions=board.getPlayerList().get(board.getPlayerTurn()-1).getNoOfMinions();
		Integer randomNumber=0;
		List<Integer> list1=new ArrayList<Integer>(Arrays.asList(6,12,11,3,9,5));
		List<Integer> list2=new ArrayList<Integer>(Arrays.asList(4,10,8,1));
		List<Integer> player_Card = board.getPlayerList().get(board.getPlayerTurn()-1).getGreenPlayerCards();
		Integer money=0;
		String input=null;
		try{
		if(list1.contains(cityAreaCard2.getIdentifier())){
			money=Integer.parseInt(String.valueOf(cityAreaCard2.getDescription().charAt(cityAreaCard2.getDescription().indexOf('$')+1)));
			takeMoneyFromBank(board,money);
			System.out.println("\nYour AnkhMorporkh Dollar balance is credited with $"+money);
		}
		if(cityAreaCard2.getIdentifier()==5||cityAreaCard2.getIdentifier()==2){
					if(cityAreaCard2.getIdentifier()==2){
						randomNumber = Common.generateRandom(1, 48, Global.existingGreenCards);
						player_Card.add(randomNumber);
						Global.existingGreenCards.add(randomNumber);
						System.out.println("\n\t\t\tNew Card Added : "+BoardManager.playerCardMap.get(randomNumber).getName());
					}
			System.out.println("\nEnter a Card of your Choice to Discard : ");
			input=sc.nextLine();
			player_Card=trashACard(player_Card, Integer.parseInt(input));
			board.getPlayerList().get(board.getPlayerTurn()-1).setGreenPlayerCards(player_Card);
		}
		if(list2.contains(cityAreaCard2.getIdentifier())){
			money=Integer.parseInt(String.valueOf(cityAreaCard2.getDescription().charAt(cityAreaCard2.getDescription().indexOf('$')+1)));
			payMoneyToBank(board,money);
			System.out.println("\nYour AnkhMorporkh Dollar balance is debited with $"+money);
		}
		if(cityAreaCard2.getIdentifier()==1 || cityAreaCard2.getIdentifier()==8){
			List<Integer> temporaryList=BoardManager.AdjacentAreaMap.get(cityAreaCard2.getIdentifier());
			System.out.println("\nYou are allowed to place the minion in the following Area :\n");
			System.out.println("\t\tAreaNo.\t\t\tAreaName");
			System.out.println("\t\t"+cityAreaCard2.getIdentifier()+"\t\t\t"+BoardManager.cityAreaCardList.get(cityAreaCard2.getIdentifier()-1).getName());
			 for (Integer integer : temporaryList) {
				 System.out.println("\t\t"+integer+"\t\t\t"+BoardManager.cityAreaCardList.get(integer-1).getName());
			}
			 System.out.println("\nEnter AreaNo. : \n");	
			 input = sc.nextLine();
				if(temporaryList.contains(Integer.parseInt(input))){
				
					System.out.println("Before Updating : "+board.getArea().get(Integer.parseInt(input)-1).getColorOfMinion());
					board.UpdateAreaDetails(board.getArea().get(Integer.parseInt(input)-1),currentPlayerColor);
					System.out.println("After Updating : "+board.getArea().get(Integer.parseInt(input)-1).getColorOfMinion());
					board.getPlayerList().get(board.getPlayerTurn()-1).setNoOfMinions(noOfMinions - 1);
				}
				else{
					System.out.println("Invalid input !");
				}
			 
		}
		if(cityAreaCard2.getIdentifier()==10){
			board=ActionItemImpl.removeOneTroubleMarker(board);
		}
		else if(cityAreaCard2.getIdentifier()==7){
			List<Integer> temporaryList=BoardManager.AdjacentAreaMap.get(cityAreaCard2.getIdentifier());
			System.out.println("\nYou are allowed to place one trouble Marker in the following Area :\n");
			System.out.println("\t\tAreaNo.\t\t\tAreaName");
			System.out.println("\t\t"+cityAreaCard2.getIdentifier()+"\t\t\t"+BoardManager.cityAreaCardList.get(cityAreaCard2.getIdentifier()-1).getName());
			 for (Integer integer : temporaryList) {
				 System.out.println("\t\t"+integer+"\t\t\t"+BoardManager.cityAreaCardList.get(integer-1).getName());
			}
			 System.out.println("\nEnter AreaNo. : \n");	
			 input=sc.nextLine();
			 board.getArea().get(Integer.parseInt(input)-1).setTroubleMaker(true);
		}
		}catch(NumberFormatException e){
			 System.out.println("Invalid input ! Give Number as Input !");
		 }
		return board;
	}
	public void payMoneyToBank(Board board, Integer money) {
		Integer playerMoney=board.getPlayerList().get(board.getPlayerTurn()-1).getPlayerMoney();
		playerMoney=playerMoney-money;
		board.getPlayerList().get(board.getPlayerTurn()-1).setPlayerMoney(playerMoney);
	}
	public List<Integer> trashACard(List<Integer> player_Card, Integer card) {
		if(!(player_Card.contains(card))){
			System.out.println("\n You dont hold the given Player Card. Try Again!");
		}else{
			player_Card.remove(card);
		}
		return player_Card;
	}
	

	
}
