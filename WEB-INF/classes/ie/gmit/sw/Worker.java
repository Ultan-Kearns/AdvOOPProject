package ie.gmit.sw;

public class Worker {
	public Worker() {

	}

	public void getJob() {

	}

	// parse string into kmer
	public static StringBuilder parse(String s) {
		if (s.length() % 2 != 0) {
			s += "0";
		}
		final char delimiter = '_';
		final StringBuilder parsed = new StringBuilder(s);
		int count = 0;
		System.out.println(parsed.length());
		for (int i = 0; i < s.length(); i++) {
			if (i % 2 == 0 && i != 0) {
				parsed.insert(i, delimiter);
			}
		}
		return parsed;
	}

}
