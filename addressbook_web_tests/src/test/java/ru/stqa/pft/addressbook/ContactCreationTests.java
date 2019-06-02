package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        initContactCreation();
        fillContactForm(new ContactData("First", "Name", "1234567890", "first@name.com"));
        submitContactCreation();
        returnToMainPage();

    }

}
