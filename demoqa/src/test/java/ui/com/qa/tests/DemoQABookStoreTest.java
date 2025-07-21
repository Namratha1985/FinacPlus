package ui.com.qa.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ui.com.qa.pages.HomePage;
import ui.com.qa.pages.LoginPage;
import ui.com.qa.pages.SearchResultPage;
import ui.com.qa.pages.UserHomePage;
import ui.com.qa.util.TestBase;
import ui.com.qa.util.TestUtil;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DemoQABookStoreTest extends TestBase{
	
	static Logger log = LogManager.getLogger(DemoQABookStoreTest.class);
	HomePage homepage;
	
	@BeforeMethod
	public void setup() {
		driver = launchBrowser();
		log.info("Browser launched");
		homepage = new HomePage();
	}

	@Test
	public void bookStoreSearchTest() {
		log.info("Test Started");
	    LoginPage loginpage = homepage.navigateToBookStoreApp();
	    UserHomePage userHomePage = loginpage.login();
	   
	    
	    // Assert Username and logout button are displayed
	    Assert.assertTrue((userHomePage.getlogoutBtn().isDisplayed()), "Logout button is not displayed - Login may have failed");
	    Assert.assertTrue((userHomePage.getUsernameDisplay().isDisplayed()), "Username is not displayed - Login may have failed");
	    Assert.assertEquals(TestUtil.USERNAME, userHomePage.getUsernameDisplay().getText(), "UserName is matching!");
		
	    SearchResultPage searchResultPage = userHomePage.search("Learning JavaScript Design Patterns");
	    
	    // Validate the search result
	    boolean bookFound = searchResultPage.searchResultsValidation();
	    
	    // Assert the book is found, else fail the test
	    Assert.assertTrue(bookFound, "Book 'Learning JavaScript Design Patterns' not found in search results.");
	    
	    // If found, proceed to get book details
	    searchResultPage.bookDetails();
	   
	    // Logout
	    userHomePage.logOut();
	    log.info("Logged out successfully.");
	    
	}
	
	@AfterMethod
	public void tearDown() {
		quitBrowser();
	}
    

}
