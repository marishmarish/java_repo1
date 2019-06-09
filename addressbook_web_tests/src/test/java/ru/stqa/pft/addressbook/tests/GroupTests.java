package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupTests extends TestBase{

    @Test
    public void testGroupCreation() {
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("TestGroup", "LogoGroup", "Comment for the Group"));
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();

    }

    @Test
    public void testGroupEdition() {
        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("TestGroup", "LogoGroup", "Comment for the Group"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().editGroup();
        app.getGroupHelper().fillGroupForm(new GroupData("TestGroup1", "LogoGroup2", "Comment for the Group3"));
        app.getGroupHelper().submitAndUpdate();
        app.getGroupHelper().returnToGroupPage();
    }

    @Test
    public void testGroupDeletion() {
        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("TestGroup", "LogoGroup", "Comment for the Group"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteGroup();
        app.getGroupHelper().returnToGroupPage();

    }
}
