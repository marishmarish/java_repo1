package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("First", "Name", "1234567890", "first@name.com"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().returnToMainPage();
    }

    @Test
    public void testContactEdition() {
        app.getContactHelper().goHome();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("First", "Name", "1234567890", "first@name.com"));
        }
        app.getContactHelper().initContactEdition();
        app.getContactHelper().fillContactForm(new ContactData("First111", "Surname", "987654321", "second@name.com"));
        app.getContactHelper().submitAndUpdate();
        app.getNavigationHelper().returnToMainPage();
    }

    @Test
    public void testContactDeletion() {
        app.getContactHelper().goHome();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("First", "Name", "1234567890", "first@name.com"));
        }
        app.getContactHelper().chooseContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().confirmDelete();
    }


}
