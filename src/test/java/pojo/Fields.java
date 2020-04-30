package pojo;


public class Fields {

	private Project project;
	private IssueType issuetype;
	private String description;
	private String summary;
	private String environment;
	private Priority priority;
	private Assignee assignee;
	private Reporter reporter;
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public IssueType getIssuetype() {
		return issuetype;
	}
	public void setIssuetype(IssueType issuetype) {
		this.issuetype = issuetype;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority prio) {
		this.priority = prio;
	}
	public Assignee getAssignee() {
		return assignee;
	}
	public void setAssignee(Assignee assign) {
		this.assignee = assign;
	}
	public Reporter getReporter() {
		return reporter;
	}
	public void setReporter(Reporter string) {
		this.reporter = string;
	}



}
