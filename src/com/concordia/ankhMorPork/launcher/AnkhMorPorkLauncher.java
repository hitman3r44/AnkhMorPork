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

	private Scanner userInputForLoadOrNewGameScanner = new Scanner(System.in);
	private Scanner userNameInput = new Scanner(System.in);
	private Scanner userInputScanner = new Scanner(System.in);

	private String nameOfPlayer = null, colorOfPlayer = null;
	AnkhMorPorkLauncher ankhMorPorkLauncher;

	public AnkhMorPorkLauncher() {
		// ankhMorPorkLauncher = new AnkhMorPorkLauncher();
//		userInputForLoadOrNewGameScanner = 
	}
	/**
	 * The newGame method is responsible to start a new game. 
	 * The method is called when a user choose to start a new game	 
	 * <p>
	 * This method always asked to input the number of player playing the new game.
	 * Then it's asked to enter the players' name and system assigned a color to player
	 * for his / her minions and other objects, an amount is also assigned to each player
	 * according to game rules to initialize the game at its start point.
	 * </p>
	 * 
	 * @see         Interactive screen taking users' input
	 */
	public void newGame() {

		System.out.println("Best of Luck with your New game\n\n");
		System.out.println("How many players would like to play : (Number of player must be between 2 and 4)");

		try {
			Global.numberOfPlayers = Integer.parseInt(userInputScanner
					.nextLine());
			while(Global.numberOfPlayers<2 || Global.numberOfPlayers>4){
				System.out.println("Number of player must be between 2 and 4");
				Global.numberOfPlayers = Integer.parseInt(userInputScanner
						.nextLine());
			}

			for (int i = 0; i < Global.numberOfPlayers; i++) {
				System.out.println("Enter the name of Player" + (i + 1));
				nameOfPlayer = userNameInput.nextLine();
				playerName.add(nameOfPlayer);
				System.out.println("Hi " + nameOfPlayer + "! \n");

				colorOfPlayer = Global.colorList.get(i);
				System.out.println("Your Color is: " + colorOfPlayer);
				colorList.add(colorOfPlayer);
			}
			board = boardManager.initializeBoardforNewPlayer(
					Global.numberOfPlayers, playerName, colorList);

			boardManager.setBoard(board);
			gameStatus();

		} catch (NumberFormatException e) {
			System.out
			.println("Invalid Input : Number of player must be integer between 2 and 4");
		}

	}
	/**
	 * The gameStatus method shows the current status of the game. 
	 * The method is called when a user choose to see the game's status.	 
	 * <p>
	 * This method asked to input the Y or N if need to see the game status or not.
	 * On selecting Y it shows the current board status.
	 * </p>
	 * 
	 * @see         You see which areas are occupied with which minions, buildings etc.. 
	 * @see         How much a player have dollors, which personality card. 
	 */
	
	private void gameStatus() {
		// See Game Status
		System.out.println("\nWould you Like to see the Game Status??\t\tYes(Y)\tNo(N)");
		if ("Y".equalsIgnoreCase(userInputForLoadOrNewGameScanner
				.nextLine())) {
			boardManager.displayCurrentStatus(boardManager.getBoard());
			saveGame();
		}
		else if ("N".equalsIgnoreCase(userInputForLoadOrNewGameScanner
				.nextLine())) {
			saveGame();

		}
		
	}
	/**
	 * The savegame method saves the games current state. 
	 * The method is called when a user choose to save the game's status.	 
	 * <p>
	 * This method asked to input the file name you want to save the game.
	 * It save the game status in json format. JSON format was selected due to it's 
	 * efficiency and compatibility.
	 * </p>
	 * 
	 * @see         On calling this function, you are asked to save file name 
	 * 
	 */
	public void saveGame() {

		System.out.println("\n\n\nPress 'S' to save the game");
		String userInputForSavingTheGame = userInputScanner.nextLine();

		if ("S".equalsIgnoreCase(userInputForSavingTheGame)) {
			// Take save file name from the user
			System.out.println("Please enter the name of file to save:");
			Global.saveFileName = userInputScanner.nextLine();
			// boardManager.saveGameStatus(Global.SAVED_FILE_DIRECTORY_PATH+"/"+Global.saveFileName,ankhMorPorkLauncher.boardManager.getBoard());
			// Generate the JSON format file as a save file
			GameStateJsonGenerator gameStateJsonGenerator = new GameStateJsonGenerator();
			gameStateJsonGenerator.saveGameCurrentStateToJsonFormate(board);

		}
	}
	/**
	 * The loadGame method load the saved game. 
	 * The method is called when a user choose to load an existing game.	 
	 * <p>
	 * This method asked to input the file name you want to load an existing game.
	 * Json formatted file name is passed to a parseJson function for further operation.
	 * </p>
	 * 
	 * @see         On calling this function, you are asked to type a file name. 
	 * 
	 */
	public void loadGame() {

		System.out.println("Enter the Filename to load the game");
		
		String fileName = userInputForLoadOrNewGameScanner.nextLine();
		board = GameStateJsonParser.parseJson(fileName,
				ankhMorPorkLauncher.board);
	}
	/**
	 * The chooseGameState is function to perform the start function of the game. 
	 * The method is called by default by the system.	 
	 * <p>
	 * This method asked to input N for new game, R to load an existing game and M to show different
	 * menus option.
	 * </p>
	 * 
	 * @see         On calling this function, you are asked to type a character to perform further. 
	 * 
	 */
	public void chooseGameState() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Common.displayMenu();
		input = scanner.nextLine(); 	 	 	
			
			switch (input) {
			case "N":
				newGame();
				break;
			case "n":
				newGame();
				break;
			case "R":
				loadGame();
				break;
			case "r":
				loadGame();
				break;
			case "M":
				Common.displayMenu();
				break;
			case "m":
				break;
			default:
				System.out.println("Invalid Input");
				chooseGameState();
				break;
				
				
			}
			chooseGameState();
		

	}

	public static void main(String[] args) {
		AnkhMorPorkLauncher ankhMorPorkLauncher = new AnkhMorPorkLauncher();

		Common.display();
		ankhMorPorkLauncher.chooseGameState();

	}
}
