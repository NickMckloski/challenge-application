package com.apichallenge;

/**
 * @author Nick Mckloski
 *
 */
public class OpenPage {
	
	public static void OpenWebPage(String page) {
		 try {
	         String url = page;
	         java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
	       }
	       catch (java.io.IOException e) {
	           System.out.println(e.getMessage());
	       }
	}
   
}