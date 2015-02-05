package com.concordia.ankhMorPork.manager;
/**
 * 
 * @author VarunPattiah
 * Feb 3, 2015
 * 10:51:16 AM
 * 2015
 * @email: varunpattiah@gmail.com
 */

/**
 *  CityAreaCard class is defining the structure of the cards that a player will acquire.
 */

public class CityAreaCard {

	private Integer identifier;
	private String name;
	private String description;
	private Integer cost;
	//Setters and getters method
	public Integer getIdentifier() {
		return identifier;
	}
	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	
}
