package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsEdition() {
        app.contact().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().createContact(new ContactData().withFirstname("First").withLastname("Name").withMobile("1234567"));
        }
    }

    @Test
    public void testContactEdition() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData()
                .withId(before.get(index).getId()).withFirstname("Edited").withLastname("Name")
                .withMobile("1234567").withEmail("edited@mail.com");
        app.contact().modify(before, contact);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());


        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
