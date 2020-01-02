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
		System.out.println(s);
		putResult(r);
		return s;
	}
	/**
	 * This method will put the result from performing the distance calculation into the out-queue
	 */
	public static void putResult(Request r) {
		//put result in outqueue
		ServiceHandler.outQueue.put(r.getTaskNumber(),null);
		//need to perform distance calculation and set language
	}
}
