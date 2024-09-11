package driverfactory;

import java.io.File;

import javax.security.auth.login.LoginContext;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil {

	ExtentReports reports;
	ExtentTest logger;
	String inputPath = "./FileInput/TestData.xlsx";
	String outPutPath = "./FileOutput/DataDrivenResults.xlsx";
	@Test
	public void StartTest() throws Throwable
	{
		//define path of html report
		reports = new ExtentReports("./Reports/Login.html");
		//create reference object for excel
		ExcelFileUtil xl = new ExcelFileUtil(inputPath);
		//count no of rows in login sheet
		int rc = xl.rowCount("Login");
		Reporter.log("NO of Rowsare::"+rc,true);;
		//iterate all rows in login sheet
		for(int i=1; i<+rc; i++)
		{
			logger = reports.startTest("Login Test");
			logger.assignAuthor("vishnu");
			//read user name and password cells
			String Username = xl.getCellData("Login", i, 0);
			String Password = xl.getCellData("Login", i, 0);
			//cell login method and assing parameters
			boolean res = FunctionLibrary.adminelogine(Username, Password);
					if(res)
					{
						//if res is true write as login success into results cell
						xl.setcellData("Login", i, 2, "Login success", outPutPath);
						//if res is true write as pass into status cell
						xl.setcellData("Login", i, 3, "Pass", outPutPath);
						logger.log(LogStatus.PASS, "Valid Username and password");
					}
					else {
						
						//adding screen shot for fail test
						File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						//copy screen short into local system
						FileUtils.copyFile(screen, new File("./Screenshot/Iteration/"+i+"Login.png"));
						//if res is false write as login Fail into results cell
						xl.setcellData("Login", i, 2, "Login Fail", outPutPath);
						//if res is false write as Fail into status cell
						xl.setcellData("Login", i, 3, "Fail", outPutPath);
						logger.log(LogStatus.FAIL, "Invalid username and password");
					}
		}
		reports.endTest(logger);
		reports.flush();
	}
}