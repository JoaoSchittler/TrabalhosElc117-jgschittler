package t6;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;



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
		  try{
			  //Faz o download do arquivo
			  BufferedInputStream in = new BufferedInputStream(new URL(urlSpec).openStream());
			  //Cria o arquivo enade.csv
			  FileOutputStream fileOutputStream = new FileOutputStream("./enade.csv");
			  byte dataBuffer[] = new byte[1024];
			  int bytesRead;
			  //Coloca os dados lidos do site no arquivo usando um buffer
			  while ((bytesRead = in .read(dataBuffer, 0, 1024)) != -1) {
				  fileOutputStream.write(dataBuffer, 0, bytesRead);
				  
			  fileOutputStream.close();	  
		   }
		  } catch (IOException e) {
		   System.out.println("Erro while downloading file");
		   view.endApplication();
		  }
		  //Abre o arquivo enade.csv e cria um BufferedReader dele
		  FileReader arq_reader = new FileReader("./enade.csv");
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
