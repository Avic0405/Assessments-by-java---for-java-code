import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class GmailComposeAutomation {
    public static void main(String[] args) {
        // Sender's Gmail account credentials
        final String username = "email";
        final String password = "password";
        // email address of whos u want to share
        String toEmail = "email";
        // Sender's email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        // Create a session with authentication
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);
            // Set From: header field
            message.setFrom(new InternetAddress(username));
            // Set To: header field
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            // Set Subject: header field
            message.setSubject("Testing Gmail Automation");
            // Set the actual message
            message.setText("Hello, this is a test email sent through JavaMail API.");
            // Send the message
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}