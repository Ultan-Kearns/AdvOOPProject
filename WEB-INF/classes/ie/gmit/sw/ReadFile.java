package ie.gmit.sw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReadFile {
	//read in file store in map string languageString, String language, use regEx to find string after @ symbol
	public static void read() {
		FileReader fr = null; 
		File f = new File("/data/wili-2018-Large-117500-Edited.txt");
 		try {
			fr = new FileReader(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		StringBuffer file = new StringBuffer();
		Map<String, String> mapLanguage = new ConcurrentHashMap<String, String>();
		try {
			while((i = fr.read()) != -1) {
				System.out.println((char) i);
				file.append((char)i);
				 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}
}
