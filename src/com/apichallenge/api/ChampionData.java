package com.apichallenge.api;

import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

/**
 * @author Nick Mckloski
 *
 */
public class ChampionData {


	public static Object[][] champData = new Object[124][100];

	public static void parseChampData() {
		
		parseChampStats();
		parseUrfMatchStats();
		
    }

	private static void parseUrfMatchStats() {
		try {
			JSONParser parser = new JSONParser();
			JSONObject matchStatsJson = (JSONObject) parser.parse(new FileReader("./data/matchstats.json"));
			JSONObject champions = (JSONObject) matchStatsJson.get("champions");
			for(int i = 0; i < 500; i++) {
				JSONObject champ = (JSONObject) champions.get(""+i);
				if(champ != null) {
					for(int a = 0; a < 124; a++) {
						//int key = 0;
						if(Integer.parseInt((String) champData[a][21]) == i) {
							//key = Integer.parseInt((String) champData[a][21]);
							
							champData[a][22] = (long)champ.get("total damage");
							champData[a][23] = (long)champ.get("magic damage");
							champData[a][24] = (long)champ.get("physical damage");
							champData[a][25] = (long)champ.get("cs");
							champData[a][26] = (long)champ.get("picked");
							champData[a][27] = (long)champ.get("pentakills");
							
							//System.out.print("\n"+key+"  "+(long)champ.get("cs"));
						}
					}
				}
			}
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private static void parseChampStats() {
		try {
			JSONParser parser = new JSONParser();
			ContainerFactory orderedKeys = new ContainerFactory() {
				public Map<Object, Object> createObjectContainer() {
			      return new LinkedHashMap<Object, Object>();
			    }
				public List<Object> creatArrayContainer() {
					return null;
				}
			};
			Object championJson = parser.parse(new FileReader("./data/champion.json"),orderedKeys);
			LinkedHashMap<?, ?> map = (LinkedHashMap<?, ?>)championJson;
			
			Object data = map.get("data");
			
			Iterator<String> iterator = ((Map<String, String>) data).keySet().iterator();
			int i = 0;
			while (iterator.hasNext()) {
				champData[i][0] = iterator.next(); //read name
				Object champ = ((Map<Object, Object>) data).get(champData[i][0]);// grab champ from data
				
				Object title = ((Map<Object, Object>) champ).get("title");//read title
				champData[i][1] = title;
				
				JSONArray tags = (JSONArray) ((Map<Object, Object>) champ).get("tags");//read roles/tags
				
				for(int x = 0; x < tags.size(); x++) {
					if(champData[i][2] == null)champData[i][2] = "";
					champData[i][2] += tags.get(x)+" ";
				}
				
				Object key = ((Map<Object, Object>) champ).get("key");// grab key id
				champData[i][21] = (String)key;
				
				Object resource = ((Map<Object, Object>) champ).get("partype");// grab resource type
				
				
				Object stats = ((Map<Object, Object>) champ).get("stats");// grab stats from champ

				DecimalFormat df = new DecimalFormat("#.###");
				
				Object hp = ((Map<Object, Object>) stats).get("hp");
				champData[i][3] = df.format(hp);
				
				Object hpperlevel = ((Map<Object, Object>) stats).get("hpperlevel");
				champData[i][4] = df.format(hpperlevel);
				
				Object hpregen = ((Map<Object, Object>) stats).get("hpregen");
				champData[i][5] = df.format(hpregen);
				
				Object hpregenperlevel = ((Map<Object, Object>) stats).get("hpregenperlevel");
				champData[i][6] = df.format(hpregenperlevel);
				
				Object mp = ((Map<Object, Object>) stats).get("mp");
				champData[i][7] = (resource.equals("Mana") ?  df.format(mp) : "N/A");
				
				Object mpperlevel = ((Map<Object, Object>) stats).get("mpperlevel");
				champData[i][8] = (resource.equals("Mana") ?  df.format(mpperlevel) : "N/A");
				
				Object mpregen = ((Map<Object, Object>) stats).get("mpregen");
				champData[i][9] = (resource.equals("Mana") ?  df.format(mpregen) : "N/A");
				
				Object mpregenperlevel = ((Map<Object, Object>) stats).get("mpregenperlevel");
				champData[i][10] = (resource.equals("Mana") ?  df.format(mpregenperlevel) : "N/A");
				
				Object attackrange = ((Map<Object, Object>) stats).get("attackrange");
				champData[i][11] = df.format(attackrange);
				
				Object attackdamage = ((Map<Object, Object>) stats).get("attackdamage");
				champData[i][12] = df.format(attackdamage);
				
				Object attackdamageperlevel = ((Map<Object, Object>) stats).get("attackdamageperlevel");
				champData[i][13] = df.format(attackdamageperlevel);
				
				Object attackspeedoffset = ((Map<Object, Object>) stats).get("attackspeedoffset");
				champData[i][14] = df.format(0.625/(1+(double)attackspeedoffset));
				
				Object attackspeedperlevel = ((Map<Object, Object>) stats).get("attackspeedperlevel");
				champData[i][15] = df.format(attackspeedperlevel);
				
				Object armor = ((Map<Object, Object>) stats).get("armor");
				champData[i][16] = df.format(armor);
				
				Object armorperlevel = ((Map<Object, Object>) stats).get("armorperlevel");
				champData[i][17] = df.format(armorperlevel);
				
				Object spellblock = ((Map<Object, Object>) stats).get("spellblock");
				champData[i][18] = df.format(spellblock);
				
				Object spellblockperlevel = ((Map<Object, Object>) stats).get("spellblockperlevel");
				champData[i][19] = df.format(spellblockperlevel);
				
				Object movespeed = ((Map<Object, Object>) stats).get("movespeed");
				champData[i][20] = df.format(movespeed);
				
				i++;
			}
	 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
