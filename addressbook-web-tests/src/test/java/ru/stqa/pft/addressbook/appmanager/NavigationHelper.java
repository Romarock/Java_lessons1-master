package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {


    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {

        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {

            return;
        } else {
            wd.findElement(By.linkText("groups")).click();
        }
    }
    public void groupList() {


            wd.findElement(By.linkText("groups")).click();
        }




    public void HomePage() {

        if (isElementPresent(By.id("maintable"))) {
            return;
        } else {
        wd.findElement(By.linkText("home")).click();
        }
    }
}