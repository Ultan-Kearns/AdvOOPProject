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
	public static void getJob(Request r,String option) {
		//need to add this to outQueue
		Parser.parse(r.getMessage(),option);
	}
	/**
	 * This method will put the result from performing the distance calculation into the out-queue
	 */
	public void putResult() {
		
	}
}
