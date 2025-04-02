package pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class signUpPage extends basePage {
	
	@FindBy(xpath = "//div[@class='flex items-center justify-between mb-40']//div[1]")
	private WebElement signupTab;

    @FindBy(xpath = "//input[@placeholder='Enter email or mobile number']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@placeholder='*********']")
    private WebElement passwordField;

//    @FindBy(id = "captcha")
//    private WebElement captcha;

    @FindBy(xpath = "//button[normalize-space()='Create Account']")
	public WebElement signupButton;

    @FindBy(css = "input[aria-label='PinInput']")
    private List<WebElement> otpField;

    @FindBy(xpath = "//button[normalize-space()='Verify OTP']")
    private WebElement verifyOtpButton;
    
    @FindBy(xpath = "//h2[normalize-space()='Awesome!']")
    private WebElement successMsg;
    
    @FindBy(xpath = "//button[normalize-space()='Go to Login']")
    private WebElement gobackLogin;

    public signUpPage(WebDriver driver) {
        super(driver);
    }
    
    public void signupTab() {
    	signupTab.click();
    }
    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

//    public void clickCaptcha() {
//        captcha.click();
//    }

    public void clicksignupButton() {
    	signupButton.click();
    }

    public void enterOtp(String otp) {
        if (otpField.size() == otp.length()) {
            for (int i = 0; i < otpField.size(); i++) {
                otpField.get(i).sendKeys(String.valueOf(otp.charAt(i)));
            }
        } else {
            throw new IllegalArgumentException("OTP length does not match the number of fields.");
        }
    }

    public void clickverifyOtpButton() {
        verifyOtpButton.click();
    }
    
    public String successMsg() {
    	try {
    		return(successMsg.getText());
    	}catch (Exception e) {
    		return (e.getMessage());
    	}
    }
  public void clickGotoLogin() {
	  gobackLogin.click();
  }
}

