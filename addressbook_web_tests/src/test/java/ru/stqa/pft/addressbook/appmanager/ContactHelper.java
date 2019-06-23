package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        click(By.xpath("//tr[td/input/@id=" + index + "]/td[8]"));
    }

    public void submitAndUpdate() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void homePage() {
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


    public void modify(ContactData contact) {
        initContactEdition(contact.getId());
        fillContactForm(contact);
        submitAndUpdate();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        confirmDelete();
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }


    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name = 'entry']"));
        for (WebElement element : elements) {
            List<WebElement> elementsOfTdElement = element.findElements(By.xpath("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData()
                    .withId(id)
                    .withFirstname(elementsOfTdElement.get(2).getText())
                    .withLastname(elementsOfTdElement.get(1).getText())
                    .withMobile(elementsOfTdElement.get(5).getText())
                    .withEmail(elementsOfTdElement.get(4).getText()));
        }
        return contacts;
    }


    public void selectContact(int index) {
        wd.findElements(By.name("selected[]"))
                .get(index)
                .click();
    }


}
