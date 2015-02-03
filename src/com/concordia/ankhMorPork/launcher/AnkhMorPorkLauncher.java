package com.concordia.ankhMorPork.launcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.concordia.ankhMorPork.common.Global;
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

		Scanner scanner = new Scanner(System.in);
		
		String nameOfPlayer = null, colorOfPlayer = null;
		
		ankhMorPorkLauncher.input = scanner.nextLine();
		
		if ("N".equalsIgnoreCase(ankhMorPorkLauncher.input)) {
			System.out.println("Best of Luck with your New game\n\n");
			System.out.println("Enter the number of player would like to Play ");
			
			Scanner sc1 = new Scanner(System.in);
			try {
				Global.numberOfPlayers = Integer.parseInt(sc1.nextLine());
				for (int i = 0; i < Global.numberOfPlayers; i++) {
					System.out.println("Enter the name of Player" + (i + 1));
					nameOfPlayer = sc1.nextLine();
					ankhMorPorkLauncher.playerName.add(nameOfPlayer);
					System.out.println("Hi " + nameOfPlayer + "! \n");
					System.out.println("Please type the color you would prefer to play !! "
							+ "(Red OR Green OR Blue OR Yellow)");
					colorOfPlayer = sc1.nextLine();
					ankhMorPorkLauncher.colorList.add(colorOfPlayer);
				}

				ankhMorPorkLauncher.board = ankhMorPorkLauncher.boardManager
						.initializeBoardforNewPlayer(Global.numberOfPlayers,
								ankhMorPorkLauncher.playerName,
								ankhMorPorkLauncher.colorList);
				
				ankhMorPorkLauncher.boardManager.setBoard(ankhMorPorkLauncher.board);
				ankhMorPorkLauncher.boardManager.displayCurrentStatus(ankhMorPorkLauncher.board);
				
			} catch (NumberFormatException e) {
				System.out
						.println("Invalid Input : Number of player must be integer between 2 and 4");
			}
		} else if ("R".equalsIgnoreCase(ankhMorPorkLauncher.input)) {
			System.out.println("Enter the Filename to load the game");

		} else {
			System.out.println("Invalid Input");
		}
	}
}
