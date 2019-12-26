package ie.gmit.sw;

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
	public static StringBuffer parse(String subjectString,int kmer) {
		if (subjectString.length() % 2 != 0) {
			subjectString += "0";
		}
		final char delimiter = '_';
		//need refactor
		String kmers = "";
		for(int i = 0; i < kmer; i++)
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

}
