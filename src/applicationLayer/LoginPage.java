package applicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	//Define repository for login
	@FindBy (xpath = " //button[@id='btnreset']")
	WebElement ObjRest;
	@FindBy (id = "username")
	WebElement ObjUser;
	@FindBy (name = "password")
	WebElement Objpass;
	@FindBy(xpath = "//button[@id='btnsubmit']")
	WebElement ObjLogin;
	//write method to perform action
	public void adminLogin(String user,String pass) throws InterruptedException {
Thread.sleep(5000);
		ObjRest.click();
		ObjUser.sendKeys(user);
		Objpass.sendKeys(pass);
		ObjLogin.click();
	}



}
