package applicationLayer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage {
	//This is the Repository
	@FindBy (xpath ="(//a[@href='logout.php'])[2]")
	WebElement logoutclick;
	
	//This is the Method
	public void adminLogout() {
		
		logoutclick.click();
	}
	
	
}
