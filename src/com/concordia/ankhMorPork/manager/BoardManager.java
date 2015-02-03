package com.concordia.ankhMorPork.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.concordia.ankhMorPork.common.Global;
/**
 * @author Varun
 * Jan 29, 2015
 * 10:44:24 AM
 * 2015
 * @email: varunpattiah@gmail.com
 */
public class BoardManager {

	private Board board;
	
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public static List<Integer> existingCards = new ArrayList<Integer>();
	public static List<CityAreaCard> cityAreaCardList = new ArrayList<CityAreaCard>();
	public static List<Area> areaList = new ArrayList<Area>();
	public List<Player> playerList = new ArrayList<Player>();
	@SuppressWarnings("serial")
	public static final HashMap<Integer, String> personalityCard = new HashMap<Integer, String>() {
		{
			Scanner inFile1;
			try {
				inFile1 = new Scanner(new File("./resources/PersonalityCard.txt"));
				int i=1;
				while (inFile1.hasNext()) {
					put(i++,inFile1.nextLine());
				}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
		}
	};
	public static HashMap<String, String> randomCardsDescription;
	public static HashMap<Integer, HashMap<String, String>> randomEventCards;
	public static String[] array= null;;
	static {
		
		Scanner inFile1;
		try {
			inFile1 = new Scanner(new File("./resources/RandomEventCard.txt"));
			int i=1;
		
			randomEventCards = new HashMap<Integer, HashMap<String, String>>();
			
			while (inFile1.hasNext()) {
				StringBuilder sb = new StringBuilder();
				randomCardsDescription = new HashMap<String, String>();
				sb.append(inFile1.nextLine());
				array = sb.toString().split("|");
				if(array.length==2)
				{
				randomCardsDescription.put(array[0], array[1]);
				randomEventCards.put(i++, randomCardsDescription);
				}
			}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}

	public void displayCurrentStatus(Board board)
	{
		System.out.println("==============================================================");
		System.out.println("        Welcome to DisckWorld : AnkhMorpork                   ");
		System.out.println("=============================================================="+"\n"+"\n");
		System.out.println("                   Game Status");
		System.out.println("                   ***********\n\n");
		System.out.println("Number Of Players : "+board.getNoOfPlayer());
		for(int i=0;i<board.getNoOfPlayer();i++)
		{
			System.out.println("Player"+(i+1)+" : "+board.getPlayerList().get(i).getName()+" ("+board.getPlayerList().get(i).getColor()+" ) playing as "+personalityCard.get(board.getPlayerList().get(i).getPersonalityCard()));
		}
		System.out.println("\nArea Details : \n\n");
		//System.out.println("Area                          Minions\tIsTroubleMakerAvailable\tIsbuildingAvailable\tNoOfDemons\tNoOfTrolls\n");
		System.out.printf("%-30s%-15s%-25s%-25s%-15s%-15s\n","Area","Minions","IsTroubleMakerAvailable","IsbuildingAvailable","NoOfDemons","NoOfTrolls");
		for(int i =0 ;i<12 ; i++)
		{
		System.out.printf("%-30s%-15s%-25s%-25s%-15s%-15s\n",cityAreaCardList.get(i).getName(),areaList.get(i).getColorOfMinion(),areaList.get(i).getTroubleMaker(),areaList.get(i).getBuilding(),areaList.get(i).getNoOfDemon(),areaList.get(i).getNoOfTroll());
		}
		
		System.out.println("\n\nPlayer Details :: \n\n");
		for(int i=0;i<board.getNoOfPlayer();i++)
		{
			System.out.println(board.getPlayerList().get(i).getName()+"'s Current Holdings : ");
			System.out.println("No Of Minions        :"+(12-board.getPlayerList().get(i).getMinionsOnBoard().size()));
			System.out.println("No Of Building       :"+(6-board.getPlayerList().get(i).getBuildingOnBoard().size()));
			System.out.println("Ankh-Morpork Dollars :"+board.getPlayerList().get(i).getPlayerMoney()+"\n\n\n");
			System.out.println("City Area cards: ");
			if (board.getPlayerList().get(i).getCityAreaCard().size()==0) {
				System.out.println("No City Area cards attained yet");
			}
			else
			{
				for(int j=0;i<board.getPlayerList().get(i).getCityAreaCard().size();j++)
				{
					System.out.println((j+1)+")"+cityAreaCardList.get(board.getPlayerList().get(i).getCityAreaCard().get(j)).getName());
				}
			}
			System.out.println("\nPlayer Cards : ");
			System.out.println(" \tGreen Cards : "+board.getPlayerList().get(i).getGreenPlayerCards());
			System.out.println(" \tBrown Cards : "+board.getPlayerList().get(i).getBrownPlayerCards());
		}
		System.out.println("\nThe Bank has "+board.getBankMoney()+" Ankh-Morpork dollars");
	}
	
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
			randomCardNo=generateRandom(1, 7, existingPersonalityCard);
			player.setPersonalityCard(randomCardNo);
			existingPersonalityCard.add(randomCardNo);
			player.setBrownPlayerCards(new ArrayList<Integer>());
			player=distributePlayerCardRandomly(player);
			//System.out.println(personalityCard.get(player.getPersonalityCard()));
			//System.out.println("Player green and brown cards : "+player.getGreenPlayerCards()+"--"+player.getBrownPlayerCards());
			playerList.add(player);

		}
		board2.setPlayerList(playerList);
		return board2;
	}

	private Player distributePlayerCardRandomly(Player player) {
		List<Integer> greenPlayerCard=player.getGreenPlayerCards();
		List<Integer> brownPlayerCard=player.getBrownPlayerCards();
		Integer randomNumber;
		
		for(int i=0; i<5;i++)
			{
				if(existingCards.size()<48)
				{
					randomNumber=generateRandom(1, 48, existingCards);
					greenPlayerCard.add(randomNumber);
					existingCards.add(randomNumber);
				}
				else
				{
					randomNumber=generateRandom(1, 101, existingCards);
					brownPlayerCard.add(randomNumber-48);
					existingCards.add(randomNumber);
				}
				
			}
			player.setGreenPlayerCards(greenPlayerCard);
			player.setBrownPlayerCards(brownPlayerCard);
		return player;
	}

	private Board initializeAreaDetails(Board board2, List<String> colorList) {
		for (int i = 0; i < 12; i++) {
			Area area = new Area(false,false,0,0);
			area.setIdentifier(i + 1);
			if (i == 0||i == 4||i == 6) {
				//System.out.println("colorList -- " + colorList);
				//System.out.println(area.getNoOfDemon());
				area.setColorOfMinion(colorList);
			}
			areaList.add(area);
		}
		board2.setArea(areaList);
		return board2;
	}

	@SuppressWarnings("resource")
	private Board initializeCityAreaCard(Board board2) {

		String[] yourArray = null;
		Scanner inFile1;
		// System.out.println(new File(".").getAbsolutePath());
		try {
			inFile1 = new Scanner(new File("./resources/CityAreaInfo.txt"));

			while (inFile1.hasNext()) {
				StringBuilder sb = new StringBuilder();
				sb.append(inFile1.nextLine());
				yourArray = sb.toString().split(",");
				//System.out.println(yourArray.length);
				if (yourArray.length == 3) {
					//System.out.println(yourArray[0] + "--" + yourArray[1]
							//+ "--" + yourArray[2]);
					CityAreaCard cityAreaCard = new CityAreaCard();
					cityAreaCard.setIdentifier(Integer.parseInt(yourArray[0]));
					cityAreaCard.setName(yourArray[1]);
					cityAreaCard.setCost(Integer.parseInt(yourArray[2]));
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

	public int generateRandom(int start, int end,
			List<Integer> existingCard) {
		Random rand = new Random();
		int range = end - start + 1;

		int random = rand.nextInt(range) + 1;
		while (existingCard.contains(random)) {
			random = rand.nextInt(range) + 1;
		}

		return random;
	}

}
