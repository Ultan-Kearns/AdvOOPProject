package ie.gmit.sw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author sl0th
 * This class will handle the parsing of data into k-mers and the reading in of the dataset
 */
public class Parser {
	// read in file store in map string languageString, String language, use regEx
	// to find string after @ symbol
	/**
	 * This method reads the information from the dataset and stores it in a map
	 */
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
		Map<StringBuffer, String> mapLanguage = new ConcurrentHashMap<StringBuffer, String>();

		try {
			while ((i = fr.read()) != -1) {
				System.out.println((char) i);
				file.append(i);
				if (file.charAt(i) == '@') {
					mapLanguage.put(file, "");
					// clear map
					file.delete(0, i);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This function parse takes a string @param subjectString and another String @param option, which is defined in service handler,as
	 * parameters then breaks the string up into Kmers of the integer specified and
	 * returns a stringbuffer which is equal to the string but broken into kmers
	 */
	public static StringBuffer parse(String subjectString, String option) {
		// remove whitespace from subject string
		subjectString = subjectString.replace(" ", "");
		// check subject string has even length if not append 0
		// this is because the parser breaks the string up into even nums only
		if (subjectString.length() % 2 != 0) {
			subjectString += "0";
		}
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
		// get rid of whitespace
		subjectString = subjectString.replaceAll("\\s", "");
		final StringBuffer parsed = new StringBuffer(subjectString);
		// return string broken into k-mers
		return parsed;
	}
}