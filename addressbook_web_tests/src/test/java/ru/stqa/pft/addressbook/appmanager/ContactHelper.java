package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("email"), contactData.getEmail());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("address"), contactData.getAddress());
    }


    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void initContactEdition(int index) {
        click(By.xpath("//input[@id=" + index + "]/../../td[8]/a"));
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

    private Contacts contactCash = null;

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        contactCash = null;
    }


    public void modify(ContactData contact) {
        initContactEdition(contact.getId());
        fillContactForm(contact);
        submitAndUpdate();
        contactCash = null;
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        confirmDelete();
        contactCash = null;
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }


    public Contacts all() {
        if (contactCash != null) {
            return new Contacts(contactCash);
        }
        contactCash = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name = 'entry']"));
        for (WebElement element : elements) {
            List<WebElement> elementsOfTdElement = element.findElements(By.xpath("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstName = elementsOfTdElement.get(2).getText();
            String lastName = elementsOfTdElement.get(1).getText();
            String allEmails = elementsOfTdElement.get(4).getText();
            String allPhones = elementsOfTdElement.get(5).getText();
            String address = elementsOfTdElement.get(3).getText();
            contactCash.add(new ContactData()
                    .withId(id)
                    .withFirstname(firstName)
                    .withLastname(lastName)
                    .withAllEmails(allEmails)
                    .withAllPhones(allPhones)
                    .withAddress(address));
        }
        return new Contacts(contactCash);
    }

    public void addToGroup() {
        wd.findElement(By.name("add")).click();
    }

    public void deleteFromTheGroup() {
        wd.findElement(By.name("remove")).click();
    }


    public ContactData infoFromEditForm(ContactData contact) {
        initContactEditionById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId())
                .withFirstname(firstname)
                .withLastname(lastname)
                .withHomePhone(home)
                .withMobile(mobile)
                .withWorkPhone(work)
                .withAddress(address)
                .withEmail(email);
    }

    private void initContactEditionById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

    }

    public void addContactToGroup(ContactData selectedContact, GroupData groupData) {
        selectContactById(selectedContact.getId());
        selectGroupToAddById(groupData.getId());
        addToGroup();
    }

    private void selectGroupToAddById(int id) {
        click(By.xpath("//select[@name='to_group']/option[@value='"+ id +"']"));
    }

    public void displayContactsForGroupById(int id) {
        click(By.xpath("//select[@name='group']/option[@value='"+ id +"']"));
    }

    public void removeContactFromGroup(ContactData selectedContact) {
        selectContactById(selectedContact.getId());
        deleteFromTheGroup();
    }
}
