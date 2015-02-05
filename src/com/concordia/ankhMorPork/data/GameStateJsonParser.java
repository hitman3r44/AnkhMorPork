package com.concordia.ankhMorPork.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import com.concordia.ankhMorPork.common.Global;
import com.concordia.ankhMorPork.common.Language;
import com.concordia.ankhMorPork.manager.Area;
import com.concordia.ankhMorPork.manager.Board;
import com.concordia.ankhMorPork.manager.Player;

public class GameStateJsonParser {

	public static List<Integer> get_integer_list(String green_cards) {
		List<Integer> temp_int_list = new ArrayList<Integer>();
		if (null == green_cards && green_cards.length() == 0) {
			return temp_int_list;
		}
		else if("[]".equals(green_cards))
		{
			return temp_int_list;
		}
		String removeParenthesis = green_cards.substring(1,
				(green_cards.length() - 1));
		String[] cardNumbers = removeParenthesis.split(", ");

		try {
			for (int i = 0; i < cardNumbers.length; i++) {
				temp_int_list.add(Integer.parseInt(cardNumbers[i]));
			}

		} catch (Exception e) {// Catch exception if any

			System.err.println("Error: " + e.getMessage());

		}

		return temp_int_list;

	}

	public static Board parseJson(String fileName, Board board) {
		List<Player> playerList = new ArrayList<Player>();
		try {
			Scanner inFile1;
			// System.out.println(new File(".").getAbsolutePath());
			inFile1 = new Scanner(new File(Global.SAVED_FILE_DIRECTORY_PATH
					+ "/" + fileName + ".json"));
			StringBuilder sb = new StringBuilder();
			while (inFile1.hasNext()) {

				sb.append(inFile1.nextLine());
			}
			System.out.println(sb);
			JSONObject json = new JSONObject(sb.toString());
			// Add this line
			JSONObject responseData = json.getJSONObject(Language.DISCWORLD);
			int bank_amount = responseData.getInt(Language.BANK_MONEY);
			board.setBankMoney(bank_amount);
			int noOfPlayers = responseData.getInt(Language.NO_OF_PLAYERS);
			board.setNoOfPlayer(noOfPlayers);
			final JSONArray geodata = responseData
					.getJSONArray(Language.PLAYERS);
			final int n = geodata.length();
			System.out.println("n value = " + n);
			for (int i = 0; i < n; ++i) {
				final JSONObject player = geodata.getJSONObject(i);

				int tmp_player_id = player.getInt(Language.PLAYER_ID);

				String tmp_player_name = player.getString(Language.PLAYER_NAME);
				String tmp_player_color = player.getString(Language.COLOR);
				Player playerObject = new Player(tmp_player_id,
						tmp_player_name, tmp_player_color);
				int tmp_no_of_minion= player.getInt(Language.NO_OF_MINIONS);
				playerObject.setMinionsOnBoard((12-tmp_no_of_minion));
				int tmp_no_of_building= player.getInt(Language.NO_OF_BUILDING);
				playerObject.setBuildingOnBoard(6-tmp_no_of_building);
				int tmp_player_money = player.getInt(Language.PLAYER_MONEY);
				playerObject.setPlayerMoney(tmp_player_money);
				int tmp_player_personality_card = player
						.getInt(Language.PERSONALITY_CARD);
				playerObject.setPersonalityCard(tmp_player_personality_card);
				String green_cards = player.getString(Language.GREEN_CARDS);
				System.out.println(green_cards);
				List<Integer> green_card_list = get_integer_list(green_cards);
				playerObject.setGreenPlayerCards(green_card_list);
				System.out.println(green_card_list);
				String brown_cards = player.getString(Language.BROWN_CARDS);
				List<Integer> brown_card_list = get_integer_list(brown_cards);
				playerObject.setBrownPlayerCards(brown_card_list);
				System.out.println(brown_card_list);
				String city_area_cards = player
						.getString(Language.CITY_AREA_CARD);
				System.out.println("hereee");
				if ("NIL".equals(city_area_cards)) {
					playerObject.setCityAreaCard(new ArrayList<Integer>());
				} else {
					List<Integer> city_ara_card_list = get_integer_list(city_area_cards);
					playerObject.setCityAreaCard(city_ara_card_list);
				}
				/*
				 * String random_even_cards = player
				 * .getString(Language.RANDOM_CARDS); List<Integer>
				 * random_even_cards_list = get_integer_list(random_even_cards);
				 */
				playerList.add(playerObject);
			}
			System.out.println("----------" + playerList);
			board.setPlayerList(playerList);
			final JSONArray geodata1 = responseData
					.getJSONArray(Language.AREA_DETAILS);
			final int length = geodata1.length();
			System.out.println("length value = " + length);
			List<Area> areaList= new ArrayList<Area>();
			for (int i = 0; i < length; ++i) {
				final JSONObject area = geodata1.getJSONObject(i);
				int tmp_area_No = area.getInt(Language.AREA_NO);
				System.out.println(tmp_area_No);
				String tmp_Area = area.getString(Language.AREA);
				String tmp_available_minions = area.getString(Language.MINIONS);
				List<String> tmp_available_minions_list=get_String_list(tmp_available_minions);
				Boolean tmp_Is_TroubleMaker_Available = area
						.getBoolean(Language.IS_TROUBLEMAKER_AVAILABLE);
				Boolean tmp_Is_building_Available = area
						.getBoolean(Language.IS_BUILDING_AVAILABLE);
				int tmp_no_Of_demons = area.getInt(Language.NO_OF_DEMONS);
				int tmpno_of_troll = area.getInt(Language.NO_OF_TROLL);
				Area areaObject = new Area(tmp_Is_TroubleMaker_Available,
						tmp_Is_building_Available, tmp_no_Of_demons,
						tmpno_of_troll);
				areaObject.setIdentifier(tmp_area_No);
				areaObject.setColorOfMinion(tmp_available_minions_list);
				areaList.add(areaObject);
			}
			board.setArea(areaList);
		} catch (Exception e) {// Catch exception if any

			System.err.println("Error: " + e.getMessage());

		}
		return board;

	}
	
	public static List<String> get_String_list(String string) {
		List<String> temp_string_list = new ArrayList<String>();
		if (null == string && string.length() == 0) {
			return temp_string_list;
		}
		else if("[]".equals(string))
		{
			return temp_string_list;
		}
		String removeParenthesis = string.substring(1,
				(string.length() - 1));
		String[] stringList = removeParenthesis.split(", ");

		try {
			for (int i = 0; i < stringList.length; i++) {
				temp_string_list.add(stringList[i]);
			}

		} catch (Exception e) {// Catch exception if any

			System.err.println("Error: " + e.getMessage());

		}

		return temp_string_list;

	}

}
