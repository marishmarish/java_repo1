package ru.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordResetTests extends TestBase {

    private String user;
    private String password;
    private String email;

    @BeforeMethod
    public void ensurePreconditionsForPasswordReset() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        user = String.format("user%s", now);
        password = "password";
        email = String.format("user%s@localhost", now);
        app.james().createUser(user, password);
        app.registration().start(user, email);
        getPasswordMailAndUpdate(password);
        assertTrue(app.newSession().login(user, password));
    }


    @Test
    public void testResetPassword() throws MessagingException, IOException {
        String userAdmin = "administrator";
        String passwordAdmin = "admin";
        app.login().start(userAdmin, passwordAdmin);
        app.manageUsers().findAndSelectUser(user);
        app.manageUsers().resetPassword();
        String newPassword = "newPwd";
        getPasswordMailAndUpdate(newPassword);
        HttpSession session = app.newSession();
        assertTrue(session.login(user, newPassword));
        assertTrue(session.isLoggedInAs(user));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    private void getPasswordMailAndUpdate(String newPassword) throws MessagingException {
        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 90000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, newPassword);
        app.james().drainEmail(user, password);
    }
}
