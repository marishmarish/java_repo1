package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.util.List;

public class GroupHelper extends BaseHelper {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void submitAndUpdate() {
        click(By.xpath("//input[@name='update']"));
    }

    public void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void editGroup() {
        click(By.xpath("(//input[@name='edit'])[2]"));
    }

    public void deleteGroup() {
        click(By.xpath("(//input[@name='delete'])[2]"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
        groupCash = null;
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        editGroup();
        fillGroupForm(group);
        submitAndUpdate();
        groupCash = null;
        returnToGroupPage();
    }


    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteGroup();
        groupCash = null;
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.xpath("(//input[@name='selected[]'])[1]"));
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Groups groupCash = null;


    public Groups all() {
        if (groupCash !=null) {
            return new Groups(groupCash);
        }

        groupCash = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCash.add(new GroupData().withId(id).withName(name));
        }

        return new Groups(groupCash);
    }


}


