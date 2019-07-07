package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroupTests extends TestBase {

    private ContactData selectedContact;
    private GroupData selectedGroup;

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
        Contacts contactsFromDb = app.db().contacts();
        this.selectedContact = contactsFromDb.iterator().next();
        Groups groupsFromDb = app.db().groups();
        this.selectedGroup = groupsFromDb.iterator().next();
        app.goTo().homePage();
        app.contact().addContactToGroup(selectedContact, selectedGroup);
        verifyThatContactBelongsToGroup(selectedContact, selectedGroup);
        app.goTo().homePage();
    }

    @Test
    public void testDeleteContactFromGroup() {
        app.contact().displayContactsForGroupById(selectedGroup.getId());
        app.contact().removeContactFromGroup(selectedContact);
        verifyThatContactDoesntBelongToGroup(selectedContact, selectedGroup);
    }

    private void verifyThatContactDoesntBelongToGroup(ContactData selectedContact, GroupData selectedGroup) {
        ContactData contactFromDb = app.db().getContactById(selectedContact.getId());
        assertThat(contactFromDb.getGroups(), is(not(hasItem(selectedGroup))));
    }
}
