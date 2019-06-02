package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() {
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("TestGroup", "LogoGroup", "Comment for the Group"));
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();

    }

}
