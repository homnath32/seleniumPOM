package utilities;
import java.io.IOException;
import java.util.regex.Matcher;
import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.*;
import java.util.regex.Pattern;

public class MailosaurUtils {
	private static final String apiKey = "your api key";
	private static final String serverId = "serverId";
	private static final String serverDomain = "serverdomain.mailosaur.io";
	private static final String from = "emailsender.com.np";   
	
    public static String getRandomEmail() {
    	return "user" + System.currentTimeMillis() + "@" + serverDomain;
    }    
    
    public static String  fetchOtp(String emailId) throws IOException, MailosaurException, InterruptedException {
//        String emailId = getRandomEmail();
            // Initialize Mailosaur client
            MailosaurClient mailosaur = new MailosaurClient(apiKey);
            MessageSearchParams params = new MessageSearchParams();
            params.withServer(serverId);
            SearchCriteria criteria = new SearchCriteria();
			criteria.withSentTo(emailId);
			criteria.withSentFrom(from);
            Message message = mailosaur.messages().get(params, criteria);
            String htmlBody = message.html().body();
            System.out.println("HTML Body: \n" + htmlBody);

            // Extract OTP from the HTML body
            Pattern pattern = Pattern.compile("Your OTP Code:\\s*([0-9]{6})");
            Matcher matcher = pattern.matcher(htmlBody);
            if (matcher.find()) {
                return matcher.group(1); // Return the OTP
            } else {
                throw new IllegalStateException("OTP not found in the email body");
            }
            }
    		}




