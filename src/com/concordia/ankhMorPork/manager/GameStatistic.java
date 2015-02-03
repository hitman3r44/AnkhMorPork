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
