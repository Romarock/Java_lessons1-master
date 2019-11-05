package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {



    @Test

    public void contactDeletionTests() {

    app.goTo().HomePage();

    if (app.db().contacts().size() == 0)
     {

        app.contact().create(new ContactData()
                .withName("kolya").withSecondName("ivanov").withPhone("777").withEmail("777@888").withAddress("iii"));
        app.goTo().HomePage();

    }
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().HomePage();

    Contacts after = app.db().contacts();
    Assert.assertEquals(after.size(), before.size() - 1);

        assertThat(after, equalTo(before.withOut(deletedContact)));



 }


}
