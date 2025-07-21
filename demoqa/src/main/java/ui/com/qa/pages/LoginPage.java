package ui.com.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.com.qa.util.TestBase;
import ui.com.qa.util.TestUtil;

public class LoginPage extends TestBase{
	
	@FindBy(id = "userName")
	WebElement userName;
	
	@FindBy(id = "password")
	WebElement password;
	
	@FindBy(id = "login")
	WebElement login;
	
	//Initializing page objects
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public UserHomePage login() {
		userName.sendKeys(TestUtil.USERNAME);
		password.sendKeys(TestUtil.PASSWORD);
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.PAGE_DOWN).build().perform();
		waitForPageToLoad(driver);
		login.click();
		waitForPageToLoad(driver);
		return new UserHomePage();	
	}
	
}
