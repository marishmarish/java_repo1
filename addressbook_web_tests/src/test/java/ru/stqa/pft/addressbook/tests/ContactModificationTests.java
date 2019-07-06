package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsEdition() {
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
    public void testContactEdition() {
        Contacts before = app.db().contacts();
        ContactData editedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(editedContact.getId()).withFirstname("Edited").withLastname("Name")
                .withEmail("edited@mail.com")
                .withMobile("1234567")
                .withWorkPhone("123")
                .withHomePhone("567")
                .withAddress("66address street");
        app.contact().modify(contact);
        app.goTo().homePage();
        assertEquals(app.contact().count(), before.size());
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(editedContact).withAdded(contact)));
    }
}
