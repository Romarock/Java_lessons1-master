package ru.stqa.pft.addressbook.tests;
import org.hibernate.sql.Select;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;


public class AddContactToGroupTest extends TestBase {

    @BeforeMethod

    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0)
        {

            app.contact().create(new ContactData()
                    .withName("kolya").withSecondName("ivanov").withPhone("777").withEmail("777@888").withAddress("iii"));
            app.goTo().HomePage();

        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test2"));
        }

        app.contact().ContactFilter();

    }





    @Test
    public void contactModificationTests(){
        app.goTo().HomePage();
        Contacts contactList = app.db().contacts();
        Groups groupList = app.db().groups();


            ContactData addingContact = contactList.iterator().next();



        }





