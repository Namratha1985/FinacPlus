package ui.com.qa.pages;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.com.qa.util.TestBase; 

public class SearchResultPage extends TestBase{
	
	@FindBy(xpath = "//div[@class='rt-tr-group']")
	List<WebElement> searchTableRows;
	
	@FindBy(className = "rt-td")
	List<WebElement> searchTableCells;
	
	private Map<String, String> bookDetails = new HashMap<>();
	
	//Initializing page objects
	public SearchResultPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean searchResultsValidation() {
		boolean bookFound = false;
        
        for (WebElement row : searchTableRows) {
            String rowText = row.getText();
            if (rowText.contains("Learning JavaScript Design Patterns")) {
                bookFound = true;     
                bookDetails.put("Title", searchTableCells.get(1).getText());
                bookDetails.put("Author", searchTableCells.get(2).getText());
                bookDetails.put("Publisher", searchTableCells.get(3).getText());           
                break;
            }
        }
        return bookFound;
    }
	
	public void bookDetails() {
         BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter("BookDetails.txt"));
			for (Map.Entry<String, String> entry : bookDetails.entrySet()) {
                writer.write(entry.getKey() + " : " + entry.getValue());
                writer.newLine(); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
        
    }

}
