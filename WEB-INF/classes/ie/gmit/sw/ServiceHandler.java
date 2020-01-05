package ie.gmit.sw;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.*;
import javax.servlet.http.*;

import com.sun.org.apache.xml.internal.security.Init;

/*
 * To compile this servlet, open a command prompt in the web application directory and execute the following commands:
 *
 * Linux/Mac																	Windows
 * ---------																	---------
 * cd WEB-INF/classes/															cd WEB-INF\classes\
 * javac -cp .:$TOMCAT_HOME/lib/servlet-api.jar ie/gmit/sw/*.java				javac -cp .:%TOMCAT_HOME%/lib/servlet-api.jar ie/gmit/sw/*.java
 * cd ../../																	cd ..\..\
 * jar -cf ngrams.war *															jar -cf ngrams.war *
 *
 * Drag and drop the file ngrams.war into the webapps directory of Tomcat to deploy the application. It will then be
 * accessible from http://localhost:8080.
 *
 * NOTE: the text file containing the 253 different languages needs to be placed in /data/wili-2018-Edited.txt. This means
 * that you must have a "data" directory in the root of your file system that contains a file called "wili-2018-Edited.txt".
 * Do NOT submit the wili-2018 text file with your assignment!
 *
*/
/**
 * 
 * @author Ultan Kearns
 * @version 1.00
 * Code was made using templates from John Healy which will be refactored
 *       and modified by me
 * 
 */
public class ServiceHandler extends HttpServlet {

	private String languageDataSet; // This variable is shared by all HTTP requests for the servlet
	private static long jobNumber = 0; // The number of the task in the async queue
	private Database d;
	private File f;
	static Map<Long, Language> outQueue = new ConcurrentHashMap<Long, Language>();
	private List<Request> inQueue = new LinkedList<Request>();

	/**
	 * Initializes the servelet this is used to do a basic setup of the server and
	 * throws an exception if servlet fails to start and it loads the data set
	 */
	public void init() throws ServletException {
		ServletContext ctx = getServletContext(); // Get a handle on the application context
		languageDataSet = ctx.getInitParameter("LANGUAGE_DATA_SET"); // Reads the value from the <context-param> in
																		// web.xml
		// You can start to build the subject database at this point. The init() method
		// is only ever called once during the life cycle of a servlet
		f = new File(languageDataSet);
	}

	/**
	 *  
	 * @param HttpServletRequest req
	 *  @param HttpServletResponse resp 
	 *  The doGet method takes two input params
	 *  and is responsible for setting up the UI
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html"); // Output the MIME type
		PrintWriter out = resp.getWriter(); // Write out text. We can write out binary too and change the MIME type...

		// Initialise some request varuables with the submitted form info. These are
		// local to this method and thread safe...
		String option = req.getParameter("cmbOptions"); // Change options to whatever you think adds value to your
														// assignment...
		String message = req.getParameter("query");
		String taskNumber = req.getParameter("frmTaskNumber");

		out.print("<html><head><title>Advanced Object Oriented Software Development Assignment</title>");
		out.print("</head>");
		out.print("<body>");
		if (taskNumber == null) {
			taskNumber = new String("Task Number: " + jobNumber);
			jobNumber++;
			// Add job to in-queue
			inQueue.add(0, new Request(message, jobNumber));
 		}  
		out.print("<H1>Processing job number: " + taskNumber + " Results will appear below</H1>");
		out.print("<div id=\"r\"></div>");

		out.print("<font color=\"#993333\"><b>");
		out.print("Language Dataset is located at " + languageDataSet + " and is <b><u>" + f.length()
				+ "</u></b> bytes in size");
		out.print("<br>Option(s): " + option);
		out.print("<br>Query Text : " + message);
		out.print("</font><p/>");
		Worker.getJob(inQueue.get(0), option);
		//poll queue for new jobs
		if(outQueue.containsKey(jobNumber) || inQueue.get(0).getTaskNumber() != jobNumber)
		{
			taskNumber = new String("Task Number: " + jobNumber);
			message = inQueue.get(0).getMessage();

			out.print("<h1> The language is " + outQueue.get(jobNumber).getLanguageName() + "</h1>");
			out.print(inQueue.size());
		}
		out.print("<form method=\"POST\" name=\"frmRequestDetails\">");
		out.print("<input name=\"cmbOptions\" type=\"hidden\" value=\"" + option + "\">");
		out.print("<input name=\"query\" type=\"hidden\" value=\"" + message + "\">");
		out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("<meta http-equiv=\\\"refresh\\\" content=\\\"10\\\">");
 
		out.print("</form>");
		out.print("</body>");
		out.print("</html>");

		out.print("<script>");
		// set waiting period
		out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 10000);");
		out.print("</script>");
	}

	/**
	 *  
	 * @param HttpServletRequest req 
	 * @param HttpServletResponse resp 
	 * doPost method takes two params and is responsible for taking in the request and
	 * returning a response
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	public static void main(String[] args) {
		
	}
}