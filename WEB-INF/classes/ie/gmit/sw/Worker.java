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
 		Map result = Parser.parse(r.getMessage(),option);
 		Database db = new Database();
 		if(result.size() != 0) {  
 			Parser.readFile(option);
 			System.out.println();
 		}
 		putResult(r,db.getLanguage(result));
	}
	/**
	 * This method will put the result from performing the distance calculation into the out-queue
	 * @param Request
	 */
	public static void putResult(Request r,Language lang) {
		//put result in outqueue
		ServiceHandler.outQueue.put(r.getTaskNumber(),lang);
		//need to perform distance calculation and set language
	}
}
