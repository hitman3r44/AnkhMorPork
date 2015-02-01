package com.concordia.ankhMorPork.manager;

import java.util.UUID;

/**
 * @author elDiablo
 * Feb 1, 2015
 * 3:37:53 PM
 * 2015
 * @email: sumitsarkarbd@gmail.com
 */


public class CityAreaCard {
	
	private UUID identifier;
	private String name;
	private double cost;
	private String description;
	
	public UUID getIdentifier() {
		return identifier;
	}
	public void setIdentifier(UUID identifier) {
		this.identifier = identifier;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
