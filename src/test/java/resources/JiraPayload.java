package resources;

import java.io.IOException;
import java.util.List;
import pojo.Assignee;
import pojo.ClassFile;
import pojo.CreateIssue;
import pojo.Fields;
import pojo.IssueType;
import pojo.Priority;
import pojo.Project;
import pojo.Reporter;

public class JiraPayload extends ClassFile {

	public static String Login() {
		return "{ \r\n" + "	\"username\": \"admin\", \r\n" + "	\"password\": \"admin\"\r\n" + "}";
	}

	public static pojo.CreateIssue CreateIssue() throws IOException {
			
				CreateIssue ci = new CreateIssue();
		
				//Creating object for IssueType Class
				IssueType it = new IssueType();
				it.setName("Bug");
				
				//Creating object for Project class
				Project proj = new Project();
				proj.setKey("RP");
	
				//Creating object for ClassFile Class
				ClassFile cf = new ClassFile();
				List<String> summary = cf.ReadSummary();
				List<String> description = cf.ReadDescription();
				List<String> environment = cf.ReadEnvironment();
				List<String> prio = cf.ReadPriority();
				List<String> assign = cf.ReadAssignee();
				List<String> report = cf.ReadReporter();
						
				for(int i=0; i<summary.size();i++) {
					
					Priority priority = new Priority();
					priority.setName(prio.get(i));
					
					Assignee assignee = new Assignee();
					assignee.setName(assign.get(i));
					
					Reporter reporter = new Reporter();
					reporter.setName(report.get(i));
					
					Fields fi = new Fields();
					fi.setProject(proj);
					fi.setSummary(summary.get(i));
					fi.setDescription(description.get(i));
					fi.setIssuetype(it);
					fi.setEnvironment(environment.get(i));
					fi.setAssignee(assignee);
					fi.setPriority(priority);
					fi.setReporter(reporter);
					
					ci.setFields(fi);
					return ci;
					
				}
				return ci;
			
	}
	
	public static String CloseIssue()
	{
		return "{    \"transition\": {\r\n" + 
				"        \"id\": \"41\"\r\n" + 
				"    }\r\n" + 
				"}";
	}

}
