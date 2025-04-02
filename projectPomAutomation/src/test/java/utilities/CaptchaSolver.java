package utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CaptchaSolver {
    private static final String API_KEY = "0b0c2a40d15f41160b0f889f1305403b";

    public String solveCaptcha(String siteKey, String pageUrl) {
        try {
            // Step 1: Submit CAPTCHA solving request          	
            String requestUrl = "http://2captcha.com/in.php?key=" + API_KEY +
                                "&method=userrecaptcha&googlekey=" + "6LdKSvEpAAAAAKQdTuJSk3WMGjMopkkitDSjES--" +
                                "&pageurl=" + "https://app.quickconnect.biz/sign-up";
            HttpURLConnection conn = (HttpURLConnection) new URL(requestUrl).openConnection();
            conn.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = reader.readLine();
            reader.close();

            if (response.startsWith("OK|")) {
                String captchaId = response.split("\\|")[1];

                // Step 2: Wait for solution
                Thread.sleep(15000); // Wait for 15 seconds (adjust as needed)

                // Step 3: Fetch the solution
                String solutionUrl = "http://2captcha.com/res.php?key=" + API_KEY + "&action=get&id=" + captchaId;
                conn = (HttpURLConnection) new URL(solutionUrl).openConnection();
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String solutionResponse = reader.readLine();
                reader.close();

                if (solutionResponse.startsWith("OK|")) {
                    return solutionResponse.split("\\|")[1]; // CAPTCHA solution
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
//solve Captcha
//CaptchaSolver captchaSolver = new CaptchaSolver();
//String siteKey = "site-key-from-page-source";
//String pageUrl = driver.getCurrentUrl();
//String captchaSolution = captchaSolver.solveCaptcha(siteKey, pageUrl);
//if (captchaSolution != null) {
//  // Inject the CAPTCHA solution
//  WebElement captchaResponseField = driver.findElement(By.id("g-recaptcha-response"));
//  js.executeScript("arguments[0].value='" + captchaSolution + "';", captchaResponseField);
//
//  // Trigger CAPTCHA validation
//  js.executeScript("___grecaptcha_cfg.clients[0].L.L.callback('" + captchaSolution + "');");
//} else {
//  System.err.println("CAPTCHA solving failed!");
//  Assert.fail("CAPTCHA solving failed");
//}

