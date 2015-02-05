package com.concordia.ankhMorPork.manager;
/**
 * 
 * @author VarunPattiah
 * Feb 3, 2015
 * 10:38:54 AM
 * 2015
 * @email: varunpattiah@gmail.com
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Class contains the information about the game including details of each player
 * Complete Information about area,personalityCardDetails,cityAreaCard,randomEventCard,playerCard
 */
public class Board {
	//instance variable
	private List<Area> area;
	private Integer bankMoney;
	private Map<String,String> personalityCardDetails;
	private List<CityAreaCard> cityAreaCard;
	private HashMap<Integer, HashMap<String, String>> randomEventCard;
	private Integer noOfPlayer;
	private List<Player> playerList;
	private List<Integer> existingPlayercards;
	
	//setters and getters
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
