package t6;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import com.opencsv.CSVReader;

public class AppController {
	private ViewFunctions view ;
	
	AppController(ViewFunctions view){
		this.view = view;
	}
	public void fillTable(String urlstr) {
		Reader reader;
		CSVReader csvReader;
		ArrayList<String[]> data;
		try {
			reader = getReaderFromUrl(urlstr);
			//Transforma o Reader em um CSVReader
			csvReader = new CSVReader(reader);
			//Lê todas as linhas do arquivo  e coloca em data
			data = (ArrayList<String[]>) csvReader.readAll();
			reader.close();
			csvReader.close();
		} catch (IOException e) {
			
			System.out.println("Error while reading table data file");
			return;
		}
		view.setTableList(data);
	}
	private BufferedReader getReaderFromUrl(String urlSpec) throws IOException {

		  URL url = new URL(urlSpec);
	      File file = new File("enade.csv");
	      //Realiza o download da url e guarda os resultados em enade.csv
		  FileUtils.copyURLToFile(url, file);
		  //Abre o arquivo enade.csv e cria um BufferedReader dele
		  FileReader arq_reader = new FileReader(file);
		  BufferedReader reader = new BufferedReader(arq_reader);
		  return reader;
		  
	}
	public void displayDetailedLine(int selectedIndex) {
		view.makeDetailedTableWindow(selectedIndex);
	}
	public void showAbout() {
		view.makeAboutWindow();
	}
	
}
