package portal.tests.tests;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.openqa.selenium.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class PortalTest {
    private WebDriver wd;


    @Test


    public void testPortal1() throws Exception {


        int e = 300;

        for (int i = 0; i < e ; i++) {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get("https://tvpp-cis1.otc.mshop.csolab.ru/portal/login.jsp");
        login();
        assertThat(isErrorPresent(), equalTo(false));
        customerSearch();
        // goToTSG();
        wd.quit();

        }


    }


    public void login() {
        wd.findElement(By.id("j_username")).click();
        wd.findElement(By.id("j_username")).clear();
        wd.findElement(By.id("j_username")).sendKeys("");
        wd.findElement(By.id("j_vo")).click();
        wd.findElement(By.id("j_vo")).clear();
        wd.findElement(By.id("j_vo")).sendKeys("");
        wd.findElement(By.id("j_password")).click();
        wd.findElement(By.id("j_password")).clear();
        wd.findElement(By.id("j_password")).sendKeys("");
        wd.findElement(By.id("login")).click();




        for (int i = 0; i<10; i ++) {

            isWarningPresent();
            if (isWarningPresent() == false){break;}

            wd.findElement(By.id("j_idt32:alreadyLogged-txt"));
            wd.findElement(By.id("j_idt32:kickUser-rd-grp:0")).click();
            wd.findElement(By.id("j_idt32:submit-btn")).click();
        }


    }

    public void customerSearch() {
        wd.findElement(By.id("customerSearchForm:j_idt111:customerSearchTmoBox:numberEntry")).click();
        wd.findElement(By.id("customerSearchForm:j_idt111:customerSearchTmoBox:numberEntry")).clear();
        wd.findElement(By.id("customerSearchForm:j_idt111:customerSearchTmoBox:numberEntry")).sendKeys("4916099661100");
        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='*'])[5]/following::span[1]")).click();

    }

    public void goToTSG() {
        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='T-VPP Pflegetool Portal >'])[1]/following::span[1]")).click();
    }


    public boolean isWarningPresent() {


        try {
            wd.findElement(By.id("j_idt32:alreadyLogged-txt"));

            return true;
        } catch (NoSuchElementException e) {
            return false;
        }



    }

    public boolean isErrorPresent() {


        try {
            wd.findElement(By.id("j_idt9"));

            return true;
        } catch (NoSuchElementException e) {
            return false;
        }



    }
}






