package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author sl0th This class will handle the parsing of data into k-mers and the
 *         reading in of the dataset
 */
public class Parser {
	static Database database = new Database();
	static String key;
	Map query = new ConcurrentHashMap<String, Integer>();

	// read in file store in map string languageString, String language, use regEx
	// to find string after @ symbol
	/**
	 * This method reads the information from the dataset and stores it in a map
	 */
	public static void readFile(String option) {
		BufferedReader br = null;
		File f;
		Request r = new Request("tjeo", 1);
		Worker.getJob(r, option);
		try {
			f = new File("/data/wili-2018-Edited.txt");
			br = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch blsock
			e.printStackTrace();
		}
 		String temp = "";
		try {
			Language lang;
			while ((temp = br.readLine()) != null) {

				// get language
				key = temp.substring(temp.lastIndexOf('@'));
				key = key.replace("@", "");
				// get rid of anything that isn't a letter or a digit
				temp = temp.replaceAll("\\W", "");
				// needed to replace all whitespace in key always fun to debug....
				key = key.replaceAll("\\W", "");
				lang = Language.valueOf(key);
				int op = Integer.parseInt(option);
				// parse into kmers then insert kmer size need to refactor only works for 2
				for (int i = 0; i < temp.length() - op; i++) {
					database.add(temp.substring(i, i + op), lang);
				}
			}
 			database.resize(300);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This function parse takes a string @param subjectString and another
	 * String @param option, which is defined in service handler,as parameters then
	 * bre ks the string up into Kmers of the integer specified and returns a
	 * stringbuffer which is equal to the string but broken into kmers
	 */
	public static Map parse(String subjectString, String option) {
		Map<Integer, LanguageEntry> queryMap = new HashMap<Integer, LanguageEntry>();
		// check subject string has even length if not append 0
		// this is because the parser breaks the string up into even nums only
		// need to fix for larger kmers than 2
		if (subjectString.length() % Integer.parseInt(option) != 0) {
			subjectString += "0";
		}
		// need refactor
		int frequency = 0;
		// replace character at every two characters with delimiter except for end of
		// string
		// ?!$ ensures that no delimiter will be placed at end of the subject string
		final StringBuffer parsed = new StringBuffer(subjectString);
		int op = Integer.parseInt(option);
		for (int i = op; i < parsed.length() - op; i++) {

			if (queryMap.containsKey(parsed.subSequence(i, i + op).hashCode())) {
				frequency++;
			}
			queryMap.put(parsed.substring(i, i + op).hashCode(),
					new LanguageEntry(parsed.substring(i, i + op).hashCode(), frequency));
		}
		// return string broken into k-mers need to do analysis here also make lower to
		// lower probability
		return queryMap;
	}
}
