package com.concordia.ankhMorPork.manager;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author elDiablo
 * Feb 1, 2015
 * 2015
 * @email: varun@gmail.com
 */
public class BoardDetails {
	
	private UUID identifier;
	private ArrayList<Area> area;
	private double money;
	private ArrayList<PersonalityCards> personalityCardsList;
	private ArrayList<RandomEventCards> randomEventCardsList;
	private ArrayList<PlayerCards> playerCardsList;
	private int numberOfPlayers;
	private ArrayList<Player> playersList;
	
	public UUID getIdentifier() {
		return identifier;
	}
	public void setIdentifier(UUID identifier) {
		this.identifier = identifier;
	}
	public ArrayList<Area> getArea() {
		return area;
	}
	public void setArea(ArrayList<Area> area) {
		this.area = area;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public ArrayList<PersonalityCards> getPersonalityCardsList() {
		return personalityCardsList;
	}
	public void setPersonalityCardsList(
			ArrayList<PersonalityCards> personalityCardsList) {
		this.personalityCardsList = personalityCardsList;
	}
	public ArrayList<RandomEventCards> getRandomEventCardsList() {
		return randomEventCardsList;
	}
	public void setRandomEventCardsList(
			ArrayList<RandomEventCards> randomEventCardsList) {
		this.randomEventCardsList = randomEventCardsList;
	}
	public ArrayList<PlayerCards> getPlayerCardsList() {
		return playerCardsList;
	}
	public void setPlayerCardsList(ArrayList<PlayerCards> playerCardsList) {
		this.playerCardsList = playerCardsList;
	}
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}
	public ArrayList<Player> getPlayersList() {
		return playersList;
	}
	public void setPlayersList(ArrayList<Player> playersList) {
		this.playersList = playersList;
	}
	

}
