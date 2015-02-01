package com.concordia.ankhMorPork.manager;

import java.util.UUID;

/**
 * @author elDiablo
 * Jan 29, 2015
 * 10:38:57 AM
 * 2015
 * @email: sumitsarkarbd@gmail.com
 */


public class Player {
	
	private UUID identifier;
	private String name;
	private String color;
	private int minions;
	private int buldings;
	private int money;
	private PersonalityCards personalityCards;
	private CityAreaCard cityAreaCard;
	
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getMinions() {
		return minions;
	}
	public void setMinions(int minions) {
		this.minions = minions;
	}
	public int getBuldings() {
		return buldings;
	}
	public void setBuldings(int buldings) {
		this.buldings = buldings;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public PersonalityCards getPersonalityCards() {
		return personalityCards;
	}
	public void setPersonalityCards(PersonalityCards personalityCards) {
		this.personalityCards = personalityCards;
	}
	public CityAreaCard getCityAreaCard() {
		return cityAreaCard;
	}
	public void setCityAreaCard(CityAreaCard cityAreaCard) {
		this.cityAreaCard = cityAreaCard;
	}

}
