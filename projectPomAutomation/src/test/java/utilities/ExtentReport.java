package utilities;

import java.util.Date;
import java.io.IOException;
import java.text.SimpleDateFormat;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import testBase.baseClass;

public class ExtentReport implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\" + repName);
		sparkReporter.config().setDocumentTitle("Automation Testing Report");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Computer Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester Name", "Homnath");
		extent.setSystemInfo("OS", "Windows11");
		extent.setSystemInfo("Browser name", "Chrome");
		
	}
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test Case PASSED is:" + result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Case FAILED is:" + result.getName());
		test.log(Status.FAIL, "Test case FAILED cause is: "+ result.getThrowable());
		
		try {
			String imgPath = new baseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
	}
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Case SKIPPED is:" + result.getName());
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
		
	}

	}

