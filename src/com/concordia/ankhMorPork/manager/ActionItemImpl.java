package com.concordia.ankhMorPork.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import com.concordia.ankhMorPork.common.Common;

public class ActionItemImpl {

	public Board PlaceTheMinion(Board board) {
		String input=null;
		Scanner sc = new Scanner(System.in);
		String currentPlayerColor=board.getPlayerList().get(board.getPlayerTurn()-1).getColor();
		List<Integer> minionLocatedonBoard=new ArrayList<Integer>();
		for(int index=0;index<12;index++){
		if(board.getArea().get(index).colorOfMinion.contains(currentPlayerColor)){
			minionLocatedonBoard.add(index+1);
		}
		}
		 Set<Integer> adjacentAreaSet = new TreeSet<Integer>(minionLocatedonBoard);
		 System.out.println("\nYour Minions ON Board are located at the following Area :\n");
		 System.out.println("\t\tAreaNo.\t\t\tAreaName");
		for(int i=0;i<minionLocatedonBoard.size();i++){
			System.out.println("\t\t"+minionLocatedonBoard.get(i)+"\t\t\t"+BoardManager.cityAreaCardList.get(minionLocatedonBoard.get(i)-1).getName());
			List<Integer> temporaryList=BoardManager.AdjacentAreaMap.get(minionLocatedonBoard.get(i));
			adjacentAreaSet.addAll(temporaryList);
		}
		 System.out.println("\nYou are allowed to place the minion in the following Area :\n");
		 System.out.println("\t\tAreaNo.\t\t\tAreaName");
		 for (Integer integer : adjacentAreaSet) {
			 System.out.println("\t\t"+integer+"\t\t\t"+BoardManager.cityAreaCardList.get(integer-1).getName());
		}
		 System.out.println("\nEnter your Choice : \n");	
		 try{
		 input = sc.nextLine();
			if(adjacentAreaSet.contains(Integer.parseInt(input))){
			
				System.out.println("Before Updating : "+board.getArea().get(Integer.parseInt(input)-1).getColorOfMinion());
				board.UpdateAreaDetails(board.getArea().get(Integer.parseInt(input)-1),currentPlayerColor);
				System.out.println("After Updating : "+board.getArea().get(Integer.parseInt(input)-1).getColorOfMinion());
		
			}
			else{
				System.out.println("Invalid input !");
				board=PlaceTheMinion(board);
			}
		 }catch(NumberFormatException e){
			 System.out.println("Invalid input ! Give Number as Input !");
				board=PlaceTheMinion(board);
		 }
		return board;
		// TODO Auto-generated method stub
		
	}

	public Board PlaceTheBuilding(Board board2) {
		return board2;
		// TODO Auto-generated method stub
		
	}

}
