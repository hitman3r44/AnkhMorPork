package com.concordia.ankhMorPork.manager;

import java.util.ArrayList;
import java.util.List;

public class ActionItemImpl {

	public Board PlaceTheMinion(Board board) {
		System.out.println("AdjacentAreaMap ==1==>"+BoardManager.AdjacentAreaMap.get(1));
		List<Integer> minionLocatedonBoard=new ArrayList<Integer>();
		for(int index=0;index<12;index++){
		if(board.getArea().get(index).colorOfMinion.contains(board.getPlayerList().get(board.getPlayerTurn()-1).getColor())){
			minionLocatedonBoard.add(index+1);
		}
		System.out.println("minionLocatedonBoard"+minionLocatedonBoard);
		}
		return board;
		// TODO Auto-generated method stub
		
	}

	public Board PlaceTheBuilding(Board board2) {
		return board2;
		// TODO Auto-generated method stub
		
	}

}
