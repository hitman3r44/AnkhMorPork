package com.concordia.ankhMorPork.manager;

import java.util.ArrayList;
import java.util.List;

public class Area {
	
	private Integer identifier;
	private List<String> colorOfMinion;
	private Boolean troubleMaker;
	private Boolean building;
	private Integer noOfDemon;
	private Integer noOfTroll;
	
	public Area(Boolean troubleMaker, Boolean building, Integer noOfDemon, Integer noOfTroll)
	{
		this.troubleMaker=troubleMaker;
		this.building=building;
		this.noOfDemon=noOfDemon;
		this.noOfTroll=noOfTroll;
		this.colorOfMinion=new ArrayList<String>();
	}
	
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
