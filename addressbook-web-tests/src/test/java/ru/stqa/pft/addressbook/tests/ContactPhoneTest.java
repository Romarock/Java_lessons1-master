package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().HomePage();
        if (!app.contact().isThereAContact()) {

            app.contact().create(new ContactData().withName("Ivan").withSecondName("Ivanov").withEmail("888@uuu").withAddress("yyyy").withPhone("888").withHomePhone("555").withWorkPhone("666"));
            app.goTo().HomePage();

        }
    }

    @Test

    public void  testContactPhones() {

        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getPhone(), contact.getWorkPhone()).
                stream().filter((s) -> !s.equals(""))
                .map(ContactPhoneTest::cleaned)
                .collect(Collectors.joining("\n"));

    }

    public static String cleaned (String phone)  {

        return phone.replaceAll("\\s", "").replaceAll("[- ()]", "");
        }

}



