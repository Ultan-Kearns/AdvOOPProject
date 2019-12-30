package ie.gmit.sw;

/**
 * 
 * @author sl0th
 * Worker gets next job in the inQueue performs operations and returns the result to the out-queue
 */
public class Worker {
	public Worker() {

	}
	/**
	 * Get next job from the in-queue which is defined in service handler
	 */
	public static String getJob(Request r,String option) {
		String s = Parser.parse(r.getMessage(),option).toString();
		//need to perform distance calculation and compare to the file
		
		System.out.println(s);
		return s;
	}
	/**
	 * This method will put the result from performing the distance calculation into the out-queue
	 */
	public void putResult() {
		
	}
}
