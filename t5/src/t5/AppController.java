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
		}
	}
	public void shuffle(String s) {
		ArrayList<String> l = Manager.StringtoList(s);
		l = ListShuffler.offlineshuffle(l);
		this.view.setShuffledList(l);
		if(l.size()>1) this.view.enableNextButton();
	}
	//Shows first element of shuffled list on textarea, removes that element from the list
	public void putelelement(ArrayList<String> l){
		if(l.size() > 0) {
			this.view.fillLabel(l.get(0));
			l.remove(0);
			//If the list had only one element, it disables the "Next" button
			if(l.size()==0)
				this.view.disableNextButton();
		}
		
	}
	public void checkifcanshuffle(String text) {
		String newline= System.getProperty("line.separator");
		// text.contains("\n") procura por literalmente \n na string ao invés de procurar quebra de linha
		if(text.contains(newline)) this.view.enableShuffleButton();
	}
	
}
