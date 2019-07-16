package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class UserManagerHelper extends HelperBase {

    public UserManagerHelper(ApplicationManager app) {
        super(app);
    }

    public void findAndSelectUser(String user) {
        type(By.xpath("//input[@id='search']"), user);
        click(By.xpath("//input[@value='Apply Filter']"));
        click(By.xpath("//tbody//a"));
    }

    public void resetPassword() {
        click(By.xpath("//input[@value='Reset Password']"));
    }
}
