package com.concordia.ankhMorPork.data;
import java.io.*;
import org.json.JSONArray;
import org.json.JSONObject;


public class JsonParser {
	
	public String read_json_file(String file_name) {
    String file_text="";
	try{
		 
        // Open the file that is the first

        // command line parameter

 

        //checks where the path is going ?????????????????????

        System.out.println(System.getProperty("user.dir"));

         

        //

        FileInputStream fstream = new FileInputStream(System.getProperty("user.dir") + "\\resources\\savedGame\\"+file_name);

         

         

        // Get the object of DataInputStream

        DataInputStream in = new DataInputStream(fstream);

        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String strLine;
     

         

         

        //Read File Line By Line

       while ((strLine = br.readLine()) != null)   {

            // Print the content on the console

    	   file_text =file_text + strLine;

        }
        
     
        //Close the input stream

        in.close();
        
	  } catch (Exception e){//Catch exception if any

          System.err.println("Error: " + e.getMessage());

      }
      
	 return file_text;
        
	}
	
	public void parseJson(String file_text){

	  try{
        
        
      JSONObject json = new JSONObject(file_text.toString());
      //Add this line 
      JSONObject responseData = json.getJSONObject("discworld");
      int bank_amount = responseData.getInt("bank_amount");
     
              final JSONArray geodata = responseData.getJSONArray("players");
              final int n = geodata.length();
              
              for (int i = 0; i < n; ++i) {
	               final JSONObject player = geodata.getJSONObject(i);
	
	               int tmp_player_id = player.getInt("player_id");
	               String tmp_player_name = player.getString("player_name");
	               String tmp_player_color = player.getString("color");
	               int tmp_player_money = player.getInt("player_money");
	               int tmp_player_personality_card = player.getInt("personality_card");
               
              }
        
	  } catch (Exception e){//Catch exception if any

          System.err.println("Error: " + e.getMessage());

      }
        


      
	}

}
