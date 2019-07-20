package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import static org.testng.Assert.assertTrue;

public class RegistrationRestBugifyTests extends TestBase {

    @Test
    public void testRegestration() throws IOException, MessagingException {
        skipIfNotFixedRest(1591);
        long now = System.currentTimeMillis();
        String user = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost", now);
        app.james().createUser(user, password);
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 90000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        app.newSession().login(user, password);
        assertTrue(app.newSession().login(user, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

}
