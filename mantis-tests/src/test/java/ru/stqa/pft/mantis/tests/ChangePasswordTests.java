package ru.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

public class ChangePasswordTests extends TestBase {

   @BeforeMethod

   public void startMailServer()  {

       app.mail().start();
}

@Test

public void testChangePassword() throws IOException {

    app.db().users();

    String email = "user1@localhost.localdomain";
    app.changePassword().login();
    app.changePassword().resetPassword();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink =findConfirmationLink(mailMessages, email);
    app.changePassword().newPassword(confirmationLink);



}



    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage =  mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }




}