package stepdefinitions;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Assignee;
import pojo.ClassFile;
import pojo.CreateIssue;
import pojo.Fields;
import pojo.IssueType;
import pojo.Priority;
import pojo.Project;
import pojo.Reporter;
import resources.EnumResources;
import resources.JiraPayload;
import resources.Utilities;

public class StepDefinitions extends ClassFile{
	RequestSpecification response;
	ResponseSpecification responsespec;
	Response resp;
	public String id, key;
	String raisedlog = "C:\\Kiruba\\Test Folder\\Defect Logs\\Raised\\";
	String closedlog = "C:\\Kiruba\\Test Folder\\Closure Logs\\Closed\\";
	SessionFilter session=new SessionFilter();
	JiraPayload data = new JiraPayload();
	List<String> defectid = GetDefectID();

	@Given("Add Login to JIRA API Payload")
	public void add_Login_to_JIRA_API_Payload() throws IOException {
		
		try {
			response = given().spec(Utilities.requestspecification()).body(JiraPayload.Login());
		} catch (IOException e) {
			response = given().spec(Utilities.requestspecification()).body(JiraPayload.Login());
			e.printStackTrace();
		}

	}

	@When("User should call the {string} using {string} http method")
	public void user_should_call_the_using_http_method(String resource, String httpmethod) {
		
		EnumResources apiresource = EnumResources.valueOf(resource);
		if(httpmethod.equalsIgnoreCase("POST"))
			resp = response.when().post(apiresource.getResource());
		else if(httpmethod.equalsIgnoreCase("GET"))
			resp = response.when().get(apiresource.getResource());

	}

	@Then("API call got success with the response code {int}")
	public void api_call_got_success_with_the_response_code(Integer statuscode) {
		
		if(resp.getStatusCode()==statuscode)
			System.out.println("Login successfull");
		else if(resp.getStatusCode()==statuscode)
			System.out.println("Issue created successfully");
		else if(resp.getStatusCode()==statuscode)
			System.out.println("Issue Closed Successfully");
		else if(resp.getStatusCode()==statuscode)
			System.out.println("Created issue log moved to Raised directory");
		else if(resp.getStatusCode()==statuscode)
			System.out.println("Closed issue log moved to Closed directory");
	}

	@Given("Add Create Issue API Payload")
	public void add_Create_Issue_API_Payload() throws IOException {
		
		CreateIssue ci = new CreateIssue();
		
		//Creating object for IssueType Class
		IssueType it = new IssueType();
		it.setName("Bug");

		//Creating object for ClassFile Class
		List<String> summary = ReadSummary();
		List<String> description = ReadDescription();
		List<String> environment = ReadEnvironment();
		List<String> prio = ReadPriority();
		List<String> assign = ReadAssignee();
		List<String> report = ReadReporter();
		List<String> proj = ReadProject();
				
		for(int i=0; i<summary.size();i++) {
			
			Priority priority = new Priority();
			priority.setName(prio.get(i));
			
			Assignee assignee = new Assignee();
			assignee.setName(assign.get(i));
			
			Reporter reporter = new Reporter();
			reporter.setName(report.get(i));
			
			Project project = new Project();
			project.setKey(proj.get(i));
			
			Fields fi = new Fields();
			fi.setProject(project);
			fi.setSummary(summary.get(i));
			fi.setDescription(description.get(i));
			fi.setIssuetype(it);
			fi.setEnvironment(environment.get(i));
			fi.setAssignee(assignee);
			fi.setPriority(priority);
			fi.setReporter(reporter);
			
			ci.setFields(fi);

    	response = given().spec(Utilities.requestspecification()).body(ci);
    			   user_should_call_the_using_http_method("CreateIssueAPI", "POST");
    			   api_call_got_success_with_the_response_code(200);
    			   log_the_and_from_the_API_response(id, key);
    			   attach_the_defect_log_file_to_the_created_issue(i);
    			   }  			 		
	}

	@Then("Log the {string} and {string} from the API response")
	public void log_the_and_from_the_API_response(String id, String key) {
		
		this.id = Utilities.getJsonPath(resp,"id");
		this.key = Utilities.getJsonPath(resp, "key");
				System.out.println(this.id+"\n"+this.key);
	}

	@Then("Attach the defect log file to the created issue")
	public void attach_the_defect_log_file_to_the_created_issue(int i) throws IOException {
		
		   response = given().spec(Utilities.requestspecification()).pathParam("id",id)
					.header("X-Atlassian-Token", "no-check")
					.header("Content-Type", "multipart/form-data").multiPart(listfiles[i]);
					user_should_call_the_using_http_method("AddAttachmentAPI", "POST");
			if(resp.getStatusCode()==200)
			{
				System.out.println("File attached success");
			listfiles[i].renameTo(new File(raisedlog+this.key+"_"+listfiles[i].getName()));
			  System.out.println("File moved to Raised folder"); 
			}

	}

	@Given("Add Close Issue API Payload")
	public void add_Close_Issue_API_Payload() throws IOException {
		
		
		for(int m=0;m<defectid.size();m++)
		{
			response = given().spec(Utilities.requestspecification()).pathParam("id",
			  defectid.get(m)) .queryParam("expand","transitions.fields").body(JiraPayload.CloseIssue());
			  user_should_call_the_using_http_method("CloseIssueAPI", "POST");
			  api_call_got_success_with_the_response_code(204);
			  attach_the_defect_log_file_to_the_closed_issue(m);
		}
	}

	@Then("Attach the defect log file to the closed issue")
	public void attach_the_defect_log_file_to_the_closed_issue(int k) throws IOException {

		   response = given().spec(Utilities.requestspecification()).pathParam("id", defectid.get(k))
					.header("X-Atlassian-Token", "no-check")
					.header("Content-Type", "multipart/form-data").multiPart(listfiles1[k]);
					user_should_call_the_using_http_method("AddAttachmentAPI", "POST");
			
					if(resp.getStatusCode()==200)
					{
						System.out.println("File attached success");
						listfiles1[k].renameTo(new File(closedlog+"Closed_"+listfiles1[k].getName()));
						System.out.println("File moved to Raised folder"); 
						}

		}
	
}


