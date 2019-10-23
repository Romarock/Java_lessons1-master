package ru.stqa.pft.addressbook.tests;

import org.hamcrest.junit.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().HomePage();
        if (!app.contact().isThereAContact()) {

            app.contact().create(new ContactData()
                    .withName("Ivan").withSecondName("Ivanov").withEmail("888@uuu").withAddress("yyyy")
                    .withPhone("888").withHomePhone("555").withWorkPhone("666")
                    .withEmail2("222@222").withEmail3("333@333"));
            app.goTo().HomePage();

        }
    }

    @Test

    public void testContactAddress() {

        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(cleaned(contact.getAddress()), equalTo(cleaned(contactInfoFromEditForm.getAddress())));


    }

    public static String cleaned(String address) {

        return address.replaceAll("\\s", "").replaceAll("[- ()]", "");

    }
}