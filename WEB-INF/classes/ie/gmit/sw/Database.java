package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 
 * Deals with establishing a database for the language and comparing it to the query set
 * uses code from John Healy   
 *
 */
public class Database {
	private  Map<Language, Map<Integer, LanguageEntry>> db = new ConcurrentHashMap<>();

	/**
	 * 
	 * @param s
	 * @param lang Takes charsequence and language as parameters and stores them in
	 *             a language database
	 */
	public void add(CharSequence s, Language lang) {
		int kmer = s.hashCode();
		Map<Integer, LanguageEntry> langDb = getLanguageEntries(lang);
		int frequency = 1;
		if (langDb.containsKey(kmer)) {
			frequency += langDb.get(kmer).getFrequency();
 		}
		// may be issue since passing full string with delimiter
		langDb.put(kmer, new LanguageEntry(kmer, frequency));
	}

	private Map<Integer, LanguageEntry> getLanguageEntries(Language lang) {
		Map<Integer, LanguageEntry> langDb = null;
		if (db.containsKey(lang)) {
			langDb = db.get(lang);
		} else {
			langDb = new TreeMap<Integer, LanguageEntry>();
			db.put(lang, langDb);
 		}
		return langDb;
	}

	/**
	 * 
	 * @param max Resizes the data set to get top kmers
	 */
	public void resize(int max) {
		Set<Language> keys = db.keySet();
		for (Language lang : keys) {
			Map<Integer, LanguageEntry> top = getTop(max, lang);
			db.put(lang, top);
		}
	}

	/**
	 * Gets Language that has highest rank that is the language 
	 */
	public Map<Integer, LanguageEntry> getTop(int max, Language lang) {
		Map<Integer, LanguageEntry> temp = new ConcurrentHashMap<>();
		List<LanguageEntry> les = new ArrayList<>(db.get(lang).values());
		Collections.sort(les);

		int rank = 1;
		for (LanguageEntry le : les) {
			le.setRank(rank);
 			temp.put(le.getKmer(), le);
			if (rank == max)
				break;
			rank++;
		}

		return temp;
	}

	/**
	 * Calls function to calculate out of place distance and returns top ranking language.
	 * @param Map<Integer, LanguageEntry> 
	 * @return oopm
	 */
	public Language getLanguage(Map<Integer, LanguageEntry> query) {
		TreeSet<OutOfPlaceMetric> oopm = new TreeSet<>();
		Set<Language> langs = db.keySet();
		for (Language lang : langs) {
			oopm.add(new OutOfPlaceMetric(lang, getOutOfPlaceDistance(query, db.get(lang))));
		}
  		return oopm.first().getLanguage();
	}

	private int getOutOfPlaceDistance(Map<Integer, LanguageEntry> query, Map<Integer, LanguageEntry> subject) {
		int distance = 0;

		Set<LanguageEntry> les = new TreeSet<>(query.values());
		for (LanguageEntry q : les) {
			LanguageEntry s = subject.get(q.getKmer());
			if (s == null) {
				distance += subject.size() + 1;
			} else {
				distance += s.getRank() - q.getRank();
			}
		}
	
		return distance;
	}

	private class OutOfPlaceMetric implements Comparable<OutOfPlaceMetric> {
		private Language lang;
		private int distance;

		public OutOfPlaceMetric(Language lang, int distance) {
			super();
			this.lang = lang;
			this.distance = distance;
		}

		public Language getLanguage() {
			return lang;
		}

		public int getAbsoluteDistance() {
			return Math.abs(distance);
		}

		@Override
		public int compareTo(OutOfPlaceMetric o) {
			return Integer.compare(this.getAbsoluteDistance(), o.getAbsoluteDistance());
		}

		@Override
		public String toString() {
			return "[lang=" + lang + ", distance=" + getAbsoluteDistance() + "]";
		}

	}

	/**
	 * Returns all pertinent information from this class such as total kmers in
	 * language and all languages in the set
	 */
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		int langCount = 0;
		int kmerCount = 0;
		Set<Language> keys = db.keySet();
		for (Language lang : keys) {
			langCount++;
			sb.append(lang.name() + "->\n");
			Collection<LanguageEntry> m = new TreeSet<>(db.get(lang).values());
			kmerCount += m.size();
			for (LanguageEntry le : m) {
				sb.append("\t" + le + "\n");
			}
		}

		sb.append(kmerCount + " total k-mers in " + langCount + " languages");
		return sb.toString();
	}
}