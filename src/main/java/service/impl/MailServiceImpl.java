package service.impl;

import model.Order;
import org.apache.log4j.Logger;
import service.MailService;
import utils.RandomHelper;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailServiceImpl implements MailService {

    private static final Logger logger = Logger.getLogger(MailServiceImpl.class);

    @Override
    public void sendConfirmCode(Order order) {
        final String username = "maranasama@gmail.com";
        final String password = "maranasama123";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(order.getUser().getEmail())
            );
            message.setSubject("Don't show your code anybody: ");
            message.setText(order.getCode().getCode());

            Transport.send(message);
            logger.info(RandomHelper.getFourDigitCode() + " was send to" + order.getUser().getEmail());

        } catch (MessagingException e) {
            logger.error("Mail can't send", e);
        }
    }
}
