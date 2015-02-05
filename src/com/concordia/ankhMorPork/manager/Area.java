package com.concordia.ankhMorPork.manager;

import java.util.ArrayList;
import java.util.List;

/**
 * Class contains the information about an Area on the board.
 * colorOfMinion,troubleMaker,building,noOfDemon and noOfTroll
 */
public class Area {
	
	Integer identifier;
	List<String> colorOfMinion;
	Boolean troubleMaker;
	Boolean building;
	Integer noOfDemon;
	Integer noOfTroll;
	/**
	 * Parameterized constructor to initialize area details on instantiation.
	 * @param troubleMaker : Boolean value if troubleMaker exists on an area.
	 * @param building	: Boolean value if troubleMaker exists on an area.
	 * @param noOfDemon : Number of demons found for that area.
	 * @param noOfTroll : Number of trolls found in that area.
	 */
	public Area(Boolean troubleMaker, Boolean building, Integer noOfDemon, Integer noOfTroll)
	{
		this.troubleMaker=troubleMaker;
		this.building=building;
		this.noOfDemon=noOfDemon;
		this.noOfTroll=noOfTroll;
		this.colorOfMinion=new ArrayList<String>();
	}
	//setters and getters
	public Integer getIdentifier() {
		return identifier;
	}
	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}
	public List<String> getColorOfMinion() {
		return colorOfMinion;
	}
	public void setColorOfMinion(List<String> colorOfMinion) {
		this.colorOfMinion = colorOfMinion;
	}
	public Boolean getTroubleMaker() {
		return troubleMaker;
	}
	public void setTroubleMaker(Boolean troubleMaker) {
		this.troubleMaker = troubleMaker;
	}
	public Boolean getBuilding() {
		return building;
	}
	public void setBuilding(Boolean building) {
		this.building = building;
	}
	public Integer getNoOfDemon() {
		return noOfDemon;
	}
	public void setNoOfDemon(Integer noOfDemon) {
		this.noOfDemon = noOfDemon;
	}
	public Integer getNoOfTroll() {
		return noOfTroll;
	}
	public void setNoOfTroll(Integer noOfTroll) {
		this.noOfTroll = noOfTroll;
	}
	
	

}
