package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{


    @Test
    public void testGroupCreation() {
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("TestGroup", "LogoGroup", "Comment for the Group"));
        app.submitGroupCreation();
        app.returnToGroupPage();

    }

}
