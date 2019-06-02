package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        app.initContactCreation();
        app.fillContactForm(new ContactData("First", "Name", "1234567890", "first@name.com"));
        app.submitContactCreation();
        app.returnToMainPage();

    }

}
