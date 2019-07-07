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
	private ThreadData data;
	private File file;
	public RequesterThread(ThreadData data,File file) {
		this.data = data;
		this.file = file;
	}
	public void run() {
		BufferedReader reader = null;
		try {reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {System.out.println("File "+ file.toString()+ " Not Found");	};
		
		ArrayList<String> repos = new ArrayList<String>();
		String aux;
		try {
			while( (aux = reader.readLine()) != null){
				System.out.println("Aux: "+ aux);
				if(aux!=null){
					System.out.println("Add to rep");
					repos.add(aux);
				}
			}
		} catch (IOException e) {}


		for(String rep : repos) {
			System.out.println("Analyzing rep: "+rep);
			data.repdata.add(getRepData(rep));
		}
		data.endrequest();
	}
	private DataEntry getRepData(String rep) {
		String urlbase = rep,firstcommitdata = "",lastcommitdata = "";	    
	    Integer requestNumber = 1,totalcommits = 0;
	    int endRequests = 0, totalmsglength = 0;
	    String repname = getRepName(rep);
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
	    		//System.out.println("Size: "+ results.size());
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
		System.out.println("Finish request from " + urlbase);
		return new DataEntry(repname,totalcommits.toString(),mediatammsg,firstcommitdata,lastcommitdata);
	}
	private String getRepName(String rep) {
		String[] urlparts = rep.split("/");
		System.out.println(urlparts);
		try {
			return urlparts[6];
		}catch(IndexOutOfBoundsException e) {System.out.println("Error while getting rep name");}
		return "ERROR NAME";
	}
	
}
