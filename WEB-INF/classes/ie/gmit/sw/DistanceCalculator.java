package ie.gmit.sw;

public class DistanceCalculator {
	public static void Compare(String subject, String kmer) {
		int frequency = 0, kmerCount = 0;
		LanguageEntry e = new LanguageEntry(frequency, kmerCount);
		for(int i = 0; i < subject.length(); i++) {
			if(subject.charAt(i) == kmer.charAt(i)) {
				e.setFrequency(frequency++);
		
			}
		}
	}
}
