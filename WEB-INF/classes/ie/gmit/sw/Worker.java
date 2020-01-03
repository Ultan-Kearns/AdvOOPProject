package ie.gmit.sw;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author sl0th
 * Worker gets next job in the inQueue performs operations and returns the result to the out-queue
 */
public class Worker {
	static Map query = new ConcurrentHashMap<Integer,String>();
	public Worker() {

	}
	/**
	 * Gets next job from the in-queue which is defined in service handler
	 * @param Request
	 * @param String
	 */
	public static String getJob(Request r,String option) {
		String s = Parser.parse(r.getMessage(),option).toString();
		query.put(r.getMessage().toString(),s);
 		putResult(r);
		return s;
	}
	/**
	 * This method will put the result from performing the distance calculation into the out-queue
	 * @param Request
	 */
	public static void putResult(Request r) {
		//put result in outqueue
		ServiceHandler.outQueue.put(r.getTaskNumber(),Language.Achinese);
		//need to perform distance calculation and set language
	}
}
