package testCases;
import pageObjects.loginPage;
import pageObjects.signUpPage;
import testBase.baseClass;
import utilities.MailosaurUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(utilities.ExtentReport.class)
public class signUpandLoginFlow extends baseClass {
    @Test(priority = 1)
    public void signupandLogin() throws Exception {
        signUpPage signupPage = new signUpPage(driver);
        String emailId = MailosaurUtils.getRandomEmail();
        System.out.println("Generated Email ID: " + emailId);
        signupPage.signupTab();
        driver.navigate().refresh();
        signupPage.enterEmail(emailId);
        signupPage.enterPassword("Password@123");        
        wait.until(ExpectedConditions.elementToBeClickable(signupPage.signupButton)); 
        signupPage.clicksignupButton();
        // Fetch the OTP using Mailosaur
        String otp = MailosaurUtils.fetchOtp(emailId);
        System.out.println("Fetched OTP:" + otp);
        signupPage.enterOtp(otp);
        signupPage.clickverifyOtpButton();       
        // Verify signup success
        String successMsg = signupPage.successMsg();
        Assert.assertEquals(successMsg, "Awesome!");
        signupPage.clickGotoLogin();   
        
        //Login with the same email
        loginPage loginPage = new loginPage(driver);
    	loginPage.enterEmail(emailId);
    	loginPage.enterPassword("Password@123");
    	loginPage.clickloginButton(); 
		wait.until(ExpectedConditions.urlToBe("https://app.quickconnect.biz/dashboard"));
  
//  	 Verify login success
    	String currentUrl = driver.getCurrentUrl();
    	Assert.assertEquals(currentUrl, "https://app.quickconnect.biz/dashboard");
    }
	}