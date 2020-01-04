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
	public static void getJob(Request r,String option) {
		System.out.println("IN get job"); 
		Parser.readFile(option);
		System.out.println("HELLO " + r.getMessage());
 		Map result = Parser.parse(r.getMessage(),option);
 		putResult(r,Parser.database.getLanguage(result));
	}
	/**
	 * This method will put the result from performing the distance calculation into the out-queue
	 * @param Request
	 */
	public static Language putResult(Request r,Language lang) {
		//put result in outqueue
		ServiceHandler.outQueue.put(r.getTaskNumber(),lang);
		System.out.println(ServiceHandler.outQueue.containsKey(r.getTaskNumber()));
		System.out.println("Langauge is " + lang);
		checkQueue();
		return lang;
	}
	public static long checkQueue() {
		if(ServiceHandler.inQueue.isEmpty() == false) {
			System.out.println(ServiceHandler.outQueue.get(1));
			return 0;
		}
		return -1;
	}
}
