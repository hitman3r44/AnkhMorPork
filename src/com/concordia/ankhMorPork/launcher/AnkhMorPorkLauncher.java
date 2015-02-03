package com.concordia.ankhMorPork.launcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.concordia.ankhMorPork.common.Global;
import com.concordia.ankhMorPork.data.GameStateJsonGenerator;
import com.concordia.ankhMorPork.manager.Board;
import com.concordia.ankhMorPork.manager.BoardManager;

public class AnkhMorPorkLauncher {

	private String input = null;
	private List<String> playerName = new ArrayList<String>();
	private List<String> colorList = new ArrayList<String>();
	private Board board;
	private BoardManager boardManager = new BoardManager();

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AnkhMorPorkLauncher ankhMorPorkLauncher = new AnkhMorPorkLauncher();

		System.out.println("==============================================================");
		System.out.println("        Welcome to DisckWorld : AnkhMorpork                   ");
		System.out.println("==============================================================\n\n\n");
		System.out.println("Press 'R' to Resume the game or Press 'N' to Start a new game\n\n\n");

		Scanner userInputForLoadOrNewGameScanner = new Scanner(System.in);
		
		String nameOfPlayer = null, colorOfPlayer = null;
		
		ankhMorPorkLauncher.input = userInputForLoadOrNewGameScanner.nextLine();
		
		if ("N".equalsIgnoreCase(ankhMorPorkLauncher.input)) {
			System.out.println("Best of Luck with your New game\n\n");
			System.out.println("Enter the number of player would like to Play ");
			
			Scanner userInputScanner = new Scanner(System.in);
			try {
				Global.numberOfPlayers = Integer.parseInt(userInputScanner.nextLine());
				
				for (int i = 0; i < Global.numberOfPlayers; i++) {
					System.out.println("Enter the name of Player" + (i + 1));
					nameOfPlayer = userInputScanner.nextLine();
					ankhMorPorkLauncher.playerName.add(nameOfPlayer);
					System.out.println("Hi " + nameOfPlayer + "! \n");
					System.out.println("Please type the color you would prefer to play !! "
							+ "(Red OR Green OR Blue OR Yellow)");
					colorOfPlayer = userInputScanner.nextLine();
					ankhMorPorkLauncher.colorList.add(colorOfPlayer);
				}

				ankhMorPorkLauncher.board = ankhMorPorkLauncher.boardManager
						.initializeBoardforNewPlayer(Global.numberOfPlayers,
								ankhMorPorkLauncher.playerName,
								ankhMorPorkLauncher.colorList);
				
				ankhMorPorkLauncher.boardManager.setBoard(ankhMorPorkLauncher.board);
				ankhMorPorkLauncher.boardManager.displayCurrentStatus(ankhMorPorkLauncher.board);
				
				System.out.println("\n\n\nPress 'S' to save the game");
				String userInputForSavingTheGame = userInputScanner.nextLine();
				
				if ("S".equalsIgnoreCase(userInputForSavingTheGame)) {
					//Take save file name from the user
					System.out.println("Please entre the save file name:");
					Global.saveFileName = userInputScanner.nextLine();
					
					//Generate the JSON formate file as a save file
					GameStateJsonGenerator gameStateJsonGenerator = new GameStateJsonGenerator();
					gameStateJsonGenerator.saveGameCurrentStateToJsonFormate(ankhMorPorkLauncher.board);
					
				}else {
					System.out.println("Sorry you put the wrong input");
				}
				
			} catch (NumberFormatException e) {
				System.out.println("Invalid Input : Number of player must be integer between 2 and 4");
			}
		} else if ("R".equalsIgnoreCase(ankhMorPorkLauncher.input)) {
			System.out.println("Enter the Filename to load the game");

		} else {
			System.out.println("Invalid Input");
		}
	}
}
