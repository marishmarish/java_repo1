package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{


    @Test
    public void testGroupCreation() {
        initGroupCreation();
        fillGroupForm(new GroupData("TestGroup", "LogoGroup", "Comment for the Group"));
        submitGroupCreation();
        returnToGroupPage();

    }

}
