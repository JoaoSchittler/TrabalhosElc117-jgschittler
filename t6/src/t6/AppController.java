package t6;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import com.opencsv.CSVReader;

public class AppController {
	private ViewFunctions view ;
	AppController(ViewFunctions view){
		this.view = view;
	}
	public void fillTable(String urlstr,String fileName) {
		Reader reader;
		CSVReader csvReader;
		ArrayList<String[]> data;
		try {
			reader = Files.newBufferedReader(Paths.get(fileName));
			csvReader = new CSVReader(reader);
			data = (ArrayList<String[]>) csvReader.readAll();
			reader.close();
			csvReader.close();
			 
		} catch (IOException e) {
			
			System.out.println("Error while reading table data file");
			return;
		}
		view.setTableList(data);
	}
	
}
