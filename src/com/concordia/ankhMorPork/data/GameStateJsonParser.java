package com.concordia.ankhMorPork.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import com.concordia.ankhMorPork.common.Global;
import com.concordia.ankhMorPork.common.Language;
import com.concordia.ankhMorPork.manager.Board;
import com.concordia.ankhMorPork.manager.Player;

public class GameStateJsonParser {

	public static List<Integer> get_integer_list(String green_cards) {
		List<Integer> temp_int_list = new ArrayList<Integer>();
		if (null == green_cards && green_cards.length() == 0) {
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
				List<Integer> city_ara_card_list = get_integer_list(city_area_cards);
				playerObject.setCityAreaCard(city_ara_card_list);
				String random_even_cards = player
						.getString(Language.RANDOM_CARDS);
				List<Integer> random_even_cards_list = get_integer_list(random_even_cards);
				playerList.add(playerObject);
			}
			board.setPlayerList(playerList);
		} catch (Exception e) {// Catch exception if any

			System.err.println("Error: " + e.getMessage());

		}
		return board;
	}

}
