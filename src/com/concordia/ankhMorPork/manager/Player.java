package com.concordia.ankhMorPork.manager;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private int playerId;
	private String name;
	private String color;
	private Integer minionsOnBoard;
	private Integer buildingOnBoard;
	private Integer playerMoney;
	private Integer personalityCard;
	private String personalityCardString;
	
	public String getPersonalityCardString() {
		return personalityCardString;
	}

	public void setPersonalityCardString(String personalityCardString) {
		this.personalityCardString = personalityCardString;
	}

	private List<Integer> greenPlayerCards;
	private List<Integer> brownPlayerCards;
	private List<Integer> cityAreaCard;

	/**
	 * Parameterized constructor to initialize Player details on initialization.
	 * @param playerID : Assigning player id to class member.
	 * @param playerName	: Setting up the player name.
	 * @param color : Giving a color to a player's object.
	 
	 */
	
	
	public Player(int playerID, String playerName, String color) {
		this.playerId = playerID;
		this.name = playerName;
		this.color = color;
		this.greenPlayerCards = new ArrayList<Integer>();

	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getMinionsOnBoard() {
		return minionsOnBoard;
	}

	public void setMinionsOnBoard(Integer minionsOnBoard2) {
		this.minionsOnBoard = minionsOnBoard2;
	}

	public Integer getBuildingOnBoard() {
		return buildingOnBoard;
	}

	public void setBuildingOnBoard(Integer buildingOnBoard) {
		this.buildingOnBoard = buildingOnBoard;
	}

	public Integer getPlayerMoney() {
		return playerMoney;
	}

	public void setPlayerMoney(Integer playerMoney) {
		this.playerMoney = playerMoney;
	}

	public Integer getPersonalityCard() {
		return personalityCard;
	}

	public void setPersonalityCard(Integer personalityCard) {
		this.personalityCard = personalityCard;
	}

	public List<Integer> getGreenPlayerCards() {
		return greenPlayerCards;
	}

	public void setGreenPlayerCards(List<Integer> playerCards) {
		this.greenPlayerCards = playerCards;
	}

	public List<Integer> getBrownPlayerCards() {
		return brownPlayerCards;
	}

	public void setBrownPlayerCards(List<Integer> playerCards) {
		this.brownPlayerCards = playerCards;
	}

	public List<Integer> getCityAreaCard() {
		return cityAreaCard;
	}

	public void setCityAreaCard(List<Integer> cityAreaCard) {
		this.cityAreaCard = cityAreaCard;
	}

}
