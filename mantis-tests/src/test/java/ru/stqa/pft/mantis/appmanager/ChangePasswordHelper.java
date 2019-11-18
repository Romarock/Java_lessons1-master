package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;


import java.io.IOException;


import static org.testng.Assert.assertTrue;

public class ChangePasswordHelper extends HelperBase {
    long now = System.currentTimeMillis();
    String user = "user1";
    String password = "root" + now;

    public ChangePasswordHelper(ApplicationManager app) {



        super(app);
    }
    public void login() {
        wd.get("http://localhost/mantisbt-2.22.1/mantisbt-2.22.1/login_page.php");
        wd.findElement(By.id("username")).clear();
        wd.findElement(By.id("username")).sendKeys("administrator");
        wd.findElement(By.xpath("//input[@value='Login']")).click();
        wd.findElement(By.id("password")).clear();
        wd.findElement(By.id("password")).sendKeys("root");
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    public void resetPassword() {

        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Summary'])[1]/following::i[1]")).click();
        wd.findElement(By.linkText("Manage Users")).click();
        wd.findElement(By.linkText(user)).click();
        wd.findElement(By.xpath("//input[@value='Reset Password']")).click();
    }

    public void newPassword(String confirmationLink) throws IOException {
        wd.get(confirmationLink);
        wd.findElement(By.id("password")).click();
        wd.findElement(By.id("password")).clear();
        wd.findElement(By.id("password")).sendKeys(password);
        wd.findElement(By.id("password-confirm")).click();
        wd.findElement(By.id("password-confirm")).clear();
        wd.findElement(By.id("password-confirm")).sendKeys(password);
        wd.findElement(By.xpath("//button[@type='submit']")).click();
        HttpSession session = app.newSession();
        assertTrue(session.login(user, password));


    }



}

