package resources;

public enum EnumResources {

	LoginAPI("/rest/auth/1/session"),
	CreateIssueAPI("/rest/api/2/issue/"),
	AddAttachmentAPI("/rest/api/2/issue/{id}/attachments"),
	CloseIssueAPI("/rest/api/2/issue/{id}/transitions");

	private String resource;
	
	EnumResources(String resource) {
		// TODO Auto-generated constructor stub
		
		this.resource=resource;
	}
	
	public String getResource()

	{
		return resource;
	}
	
}
