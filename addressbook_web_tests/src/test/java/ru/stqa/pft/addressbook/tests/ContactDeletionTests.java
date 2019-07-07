package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditionsForDeletion() {
        if (app.db().contacts().size() == 0) {
            app.contact().homePage();
            app.contact().createContact(new ContactData()
                    .withFirstname("First")
                    .withLastname("Name")
                    .withMobile("1234567")
                    .withEmail("qqq@sdfd.ty")
                    .withWorkPhone("123")
                    .withHomePhone("567")
                    .withAddress("66address street"));
        }
    }

    @Test
    public void testContactDeletion() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().homePage();
        app.contact().delete(deletedContact);
        app.contact().homePage();
        assertEquals(app.contact().count(), before.size() - 1);
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
        verifyContactListInUI();
    }


}
