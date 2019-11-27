package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class RemoveContactFromGroupTest extends TestBase {


    @BeforeMethod

    public void ensurePreconditions() {

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test2"));
        }


        if (app.db().contacts().size() == 0) {

            app.contact().create(new ContactData()
                    .withName("kolya").withSecondName("ivanov").withPhone("777").withEmail("777@888"));
            app.contact().selectContact();
            app.contact().submitAddingContact();

            app.goTo().HomePage();
        }
        Contacts contacts = app.db().contacts();

        int ContactsWithGroupsCount = 0;
        for (int i = 0; i == contacts.size(); i++) {
            if (app.db().result().get(i).getGroups() != null) {
                ContactsWithGroupsCount++;

            }

            if (ContactsWithGroupsCount == 0) {

                app.contact().ContactFilter();
                app.contact().selectContact();
                app.contact().submitAddingContact();

            }

        }


    }







    @Test
    public void removeContact() {

        app.goTo().groupPage();
        int GroupId =  app.group().getGroupID();
        app.goTo().HomePage();
        app.contact().filterContactsInGroups(GroupId);

        Contacts before = app.db().contacts();

        int id = app.contact().selectContact();
        ContactData removedContact =  app.db().selectedContact(id);
        app.contact().submitContactRemoving();
        ContactData contactOutOfGroup = app.db().selectedContact(id);
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withOut(removedContact).withAdded(contactOutOfGroup)));
        System.out.println("ok");


    }





}

