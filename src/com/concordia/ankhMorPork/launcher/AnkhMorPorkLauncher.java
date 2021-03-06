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
import com.concordia.ankhMorPork.manager.WinningCondition;

/**
 * This Class Launch the game and used as a input interface between user and the
 * game.
 */

public class AnkhMorPorkLauncher {

	private String input = null;
	private List<String> playerName = new ArrayList<String>();
	private List<String> colorList = new ArrayList<String>();
	private Board board;
	private BoardManager boardManager;

	private Scanner userInputForLoadOrNewGameScanner = new Scanner(System.in);
	private Scanner userNameInput = new Scanner(System.in);
	private Scanner userInputScanner = new Scanner(System.in);
	private String nameOfPlayer = null, colorOfPlayer = null;
	public static AnkhMorPorkLauncher ankhMorPorkLauncher=null;
	public WinningCondition winningCondition=new WinningCondition();
	public AnkhMorPorkLauncher() {
		this.boardManager = new BoardManager();
	}
	public static AnkhMorPorkLauncher getInstanceOf(){
		if(ankhMorPorkLauncher==null){
			ankhMorPorkLauncher=new AnkhMorPorkLauncher();
		}
		return ankhMorPorkLauncher;
		
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

				colorOfPlayer = Global.Color.get(i);
				System.out.println("Your Color is: " + colorOfPlayer);
				colorList.add(colorOfPlayer);
			}
			board = boardManager.initializeBoardforNewPlayer(
					Global.numberOfPlayers, playerName, colorList);

			boardManager.setBoard(board);
			gameStatus(ankhMorPorkLauncher);

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
	 * @param ankhMorPorkLauncher2 
	 * 
	 * @see         You see which areas are occupied with which minions, buildings etc.. 
	 * @see         How much a player have dollors, which personality card. 
	 */
	
	private void gameStatus(AnkhMorPorkLauncher ankhMorPorkLauncher2) {
		// See Game Status
		System.out.println("\nWould you Like to see the Game Status??\t\tYes(Y)\tNo(N)");
		String input=userInputForLoadOrNewGameScanner
				.nextLine();
		if ("Y".equalsIgnoreCase(input)) {
			boardManager.displayBoardStatus(boardManager.getBoard());
			saveGame(ankhMorPorkLauncher2);
		}
		else if ("N".equalsIgnoreCase(input)) {
			playGame(ankhMorPorkLauncher2);
			//saveGame(ankhMorPorkLauncher2);

		}
		else
		{
			System.out.println("\nInvalid Input !! lets Get back to Menu\n");
			chooseGameState(ankhMorPorkLauncher2);
		}
		
	}
	private void playGame(AnkhMorPorkLauncher ankhMorPorkLauncher2) {
		int playerTurn=boardManager.getBoard().getPlayerTurn() - 1;
		boolean win=validateWinningCondition(ankhMorPorkLauncher2,boardManager.getBoard().getPlayerList().get(playerTurn).getPersonalityCard());
		if(!win){
		board=boardManager.showPlayerDetails(boardManager.getBoard(),boardManager.getBoard().getPlayerList().get(playerTurn));
		boardManager.setBoard(board);
		}else{
			System.out.println("\n\t\t\t\tCongratulation!!");
			System.out.println("\n\n\t\t"+board.getPlayerList().get(playerTurn).getName()+" have won the game.");
		}
		
	}

	private boolean validateWinningCondition(AnkhMorPorkLauncher ankhMorPorkLauncher2, Integer personalityID) {
		boolean win=false;
		Board board=ankhMorPorkLauncher2.boardManager.getBoard();
		switch (personalityID) {
		case 1:
			win=this.winningCondition.lordVetinari(board);
			break;
		case 2:
			win=this.winningCondition.lordSelachii(board);
			break;
		case 3:
			win=this.winningCondition.lordRust(board);
			break;
		case 4:
			win=this.winningCondition.lordDeWorde(board);
			break;
		case 5:
			win=this.winningCondition.dragonKingOfArms(board);
			break;
		case 6:
			win=this.winningCondition.chrysoprase(board);
			break;
		case 7:
			win=this.winningCondition.commanderVimes(board);
			break;
		default:
			break;
			
			
		}
		return false;
	}
	/**
	 * The saveGame method saves the games current state. 
	 * The method is called when a user choose to save the game's status.	 
	 * <p>
	 * This method asked to input the file name you want to save the game.
	 * It save the game status in json format. JSON format was selected due to it's 
	 * efficiency and compatibility.
	 * </p>
	 * @param ankhMorPorkLauncher2 
	 * 
	 * @see         On calling this function, you are asked to save file name 
	 * 
	 */
	public void saveGame(AnkhMorPorkLauncher ankhMorPorkLauncher2) {

		System.out.println("\n\n\nWould you like to save the game\t\t:\t\t(Press 'S')");
		String userInputForSavingTheGame = userInputScanner.nextLine();

		if ("S".equalsIgnoreCase(userInputForSavingTheGame)) {
			// Take save file name from the user
			System.out.println("Please enter the name of file to save:");
			Global.saveFileName = userInputScanner.nextLine();
			// Generate the JSON format file as a save file
			GameStateJsonGenerator gameStateJsonGenerator = new GameStateJsonGenerator();
			gameStateJsonGenerator.saveGameCurrentStateToJsonFormate(board);

		}
		else{
			System.out.println("Invalid Input !! lets Get back to Menu");
			playGame(ankhMorPorkLauncher2);
			//chooseGameState(ankhMorPorkLauncher2);
		}
	}
	/**
	 * The loadGame method load the saved game. 
	 * The method is called when a user choose to load an existing game.	 
	 * <p>
	 * This method asked to input the file name you want to load an existing game.
	 * Json formatted file name is passed to a parseJson function for further operation.
	 * </p>
	 * @param ankhMorPorkLauncher2 
	 * 
	 * @see         On calling this function, you are asked to type a file name. 
	 * 
	 */
	public void loadGame(AnkhMorPorkLauncher ankhMorPorkLauncher2) {
		Common.listOfTheFiles(Global.SAVED_FILE_DIRECTORY_PATH);

		System.out.println("Enter the a Number to load the game");
		
		int userInput = Common.userInputValidationCheck();
		String fileName = Common.userChoice(userInput);
		
//		int fileName = userInputForLoadOrNewGameScanner.nextInt();
		board = GameStateJsonParser.parseJson(fileName,
				ankhMorPorkLauncher2.boardManager.getBoard());
		if(board!=null){
			gameStatus(ankhMorPorkLauncher2);
		}
	}
	/**
	 * The chooseGameState is function to perform the start function of the game. 
	 * The method is called by default by the system.	 
	 * <p>
	 * This method asked to input N for new game, R to load an existing game and M to show different
	 * menus option.
	 * </p>
	 * @param ankhMorPorkLauncher2 
	 * 
	 * @see         On calling this function, you are asked to type a character to perform further. 
	 * 
	 */
	public void chooseGameState(AnkhMorPorkLauncher ankhMorPorkLauncher2) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Common.displayMenu();
		input = scanner.nextLine(); 	 	 	
			
			switch (input.toLowerCase()) {
			case "n":
				newGame();
				break;
			case "r":
				loadGame(ankhMorPorkLauncher2);
				break;
			case "e":
				Common.displayThankyouMenu();
				break;
			default:
				System.out.println("Invalid Input");
				chooseGameState(ankhMorPorkLauncher2);
				break;
				
				
			}
			if(!(input.equalsIgnoreCase("E")))
			{
			chooseGameState(ankhMorPorkLauncher2);
			}
		

	}

	public static void main(String[] args) {
		AnkhMorPorkLauncher ankhMorPorkLauncher = getInstanceOf();

		Common.display();
		ankhMorPorkLauncher.chooseGameState(ankhMorPorkLauncher);

	}
}
