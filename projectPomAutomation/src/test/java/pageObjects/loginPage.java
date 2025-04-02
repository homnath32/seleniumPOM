package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginPage extends basePage {

    @FindBy(xpath = "//input[@placeholder='Enter email or mobile number']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@placeholder='*********']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[normalize-space()='Forgot Password?']")
    private WebElement forgotPasswordButton;
    
    @FindBy(xpath = "//button[normalize-space()='Submit']")
    private WebElement submitButton;
    
    @FindBy(xpath = "//div[@id='mantine-t4x9duqss']")
    private WebElement otpField;

    @FindBy(xpath = "//button[normalize-space()='Verify OTP']")
    private WebElement verifyOtpButton;
    
    @FindBy(xpath = "//input[@placeholder='Enter password']")
    private WebElement resetPassword;
    
    @FindBy(xpath = "//h2[normalize-space()='Awesome!']")
    private WebElement successMsg;
    
//    @FindBy(xpath = "//button[normalize-space()='Go to Login']")
//    private WebElement goBackButton;

    public loginPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickloginButton() {
        loginButton.click();
    }

    public void clickForgotPasswordButton() {
        forgotPasswordButton.click();
    }
    
    public void submitButton() {
       submitButton.click();
    }
    
    public void otpField(String otp) {
        otpField.sendKeys(otp);
    }
    
    public void clickverifyOtpButton() {
    	verifyOtpButton.click();
    }
    
    public void resetPassword(String password) {
    	resetPassword.sendKeys(password);
    }
    
    public String successMsg() {
    	try {
    		return(successMsg.getText());
    	}catch (Exception e) {
    		return (e.getMessage());
    	}
}
}

