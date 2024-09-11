package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Driver;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {

	public static WebDriver driver ;
	public static Properties conpro;
	
	
	@BeforeTest
	public static void setup() throws Throwable{
	conpro   = new Properties();
	
	//load property file
     conpro.load(new FileInputStream("D:\\Live_project12_08_2024Qedgetech\\Data_Driven_FrameWork\\PropertyFile\\Environement.properties"));
	if (conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
       driver = new ChromeDriver();
       driver.manage().window().maximize();
       
	}	
		
	else if( conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
	{
		
		driver = new FirefoxDriver();
	}
	else 
	{
		Reporter.log("Browswe value is not Matching",true);
	}
	}
	
	@AfterTest
	public static void teardown() {
		
		driver.quit();
		
		
		
	}
}
