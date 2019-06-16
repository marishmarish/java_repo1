package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
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

    public void initContactEdition(int index) {
        click(By.xpath("(//img[@alt='Edit'])["+index+"]"));
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

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("(//input[@name='selected[]'])[1]"));
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name = 'entry']"));
        for (WebElement element : elements) {
            String elementText = element.getText();
            String[] contactInfo = elementText.split(" ");
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(id, contactInfo[1], contactInfo[0], contactInfo[2], contactInfo[3]);
            contacts.add(contact);
        }
        return contacts;
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]"))
                .get(index)
                .click();
    }
}
