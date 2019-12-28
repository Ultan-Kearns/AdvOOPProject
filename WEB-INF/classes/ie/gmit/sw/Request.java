package ie.gmit.sw;
/**
 * 
 * @author sl0th
 * This class creates a request and will be used to store the message and job nummber
 */
public class Request {
	private long taskNumber;
	private String message;
	/**
	 * Constructor that takes two params
	 * @param message
	 * @param jobNumber
	 * This will create a request which will be stored on a list for the incoming jobs
	 */
	public Request(String message,long taskNumber)
	{
		this.message = message;
		this.taskNumber = taskNumber;
	}
	public long getTaskNumber() {
		return taskNumber;
	}
	public void setTaskNumber(long taskNumber) {
		this.taskNumber = taskNumber;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
