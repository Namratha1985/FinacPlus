package ui.com.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.com.qa.util.TestBase;
import ui.com.qa.util.TestUtil;

public class UserHomePage extends TestBase{
	private WebDriver  driver;
	@FindBy(id = "userName-value")
	WebElement userNameDisplay;
	
	@FindBy(xpath = "//button[text()='Log out']")
	WebElement logoutBtn;
	
	@FindBy(xpath = "//span[text()='Book Store']")
	WebElement bookStoreBtn;
	
	@FindBy(id = "searchBox")
	WebElement searchBox;
	
	//Initializing page objects
	public UserHomePage(WebDriver  driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public SearchResultPage search(String bookName){
		
		pageDownAction();
		waitForElementToBeClickable(bookStoreBtn);
		bookStoreBtn.click();
		waitForPageToLoad(driver);
		searchBox.sendKeys(bookName);
		waitForPageToLoad(driver);
		return new SearchResultPage(driver);
	}

    // Method to return userNameDisplay
    public WebElement getUsernameDisplay() {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
        return userNameDisplay;
    }
    	
    // Method to return Logout Button
    public WebElement getlogoutBtn() {
    	waitForElementToBeClickable(logoutBtn);
        return logoutBtn;
    }
    
    // Method to return Logout Button
    public void logOut() {
        logoutBtn.click();
    }
}
