package t7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class AppController {

	private View view;

	public AppController(View view) {
		this.view = view;
	}

	public void enableFileChooser() {
		this.view.enableFileChooser();
	}

	public void showUrlsFromFile(File file) {
		ArrayList<String> urlList = new ArrayList<String>();
		
		try {	
			BufferedReader bufferRead = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = bufferRead.readLine()) != null)urlList.add(line);
			bufferRead.close();
			this.view.fillTable(urlList);
		}catch(IOException e) { System.out.println("Unable to Open or Get Items from file "+ file.toString()); System.exit(0);}	

	}
}
