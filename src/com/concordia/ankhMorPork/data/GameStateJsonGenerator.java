package com.concordia.ankhMorPork.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.concordia.ankhMorPork.common.Global;
import com.concordia.ankhMorPork.manager.Board;
import com.concordia.ankhMorPork.manager.CityAreaCard;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author elDiablo Jan 29, 2015 12:02:00 PM 2015
 * @email: sumitsarkarbd@gmail.com
 */

/*
 * This class have all the methods to generate the JSON file about the current
 * game status
 */
public class GameStateJsonGenerator {
	public static List<CityAreaCard> cityAreaCardList = new ArrayList<CityAreaCard>();

	public static void main(String[] args) {

		try {

			JsonFactory jfactory = new JsonFactory();

			/*** write to file ***/
			JsonGenerator jGenerator = jfactory
					.createJsonGenerator(new File(
							Global.SAVED_FILE_DIRECTORY_PATH + "/"
									+ Global.saveFileName + ".json"),
							JsonEncoding.UTF8);

			jGenerator.writeStartObject(); // {

			// Number of player
			jGenerator.writeStringField("numberOfPlayers",
					Global.numberOfPlayers.toString());

			jGenerator.writeStringField("name", "mkyong"); // "name" : "mkyong"
			jGenerator.writeNumberField("age", 29); // "age" : 29

			jGenerator.writeFieldName("messages"); // "messages" :
			jGenerator.writeStartArray(); // [

			jGenerator.writeString("msg 1"); // "msg 1"
			jGenerator.writeString("msg 2"); // "msg 2"
			jGenerator.writeString("msg 3"); // "msg 3"

			jGenerator.writeEndArray(); // ]

			jGenerator.writeEndObject(); // }

			jGenerator.close();

		} catch (JsonGenerationException e) {

			e.printStackTrace();

		} catch (JsonProcessingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	public boolean saveGameCurrentStateToJsonFormate(Board board) {
		boolean isSaved = false;
		
		try {

			JsonFactory jfactory = new JsonFactory();

			/*** write to file ***/
			JsonGenerator jGenerator = jfactory
					.createJsonGenerator(new File(
							Global.SAVED_FILE_DIRECTORY_PATH + "/"
									+ Global.saveFileName + ".json"),
							JsonEncoding.UTF8);

			jGenerator.writeStartObject(); // {

			// Number of player
			jGenerator.writeStringField("numberOfPlayers",board.getNoOfPlayer().toString());
			
			for (int i = 0; i < board.getNoOfPlayer(); i++) {
				
				jGenerator.writeFieldName("player "+ i+1);
				
				//Array of player information
				jGenerator.writeStartArray();
				
				jGenerator.writeStringField("playerName", board.getPlayerList().get(i).getName());
				jGenerator.writeStringField("playerColor", board.getPlayerList().get(i).getColor());
				jGenerator.writeNumberField("playerPersonalityCard", board.getPlayerList().get(i).getPersonalityCard());
				jGenerator.writeNumberField("minions", Global.MINIONS - board.getPlayerList().get(i).getMinionsOnBoard()
						.size());
				jGenerator.writeNumberField("buildings", Global.BUILDINGS - board.getPlayerList().get(i).getBuildingOnBoard()
						.size());
				jGenerator.writeNumberField("money", board.getPlayerList().get(i).getPlayerMoney());
				
				//Write array for city area cards
				jGenerator.writeFieldName("cityAreaCards");
				jGenerator.writeStartArray();
				
				if (board.getPlayerList().get(i).getCityAreaCard().size() == 0) {
					jGenerator.writeString("No City Area cards attained yet");
				} else {
					for (int j = 0; i < board.getPlayerList().get(i).getCityAreaCard().size(); j++) {
						jGenerator.writeNumber(cityAreaCardList.get(
								board.getPlayerList().get(i)
								.getCityAreaCard().get(j))
						.getName());
								
					}
				}
				
				jGenerator.writeEndArray();
				//Write array for city area cards
				
				jGenerator.writeStringField("greenCards", board.getPlayerList().get(i).getGreenPlayerCards().toString());
				jGenerator.writeStringField("brownCards", board.getPlayerList().get(i).getBrownPlayerCards().toString());
				
				
				
				jGenerator.writeEndArray();
			}
			
			jGenerator.writeNumberField("bankMoney", board.getBankMoney());

			jGenerator.writeEndObject(); // }

			jGenerator.close();

		} catch (JsonGenerationException e) {

			e.printStackTrace();

		} catch (JsonProcessingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		//If file saved
		isSaved = true;

		return isSaved;
	}

}
