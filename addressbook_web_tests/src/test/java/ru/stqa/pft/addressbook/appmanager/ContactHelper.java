package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {

    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());
    }


    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void initContactEdition() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitAndUpdate() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void goHome() {
        click(By.linkText("home"));
    }

    public void chooseContact() {
        click(By.xpath("(//input[@name='selected[]'])[1]"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void confirmDelete() {
        confirmPopUp();
    }
}
