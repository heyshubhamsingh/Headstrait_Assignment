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


public class Headstrait_Project {

	static String excelFileReader(String fileContent, String excelFilePath) {
		try {
			FileInputStream fis=new FileInputStream(new File(excelFilePath));   // Opening excel file.
			XSSFWorkbook wb = new XSSFWorkbook(fis);     // Opening the workbook.
			XSSFSheet s = wb.getSheetAt(0);            // Opening the 0th Sheet(first).
			Iterator<Row> rowItr = s.iterator();      // Made a iterator to iterate the Rows.
			Row r1 = rowItr.next();        //  Row is a class in java that keeps track of a row's values.
			
				while (rowItr.hasNext())                 
				{  
					r1=rowItr.next();              // Skipped the first line to reach the word we want to replace
					Iterator<Cell> cellItr = r1.cellIterator();     // Made a iterator to iterate through cell.
					
					String origional=" ",replace_with=" ";
					
						while (cellItr.hasNext()) 
						{    
							Cell c1 = cellItr.next();     // it will point to the cell of the row.
							if(origional.equals(" ")) {
								origional=" "+c1.getStringCellValue()+" ";
								continue;
							}
							replace_with=" "+c1.getStringCellValue()+" ";
								fileContent=fileContent.replaceAll(origional, replace_with);
						}
				
				}
			}
			catch(Exception e) {
				System.out.println(e+" Exception Occured");
			}
		return fileContent;
	}
	
	static String fileReader(String filePath) {
		BufferedReader r1=null;       // BufferedReader class is used to read the text from a character-based input stream.
		String fileContent="";
		try {
			r1 = new BufferedReader(new FileReader(filePath));
			String line = r1.readLine();       // It is used for reading a line of text.
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
	
	
	static void createNewFile(String replacedFileContent, String fileName) {
		String ProcessedFilePath = "C:\\Users\\Shubham Singh\\Desktop\\processed\\"+fileName;
        File f1=new File(ProcessedFilePath);       // Creating new file using File Class
        FileWriter fw = null;
        try {
        	fw = new FileWriter(f1);
        	Files.writeString(Path.of(ProcessedFilePath), replacedFileContent);
        }
        catch(Exception e) {
        	System.out.println(e+" Exception Occured");
        }
	}
	
	
	public static void main(String[] args) {
		try {
			   File unprocessedFolder = new File("C:\\Users\\Shubham Singh\\Desktop\\unprocessed");
		       String fileName[] = unprocessedFolder.list();    // this method provides array of file names in the given directory. 
		       String excelFilePath = "C:\\Users\\Shubham Singh\\Desktop\\Word Substitutions.xlsx";
			      
		       for(int i=0; i<fileName.length; i++) {
			    	  String filePath = "C:\\Users\\Shubham Singh\\Desktop\\unprocessed\\";
			    	  filePath = filePath+fileName[i];         // Here we are concatenating file name with path of unprocessed Folder so that we can get full path of each files.
			    	  
			    	  String fileContent = fileReader(filePath);
			    	  String replacedFileContent = excelFileReader(fileContent, excelFilePath);
			    	  createNewFile(replacedFileContent, fileName[i]);
			   }
		       System.out.println("Thanks for being patience, now you can check your file.");
			}
			   catch(Exception e) {
			   System.out.println(e+" Exception Occured");
			}


	}
}
