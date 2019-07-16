package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase{

    public LoginHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        type(By.xpath("//input[@id='username']"), username);
        click(By.cssSelector("input[value='Войти']"));
        type(By.xpath("//input[@id='password']"), password);
        click(By.cssSelector("input[value='Войти']"));
    }
}
