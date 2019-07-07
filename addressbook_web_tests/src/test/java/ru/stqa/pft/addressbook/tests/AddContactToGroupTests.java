package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class AddContactToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
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
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
    }

    @Test
    public void testAddContactToGroup() {
        Contacts contactsFromDb = app.db().contacts();
        ContactData selectedContact = contactsFromDb.iterator().next();
        Groups groupsFromDb = app.db().groups();
        GroupData selectedGroup = groupsFromDb.iterator().next();
        app.goTo().homePage();
        app.contact().addContactToGroup(selectedContact, selectedGroup);
        verifyThatContactBelongsToGroup(selectedContact, selectedGroup);
    }
}
