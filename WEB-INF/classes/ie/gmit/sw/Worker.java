package ie.gmit.sw;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author sl0th
 * Worker gets next job in the inQueue performs operations and returns the result to the out-queue
 */
public class Worker {
	public Worker() {

	}
	/**
	 * Gets next job from the in-queue which is defined in service handler
	 * @param Request
	 * @param String
	 */
	static Request response;
	public static void getJob(Request r,String option) {
		System.out.println("IN get job"); 
		Parser.readFile(option);
  		Map result = Parser.parse(r.getMessage(),option);
 		putResult(r,Parser.database.getLanguage(result));
  		response = r;
	}
	/**
	 * This method will put the result from performing the distance calculation into the out-queue
	 * @param Request
	 */
	public static Language putResult(Request r,Language lang) {
		//put result in outqueue
		System.out.println("NUMBER " + r.getTaskNumber() + " " + lang);
		ServiceHandler.outQueue.put(r.getTaskNumber(),lang);
 		checkQueue();
		return lang;
	}
	public static Request checkQueue() {
		return response;
	}
}
