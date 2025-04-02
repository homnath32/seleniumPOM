package testCases;
import pageObjects.signUpPage;
import testBase.baseClass;
import utilities.MailosaurUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(utilities.ExtentReport.class)
public class signUpTest extends baseClass {
    @Test
    public void testValidSignup() throws Exception {
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
    }

    @Test
    public void testInvalidEmailSignup() {
        signUpPage signupPage = new signUpPage(driver);
        signupPage.signupTab();
        signupPage.enterEmail("invalid-email");
        signupPage.enterPassword("password123");
        signupPage.clicksignupButton();

        // Verify error message
        WebElement errorMessage = driver.findElement(By.xpath("//label[normalize-space()='Please enter valid email address.']"));
        Assert.assertEquals(errorMessage.getText(), "Please enter valid email address.");
    }

}
