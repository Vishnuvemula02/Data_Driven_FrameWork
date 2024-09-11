package driverfactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import applicationLayer.SupplierPage;
import config.AppUtilPOM;
import utilities.ExcelFileUtil;

public class TestScript  extends AppUtilPOM
{

	String inputpath = "./FileInput\\supplierData.xlsx"	;
	String outputpath = "./FileOutput/supplierresults.xlsx";
	ExtentReports reports ;
	ExtentTest logger;
	String Tcsheet ="SupplierPag";

	@Test
	public void startTest() throws Throwable
	{
		reports = new ExtentReports("./ExtentReports/Supplier.html");
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		int rc = xl.rowCount(Tcsheet);
		Reporter.log("NO of  Rows are:::"+rc,true);
		for(int i=1;i<=rc;i++)
		{
			logger = reports.startTest("Validate Supplier Test");
			String sname = xl.getCellData(Tcsheet, i, 0);
			String address = xl.getCellData(Tcsheet, i, 1);
			String city = xl.getCellData(Tcsheet, i, 2);
			String country = xl.getCellData(Tcsheet, i, 3);
	    	String cperson = xl.getCellData(Tcsheet, i, 4);
			String pnumber = xl.getCellData(Tcsheet, i, 5);
			String email  = xl.getCellData(Tcsheet, i, 6);
			String mnumber = xl.getCellData(Tcsheet, i, 7);
			String notes = xl.getCellData(Tcsheet, i, 8);

			logger.log(LogStatus.INFO, sname+"    "+address+"   "+city+"    "+country+"    "+cperson+"   "+pnumber+"    "+email+"   "+mnumber+"   "+notes+"    ");
			SupplierPage sup = PageFactory.initElements(driver, SupplierPage.class);
			
			boolean res = sup.addSupplier(sname, address, city, country, cperson, pnumber, email, mnumber, notes);
			if(res)
			{
				xl.setcellData(Tcsheet, i, 9, "pass", outputpath);
				logger.log(LogStatus.PASS, "Add Supplier Success");

			}else 
			{
				xl.setcellData(Tcsheet, i, 9, "Fail", outputpath);
				logger.log(LogStatus.FAIL, "Add Supplier Fail");
			}
			
			reports.endTest(logger);
			reports.flush();

		}

	}

}
