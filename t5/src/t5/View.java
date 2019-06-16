package t5;

import java.util.ArrayList;

public interface View {
	ArrayList<String> shuffledList = null;
	public void enableFileChooser();
	public void fillTextArea(String s);
	public void fillLabel(String s);
	public void disableLabel();
	public void setShuffledList(ArrayList<String> l);
	public void disableNextButton();
	public void enableNextButton();
	public void enableShuffleButton();
	public void enableClearButton();
	
}
