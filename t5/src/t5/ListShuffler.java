package t5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
public class ListShuffler {
	private static boolean online_success = false;
	public static void offlineshuffle(ArrayList<String> l) {
		Collections.shuffle(l);
		try {System.in.read();}catch (IOException e1) {}
		//Prints One element and Waits for a console input
		for(String element: l) {
			System.out.print(element);
			try {System.in.read();} catch (IOException e) {}
		}
		
	}
	public static void onlineshuffle(ArrayList<String>l) {
		try {
        	  System.in.read();
        	  
		      String urlstr = "https://www.random.org/lists/?mode=advanced";
		      URL url = new URL(urlstr);
		      HttpURLConnection con = (HttpURLConnection) url.openConnection();
		      con.setRequestMethod("POST");
		      con.setRequestProperty("User-Agent", "Mozilla/5.0");
		      con.setDoOutput(true);
		      //Cria String para enviar para o site, colocando todos os elementos da lista
		      String data = "list=";
		      for(String element: l) {
					data = data + element + "%0D%0A";
				}
		      data += "&format=plain&rnd=new";
		      
		      // Envia dados pela conexão aberta
		      con.getOutputStream().write(data.getBytes("UTF-8"));
		     // System.out.println("Response code: " + con.getResponseCode());

		      // Cria objeto que fará leitura da resposta pela conexão aberta
		      BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));

		      // Lê  e mostra a resposta, linha por linha, esperando input
		      String responseLine;
		      StringBuffer response = new StringBuffer();
		      while ((responseLine = in.readLine()) != null) {
		    	  	response.append(responseLine + "\n");
		        	System.out.print(responseLine);
		        	System.in.read();
		        	online_success = true;
		      }
		      // Mostra resposta
		      System.out.println(response); 

		      in.close();
		    } catch (IOException e) {e.printStackTrace();}
		if(online_success == false)
			offlineshuffle(l);
	}
		
}