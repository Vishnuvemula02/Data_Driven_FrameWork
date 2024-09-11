package config;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import applicationLayer.LoginPage;
import applicationLayer.LogoutPage;

public class AppUtilPOM  {

	public static WebDriver driver;
	public static Properties conpro;
	@BeforeTest
	public static void setUp() throws Throwable {

		conpro = new Properties() ;
		//loading properties File 
		conpro.load(new FileInputStream("./PropertyFile/Enironementpom.properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome")) 
		{

			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(conpro.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			LoginPage login = PageFactory.initElements(driver, LoginPage.class);
			//call login method
			login.adminLogin("admin", "master");	

		}
		else if (conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
			driver.get(conpro.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			LoginPage login = PageFactory.initElements(driver, LoginPage.class);
			//call login method
			login.adminLogin("admin", "master");		
		}

	}

	@AfterTest
	public static  void tearDown()
	{
		LogoutPage logut = PageFactory.initElements(driver, LogoutPage.class);
		logut.adminLogout();
		driver.quit();

	}


}
