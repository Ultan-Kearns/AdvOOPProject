package ie.gmit.sw;

public class Request {
	private long taskNumber;
	private String m;
	public Request(String message,long jobNumber)
	{
		this.m = message;
		this.taskNumber = jobNumber;
	}
}
