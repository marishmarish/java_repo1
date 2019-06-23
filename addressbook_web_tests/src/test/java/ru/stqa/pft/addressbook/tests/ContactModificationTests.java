package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsEdition() {
        app.contact().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactData().withFirstname("First").withLastname("Name").withMobile("1234567").withEmail("qqq@sdfd.ty"));
        }
    }

    @Test
    public void testContactEdition() {
        Contacts before = app.contact().all();
        ContactData editedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(editedContact.getId()).withFirstname("Edited").withLastname("Name")
                .withMobile("1234567").withEmail("edited@mail.com");
        app.contact().modify(contact);
        app.goTo().homePage();
        assertEquals(app.contact().count(), before.size());
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(editedContact).withAdded(contact)));
    }
}
