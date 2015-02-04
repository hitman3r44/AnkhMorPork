package com.concordia.ankhMorPork.manager;
/**
 * @author varun
 * Feb 1, 2015
 * 5:00:23 AM
 * 2015
 * @email: varunpattiah@gmail.com
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.concordia.ankhMorPork.common.Common;
import com.concordia.ankhMorPork.common.Global;

/**
 *This class Manages the Board Information and manage the game as goes on.
 *All initialization like areaList,personalityCard,cityAreaCardList,randomEventCards and Playercards are done here.
 */
public class BoardManager {

	private Board board;	
	public static List<Integer> existingCards = new ArrayList<Integer>();
	public static List<CityAreaCard> cityAreaCardList = new ArrayList<CityAreaCard>();
	public static List<Area> areaList = new ArrayList<Area>();
	public List<Player> playerList = new ArrayList<Player>();
	public static String[] yourArray = null;
	private Integer moneyDistributedCount=0;
	//setter and getters
	public Integer getMoneyDistributedCount() {
		return moneyDistributedCount;
	}
	public void setMoneyDistributedCount(Integer moneyDistributedCount) {
		this.moneyDistributedCount = moneyDistributedCount;
	}

	//Map is created to load PersonalityCards Information from the text file
	@SuppressWarnings("serial")
	public static final HashMap<Integer, String> personalityCard = new HashMap<Integer, String>() {
		{
			Scanner inFile1;
			Integer i=1;
			try {
				inFile1 = new Scanner(new File("./resources/PersonalityCard.txt"));

				while (inFile1.hasNext()) {
					put(i++,inFile1.nextLine());
				}
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	};
	public static HashMap<String, String> randomCardsDescription;
	public static HashMap<Integer, HashMap<String, String>> randomEventCards;
	//Static block to load Random EventCards
	static {
		
		Scanner inFile1;
		Integer i=1;
		try {
			inFile1 = new Scanner(new File("./resources/RandomEventCard.txt"));

			while (inFile1.hasNext()) {
				StringBuilder sb = new StringBuilder();
				randomCardsDescription = new HashMap<String, String>();
				randomEventCards = new HashMap<Integer, HashMap<String, String>>();
				sb.append(inFile1.nextLine());
				yourArray = sb.toString().split("|");
				if (yourArray.length == 2) {
					randomCardsDescription.put(yourArray[0],yourArray[1]);	
					randomEventCards.put(i++,randomCardsDescription);
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
/**
 * This method displays the current Status of the Game.
 * @param board
 */
	public void displayCurrentStatus(Board board) {
		
		Common.display();
		System.out.println("                   Game Status");
		System.out.println("                   ************\n\n");
		System.out.println("Number Of Players : " + board.getNoOfPlayer());
		
		Global.gameStatistic.setNumberOfPlayers(board.getNoOfPlayer());
		
		//First portion of Player Statistics 
		 
		ArrayList<Player> playersList = new ArrayList<Player>();
		for (int i = 0; i < board.getNoOfPlayer(); i++) {
			Player player = new Player((i + 1), board.getPlayerList().get(i).getName(), board.getPlayerList().get(i).getColor());
			
			System.out.println("Player"
					+ (i + 1)
					+ " : "
					+ board.getPlayerList().get(i).getName()
					+ " ("
					+ board.getPlayerList().get(i).getColor()
					+ " ) playing as "
					+ personalityCard.get(board.getPlayerList().get(i)
							.getPersonalityCard()));
			
			//Added to the player object
			player.setPersonalityCardString(
					personalityCard.get(board.getPlayerList().get(i)
							.getPersonalityCard()));
			
			//Added to the playerInventories ArrayList 
			playersList.add(i, player);
			
		}//End of For loop
		
		//Set to the global variable
		Global.gameStatistic.setPlayersList(playerList);
		
		//Board Details
		System.out.println("\nArea Details : \n\n");
		
		System.out.printf("%-30s%-15s%-25s%-25s%-15s%-15s\n", "Area",
				"Minions", "IsTroubleMakerAvailable", "IsbuildingAvailable",
				"NoOfDemons", "NoOfTrolls");
		
		for (int i = 0; i < 12; i++) {
			System.out.printf("%-30s%-15s%-25s%-25s%-15s%-15s\n",
					cityAreaCardList.get(i).getName(), areaList.get(i)
							.getColorOfMinion(), areaList.get(i)
							.getTroubleMaker(), areaList.get(i).getBuilding(),
					areaList.get(i).getNoOfDemon(), areaList.get(i)
							.getNoOfTroll());
		}

		System.out.println("\n\nPlayer Details :: \n\n");
		
		ArrayList<PlayerInventory> playerInventoriesList = new ArrayList<PlayerInventory>();
		// Player Statistic
		for (int i = 0; i < board.getNoOfPlayer(); i++) {
			//Fill the PlayerInventory object 
			PlayerInventory playerInventory = new PlayerInventory();
			
			playerInventory.setMinions(Global.MINIONS - board.getPlayerList().get(i).getMinionsOnBoard()
							.size());
			playerInventory.setBuildings(Global.BUILDINGS - board.getPlayerList().get(i).getBuildingOnBoard()
					.size());
			playerInventory.setMoney(board.getPlayerList().get(i).getPlayerMoney());
			//Fill the PlayerInventory object
			
			//Print on the screen
			System.out.println(board.getPlayerList().get(i).getName()
					+ "'s Current Holdings : ");
			System.out.println("No Of Minions        :"
					+ (Global.MINIONS - board.getPlayerList().get(i).getMinionsOnBoard()
							.size()));
			System.out.println("No Of Building       :"
					+ (Global.BUILDINGS - board.getPlayerList().get(i).getBuildingOnBoard()
							.size()));
			System.out.println("Ankh-Morpork Dollars :"
					+ board.getPlayerList().get(i).getPlayerMoney() + "\n\n\n");
			System.out.println("City Area cards: ");
			this.setMoneyDistributedCount((this.getMoneyDistributedCount()+board.getPlayerList().get(i).getPlayerMoney()));
			//Print on the screen
			
			//City area cards list

			//Initial a arraylist of city area
			ArrayList<String> cityAreaList = new ArrayList<String>();
			
			if (board.getPlayerList().get(i).getCityAreaCard().size() == 0) {
				System.out.println("No City Area cards attained yet");

				//Add to the playerInventory object
				cityAreaList.add("No City Area cards attained yet");
				playerInventory.setCityAreaList(cityAreaList);
			} else {
				for (int j = 0; i < board.getPlayerList().get(i)
						.getCityAreaCard().size(); j++) {
					System.out.println((j + 1)
							+ ")"
							+ cityAreaCardList.get(
									board.getPlayerList().get(i)
											.getCityAreaCard().get(j))
									.getName());
					
					//Add to the playerInventory object
					cityAreaList.add(j, cityAreaCardList.get(
									board.getPlayerList().get(i)
											.getCityAreaCard().get(j))
									.getName());
					
					playerInventory.setCityAreaList(cityAreaList);
				}
			}
			
			System.out.println("\nPlayer Cards : ");
			System.out.println(" \tGreen Cards : "
					+ board.getPlayerList().get(i).getGreenPlayerCards());
			System.out.println(" \tBrown Cards : "
					+ board.getPlayerList().get(i).getBrownPlayerCards());
		}
		
		System.out.println("\nThe Bank has " + (board.getBankMoney()-this.getMoneyDistributedCount())
				+ " Ankh-Morpork dollars");
	}
/**
 * This method initialize the Board for new player
 * @param noOfPlayer : number of player playing
 * @param playerList : name of the players
 * @param colorList  : color of the player
 * @return the initialized board object
 */
	public Board initializeBoardforNewPlayer(Integer noOfPlayer,
			List<String> playerList, List<String> colorList) {
		board = new Board();
		board.setBankMoney(Global.BANK_MONEY);
		board.setNoOfPlayer(noOfPlayer);
		board.setRandomEventCard(randomEventCards);
		board = initializeCityAreaCard(board);
		board = initializeAreaDetails(board, colorList);
		board = initializeNewPlayer(board, noOfPlayer, playerList, colorList);
		return board;
	}
/**
 * This method create Object for new player with initial values
 * @param board2        : board object which wil contain player object as its member
 * @param noOfPlayer    :Number Of Player
 * @param playerNameList:Name of the players
 * @param colorList     :Color of the player
 * @return the initialized board with new players
 */
	private Board initializeNewPlayer(Board board2, Integer noOfPlayer,
			List<String> playerNameList, List<String> colorList) {
		Integer randomCardNo;
		List<Integer> existingPersonalityCard = new ArrayList<Integer>();
		List<Integer> minionsOnBoard = new ArrayList<Integer>();
		Collections.addAll(minionsOnBoard, 1, 5, 7);

		for (int i = 0; i < noOfPlayer; i++) {
			Player player = new Player(i + 1, playerNameList.get(i),
					colorList.get(i));
			player.setBuildingOnBoard(new ArrayList<Integer>());
			player.setPlayerMoney(10);
			player.setCityAreaCard(new ArrayList<Integer>());
			player.setMinionsOnBoard(minionsOnBoard);
			randomCardNo = generateRandom(1, 7, existingPersonalityCard);
			player.setPersonalityCard(randomCardNo);
			existingPersonalityCard.add(randomCardNo);
			player.setBrownPlayerCards(new ArrayList<Integer>());
			player = distributePlayerCardRandomly(player);
			// System.out.println(personalityCard.get(player.getPersonalityCard()));
			// System.out.println("Player green and brown cards : "+player.getGreenPlayerCards()+"--"+player.getBrownPlayerCards());
			playerList.add(player);

		}
		board2.setPlayerList(playerList);
		return board2;
	}
/**
 * THis method distribute the playerCards randomly among the player
 * @param player : player object to which cards has to be assigned randomly
 * @return the player object with assigned random playerCards
 */
	private Player distributePlayerCardRandomly(Player player) {
		List<Integer> greenPlayerCard = player.getGreenPlayerCards();
		List<Integer> brownPlayerCard = player.getBrownPlayerCards();
		Integer randomNumber;

		for (int i = 0; i < 5; i++) {
			if (existingCards.size() < 48) {
				randomNumber = generateRandom(1, 48, existingCards);
				greenPlayerCard.add(randomNumber);
				existingCards.add(randomNumber);
			} else {
				randomNumber = generateRandom(1, 101, existingCards);
				brownPlayerCard.add(randomNumber - 48);
				existingCards.add(randomNumber);
			}

		}
		player.setGreenPlayerCards(greenPlayerCard);
		player.setBrownPlayerCards(brownPlayerCard);
		return player;
	}
/**
 * THis Method initializes the Area's on the board
 * @param board2   : board object
 * @param colorList:color of the players
 * @return
 */
	private Board initializeAreaDetails(Board board2, List<String> colorList) {
		for (int i = 0; i < 12; i++) {
			Area area = new Area(false, false, 0, 0);
			area.setIdentifier(i + 1);
			if (i == 0 || i == 4 || i == 6) {
				// System.out.println("colorList -- " + colorList);
				// System.out.println(area.getNoOfDemon());
				area.setColorOfMinion(colorList);
			}
			areaList.add(area);
		}
		board2.setArea(areaList);
		return board2;
	}

	/**
	 * This method initializes the cityAreaCards of the board from a text file.
	 * @param board2 : board object
	 * @return
	 */
	@SuppressWarnings("resource")
	private Board initializeCityAreaCard(Board board2) {

		String[] dataArray = null;
		Scanner inFile1;
		// System.out.println(new File(".").getAbsolutePath());
		try {
			inFile1 = new Scanner(new File("./resources/CityAreaInfo.txt"));

			while (inFile1.hasNext()) {
				StringBuilder sb = new StringBuilder();
				sb.append(inFile1.nextLine());
				dataArray = sb.toString().split(",");
				// System.out.println(dataArray.length);
				if (dataArray.length == 3) {
					// System.out.println(dataArray[0] + "--" + dataArray[1]
					// + "--" + dataArray[2]);
					CityAreaCard cityAreaCard = new CityAreaCard();
					cityAreaCard.setIdentifier(Integer.parseInt(dataArray[0]));
					cityAreaCard.setName(dataArray[1]);
					cityAreaCard.setCost(Integer.parseInt(dataArray[2]));
					cityAreaCardList.add(cityAreaCard);
				}
			}
			board2.setCityAreaCard(cityAreaCardList);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return board2;
	}
/**
 * 
 * @param start		   : random value lower bound
 * @param end          : random value upper bound
 * @param existingCard : contains the random values already taken before 
 * @return the random value not taken before
 */
	public int generateRandom(int start, int end, List<Integer> existingCard) {
		Random rand = new Random();
		int range = end - start + 1;

		int random = rand.nextInt(range) + 1;
		while (existingCard.contains(random)) {
			random = rand.nextInt(range) + 1;
		}

		return random;
	}
	//setters and getters method
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
/**
 * This function store the status of the game in a text file
 * @param fileName : Name of the file to which game status to be saved
 * @param board
 */
	public void saveGameStatus(String fileName,Board board) {
		// TODO Auto-generated method stub
		PrintWriter writer;
		List<String> listOfCityAreaCard=new ArrayList<String>();
		try {
			System.out.println(fileName);
			writer = new PrintWriter(fileName, "UTF-8");
			writer.println("noOfPlayers ="+Global.numberOfPlayers);
			for (int i = 0; i < board.getNoOfPlayer(); i++) {
				writer.println("Player"+ (i + 1)+" ="+board.getPlayerList().get(i).getName());
				writer.println("Color ="+board.getPlayerList().get(i).getColor());
				writer.println("PersonalityCard ="+personalityCard.get(board.getPlayerList().get(i).getPersonalityCard()));
				writer.println("No Of Minions ="
						+ (Global.MINIONS - board.getPlayerList().get(i).getMinionsOnBoard().size()));
				writer.println("No Of Building ="
						+ (Global.BUILDINGS - board.getPlayerList().get(i).getBuildingOnBoard()
								.size()));
				writer.println("Ankh-Morpork Dollars ="
						+ board.getPlayerList().get(i).getPlayerMoney());
				if (board.getPlayerList().get(i).getCityAreaCard().size()==0) {
					writer.println("CityAreaCard =NIL");
				} else {
					for (int j = 0; i < board.getPlayerList().get(i)
							.getCityAreaCard().size(); j++) {
						
						listOfCityAreaCard.add(cityAreaCardList.get(board.getPlayerList().get(i).getCityAreaCard().get(j)).getName()); 
					}
					
					writer.println(("CityAreaCard ="+listOfCityAreaCard));
				}
				writer.println("Green Cards ="+board.getPlayerList().get(i).getGreenPlayerCards());
				writer.println("Brown Cards ="+board.getPlayerList().get(i).getBrownPlayerCards());
			}
			writer.println("BankMoney ="+(board.getBankMoney()-this.getMoneyDistributedCount()));
			
			for (int i = 0; i < 12; i++) {
				writer.println("Area No. ="+(i+1));
				writer.println("Area ="+cityAreaCardList.get(i).getName());
				writer.println("Minions ="+areaList.get(i).getColorOfMinion());
				writer.println("IsTroubleMakerAvailable ="+areaList.get(i).getTroubleMaker());
				writer.println("IsbuildingAvailable ="+areaList.get(i).getBuilding());
				writer.println("NoOfDemons ="+areaList.get(i).getNoOfDemon());
				writer.println("NoOfTrolls ="+areaList.get(i).getNoOfTroll());
			}
			writer.println("PlayerCards played ="+existingCards);
			writer.close();	
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * This function load the game and update the dataStructure containing PlayerInfo
	 * @param inputMap: contains the Status of the game in Map
	 * @return
	 */
	public Board updatePlayerInfo(List<String> inputList) {
		System.out.println(inputList);
		try
		{
			Integer noOfPlayer=Integer.parseInt(inputList.get(0));
			for(int i =0;i<noOfPlayer;i++)
			{
				
			}
		}catch(NumberFormatException e)
		 {
			 System.out.println("Input File Validation Error : NoOfPlayer not a valid value.");
		 }
		 

		return board;
	}

}
