package t7;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GitHubRequisitionTests {
	public static void main(String[] args) {
	    String urlbase = "https://api.github.com/repos/JoaoSchittler/TrabalhosElc117-jgschittler/commits?page=";	    
	    Integer requestNumber = 1,totalcommits = 0;
	    int endRequests = 0, totalmsglength = 0;
	    while(endRequests == 0) {
	    	String urltorequest = urlbase + requestNumber.toString();
	    	try {
	    	URL url = new URL(urltorequest);
	    	
		    HttpURLConnection con = (HttpURLConnection) url.openConnection();
		    con.setRequestMethod("GET");
		    con.setRequestProperty("User-Agent", "Mozilla/5.0");
		    
		    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		    JsonParser parser = new JsonParser();
		    JsonArray results = parser.parse(in.readLine()).getAsJsonArray();
		    System.out.println("Size: "+ results.size());
		    if(results.size() < 30) endRequests = 1;
		    totalcommits += results.size();
		    for (JsonElement e : results) {
		    	//Pega dados
			     String data = e.getAsJsonObject().get("commit").getAsJsonObject().get("author").getAsJsonObject().get("date").toString();
				 String msgString = e.getAsJsonObject().get("commit").getAsJsonObject().get("message").toString();
			     totalmsglength += msgString.length();
			}   

			    
			in.close();
	    	}catch(IOException e) { System.out.println("Error while getting repository data");endRequests = 1;}
	    	requestNumber++;
	    }
	    Float f = (float)totalmsglength/totalcommits;
	    String mediatammsg = f.toString();
	    //System.out.println("Response code: " + con.getResponseCode());    
	    //System.out.println(con.getHeaderFields().get("Link").get(0));
	}	
}
