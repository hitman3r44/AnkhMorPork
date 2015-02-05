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
	
	/**
	 * The function retrieves the city area card identifier
	 * @return identifier of city cards 
	 */
	public Integer getIdentifier() {
		return identifier;
	}
	/**
	 * sets the identifier of city area cards to differentiate them 
	 */
	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}
	/**
	 * Fetches the name of the city area cards
	 * @return the name of the city area card
	 */
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
