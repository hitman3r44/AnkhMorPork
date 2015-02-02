package com.concordia.ankhMorPork.launcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.concordia.ankhMorPork.manager.Board;
import com.concordia.ankhMorPork.manager.BoardManager;

public class AnkhMorPorkLauncher {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		String input = null;
		int playerCount=0;
		List<String> playerName = new ArrayList<String>();
		List<String> colorList = new ArrayList<String>();
		System.out.println("==============================================================");
		System.out.println("        Welcome to DisckWorld : AnkhMorpork                   ");
		System.out.println("==============================================================\n\n\n");
		System.out.println("Press 'R' to Resume the game or Press 'N' to Start a new game\n\n\n");
		Board board;
		BoardManager boardManager = new BoardManager();
		Scanner sc = new Scanner(System.in);
		String nameOfPlayer=null,colorOfPlayer=null;
		input=sc.nextLine();
		if("N".equalsIgnoreCase(input))
		{
			System.out.println("Best of Luck with your New game\n\n");
			System.out.println("Enter the number of player would like to Play ");
			Scanner sc1 = new Scanner(System.in);
			try
			{
				playerCount=Integer.parseInt(sc1.nextLine());
			for(int i =0 ; i < playerCount ; i++)
			{
				System.out.println("Enter the name of Player"+(i+1));
				nameOfPlayer=sc1.nextLine();
				playerName.add(nameOfPlayer);
				System.out.println("Hi "+nameOfPlayer+"! \n");
				System.out.println("Please type the color you would prefer to play !! (Red OR Green OR Blue OR Yellow)");
				colorOfPlayer=sc1.nextLine();
				colorList.add(colorOfPlayer);
			}
			
			board=boardManager.initializeBoardforNewPlayer(playerCount, playerName, colorList);
			boardManager.setBoard(board);
			boardManager.displayCurrentStatus(board);
			}catch(NumberFormatException e)
			{
				System.out.println("Invalid Input : Number of player must be integer between 2 and 4");
			}
		}
		else if("R".equalsIgnoreCase(input))
		{
			System.out.println("Enter the Filename to load the game");
			
		}
		else
		{
			System.out.println("Invalid Input");
		}
	}
}
