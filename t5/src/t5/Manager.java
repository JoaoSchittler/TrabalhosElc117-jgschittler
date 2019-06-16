package t5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Manager {
		
		private static ArrayList<String> read_file(File file ) throws IOException
		{
			ArrayList<String> l =  new ArrayList<String>();
			BufferedReader bufferRead = new BufferedReader(new FileReader(file));
	        String line = null;
	        while((line = bufferRead.readLine()) != null){
	            l.add(line);
	        }
	        bufferRead.close();
			return l;
		}
		public static ArrayList<String> FiletoList(File file) throws IOException {
			    ArrayList<String> list = read_file(file);
				return list;
		}

		public static String ListtoString(ArrayList<String> list) {
			StringBuffer str = new StringBuffer();
			
			for(String element: list) {
				str.append(element + "\n");
				
			}
			System.out.println(str.toString());
			return str.toString();
		}
		public static ArrayList<String> StringtoList(String s) {
			ArrayList<String> list = new ArrayList<String>();
			String[] strarray = s.split("\n");
			for(String element:strarray) {
				list.add(element + System.getProperty("line.separator"));
			}
			return list;
		}
}
