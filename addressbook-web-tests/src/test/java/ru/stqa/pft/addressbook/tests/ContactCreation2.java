package ru.stqa.pft.addressbook.tests;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactCreation2 extends TestBase {

//    @DataProvider
//    public Iterator<Object[]> validContacts() throws IOException {
//        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
//        String xml = "";
//        String line = reader.readLine();
//        while (line != null) {

//            xml += line;

//            line = reader.readLine();
//        }
//        XStream xstream = new XStream();
//        xstream.processAnnotations(ContactData.class);
//        List<ContactData> contacts =  (List<ContactData>) xstream.fromXML(xml);
//        return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();



//    }




  @Test
  public void testContactCreation2() throws Exception {

      Groups groups = app.db().groups();
    app.goTo().HomePage();
    Contacts before = app.db().contacts();
  //  File photo = new File("src/test/resources/onyx.png");
      ContactData contact = new ContactData()
              .withName("ivan").withAddress("ttt").withSecondName("hkhk").withEmail("666@uuu").withPhone("555")
              .withWorkPhone("555").withHomePhone("757").withEmail2("5865").withEmail3("24647").inGroup(groups.iterator().next());
    app.contact().create(contact);
    app.goTo().HomePage();
   Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size() +1));
    assertThat(after, equalTo(
            before.withAdded( contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }




}

