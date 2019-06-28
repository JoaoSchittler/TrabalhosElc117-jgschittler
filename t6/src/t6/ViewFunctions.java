package t6;

import java.util.ArrayList;

public interface ViewFunctions {

	public void makeDetailedTableWindow(int selectedIndex);
	
	public void makeAboutWindow();
	
	public void makeSourceChangerWindow();

	public void setTableList(ArrayList<String[]> data);

	public void endApplication();

}
