package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author sl0th This class will handle the parsing of data into k-mers and the
 *         reading in of the dataset
 */
public class Parser {
	static Database db = new Database();
	static LanguageEntry lang = new LanguageEntry(0,0);
	// read in file store in map string languageString, String language, use regEx
	// to find string after @ symbol
	/**
	 * This method reads the information from the dataset and stores it in a map
	 */
	public static Map readFile(String option) {
		BufferedReader br = null;
		File f;
		try {
			f = new File("/data/wili-2018-Edited.txt");
			br = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch blsock
			e.printStackTrace();
		}
 		Map<String, String> mapLanguage = new ConcurrentHashMap<String, String>();
 		String temp = "";
 		String key = "";
		try {
			while ((temp = br.readLine()) != null) {
				//get language
				key = temp.substring(temp.lastIndexOf('@'));
				//get rid of anything that isn't a letter or a digit
				temp = temp.replaceAll("\\W", "");
				//parse using option
				temp = Parser.parse(temp, option);
				//put in map using the Language as a key
				mapLanguage.put(key, temp);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		return mapLanguage;
	}

	/**
	 * This function parse takes a string @param subjectString and another
	 * String @param option, which is defined in service handler,as parameters then
	 * breaks the string up into Kmers of the integer specified and returns a
	 * stringbuffer which is equal to the string but broken into kmers
	 */
	public static String parse(String subjectString, String option) {
		// check subject string has even length if not append 0
		// this is because the parser breaks the string up into even nums only
		//need to fix for larger kmers than 2
		if (subjectString.length() % Integer.parseInt(option) != 0) {
				subjectString += "0";
		}
		//think that I need to just compare here then update frequency
		final char delimiter = '_';
		// need refactor
		String kmers = "";
		for (int i = 0; i < Integer.parseInt(option); i++) {
			kmers += ".";
		}
		// replace character at every two characters with delimiter except for end of
		// string
		// ?!$ ensures that no delimiter will be placed at end of the subject string
		subjectString = subjectString.replaceAll(kmers + "(?!$)", "$0" + delimiter);		
		final StringBuffer parsed = new StringBuffer(subjectString);
		// return string broken into k-mers
		return parsed.toString();
	}
}
