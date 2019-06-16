package t5;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
public class RandomPickerCmd {
	static File file;
	private static void open_file(String name) 
	{
		file = new File(name);
	}
	public static void main(String[] args) {
		open_file("names.txt"); // Change to open_file(args[0]);
		ArrayList<String> Names = null;
		try {
			if(file!=null) {
				Names = Manager.FiletoList(file);
				System.out.println("Initial list : "+Names);
			}
			else
				System.out.println("File not found");
		} catch (IOException e) {e.printStackTrace();}
		
		if(Names != null && Names.size()>0) {
			Names = ListShuffler.onlineshuffle(Names);	
			//System.out.println("List after Shuffle: "+Names);
			System.out.print("List to show user : ");
			for(String element:Names) {
				try {
					System.in.read();
					System.out.print(element);
				} catch (IOException e) {e.printStackTrace();}
				
			}
		}
	}
}
