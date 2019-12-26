package ie.gmit.sw;
/**
 * 
 * @author sl0th
 * This class creates a request and will be used to store the message and job nummber
 */
public class Request {
	private long taskNumber;
	private String m;
	/**
	 * Constructor that takes two params
	 * @param message
	 * @param jobNumber
	 * This will create a request which will be stored on a list for the incoming jobs
	 */
	public Request(String message,long jobNumber)
	{
		this.m = message;
		this.taskNumber = jobNumber;
	}
}
