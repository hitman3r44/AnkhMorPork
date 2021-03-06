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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;

import com.concordia.ankhMorPork.common.Common;
import com.concordia.ankhMorPork.common.Global;
import com.concordia.ankhMorPork.launcher.AnkhMorPorkLauncher;

/**
 *This class Manages the Board Information and manage the game as goes on.
 *All initialization like areaList,personalityCard,cityAreaCardList,randomEventCards and Playercards are done here.
 */
public class BoardManager {

	private Board board;
	private ActionItemImpl ActionItemImpl=new ActionItemImpl();
	//public static List<Integer> existingGreenCards = new ArrayList<Integer>();
	public static List<Integer> existingBrownCards = new ArrayList<Integer>();
	public static List<CityAreaCard> cityAreaCardList = new ArrayList<CityAreaCard>();
	public static List<Area> areaList = new ArrayList<Area>();
	public List<Player> playerList = new ArrayList<Player>();
	public static String[] yourArray = null;
	private Integer moneyDistributedCount=0;
	
	public BoardManager()
	{
		this.board=new Board();
		this.board=this.initializeCityAreaCard(board);
	}
	
	//setter and getters
	/**
	 * It's a function which get the board money distributed among the players. 
	 * It doesn't require any parameter as it's class's getter function. 
	 * <p>
	 * It's to get the board money and use to it in some functions. 
	 *	 
	 * @return      It return the money value which has been distributed among players.
	 * 
	 */
	
	public Integer getMoneyDistributedCount() {
		return moneyDistributedCount;
	}
	
	
	/**
	 * It's a function to set board distributed money among players. 
	 * It doesn't require any parameter as it's class's getter function. 
	 * <p>
	 * It's set the board money and use to set it later stages. 
	 *
	 * @param  moneyDistributedCount  : Money value is passed to set in member function
	 * 
	 */
	
	public void setMoneyDistributedCount(Integer moneyDistributedCount) {
		this.moneyDistributedCount = moneyDistributedCount;
	}
	
	/**
	 * Map is created to load PersonalityCards Information from the text file. 
	 * It loading the values from a text file which are predefined then randomize them.
	 * <p>
	 * It's initialized the board money and use to set it later stages. 
	 *
	 * @return      It's returning a hash table of personality cards
	 *  
	 */

	
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

	public static final HashMap<Integer, List<Integer>> AdjacentAreaMap = new HashMap<Integer, List<Integer>>() {
		{
		Scanner inFile1;
		Integer i=1;
		try {
			inFile1 = new Scanner(new File("./resources/AdjacencyAreaList.txt"));

			while (inFile1.hasNext()) {
				String data[] =inFile1.nextLine().split("--");	
				String[] numberStrs = data[1].split(",");
				int[] numbers = new int[numberStrs.length];
				for(int l = 0;l < numberStrs.length;l++)
				{
				   numbers[l] = Integer.parseInt(numberStrs[l]);
				}
				put(i++,Arrays.asList(ArrayUtils.toObject(numbers)));
			}
	}catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		}
	};
	
	@SuppressWarnings("serial")
	public static final HashMap<Integer, PlayerCard> playerCardMap = new HashMap<Integer, PlayerCard>() {
		{
			Scanner inFile1;
			try {
				inFile1 = new Scanner(new File("./resources/GreenPlayerCards.txt"));
				int index=1;
				String[] actionItem=new String[5];
				while (inFile1.hasNext()) {
				PlayerCard playerCard = new PlayerCard();	
				String data[] =inFile1.nextLine().split("\\|");
				//System.out.println("data[]" + data[1]);
				playerCard.setIdentifier(index);
				playerCard.setName(data[0]);
				if(data.length==1){
					playerCard.setActionItem(actionItem);
				}else{
				playerCard.setActionItem(data[1].split(","));
				}
				if(data.length==3){
				playerCard.setDescription(data[2]);
				}else{
					playerCard.setDescription("");	
				}
				//System.out.println("index :"+index);
					put(index++,playerCard);
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
			randomCardsDescription = new HashMap<String, String>();
			randomEventCards = new HashMap<Integer, HashMap<String, String>>();
			while (inFile1.hasNext()) {
				StringBuilder sb = new StringBuilder();
				randomCardsDescription = new HashMap<String, String>();
				sb.append(inFile1.nextLine());
				yourArray = sb.toString().split("\\|");
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
	 * It receives the board object which have details of the players like number of players
	 * playing this game, their color, name etc...
	 * @param board 	:board class object is passed. 
	 */
	
	
	public void displayBoardStatus(Board board) {
		
		Common.display();
		System.out.println("                   Game Status");
		System.out.println("                   ************\n\n");
		System.out.println("Number Of Players : " + board.getNoOfPlayer());
		 System.out.println("\nPlayer Turn   : Player No. "+board.getPlayerTurn());
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
			
			
		}//End of For loop
		
		//Board Details
		System.out.println("\nArea Details : \n\n");
		
		System.out.printf("%-30s%-30s%-25s%-25s%-25s%-15s%-15s\n", "Area",
				"Minions", "IsTroubleMakerAvailable", "IsbuildingAvailable","buildingHolderName",
				"NoOfDemons", "NoOfTrolls");
		
	
		
		for (int i = 0; i < 12; i++) {
			System.out.printf("%-30s%-30s%-25s%-25s%-25s%-15s%-15s\n",
					cityAreaCardList.get(i).getName(), board.getArea().get(i)
							.getColorOfMinion(), board.getArea().get(i)
							.getTroubleMaker(), board.getArea().get(i).getBuilding(),board.getArea().get(i).getBuildingHolderName(),
							board.getArea().get(i).getNoOfDemon(), board.getArea().get(i)
							.getNoOfTroll());		
		}
		
		System.out.println("\n\nPlayer Details :: \n\n");
		this.setMoneyDistributedCount(0);
		// Player Statistic
		for (int i = 0; i < board.getNoOfPlayer(); i++) {
			System.out.println(board.getPlayerList().get(i).getName()
					+ "'s Current Holdings : ");
			System.out.println("No Of Minions        :"
					+ (board.getPlayerList().get(i).getNoOfMinions()));
			System.out.println("No Of Building       :"
					+ (board.getPlayerList().get(i).getNoOfBuilding()));
			System.out.println("Ankh-Morpork Dollars :"
					+ board.getPlayerList().get(i).getPlayerMoney() + "\n\n\n");
			System.out.println("City Area cards: ");
			this.setMoneyDistributedCount((this.getMoneyDistributedCount()+board.getPlayerList().get(i).getPlayerMoney()));
			//Print on the screen
			
			//City area cards list
			
			if (board.getPlayerList().get(i).getCityAreaCard().size() == 0) {
				System.out.println("No City Area cards attained yet");
			} else {
				for (int j = 0; j < board.getPlayerList().get(i)
						.getCityAreaCard().size(); j++) {
					System.out.println((j + 1)
							+ ")"
							+ board.getCityAreaCard().get(
									board.getPlayerList().get(i)
											.getCityAreaCard().get(j))
									.getName());
				}
			}
			
			System.out.println("\nPlayer Cards : ");
			System.out.println(" \tGreen Cards : "
					+ board.getPlayerList().get(i).getGreenPlayerCards());
			for (int k=0; k<board.getPlayerList().get(i).getGreenPlayerCards().size();k++){
				
				System.out.println(playerCardMap.get(board.getPlayerList().get(i).getGreenPlayerCards().get(k)).getName());
			}
			System.out.println(" \tBrown Cards : "
					+ board.getPlayerList().get(i).getBrownPlayerCards());
				
		}// End of loop		
		System.out.println("\nThe Bank has " + (board.getBankMoney()-this.getMoneyDistributedCount())
				+ " Ankh-Morpork dollars");
	}
	
	
	/**
	 * This method initialize the Board for new player and return the board object with playrs 
	 * details filled.
	 * @param noOfPlayer : number of player playing
	 * @param playerList : name of the players
	 * @param colorList  : color of the player
	 * @return the initialized board object
	 */
	
	
	public Board initializeBoardforNewPlayer(Integer noOfPlayer,
			List<String> playerList, List<String> colorList) {
		Integer playerTurn=0;
		board = new Board();
		board.setBankMoney(Global.BANK_MONEY);
		board.setNoOfPlayer(noOfPlayer);
		board.setRandomEventCard(randomEventCards);
		board = initializeCityAreaCard(board);
		board = initializeAreaDetails(board, colorList);
		board = initializeNewPlayer(board, noOfPlayer, playerList, colorList);
		List<Integer> existingCard=new ArrayList<Integer>();
		playerTurn=Common.generateRandom(1, noOfPlayer, existingCard);
		board.setPlayerTurn(playerTurn);
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
		Integer minionsOnBoard = 3;

		for (int i = 0; i < noOfPlayer; i++) {
			Player player = new Player(i + 1, playerNameList.get(i),
					colorList.get(i));
			player.setNoOfBuilding(6);
			player.setPlayerMoney(10);
			player.setCityAreaCard(new ArrayList<Integer>());
			player.setNoOfMinions(Global.MINIONS - minionsOnBoard);
			randomCardNo = Common.generateRandom(1, 7, existingPersonalityCard);
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
			if (Global.existingGreenCards.size() < 48) {
				randomNumber = Common.generateRandom(1, 48, Global.existingGreenCards);
				greenPlayerCard.add(randomNumber);
				Global.existingGreenCards.add(randomNumber);
			} else {
				randomNumber = Common.generateRandom(1, 101, existingBrownCards);
				brownPlayerCard.add(randomNumber - 48);
				existingBrownCards.add(randomNumber);
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
			area.setBuildingHolderName("");
			if (i == 0 || i == 4 || i == 6) {
				List<String> colors=new ArrayList<String>(colorList);
				// System.out.println("colorList -- " + colorList);
				// System.out.println(area.getNoOfDemon());
				area.setColorOfMinion(colors);
				area.setTroubleMaker(true);
			}
			areaList.add(area);
		}
		board2.setArea(areaList);
		return board2;
	}

	/**
	 * This method initializes the cityAreaCards of the board from a text file.
	 * @param board2 : board object
	 * @return it returns the Board object with city areas filled with respect to players.
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
				if (dataArray.length == 4) {
					// System.out.println(dataArray[0] + "--" + dataArray[1]
					// + "--" + dataArray[2]);
					CityAreaCard cityAreaCard = new CityAreaCard();
					cityAreaCard.setIdentifier(Integer.parseInt(dataArray[0]));
					cityAreaCard.setName(dataArray[1]);
					cityAreaCard.setCost(Integer.parseInt(dataArray[2]));
					cityAreaCard.setDescription(dataArray[3]);
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

	//setters and getters method
	
	/**
	 * This is setter function to intialized the board 
	 * cards.
	 * @return board initialized value
	 * 
	 */
	
	public Board getBoard() {
		return board;
	}

	/**
	 * This is setter function to intialized the board 
	 * cards.
	 * @param board : Gets Board member and initialized it
	 * 
	 */
	
	
	public void setBoard(Board board) {
		this.board = board;
	}

	public Board showPlayerDetails(Board board, Player player) {
		System.out.println("\n\t\t\tWelcome "+player.getName());
		System.out.println("\n\t\t\tNo Of Minions        :"
				+ (player.getNoOfMinions()));
		System.out.println("\n\t\t\tNo Of Building       :"
				+ (player.getNoOfBuilding()));
		System.out.println("\n\t\t\tAnkh-Morpork Dollars :"
				+ player.getPlayerMoney() + "\n\n\n");
		System.out.println("\n\t\t\tCity Area cards: ");
		if (player.getCityAreaCard().size() == 0) {
			System.out.println("\n\t\t\tNo City Area cards attained yet");
		} else {
			for (int j = 0; j < player.getCityAreaCard().size(); j++) {
				System.out.println((j + 1)
						+ ")"
						+ board.getCityAreaCard().get(
								player.getCityAreaCard().get(j))
								.getName());
			}
		}
		
		System.out.println("\nPlayer Cards : ");
		System.out.println(" \tGreen Cards : "
				+ player.getGreenPlayerCards());
		String events="";
		PlayerCard playerCard =null;
		for (int k=0; k<player.getGreenPlayerCards().size();k++){
			events="";
			playerCard = playerCardMap.get(player.getGreenPlayerCards().get(k));
			for (int l=0;l<playerCard.actionItem.length;l++){
				if(l==playerCard.actionItem.length-1)
					events+=playerCard.actionItem[l];
				else
					events+=playerCard.actionItem[l]+" , ";
			}
			System.out.println("\n\t\tCard_ID : "+player.getGreenPlayerCards().get(k)+"\t\t"+playerCard.getName()+" - ("+events+")\n");
		}
		System.out.println(" \tBrown Cards : "
				+ player.getBrownPlayerCards());
		board=chooseNextAction(board);
		return board;
	}

	private Board chooseNextAction(Board board2) {
		String input=null;
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\t\t\tAction Menu\n");
		System.out.println("1.Place a Minion\n2.Place a building\n3.Asssaination\n4.Remove One troubleMarker\n5.Take Money from bank\n6.Scroll\n7.Play RandomEvent Cards\n8.Play City Area Cards\n9.Interrupt Card\n10.Pass the move to next player\n11.displayBoardStatus\n12.Save the Game and Exit");
		System.out.println("\nEnter your Choice : \n");
		input = sc.nextLine();
		switch (Integer.parseInt(input)) {
		case 1:
			board2=this.ActionItemImpl.placeTheMinion(board2);
			board2=chooseNextAction(board2);
			break;
		case 2:
			board2=this.ActionItemImpl.placeTheBuilding(board2);
			board2=chooseNextAction(board2);
			break;
		case 3:
			board2=this.ActionItemImpl.assassination(board2);
			board2=chooseNextAction(board2);
			break;
		case 4:
			board2=this.ActionItemImpl.removeOneTroubleMarker(board2);
			board2=chooseNextAction(board2);
			break;
		case 5:
			board2=this.ActionItemImpl.takeMoneyFromBank(board2);
			board2=chooseNextAction(board2);
			break;
		case 6:
			board2=this.ActionItemImpl.scroll(board2);
			board2=chooseNextAction(board2);
			break;
		case 7:
			board2=this.ActionItemImpl.playRandomEventCard(board2);
			board2=chooseNextAction(board2);
			break;
		case 8:
			board2=this.ActionItemImpl.playCityAreaCard(board2);
			board2=chooseNextAction(board2);
			break;	
		case 9:
			Common.displayThankyouMenu();
			break;	
		case 10:
			board2=this.ActionItemImpl.passTheMove(board2);
			board2=showPlayerDetails(board2,board2.getPlayerList().get(board2.getPlayerTurn() - 1));
			break;	
		case 11:
			displayBoardStatus(board2);
			board2=chooseNextAction(board2);
			break;	
		case 12:
			AnkhMorPorkLauncher ankhMorPorkLauncher2=AnkhMorPorkLauncher.getInstanceOf();
			this.setBoard(board2);
			ankhMorPorkLauncher2.saveGame(ankhMorPorkLauncher2);
			break;
		default:
			System.out.println("Invalid Input");
			//chooseGameState(ankhMorPorkLauncher2);
			break;
			
			
		}
		return board2;
	}

}
