package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import stepdefinitions.StepDefinitions;

public class Utilities {

	
	static RequestSpecification requestspec;
	static SessionFilter session = new SessionFilter();
	private static String Filepath = "C:\\Kiruba\\Test Folder\\Logs\\Log";
	static String df = new SimpleDateFormat("yyyyMMddHHmm'.txt'").format(new Date());
	static String filename = Filepath+df;
	public static RequestSpecification requestspecification() throws IOException
	{
		if(requestspec==null)
		{
		PrintStream log = new PrintStream(new FileOutputStream(filename));
		requestspec = new RequestSpecBuilder().setRelaxedHTTPSValidation().setPort(8081)
				.addFilter(session).addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
		return requestspec;
	}
		return requestspec;
		
	}
	
	public static String getJsonPath(Response response, String key)
	{
    	String finalresponse=response.asString();
    	JsonPath js = new JsonPath(finalresponse);
    	return js.get(key).toString();
	}
	
	public String getglobalValue(String key) throws IOException
	{
		Properties ps = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Kiruba\\Eclipse\\JiraDefectUpdate\\src\\test\\java\\resources\\globalvalue.properties");
		ps.load(fis);
		return ps.getProperty(key);
	}

}
