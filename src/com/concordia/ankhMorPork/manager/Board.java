package com.concordia.ankhMorPork.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
	
	private List<Area> area;
	private Integer bankMoney;
	private Map<String,String> personalityCardDetails;
	private List<CityAreaCard> cityAreaCard;
	private HashMap<Integer, HashMap<String, String>> randomEventCard;
	private List<PlayerCards> playerCard;
	private Integer noOfPlayer;
	private List<Player> playerList;
	private List<Integer> existingPlayercards;
	
	public List<Area> getArea() {
		return area;
	}
	public void setArea(List<Area> area) {
		this.area = area;
	}
	public Integer getBankMoney() {
		return bankMoney;
	}
	public void setBankMoney(Integer bankMoney) {
		this.bankMoney = bankMoney;
	}
	public Map<String, String> getPersonalityCardDetails() {
		return personalityCardDetails;
	}
	public void setPersonalityCardDetails(Map<String, String> personalityCardDetails) {
		this.personalityCardDetails = personalityCardDetails;
	}
	public List<CityAreaCard> getCityAreaCard() {
		return cityAreaCard;
	}
	public void setCityAreaCard(List<CityAreaCard> cityAreaCard) {
		this.cityAreaCard = cityAreaCard;
	}
	public HashMap<Integer, HashMap<String, String>> getRandomEventCard() {
		return randomEventCard;
	}
	public void setRandomEventCard(HashMap<Integer, HashMap<String, String>> randomEventCards) {
		this.randomEventCard = randomEventCards;
	}
	public List<PlayerCards> getPlayerCard() {
		return playerCard;
	}
	public void setPlayerCard(List<PlayerCards> playerCard) {
		this.playerCard = playerCard;
	}
	public Integer getNoOfPlayer() {
		return noOfPlayer;
	}
	public void setNoOfPlayer(Integer noOfPlayer) {
		this.noOfPlayer = noOfPlayer;
	}
	public List<Player> getPlayerList() {
		return playerList;
	}
	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}
	public List<Integer> getExistingPlayercards() {
		return existingPlayercards;
	}
	public void setExistingPlayercards(List<Integer> existingPlayercards) {
		this.existingPlayercards = existingPlayercards;
	}
	

	
}
