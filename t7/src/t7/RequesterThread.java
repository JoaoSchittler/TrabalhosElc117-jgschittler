package t7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class RequesterThread extends Thread{
	private ArrayList<DataEntry> repData;
	public Boolean requestDone = false;
	private File file;
	public RequesterThread(ArrayList<DataEntry> data,File file) {
		this.repData = data;
		this.file = file;
	}
	public void run() {
		BufferedReader reader = null;
		try {reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {System.out.println("File "+ file.toString()+ " Not Found");	};
		
		ArrayList<String> repos = new ArrayList<String>();
		String aux;
		try {
			while( (aux = reader.readLine()) != null)
				repos.add(aux);
		} catch (IOException e) {}
		
		for(String rep : repos) {
			repData.add(getRepData(rep));
		}
		signal_request();
	}
	public synchronized void signal_request() {
		this.notifyAll();	
	}
	private DataEntry getRepData(String rep) {
		String urlbase = rep,firstcommitdata = "",lastcommitdata = "";	    
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
	    			if(totalmsglength == 0) {
	    				firstcommitdata = e.getAsJsonObject().get("commit").getAsJsonObject().get("author").getAsJsonObject().get("date").toString();
	    			}
	    			lastcommitdata = e.getAsJsonObject().get("commit").getAsJsonObject().get("author").getAsJsonObject().get("date").toString();;
	    			//Pega dados
	    			String msgString = e.getAsJsonObject().get("commit").getAsJsonObject().get("message").toString();
	    			totalmsglength += msgString.length();
	    		}   
	    		in.close();
	    	}catch(IOException e) { System.out.println("Error while getting repository data");endRequests = 1;}
	    	requestNumber++;
	    }
	    Float f = (float)totalmsglength/totalcommits;
	    String mediatammsg = f.toString();
		return new DataEntry(totalcommits.toString(),mediatammsg,firstcommitdata,lastcommitdata);
	}
	
}
