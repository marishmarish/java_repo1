package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void homePage() {
        click(By.xpath("//img[@id='logo']"));
    }

    public void groupPage() {
        if(!isElementPresent(By.linkText("new group"))){
            click(By.linkText("groups"));
        }
    }
}
