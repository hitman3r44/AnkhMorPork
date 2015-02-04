package com.concordia.ankhMorPork.manager;

import java.util.ArrayList;

/**
 * @author elDiablo
 * Feb 3, 2015
 * 3:50:00 AM
 * 2015
 * @email: sumitsarkarbd@gmail.com
 */
public class PlayerInventory {
	private Integer playerNumber;
	private Integer minions;
	private Integer buildings;
	private double money;
	private String playerName;
	private String playerChoosableColor;
	private String playerPersonalityCard;
	private ArrayList<String> cityAreaList;
	
	
	public ArrayList<String> getCityAreaList() {
		return cityAreaList;
	}
	public void setCityAreaList(ArrayList<String> cityAreaList) {
		this.cityAreaList = cityAreaList;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getPlayerChoosableColor() {
		return playerChoosableColor;
	}
	public void setPlayerChoosableColor(String playerChoosableColor) {
		this.playerChoosableColor = playerChoosableColor;
	}
	public String getPlayerPersonalityCard() {
		return playerPersonalityCard;
	}
	public void setPlayerPersonalityCard(String playerPersonalityCard) {
		this.playerPersonalityCard = playerPersonalityCard;
	}
	public Integer getPlayerNumber() {
		return playerNumber;
	}
	public void setPlayerNumber(Integer playerNumber) {
		this.playerNumber = playerNumber;
	}
	public Integer getMinions() {
		return minions;
	}
	public void setMinions(Integer minions) {
		this.minions = minions;
	}
	public Integer getBuildings() {
		return buildings;
	}
	public void setBuildings(Integer buildings) {
		this.buildings = buildings;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	

}
