package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * 
 * @author sl0th This class will handle the parsing of data into k-mers and the
 *         reading in of the dataset as well as all other functions related to ngram system
 */
public class Parser {
	static Database database = new Database();
	static String key;
	 	static Map<Integer, LanguageEntry> queryMap = new HashMap<Integer, LanguageEntry>();

	// read in file store in map string languageString, String language, use regEx
	// to find string after @ symbol
	/**
	 * 
	 * This method reads the information from the dataset and stores it in a map
	 * @param String option - this is whatever option for kmers
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
	 * @return Map
	 */
	//need to sort map based on frequency
	public static Map parse(String subjectString, String option) {
		// need refactor
		final StringBuffer parsed = new StringBuffer(subjectString);
		int op = Integer.parseInt(option);
 
		for (int i = 0; i < parsed.length() - op; i+= op) {
			int frequency = 1;
			int rank = 1;

			System.out.println(parsed.substring(i, i + op));

			if (queryMap.containsKey(parsed.substring(i, i + op).hashCode())) {
				System.out.println(parsed.substring(i, i + op));
				frequency += queryMap.get(parsed.substring(i, i + op).hashCode()).getFrequency();
				queryMap.get(parsed.substring(i, i + op).hashCode()).setFrequency(frequency);

				rank += queryMap.get(parsed.substring(i, i + op).hashCode()).getRank();
				queryMap.get(parsed.substring(i, i + op).hashCode()).setRank(rank);
			}	 
			queryMap.put(parsed.substring(i, i + op).hashCode(),
					new LanguageEntry(i, frequency));
		}	 
		System.out.println(queryMap.values());
		queryMap = sort(queryMap);
		//need to sort by frequency
		return queryMap;
	}
	/**
	 * Takes map as parameter then returns the map with kmers sorted by frequency
	 * 
	 * @param Map<Integer, LanguageEntry> m
	 * @return Map<Integer, LanguageEntry> sorted query map
	 */
	public static Map<Integer,LanguageEntry>  sort(Map<Integer,LanguageEntry> m) {
		//get key set and values
		List<LanguageEntry> queryText = new ArrayList<>(m.values());	
		List<Integer> queryKeys = new ArrayList<>(m.keySet());
	 
 			 for(int i = 0; i < queryKeys.size(); i++) {
 				 LanguageEntry l = queryText.get(i);
 			 if(i != queryText.size() - 1 && l.getFrequency() > queryText.get(i + 1).getFrequency())
			 {
				 LanguageEntry temp = queryText.get(i + 1);
				 System.out.println(" test query " + queryMap.get(queryKeys.get(i + 1)).getFrequency());

				 queryText.get(i + 1).setKmer(l.getKmer());
				 queryText.get(i + 1).setFrequency(l.getFrequency());
				 queryText.get(i + 1).setRank(l.getRank());
				 System.out.println(" test " + queryText.get(i + 1).getFrequency());
 				 queryMap.put(queryKeys.get(i),queryText.get(i));
 				 queryText.get(i).setKmer(temp.getKmer());
				 queryText.get(i).setFrequency(temp.getFrequency());
				 queryText.get(i).setRank(temp.getRank());
  				 queryMap.put(queryKeys.get(i),queryText.get(i));
 
 			}
		 
			 System.out.println("L " + l.getFrequency());

		}
 			System.out.println("VALUES " + queryMap.values());

 			
		 return queryMap;
	}
}