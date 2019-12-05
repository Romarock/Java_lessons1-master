package ru.stqa.pft.mantis.appmanager;


import org.openqa.selenium.By;



import java.io.IOException;



import static org.testng.Assert.assertTrue;

public class ChangePasswordHelper extends HelperBase {

    long now = System.currentTimeMillis();

    String user = app.db().users().get(1).getName();
    String password = app.getProperty("web.adminPassword")  + now;

    public ChangePasswordHelper(ApplicationManager app) {



        super(app);
    }
    public void login() {
        wd.get(app.getProperty("web.baseUrl")+"/login_page.php");
        wd.findElement(By.id("username")).clear();
        wd.findElement(By.id("username")).sendKeys(app.getProperty("web.adminLogin"));
        wd.findElement(By.xpath("//input[@value='Login']")).click();
        wd.findElement(By.id("password")).clear();
        wd.findElement(By.id("password")).sendKeys(app.getProperty("web.adminPassword"));
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

