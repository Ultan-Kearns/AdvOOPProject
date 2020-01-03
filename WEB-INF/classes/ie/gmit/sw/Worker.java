package ie.gmit.sw;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author sl0th
 * Worker gets next job in the inQueue performs operations and returns the result to the out-queue
 */
public class Worker {
	static Map query = new HashMap<Integer,LanguageEntry>();
	public Worker() {

	}
	/**
	 * Gets next job from the in-queue which is defined in service handler
	 * @param Request
	 * @param String
	 */
	public static void getJob(Request r,String option) {
		System.out.println("IN get job");
 		Parser.parse(r.getMessage(),option,1);
 		Database db = new Database();
 
 		putResult(r);
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
