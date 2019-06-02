package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("First", "Name", "1234567890", "first@name.com"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().returnToMainPage();

    }

}
