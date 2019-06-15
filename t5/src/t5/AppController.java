package t5;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AppController {
	private View view;
	public AppController(View view) {
		this.view = view;
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
		ArrayList<String> l = Manager.StringtoList(s);
		l = ListShuffler.onlineshuffle(l);
		this.view.setShuffledList(l);
	}
	//Shows first element of shuffled list on textarea, removes that element from the list
	public void putelelement(ArrayList<String> l){
		if(l.size() > 0) {
			this.view.fillTextArea(l.get(0));
			l.remove(0);
			if(l.size()==0)
				this.view.disableNextButton();
		}
		
	}

	
}
