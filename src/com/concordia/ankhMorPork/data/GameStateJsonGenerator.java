package com.concordia.ankhMorPork.data;

import java.io.File;
import java.io.IOException;

import com.concordia.ankhMorPork.common.Global;
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

	public static void main(String[] args) {

		try {

			JsonFactory jfactory = new JsonFactory();

			/*** write to file ***/
			JsonGenerator jGenerator = jfactory.createJsonGenerator(
					new File(Global.SAVED_FILE_DIRECTORY_PATH+
							"/"+Global.saveFileName+".json"), 
							JsonEncoding.UTF8);
			
			jGenerator.writeStartObject(); // {
			
			//Number of player
			jGenerator.writeStringField("numberOfPlayers", Global.numberOfPlayers.toString());

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

}
