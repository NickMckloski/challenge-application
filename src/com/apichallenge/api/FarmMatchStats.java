package com.apichallenge.api;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FarmMatchStats {
	
	private static long [][] data = new long[500][100];
	
	public static void main(String[] args) {
		try {
			int rateLimit = 0;
			JSONParser parser = new JSONParser();
			JSONArray matchIds = (JSONArray) parser.parse(new FileReader("./data/urfmatchids.json"));
			
			for(int i = 0; i < matchIds.size(); i++) {
				if(rateLimit < 10) {
					String jsonUrl = IOUtils.toString(new URL("https://na.api.pvp.net/api/lol/na/v2.2/match/"+matchIds.get(i)+"?api_key="+APIConstants.key));
					System.out.println("parsed "+i+" matches");
					rateLimit++;
					JSONObject jsonObj = (JSONObject) JSONValue.parseWithException(jsonUrl);
					JSONArray players = (JSONArray) jsonObj.get("participants");
					for(int a = 0; a < players.size(); a++) {
						
						JSONObject playerObj = (JSONObject) players.get(a);
						long champId = (long) playerObj.get("championId");
						data[(int) champId][0] = champId;
						JSONObject matchStats = (JSONObject) playerObj.get("stats");
						
						long damageFromStats = (long) matchStats.get("totalDamageDealt");
						data[(int) champId][1] += damageFromStats;

						long physicalDamageDealt = (long) matchStats.get("physicalDamageDealt");
						data[(int) champId][2] += physicalDamageDealt;

						long magicDamageDealt = (long) matchStats.get("magicDamageDealt");
						data[(int) champId][3] += magicDamageDealt;
						
						long pentas = (long) matchStats.get("pentaKills");
						data[(int) champId][4] += pentas;
						
						long cs = (long) matchStats.get("minionsKilled");
						data[(int) champId][5] += cs;
						
						data[(int) champId][6]++;//times picked
						
					}
				} else {
					try {
						System.out.println("sleeping for rate...");
						writeMatchStatsJson();
						Thread.sleep(12000);
						System.out.println("done sleeping");
						rateLimit = 0;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
			writeMatchStatsJson();
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private static void writeMatchStatsJson() {
			
			JSONObject obj = new JSONObject();
			JSONObject champions = new JSONObject();
			for(int i = 0; i < 500; i++) {
				if(data[i][0] != 0L) {
					JSONObject champObj = new JSONObject();
					
					champObj.put("total damage", data[i][1]);
					champObj.put("physical damage", data[i][2]);
					champObj.put("magic damage", data[i][3]);
					champObj.put("pentakills", data[i][4]);
					champObj.put("cs", data[i][5]);
					champObj.put("picked", data[i][6]);
					
					champions.put(data[i][0], champObj);
				}
			}

			obj.put("champions", champions);
			System.out.println("wrote "+obj);
			try {
				FileWriter file = new FileWriter("./data/matchstats.json");
				//file.write(obj.toJSONString()); commented out so I dont accidently run it and overwrite my data lol
				file.flush();
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	
}
