package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends BaseHelper {

    public NavigationHelper(FirefoxDriver wd) {
       super(wd);
    }

    public void returnToMainPage() {
        click(By.linkText("home page"));
    }
}
