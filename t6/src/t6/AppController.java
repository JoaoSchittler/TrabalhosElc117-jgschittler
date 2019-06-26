package t6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class AppController {
	private ViewFunctions view ;
	AppController(ViewFunctions view){
		this.view = view;
	}
	public void fillTable(String urlstr,String filename) {
		URL url;
		try {
			url = new URL(urlstr);
		} catch (MalformedURLException e) {
			System.out.println("URL INVALIDA");
			return;
		}
		try {
		 HttpURLConnection con = (HttpURLConnection) url.openConnection();
		 con.setRequestMethod("GET");
		 con.setRequestProperty("User-Agent", "Mozilla/5.0");
		 System.out.println("Response code: " + con.getResponseCode());
		 BufferedReader in = new BufferedReader(
				 new InputStreamReader(con.getInputStream()));
		 
		 // DO STUFF
		 
		 in.close();
		} catch(IOException e) {System.out.println("ERRP NA COMUNICAÇÃO COM O SITE"); }
		   
	}
	
}
