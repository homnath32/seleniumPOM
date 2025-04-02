package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;


public class baseClass {
	public static WebDriver driver;
	public WebDriverWait wait;
	public JavascriptExecutor js = (JavascriptExecutor) driver;

	
	@BeforeMethod
	public void setup()
	{
	
		driver=new ChromeDriver();
		driver.get("your app url");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
	}

//	@AfterMethod
//	public void tearDown()
//	{
//		driver.quit();
//	}
	
	public String captureScreen(String tname) throws IOException {
	    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	    
	    TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
	    File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
	    
	    String targetFilePath = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timeStamp + ".png";
	    File targetFile = new File(targetFilePath);
	    
	    FileUtils.copyFile(sourceFile, targetFile);
	    return targetFilePath;
	}
}
