package t5;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Manager {
		
		private static ArrayList<String> read_file(FileReader reader) throws IOException
		{
			ArrayList<String> l =  new ArrayList<String>();
			int auxc;
			String auxs = "";
			while(true)
			{
					auxc = reader.read();
					if(auxc == -1) break; // EOF
					if(auxc!='\n')
						auxs = auxs + (char)auxc;
					else {
						l.add(auxs);
						auxs = "";
					}

			}
			System.out.println(l);
			return l;
		}
		public static ArrayList<String> FiletoList(File file) throws IOException {
			    file.createNewFile();
			    FileReader reader = new FileReader(file);
			    ArrayList<String> list = read_file(reader);
			    reader.close();
				return list;
		}
		public static ArrayList<String> FiletoList(FileReader filereader) throws IOException {
				ArrayList<String> list = new ArrayList<String>();
				read_file(filereader);
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
			char[] c = s.toCharArray();
			StringBuffer aux  = new StringBuffer();
			for(int i = 0;i < s.length();i++) {
				aux.append(c[i]);
				if(c[i]=='\n') {
					list.add(aux.toString());
					System.out.print("Aux:"+aux);
					aux.delete(0,i-1);
				}
			}
			return list;
		}
}
