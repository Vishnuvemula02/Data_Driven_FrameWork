package applicationLayer;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import freemarker.core.ReturnInstruction.Return;

public class SupplierPage {

	WebDriver driver;
	public SupplierPage(WebDriver driver)
	{
		this.driver = driver;

	}

	//define Repository for supplier Page
	@FindBy(xpath = "(//a[contains(text(),'Suppliers')])[2]")
	WebElement clickSupplier;
	@FindBy (xpath = "(//span[@data-caption='Add'])[1]")
	WebElement clickAddIcon;
	@FindBy (xpath = "//input[@id='x_Supplier_Number']")
	WebElement SupplierNumber;
	@FindBy (xpath = "//input[@id='x_Supplier_Name']")
	WebElement  SupplierName;
	@FindBy (xpath = "//textarea[@id='x_Address']")
	WebElement Address;
	@FindBy (xpath = "//input[@id='x_City']")
	WebElement City;
	@FindBy (xpath = "//input[@id='x_Country']")
	WebElement Country;
	@FindBy (xpath = "//input[@id='x_Contact_Person']")
	WebElement contactPerson;
	@FindBy (xpath = "//input[@id='x_Phone_Number']")
	WebElement PhoneNumber;
	@FindBy (xpath = "//input[@id='x__Email']")
	WebElement Emial;
	@FindBy (xpath = "//input[@id='x_Mobile_Number']")
	WebElement MobileNumber;
	@FindBy (xpath = "//textarea[@id='x_Notes']")
	WebElement Notes;
	@FindBy (xpath = "//button[@id='btnAction']")
	WebElement ClickAddBtn;

	@FindBy(xpath ="//button[normalize-space()='OK!']" )
	WebElement clickConfirmokBtn;

	@FindBy(xpath ="//button[@class='ajs-button btn btn-primary']" )
	WebElement clickAlertOKBtn;


	@FindBy (xpath ="//span[@class='glyphicon glyphicon-search ewIcon']" )
	WebElement clickSearchpanelBtn;

	@FindBy (xpath ="//input[@id='psearch']" )
	WebElement SearchTextbox;

	@FindBy (xpath ="//button[@id='btnsubmit']" )
	WebElement SearchBtn;

	@FindBy(xpath = "//span[@class='a_suppliers_Supplier_Number']/span")
	WebElement webtable;
	
	//method for supplier creation
	public boolean addSupplier(String SupplierName,String Addres,String City,String Country,
			String contactPerson,String phonenumber,String email,String Mobilenumber,String Notes) throws Throwable
	{
		Actions ac = new Actions(driver);
		ac.moveToElement(this.clickSupplier).click().perform();
		Thread.sleep(2000);
		ac.moveToElement(this.clickAddIcon).click().perform();
		Thread.sleep(2000);
		
		String Exp_Data = this.SupplierNumber.getAttribute("value");
		
		this.SupplierName.sendKeys(SupplierName);
		this.Address.sendKeys(Addres);
		this.City.sendKeys(City);
		this.Country.sendKeys(Country);
		this.contactPerson.sendKeys(contactPerson);
		this.PhoneNumber.sendKeys(phonenumber);
		this.Emial.sendKeys(email);
		this.MobileNumber.sendKeys(Mobilenumber);
		this.Notes.sendKeys(Notes);
		
		this.ClickAddBtn.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		this.clickConfirmokBtn.click();
		Thread.sleep(2000);
		this.clickAlertOKBtn.click();
		Thread.sleep(2000);
		//search Box Statement If Search Box is Present Don't click on search box
		if(!this.SearchTextbox.isDisplayed())
			this.clickSearchpanelBtn.click();
		this.SearchTextbox.clear();
		this.SearchTextbox.sendKeys(Exp_Data);
		Thread.sleep(3000);
		this.SearchBtn.click();
		String Act_Data = webtable.getText();
		if(Act_Data.equals(Exp_Data))
		{

			Reporter.log("ADD SUPPLIER IS SUCCESS::: "+Exp_Data+"       "+Act_Data ,true);
			return true;

		}else
		{
			Reporter.log("ADD SUPPLIER IS FAIL::: "+Exp_Data+"       "+Act_Data ,true);
			return true;

		}



	}


}
