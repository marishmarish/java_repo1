package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.List;

public class ContactDeletionTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditionsForDeletion() {
        app.contact().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().createContact(new ContactData("First", "Name", "1234567890", "first@name.com"));
        }
    }

    @Test (enabled = false)
    public void testContactDeletion() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }


}
