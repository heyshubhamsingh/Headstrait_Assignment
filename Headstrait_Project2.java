import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;  
import java.io.FileInputStream;  
import java.util.Iterator;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 

public class Headstrait_Project2 {
	static String fileReader(String processedFile) {
		BufferedReader r1=null;
		String fileContent="";
		try {
			r1=new BufferedReader(new FileReader(processedFile));
			String line = r1.readLine();
            while (line != null) 
            {
            	fileContent = fileContent + line + System.lineSeparator();
                line = r1.readLine();
            }
		}
		catch(Exception e) {
			System.out.println(e+" Exception Occured");
		}
		return fileContent;
	}
	
	
	static String excelFileReader(String fileContent, String excelFilePath) {
		try {
			FileInputStream fis=new FileInputStream(new File(excelFilePath));
			XSSFWorkbook wb = new XSSFWorkbook(fis);   
			XSSFSheet s = wb.getSheetAt(0); 
			Iterator<Row> rowItr = s.iterator();
			Row row = rowItr.next();
				while (rowItr.hasNext())                 
				{  
				row = rowItr.next();
				Iterator<Cell> cellItr = row.cellIterator();  
				String origional=" ",replace_with =" ";
					while (cellItr.hasNext())   
					{    
						Cell c1 = cellItr.next();
						if(replace_with.equals(" ")) {
							replace_with=" "+c1.getStringCellValue()+" ";
							continue;
						}
						origional=" "+c1.getStringCellValue()+" ";
							fileContent = fileContent.replaceAll(origional, replace_with);
					}
				
				}
			}
		
			catch(Exception e){
				System.out.println(e+" Exception Occured");
			}
		return fileContent;
	}
	
	
	public static void main(String[] args) {
		try {
			  File processedFolder = new File("C:\\Users\\Shubham Singh\\Desktop\\processed");
			  File unprocessedFolder = new File("C:\\Users\\Shubham Singh\\Desktop\\unprocessed");
		      String processedFileName[] = processedFolder.list();
		      String unprocessedFileName[]=unprocessedFolder.list();
		      String excelFilePath="C:\\Users\\Shubham Singh\\Desktop\\Word Substitutions.xlsx";
			      for(int i=0; i<processedFileName.length; i++) {
			    	  String processedFile="C:\\Users\\Shubham Singh\\Desktop\\processed\\";
			    	  processedFile=processedFile+processedFileName[i];
			    	  String unprocessedFile="C:\\Users\\Shubham Singh\\Desktop\\unprocessed\\";
			    	  unprocessedFile=unprocessedFile+processedFileName[i];
			    	  String fileContent = fileReader(processedFile);
			    	  String replacedFileContent = excelFileReader(fileContent,excelFilePath);
			    	  String oldFileContent = fileReader(unprocessedFile);
			    	  if(replacedFileContent.equals(oldFileContent)) {
			    		  System.out.println("Both are same");
			    	  }
			    	  else
			    		  System.out.println("Both are not same");
			      }
		      System.out.println("Processing Done...Thank you.");
			}
		
			catch(Exception e) {
				System.out.println(e+" Exception Occured");
			}

	}
}