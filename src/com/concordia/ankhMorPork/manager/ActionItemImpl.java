package com.concordia.ankhMorPork.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import com.concordia.ankhMorPork.common.Common;

public class ActionItemImpl {

	public Board placeTheMinion(Board board) {
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
		 System.out.println("\nEnter AreaNo. : \n");	
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
				board=placeTheMinion(board);
			}
		 }catch(NumberFormatException e){
			 System.out.println("Invalid input ! Give Number as Input !");
				board=placeTheMinion(board);
		 }
		return board;
		// TODO Auto-generated method stub
		
	}

	public Board placeTheBuilding(Board board) {
		String input=null;
		Scanner sc = new Scanner(System.in);
		Player player=board.getPlayerList().get(board.getPlayerTurn()-1);
		String currentPlayerColor=player.getColor();
		Integer noOfBuilding=player.getNoOfBuilding();
		String playerName = player.getName();
		Integer playerMoney=player.getPlayerMoney();
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
		if(buildingAllowedonBoard.size()!=0){
		System.out.println("\nEnter Area No. : \n");	
		 try{
		 input = sc.nextLine();
		 Integer costOfBuilding=BoardManager.cityAreaCardList.get(Integer.parseInt(input)-1).getCost();
		 List<Integer> cityAreaCardofPlayer=player.getCityAreaCard();
			if(playerMoney >= costOfBuilding){
				System.out.println("Before Updating : "+board.getArea().get(Integer.parseInt(input)-1).getBuilding());
				board.UpdateAreaDetailsWithBuilding(playerName,board.getArea().get(Integer.parseInt(input)-1));
				System.out.println("After Updating : "+board.getArea().get(Integer.parseInt(input)-1).getBuilding());
				player.setNoOfBuilding(noOfBuilding - 1);
				player.setPlayerMoney(playerMoney - costOfBuilding);
				cityAreaCardofPlayer.add(Integer.parseInt(input));
				player.setCityAreaCard(cityAreaCardofPlayer);
			}
			else{
				System.out.println("You dont have enough Money to afford !");
			}
		 }catch(NumberFormatException e){
			 System.out.println("Invalid input ! Give Number as Input !");
				board=placeTheBuilding(board);
		 }
		}else{
			System.out.println("You dont have Minion on board for building.");
		}
		return board;
		
	}

	public Board assassination(Board board) {
		String input=null,color=null;
		Scanner sc = new Scanner(System.in);
		Player player =board.getPlayerList().get(board.getPlayerTurn()-1);
		String colorOfPlayer=board.getPlayerList().get(board.getPlayerTurn()-1).getColor();
		List<Integer> demononBoard=new ArrayList<Integer>();
		List<Integer> trollonBoard=new ArrayList<Integer>();
		List<Integer> miniononBoard=new ArrayList<Integer>();
		for(int index=0;index<12;index++){
		if(board.getArea().get(index).getNoOfDemon()!=0 && (board.getArea().get(index).getTroubleMaker())){
			demononBoard.add(index+1);
		}
		if(board.getArea().get(index).getNoOfTroll()!=0 && (board.getArea().get(index).getTroubleMaker())){
			trollonBoard.add(index+1);
		}
		if(board.getArea().get(index).getColorOfMinion().size()!=0 && (board.getArea().get(index).getTroubleMaker())){
			miniononBoard.add(index+1);
		}
		}
		System.out.println("\nYou can remove the following pieces : \n\n\t\t1.Demon\n\t\t2.Troll\n\t\t3.Minion");
		System.out.println("\nEnter your Choice : \n");	
		 try{
		 input = sc.nextLine();
		 if(Integer.parseInt(input)==1){
			 if(demononBoard.size()==0){
				 System.out.println("No Area Availabe to remove demon !");
			 }else{
			 System.out.println("\nYou are allowed to Remove the Demon in the following Area :\n");
			 System.out.println("\t\tAreaNo.\t\t\tNoOfDemon\t\t\tAreaName");
				for(int i=0;i<demononBoard.size();i++){
					System.out.println("\t\t"+demononBoard.get(i)+"\t\t\t"+board.getArea().get(demononBoard.get(i)-1).getNoOfDemon()+"\t\t\t"+BoardManager.cityAreaCardList.get(demononBoard.get(i)-1).getName());
				}
				System.out.println("\nEnter AreaNo. : \n");
				input = sc.nextLine();
				board.RemoveDemon(board.getArea().get(Integer.parseInt(input)));
			 }
		 }
		 else if(Integer.parseInt(input)==2){
			 if(trollonBoard.size()==0){
				 System.out.println("No Area Availabe to remove Troll !");
			 }else{
			 System.out.println("\nYou are allowed to Remove the Troll in the following Area :\n");
			 System.out.println("\t\tAreaNo.\t\t\tNoOfTroll\t\t\tAreaName");
				for(int i=0;i<trollonBoard.size();i++){
					System.out.println("\t\t"+trollonBoard.get(i)+"\t\t\t"+board.getArea().get(trollonBoard.get(i)-1).getNoOfTroll()+"\t\t\t"+BoardManager.cityAreaCardList.get(trollonBoard.get(i)-1).getName());
				}
				System.out.println("\nEnter AreaNo. : \n");
				input = sc.nextLine();
				board.RemoveTroll(board.getArea().get(Integer.parseInt(input)));
			 }
		 }
		 else if(Integer.parseInt(input)==3){
			 System.out.println("\nYou are allowed to Remove the Minion in the following Area(Don't remove your's) :\n");
			 System.out.println("\t\tAreaNo.\t\t\tColorOfMinion\t\t\tAreaName");
				for(int i=0;i<miniononBoard.size();i++){
					System.out.println("\t\t"+miniononBoard.get(i)+"\t\t\t"+board.getArea().get(miniononBoard.get(i)-1).getColorOfMinion()+"\t\t\t"+BoardManager.cityAreaCardList.get(miniononBoard.get(i)-1).getName());
				}
				System.out.println("\nEnter AreaNo. : \n");
				input = sc.nextLine();
				System.out.println("\nEnter the Color ID to be removed : \n");
				List<String> colorList=board.getArea().get(Integer.parseInt(input)-1).getColorOfMinion();
				int i=0;
				for (String string : colorList) {
					i++;
					if(!(string.equals(colorOfPlayer))){
					System.out.println("\t\t"+i+"."+string);
					
					}
				}
				color = sc.nextLine();
				board.RemoveMinion(board.getArea().get(Integer.parseInt(input)-1),colorList.get(Integer.parseInt(color)-1),player);
				
		 }
		 }catch(NumberFormatException e){
			 System.out.println("Invalid input ! Give Number as Input !");
				board=assassination(board);
		 }
		return board;
	}

	public Board removeOneTroubleMarker(Board board) {
		String input=null;
		Scanner sc = new Scanner(System.in);
		List<Integer> troubleMakerOnBoard=new ArrayList<Integer>();
		for(int index=0;index<12;index++){
		if(board.getArea().get(index).getTroubleMaker()){
			troubleMakerOnBoard.add(index+1);
		}
		}
		try{
		if(troubleMakerOnBoard.size()==0){
			System.out.println("\nNo Trouble Maker Available on the board\n");
		}
		else{
			System.out.println("\nYou are allowed to Remove trouble Maker in the following Area's : \n");
			System.out.println("\t\tAreaNo.\t\t\tAreaName");
			for(int i=0;i<troubleMakerOnBoard.size();i++){
				System.out.println("\t\t"+troubleMakerOnBoard.get(i)+"\t\t\t"+BoardManager.cityAreaCardList.get(troubleMakerOnBoard.get(i)-1).getName());
			}
			System.out.println("\nEnter the AreaNo.\n");
			input=sc.nextLine();
			board.removeOneTroubleMarker(board.getArea().get(Integer.parseInt(input)-1));
		}
		}catch(NumberFormatException e){
			 System.out.println("Invalid input ! Give Number as Input !");
				board=removeOneTroubleMarker(board);
		}
		return board;
	}

}
