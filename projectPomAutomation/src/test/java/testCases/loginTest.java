package testCases;

import pageObjects.loginPage;
import testBase.baseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(utilities.ExtentReport.class)
public class loginTest extends baseClass {

	@Test
    public void testValidlogin() {
        loginPage loginPage = new loginPage(driver);

        loginPage.enterEmail("youremail.com");
        loginPage.enterPassword("12345678");
        loginPage.clickloginButton(); 
        wait.until(ExpectedConditions.urlToBe("expected url"));
        
//         Verify login success
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "url");
    }

    @Test
    public void testInvalidlogin() {
        loginPage loginPage = new loginPage(driver);

        loginPage.enterEmail("test@example.com");
        loginPage.enterPassword("1234567");
        loginPage.clickloginButton();
        // Verify error message
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".pl-2.line-height-normal.word-break")));
        Assert.assertEquals(errorMessage.getText(), "User not found");
    }
}