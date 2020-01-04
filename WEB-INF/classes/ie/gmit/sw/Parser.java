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
				// parse into kmers then insert kmer size
				for (int i = 0; i < temp.length() - op; i+= op) {
					database.add(temp.substring(i, i + op).toLowerCase(), lang);
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
		Map<String, LanguageEntry> queryMap = new HashMap<String, LanguageEntry>();
		// need refactor
		final StringBuffer parsed = new StringBuffer(subjectString);
		int op = Integer.parseInt(option);
		System.out.println(parsed);
		for (int i = 0; i < parsed.length() - op; i+= op) {
			int frequency = 1;
			// need to set frequency of kmer
			System.out.println(parsed.substring(i, i + op));
			if (queryMap.containsKey(parsed.substring(i, i + op).toLowerCase())) {
 				System.out.println("FREQUENCY " + queryMap.get(parsed.substring(i, i + op).toLowerCase()).getFrequency());
				frequency += queryMap.get(parsed.substring(i, i + op).toLowerCase()).getFrequency();
			}

			queryMap.put(parsed.substring(i, i + op).toLowerCase(),
					new LanguageEntry(parsed.substring(i, i + op).toLowerCase().hashCode(), frequency));

		}
		return queryMap;
	}
}
