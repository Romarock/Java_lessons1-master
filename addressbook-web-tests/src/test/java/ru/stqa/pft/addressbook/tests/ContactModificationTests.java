package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.junit.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @Test
    public void contactModificationTests(){

        app.goTo().HomePage();

        if (app.db().contacts().size() == 0)
        {

            app.contact().create(new ContactData()
                    .withName("kolya").withSecondName("ivanov").withPhone("777").withEmail("777@888").withAddress("iii"));
            app.goTo().HomePage();

        }
       Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();

        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withName("Vasya").withSecondName("ttt").withPhone("3956").withAddress("756").withEmail("sjgf@hh")
                .withEmail3("555").withEmail2("666").withHomePhone("666").withWorkPhone("777");
        app.contact().modify(contact);
        app.goTo().HomePage();

        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
      }


    }

