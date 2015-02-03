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
 *This class contains detailed information about CityAreaCard
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
