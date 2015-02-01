package com.concordia.ankhMorPork.manager;

import java.util.UUID;

/**
 * @author elDiablo
 * Jan 29, 2015
 * 10:41:55 AM
 * 2015
 * @email: sumitsarkarbd@gmail.com
 */
public class Area {
	
	private UUID identifier;
	private int numberOfMinios;
	private int numberOfDemons;
	private int numberOfTrolls;
	private boolean troubleMaker;
	private boolean building;
	
	public UUID getIdentifier() {
		return identifier;
	}
	public void setIdentifier(UUID identifier) {
		this.identifier = identifier;
	}
	public int getNumberOfMinios() {
		return numberOfMinios;
	}
	public void setNumberOfMinios(int numberOfMinios) {
		this.numberOfMinios = numberOfMinios;
	}
	public int getNumberOfDemons() {
		return numberOfDemons;
	}
	public void setNumberOfDemons(int numberOfDemons) {
		this.numberOfDemons = numberOfDemons;
	}
	public int getNumberOfTrolls() {
		return numberOfTrolls;
	}
	public void setNumberOfTrolls(int numberOfTrolls) {
		this.numberOfTrolls = numberOfTrolls;
	}
	public boolean isTroubleMaker() {
		return troubleMaker;
	}
	public void setTroubleMaker(boolean troubleMaker) {
		this.troubleMaker = troubleMaker;
	}
	public boolean isBuilding() {
		return building;
	}
	public void setBuilding(boolean building) {
		this.building = building;
	}
	

}
