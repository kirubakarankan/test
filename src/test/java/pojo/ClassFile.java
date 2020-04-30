package pojo;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class ClassFile {
	
    protected static int i;
	int j;
    List<String> result1 = new ArrayList<>();
    List<String> result2 = new ArrayList<>();
    List<String> result3 = new ArrayList<>();
    List<String> result4 = new ArrayList<>();
    List<String> result5 = new ArrayList<>();
    List<String> result6 = new ArrayList<>();
    List<String> result7 = new ArrayList<>();
    List<String> result8 = new ArrayList<>();
    static File defectlogs = new File("C:\\Kiruba\\Test Folder\\Defect Logs");
    static File closurelogs = new File("C:\\Kiruba\\Test Folder\\Closure Logs");
    static FileFilter filter = new FileFilter() { 
        public boolean accept(File f) 
        { 
            return f.getName().endsWith("docx"); 
        } 
    };
    protected static File[] listfiles = defectlogs.listFiles(filter);
    protected static File[] listfiles1 = closurelogs.listFiles(filter);
    
    public List<String> GetDefectID()
    {
    	for(int i=0; i<listfiles1.length;i++) {
    	
    		if(listfiles1[i].isFile())
    		{
    			String[] filename = listfiles1[i].getName().split("_");
    				result7.add(filename[0]);
    		}
    	}
    	return result7;
    }
    
	public List<String> ReadSummary() throws IOException
    {
		for (i=0; i<listfiles.length; i++) {
            
            if (listfiles[i].isFile()) {                
                FileInputStream fis = new FileInputStream(listfiles[i]);
                XWPFDocument docx = new XWPFDocument(fis);
                
                List<XWPFParagraph> paragraphlist = docx.getParagraphs();
              for(XWPFParagraph paragraph : paragraphlist) 
              { 
                  String[] summary  = paragraph.getText().split("Summary: "); 
                  for(j=1;j<summary.length;j++)
                  {
                  result1.add(summary[j]);
                  }          
              }
            }           
        }
        return result1;
    }
    
    public List<String> ReadDescription() throws IOException
    {
    	for (i=0; i<listfiles.length; i++) {
            
            if (listfiles[i].isFile()) {                
                FileInputStream fis = new FileInputStream(listfiles[i]);
                XWPFDocument docx = new XWPFDocument(fis);
                List<XWPFParagraph> paragraphlist = docx.getParagraphs();
              for(XWPFParagraph paragraph : paragraphlist) 
              { 
                  String[] description  = paragraph.getText().split("Description: "); 
                  for(int j=1;j<description.length;j++)
                  {
                  result2.add(description[j]);
                  }          
              }
            }           
        }
        return result2;
    }
    
    public List<String> ReadEnvironment() throws IOException
    {
    	for (i=0; i<listfiles.length; i++) {
            
            if (listfiles[i].isFile()) {                
                FileInputStream fis = new FileInputStream(listfiles[i]);
                XWPFDocument docx = new XWPFDocument(fis);
                List<XWPFParagraph> paragraphlist = docx.getParagraphs();
              for(XWPFParagraph paragraph : paragraphlist) 
              { 
                  String[] environment  = paragraph.getText().split("Environment: "); 
                  for(int j=1;j<environment.length;j++)
                  {
                  result3.add(environment[j]);
                  }          
              }
            }           
        }
        return result3;
    }
    
    public List<String> ReadPriority() throws IOException
    {
    	for (i=0; i<listfiles.length; i++) {
            
            if (listfiles[i].isFile()) {                
                FileInputStream fis = new FileInputStream(listfiles[i]);
                XWPFDocument docx = new XWPFDocument(fis);
                List<XWPFParagraph> paragraphlist = docx.getParagraphs();
              for(XWPFParagraph paragraph : paragraphlist) 
              { 
                  String[] priority  = paragraph.getText().split("Priority: "); 
                  for(int j=1;j<priority.length;j++)
                  {
                  result4.add(priority[j]);
                  }          
              }
            }           
        }
        return result4;
    }
    
    public List<String> ReadAssignee() throws IOException
    {
    	for (i=0; i<listfiles.length; i++) {
            
            if (listfiles[i].isFile()) {                
                FileInputStream fis = new FileInputStream(listfiles[i]);
                XWPFDocument docx = new XWPFDocument(fis);
                List<XWPFParagraph> paragraphlist = docx.getParagraphs();
              for(XWPFParagraph paragraph : paragraphlist) 
              { 
                  String[] assignee  = paragraph.getText().split("Assignee: "); 
                  for(int j=1;j<assignee.length;j++)
                  {
                  result5.add(assignee[j]);
                  }          
              }
            }           
        }
        return result5;
    }
    
    public List<String> ReadReporter() throws IOException
    {
    	for (i=0; i<listfiles.length; i++) {
            
            if (listfiles[i].isFile()) {                
                FileInputStream fis = new FileInputStream(listfiles[i]);
                XWPFDocument docx = new XWPFDocument(fis);
                List<XWPFParagraph> paragraphlist = docx.getParagraphs();
              for(XWPFParagraph paragraph : paragraphlist) 
              { 
                  String[] reporter  = paragraph.getText().split("Reporter: "); 
                  for(int j=1;j<reporter.length;j++)
                  {
                  result6.add(reporter[j]);
                  }          
              }
            }           
        }
        return result6;
    }
    
    public List<String> ReadProject() throws IOException
    {
    	for (i=0; i<listfiles.length; i++) {
            
            if (listfiles[i].isFile()) {                
                FileInputStream fis = new FileInputStream(listfiles[i]);
                XWPFDocument docx = new XWPFDocument(fis);
                List<XWPFParagraph> paragraphlist = docx.getParagraphs();
              for(XWPFParagraph paragraph : paragraphlist) 
              { 
                  String[] project  = paragraph.getText().split("Project: "); 
                  for(int j=1;j<project.length;j++)
                  {
                  result8.add(project[j]);
                  }          
              }
            }           
        }
        return result8;
    }
}


