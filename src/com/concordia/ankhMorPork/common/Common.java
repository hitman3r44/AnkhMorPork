package com.concordia.ankhMorPork.common;

import java.io.File;
import java.util.Scanner;

/**
 * @author elDiablo
 * Feb 2, 2015
 * 2:16:36 PM
 * 2015
 * @email: sumitsarkarbd@gmail.com
 */
public class Common {
	
	

	// This function list all the files and directory list of the certain path
	public void listOfTheFiles(String directoryPath){
		
		File folder = new File(directoryPath);
		File[] listOfFiles = folder.listFiles();
		
		if (listOfFiles.length == 0) {
			System.out.println("This directory is empty");
		}
		else{
			for (int i = 0; i < listOfFiles.length; i++) {
				//For files
		      if (listOfFiles[i].isFile()) {
		    	Global.LIST_OF_SAVED_FILES.add(i, listOfFiles[i].getName()
		    			.substring(0, listOfFiles[i].getName().lastIndexOf('.')));
		        System.out.println("File " + listOfFiles[i].getName());
		      }
		      //For directory
		      else if (listOfFiles[i].isDirectory()) {
		        System.out.println("Directory " + listOfFiles[i].getName());
		      }
		    }
		}
		
	}// End of listOfTheFiles
	
	public int userInputValidationCheck(){
		Scanner scanner = new Scanner(System.in);
	    int userInput;
	    
	    do {
	        System.out.println("Please enter a positive number!");
	        while (!scanner.hasNextInt()) {
	            System.out.println("That's not a number!");
	            scanner.next(); // this is important!
	        }
	        userInput = scanner.nextInt();
	        	        
	    } while ((userInput <= 0) || (userInput > Global.LIST_OF_SAVED_FILES.size()));
	    System.out.println("Thank you! Got " + userInput);
	    return userInput;
	    
	} // End of userInputValidationCheck
	
	public void userChoice(Integer arrayListPosition){
		Global.LIST_OF_SAVED_FILES.get(arrayListPosition-1);
		System.out.println(Global.LIST_OF_SAVED_FILES.get(arrayListPosition-1));
	}
	
	
	
	//For testing this class
	public static void main(String[] args){
		Common common = new Common();
		
		System.out.println("This is printing from the functions");
		common.listOfTheFiles(Global.SAVED_FILE_DIRECTORY_PATH);
		
		System.out.println("This is printing from the common list");
		for (int i = 0; i < Global.LIST_OF_SAVED_FILES.size(); i++) {
			System.out.println(Global.LIST_OF_SAVED_FILES.get(i));
			
		}
		System.out.println("Array Size: "+Global.LIST_OF_SAVED_FILES.size());
		
		// User Input thing...
		int userInput = common.userInputValidationCheck();
		System.out.println("Print from this function common.userChoice(userInput)");
		common.userChoice(userInput);
		
	}

	public static void display() {
		System.out
				.println("==============================================================");
		System.out
				.println("        Welcome to DisckWorld : AnkhMorpork                   ");
		System.out
				.println("=============================================================="
						+ "\n" + "\n");
		
	}

	public static void displayMenu() {
		System.out.println("\n\t\t\t Press 'N' to Play a New Game\n");
		System.out.println("\t\t\t Press 'R' to Resume existing Game\n");
		System.out.println("\t\t\t Press 'E' to Exit from the game Game");
	}

	public static void displayThankyouMenu() {
		System.out.println("\t\t\tThank You !! We are Exiting from the game.");
		
	}

}
