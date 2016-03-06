package com.apichallenge.api;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Nick Mckloski
 *
 */
public class FarmMatchIds {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try {
			int rateLimit = 0;
			for(long timeBlock = 1428659400; timeBlock < 2147483647; timeBlock+=300) {
				if(rateLimit < 10) {
					String jsonUrl = IOUtils.toString(new URL("https://na.api.pvp.net/api/lol/na/v4.1/game/ids?beginDate="+timeBlock+"&api_key="+APIConstants.key));
					JSONArray json = (JSONArray) JSONValue.parseWithException(jsonUrl);

					JSONParser parser = new JSONParser();
					JSONArray matchIds = (JSONArray) parser.parse(new FileReader("./data/urfmatchids.json"));
					System.out.println("currently  "+matchIds.size()+" match ids");

					for(int i = 0; i < json.size(); i++) {
						if(!matchIds.contains(json.get(i))) {
							matchIds.add(json.get(i));
						} else {
							System.out.println("duplicate");
						}
					}

					try {
						FileWriter file = new FileWriter("./data/urfmatchids.json");
						file.write(matchIds.toJSONString());
						file.flush();
						file.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

					if(json != null)
						System.out.println(json);
					rateLimit++;
				} else {
					try {
						System.out.println("sleeping for rate... limit last time used: "+timeBlock);
						Thread.sleep(12000);
						System.out.println("done sleeping");
						rateLimit = 0;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
