package com.concordia.ankhMorPork.manager;
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
