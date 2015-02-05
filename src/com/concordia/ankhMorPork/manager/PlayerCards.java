package com.concordia.ankhMorPork.manager;

import java.util.UUID;

/**
 * @author elDiablo
 * Jan 29, 2015
 * 10:44:24 AM
 * 2015
 * @email: sumitsarkarbd@gmail.com
 */
public class PlayerCards {
	
	private UUID identifier;
	private String name;
	private String color;
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
