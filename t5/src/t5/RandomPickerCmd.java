package t5;
import java.io.FileNotFoundException; 
import java.io.FileReader; 
import java.io.IOException;
import java.util.ArrayList;
public class RandomPickerCmd {
	static FileReader reader;
	private static void open_file(String name) 
	{
		try{reader = new FileReader(name);}
		catch(FileNotFoundException fnf) 
		{
			System.out.println("File  not found ");
			return;
		}
	}
	private static void close_file()
	{
		try {
			reader.close();
		} catch (IOException e){
			System.out.println("IOExeption while closing file");
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		open_file("names.txt");
		ArrayList<String> Names = null;
		try {
			Names = Manager.FiletoList(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close_file();
		if(Names != null && Names.size()>0)
			ListShuffler.onlineshuffle(Names);		
	}
}
