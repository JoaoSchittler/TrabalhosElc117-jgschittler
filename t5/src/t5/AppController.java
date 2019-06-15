package t5;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AppController {
	private View view;
	public AppController(View v) {
		view = v;
	}
	public void showFileChosser() {
		this.view.enableFileChooser();
	}
	// Shows the contents of file in textarea
	public void showFile(File file) {
		try {
			ArrayList<String> list = Manager.FiletoList(file);
			String s = Manager.ListtoString(list);
			this.view.fillTextArea(s);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("YEET");
		}
	}
	public void shuffle(String s) {
		Manager.StringtoList(s);
	}

	
}
