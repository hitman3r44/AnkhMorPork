package com.concordia.ankhMorPork.common;

import java.util.ArrayList;

import com.concordia.ankhMorPork.manager.GameStatistic;

/**
 * @author elDiablo
 *
 */

/*
 * In this file we will write the global components
 * what will need all over the project 
 * */
public class Global {
	
	final public static Integer BUILDINGS = 6;
	final public static Integer MINIONS = 12;
	final public static double INITIAL_MONEY = 10.00;
	final public static ArrayList<String> LIST_OF_SAVED_FILES = new ArrayList<String>();
	final public static String SAVED_FILE_DIRECTORY_PATH = "resources/savedGame";
	final public static Integer BANK_MONEY = 150;
	final public static Integer NO_OF_AREA=12;
	public static String saveFileName;
	public static Integer numberOfPlayers;
	public static GameStatistic gameStatistic=new GameStatistic();

}
