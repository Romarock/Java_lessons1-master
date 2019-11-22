package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;


public class AddContactToGroupTest extends TestBase {

    private Iterator<ContactData> iterator;

    @BeforeMethod

    public void ensurePreconditions() {

        if (app.db().contacts().size() == 0) {

            app.contact().create(new ContactData()
                    .withName("kolya").withSecondName("ivanov").withPhone("777").withEmail("777@888"));

            app.goTo().HomePage();
        }
        app.contact().ContactFilter();
        if (app.contact().isContactPresent() == false) {

            app.contact().create(new ContactData()
                    .withName("kolya").withSecondName("ivanov").withPhone("777").withEmail("777@888").withAddress("iii"));
            app.goTo().HomePage();
        }


        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test2"));
        }


    }


    @Test
    public void contactAddingToGroupTest() {

        Contacts before = app.db().contacts();
        app.goTo().HomePage();
        app.contact().ContactFilter();
        int id = app.contact().selectContact();
        ContactData modifiedContact =  app.db().selectedContact(id);
        app.contact().submitAddingContact();
        ContactData contactInGroup = app.db().selectedContact(id);
        Contacts after = app.db().contacts();
      assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contactInGroup)));
        System.out.println("ok");

    }






    }












