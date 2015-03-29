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
		Integer noOfMinions=board.getPlayerList().get(board.getPlayerTurn()-1).getNoOfMinions();
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
		 if(adjacentAreaSet.size()!=0){
				 for (Integer integer : adjacentAreaSet) {
					 System.out.println("\t\t"+integer+"\t\t\t"+BoardManager.cityAreaCardList.get(integer-1).getName());
				}
		 }else{//when no minions on board then player can place anywhere
			 	  for(int k=0;k<12;k++){
			 		 System.out.println("\t\t"+(k+1)+"\t\t\t"+BoardManager.cityAreaCardList.get(k).getName());
			 	  }
		 }
		 System.out.println("\nEnter your Choice : \n");	
		 try{
		 input = sc.nextLine();
			if(adjacentAreaSet.contains(Integer.parseInt(input))){
			
				System.out.println("Before Updating : "+board.getArea().get(Integer.parseInt(input)-1).getColorOfMinion());
				board.UpdateAreaDetails(board.getArea().get(Integer.parseInt(input)-1),currentPlayerColor);
				System.out.println("After Updating : "+board.getArea().get(Integer.parseInt(input)-1).getColorOfMinion());
				board.getPlayerList().get(board.getPlayerTurn()-1).setNoOfMinions(noOfMinions - 1);
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

	public Board PlaceTheBuilding(Board board) {
		String input=null;
		Scanner sc = new Scanner(System.in);
		String currentPlayerColor=board.getPlayerList().get(board.getPlayerTurn()-1).getColor();
		Integer noOfBuilding=board.getPlayerList().get(board.getPlayerTurn()-1).getNoOfBuilding();
		String playerName = board.getPlayerList().get(board.getPlayerTurn()-1).getName();
		Integer playerMoney=board.getPlayerList().get(board.getPlayerTurn()-1).getPlayerMoney();
		List<Integer> buildingAllowedonBoard=new ArrayList<Integer>();
		for(int index=0;index<12;index++){
		if(board.getArea().get(index).colorOfMinion.contains(currentPlayerColor) && (!board.getArea().get(index).getTroubleMaker()) && (!board.getArea().get(index).getBuilding())){
			buildingAllowedonBoard.add(index+1);
		}
		}
		if(buildingAllowedonBoard.size()==0 && noOfBuilding==0){//Already six building on the board
			System.out.println("\nRemove any one of your Building and then place anywhere else\n");
		}else{
			System.out.println("\nYou are allowed to place the minion in the following Area :\n");
			System.out.println("\t\tAreaNo.\t\t\tCost\t\t\tAreaName");
			for(int i=0;i<buildingAllowedonBoard.size();i++){
				System.out.println("\t\t"+buildingAllowedonBoard.get(i)+"\t\t\t"+BoardManager.cityAreaCardList.get(buildingAllowedonBoard.get(i)-1).getCost()+"\t\t\t"+BoardManager.cityAreaCardList.get(buildingAllowedonBoard.get(i)-1).getName());
			}
		}
		System.out.println("\nEnter your Choice : \n");	
		 try{
		 input = sc.nextLine();
		 Integer costOfBuilding=BoardManager.cityAreaCardList.get(Integer.parseInt(input)-1).getCost();
		 List<Integer> cityAreaCardofPlayer=board.getPlayerList().get(board.getPlayerTurn()-1).getCityAreaCard();
			if(playerMoney >= costOfBuilding){
				System.out.println("Before Updating : "+board.getArea().get(Integer.parseInt(input)-1).getBuilding());
				board.UpdateAreaDetailsWithBuilding(playerName,board.getArea().get(Integer.parseInt(input)-1));
				System.out.println("After Updating : "+board.getArea().get(Integer.parseInt(input)-1).getBuilding());
				board.getPlayerList().get(board.getPlayerTurn()-1).setNoOfBuilding(noOfBuilding - 1);
				board.getPlayerList().get(board.getPlayerTurn()-1).setPlayerMoney(playerMoney - costOfBuilding);
				cityAreaCardofPlayer.add(Integer.parseInt(input));
				board.getPlayerList().get(board.getPlayerTurn()-1).setCityAreaCard(cityAreaCardofPlayer);
			}
			else{
				System.out.println("You dont have enough Money to afford !");
				//board=PlaceTheBuilding(board);
			}
		 }catch(NumberFormatException e){
			 System.out.println("Invalid input ! Give Number as Input !");
				board=PlaceTheBuilding(board);
		 }
		return board;
		// TODO Auto-generated method stub
		
	}

}
