package t5;

import java.util.ArrayList;

public interface View {
	ArrayList<String> shuffledList = null;
	public void enableFileChooser();
	public void fillTextArea(String s);
	public void setShuffledList(ArrayList<String> l);
	public void disableNextButton();
	
}
