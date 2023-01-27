package olyamba;
import javax.mail.*;
import java.util.Properties;

public class MailConfig {
    public final String MY_EMAIL = "";
    private final String MY_PASSWORD = "";

    private Session session;

    void init() throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.yandex.ru");
        props.put("mail.smtp.user", MY_EMAIL);
        props.put("mail.smtp.password", MY_PASSWORD);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.auth", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.test-connection", "true");
        props.put("mail.debug", "true");

        this.session = Session.getDefaultInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(MY_EMAIL, MY_PASSWORD);
                    }
                });
    }

    public Session getSession() {
        return session;
    }
}