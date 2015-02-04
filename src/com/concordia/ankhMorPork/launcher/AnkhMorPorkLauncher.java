package com.concordia.ankhMorPork.launcher;

/**
 * 
 * @author VarunPattiah
 * Feb 3, 2015
 * 9:56:03 AM
 * 2015
 * @email: varunpattiah@gmail.com
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.concordia.ankhMorPork.common.Common;
import com.concordia.ankhMorPork.common.Global;
import com.concordia.ankhMorPork.data.GameStateJsonGenerator;
import com.concordia.ankhMorPork.data.GameStateJsonParser;
import com.concordia.ankhMorPork.manager.Board;
import com.concordia.ankhMorPork.manager.BoardManager;

/**
 * This Class Launch the game and used as a input interface between user and the
 * game.
 */

public class AnkhMorPorkLauncher {

	private String input = null;
	private List<String> playerName = new ArrayList<String>();
	private List<String> colorList = new ArrayList<String>();
	private Board board;
	private BoardManager boardManager = new BoardManager();

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		String fileName = null;
		AnkhMorPorkLauncher ankhMorPorkLauncher = new AnkhMorPorkLauncher();
		Common.display();
		System.out
				.println("Press 'R' to Resume the game or Press 'N' to Start a new game\n\n\n");

		Scanner userInputForLoadOrNewGameScanner = new Scanner(System.in);
		Scanner userNameInput = new Scanner(System.in);
		Scanner colorNameInput = new Scanner(System.in);
		Scanner userInputScanner = new Scanner(System.in);

		String nameOfPlayer = null, colorOfPlayer = null;

		ankhMorPorkLauncher.input = userInputForLoadOrNewGameScanner.nextLine();

		if ("N".equalsIgnoreCase(ankhMorPorkLauncher.input)) {
			System.out.println("Best of Luck with your New game\n\n");
			System.out.println("How many players you want to play: ");

			try {
				Global.numberOfPlayers = Integer.parseInt(userInputScanner
						.nextLine());

				do {
					System.out.println("PLease enter a number between 2 and 4!");
					
					while (!userInputScanner.hasNextInt()) {
						userInputScanner.next(); // this is important!
					}
					Global.numberOfPlayers = userInputScanner.nextInt();
				} while (Global.numberOfPlayers <= 0
						|| Global.numberOfPlayers < 2
						|| Global.numberOfPlayers > 4);
				System.out.println("Thank you! Got " + Global.numberOfPlayers);

				for (int i = 0; i < Global.numberOfPlayers; i++) {
					System.out.println("Enter the name of Player" + (i + 1));
					nameOfPlayer = userNameInput.nextLine();
					ankhMorPorkLauncher.playerName.add(nameOfPlayer);
					System.out.println("Hi " + nameOfPlayer + "! \n");

					colorOfPlayer = Global.colorList.get(i);
					System.out.println("Your Color is: "+ colorOfPlayer);

					if (ankhMorPorkLauncher.colorList.size() != 0
							&& ankhMorPorkLauncher.colorList
									.contains(colorOfPlayer)) {
						System.out
								.println("This color is already taken by other player. Please choose color other than this "
										+ ankhMorPorkLauncher.colorList);
						colorOfPlayer = userInputScanner.nextLine();
					}
					ankhMorPorkLauncher.colorList.add(colorOfPlayer);
				}
				ankhMorPorkLauncher.board = ankhMorPorkLauncher.boardManager
						.initializeBoardforNewPlayer(Global.numberOfPlayers,
								ankhMorPorkLauncher.playerName,
								ankhMorPorkLauncher.colorList);

				ankhMorPorkLauncher.boardManager
						.setBoard(ankhMorPorkLauncher.board);

				// See Game Status
				System.out
						.println("\nWould you Like to see the Game Status??\t\tYes\tNo");
				if ("Yes".equalsIgnoreCase(userInputForLoadOrNewGameScanner
						.nextLine())) {
					ankhMorPorkLauncher.boardManager
							.displayCurrentStatus(ankhMorPorkLauncher.boardManager
									.getBoard());
				}

				System.out.println("\n\n\nPress 'S' to save the game");
				String userInputForSavingTheGame = userInputScanner.nextLine();

				if ("S".equalsIgnoreCase(userInputForSavingTheGame)) {
					// Take save file name from the user
					System.out
							.println("Please enter the name of file to save:");
					Global.saveFileName = userInputScanner.nextLine();
					// ankhMorPorkLauncher.boardManager.saveGameStatus(Global.SAVED_FILE_DIRECTORY_PATH+"/"+Global.saveFileName,ankhMorPorkLauncher.boardManager.getBoard());
					// Generate the JSON format file as a save file
					GameStateJsonGenerator gameStateJsonGenerator = new GameStateJsonGenerator();
					gameStateJsonGenerator
							.saveGameCurrentStateToJsonFormate(ankhMorPorkLauncher.board);

				} else {
					System.out.println("Sorry you put the wrong input");
				}

			} catch (NumberFormatException e) {
				System.out
						.println("Invalid Input : Number of player must be integer between 2 and 4");
			}
		} else if ("R".equalsIgnoreCase(ankhMorPorkLauncher.input)) {
			System.out.println("Enter the Filename to load the game");
			fileName = userInputForLoadOrNewGameScanner.nextLine();
			ankhMorPorkLauncher.board = GameStateJsonParser.parseJson(fileName,
					ankhMorPorkLauncher.board);
			// /commented the below code to load from text file, since working
			// on json
			/*
			 * try {
			 * 
			 * userInputForLoadOrNewGameScanner = new Scanner(new
			 * File("./resources/savedGame/"+fileName));
			 * 
			 * while (userInputForLoadOrNewGameScanner.hasNext()) {
			 * StringBuilder sb = new StringBuilder();
			 * sb.append(userInputForLoadOrNewGameScanner.nextLine());
			 * inputArray = sb.toString().split("="); if (inputArray.length ==
			 * 2) { //InputMap.put(inputArray[0], inputArray[1]);
			 * inputList.add(sb.toString());
			 * 
			 * } else{ System.out.println("input validation fails"); } }
			 * ankhMorPorkLauncher
			 * .board=ankhMorPorkLauncher.boardManager.updatePlayerInfo
			 * (inputList); } catch (FileNotFoundException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */
		} else {
			System.out.println("Invalid Input");
		}

	}
}
