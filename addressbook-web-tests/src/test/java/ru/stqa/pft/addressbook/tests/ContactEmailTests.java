package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

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

    public void testContactEmails() {

        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);


        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));


    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).
                stream().filter((s) -> !s.equals(""))
                .map(ContactEmailTests::cleaned)
                .collect(Collectors.joining("\n"));

    }
    public static String cleaned (String email)  {

        return email.replaceAll("\\s", "").replaceAll("[- ()]", "");
    }
}