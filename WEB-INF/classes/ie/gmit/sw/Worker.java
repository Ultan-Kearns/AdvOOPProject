package ie.gmit.sw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Worker {
	public Worker() {

	}

	public void getJob() {

	}

	/**
	 * This function parse takes a string @param s and an int @param kmer as parameters then 
	 * breaks the string up into Kmers of the integer specified and 
	 * returns a stringbuffer which is equal to the string but broken into
	 * kmers 
	 * */
	public static StringBuffer parse(String subjectString,String option) {
		//remove whitespace from subject string
		subjectString = subjectString.replace(" ", "");
		//check subject string has even length if not append 0
		//this is because the parser breaks the string up into even nums only
		if (subjectString.length() % 2 != 0) {
			subjectString += "0";
		}
		final char delimiter = '_';
		//need refactor
		String kmers = "";
		for(int i = 0; i < Integer.parseInt(option); i++)
		{
			kmers += ".";
		}
		//replace character at every two characters with delimiter except for end of string
		//?!$ ensures that no delimiter will be placed at end of the subject string
		subjectString = subjectString.replaceAll(kmers+"(?!$)", "$0"+delimiter);
		final StringBuffer parsed = new StringBuffer(subjectString);
		//return string broken into k-mers
		return parsed;
	}
	public int compare() {
		return 0;
	}
	//read in file store in map string languageString, String language, use regEx to find string after @ symbol
	public static void readFile() {
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
