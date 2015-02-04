package com.concordia.ankhMorPork.manager;

import java.util.List;

/**
 * @author elDiablo
 * Feb 3, 2015
 * 5:00:23 AM
 * 2015
 * @email: sumitsarkarbd@gmail.com
 */
public class GameStatistic {
	private Integer numberOfPlayers;
	private List<PlayerInventory> playerInventoryList;
	private List<Player> playersList;
	private double bankMoney;
	private List<CityAreaCard> cityAreaCardList;
	private List<Area> areaArrayList;

	public List<Area> getAreaArrayList() {
		return areaArrayList;
	}

	public void setAreaArrayList(List<Area> areaArrayList) {
		this.areaArrayList = areaArrayList;
	}

	public List<CityAreaCard> getCityAreaCardList() {
		return cityAreaCardList;
	}

	public void setCityAreaCardList(List<CityAreaCard> cityAreaCardList2) {
		this.cityAreaCardList = cityAreaCardList2;
	}

	public double getBankMoney() {
		return bankMoney;
	}

	public void setBankMoney(double bankMoney) {
		this.bankMoney = bankMoney;
	}

	public List<Player> getPlayersList() {
		return playersList;
	}

	public void setPlayersList(List<Player> playersList) {
		this.playersList = playersList;
	}

	public List<PlayerInventory> getPlayerInventoryList() {
		return playerInventoryList;
	}

	public void setPlayerInventoryList(List<PlayerInventory> playerInventoryList) {
		this.playerInventoryList = playerInventoryList;
	}

	public Integer getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(Integer numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}
	

}
