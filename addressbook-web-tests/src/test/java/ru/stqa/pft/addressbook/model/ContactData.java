package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;
@XStreamAlias("contact")
public class ContactData {
    public ContactData withId(int id) {
        this.id = id;
        return this;
    }
    @XStreamOmitField
    private  int id = Integer.MAX_VALUE;
    private  String name;
    private  String secondName;
    private  String phone;
    private  String email;
    private  String workPhone;
    private  String homePhone;
    private  String allPhones;
    private  String email2;
    private  String email3;
    private  String allEmails;

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public File getPhoto() {
        return photo;
    }

    private File photo;

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }



    public ContactData withName(String name) {
        this.name = name;
        return this;
    }

    public ContactData withSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public ContactData withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }
    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {

        this.homePhone = homePhone;
        return this;
    }
    public ContactData withWorkPhone(String workPhone) {

        this.workPhone = workPhone;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    private  String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(secondName, that.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, secondName);
    }

    public int getId() { return id;}

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPhone() {
        return phone;

    }
    public String getWorkPhone() {
        return workPhone;
    }
    public String getHomePhone() {

        return homePhone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getAllPhones() {

        return allPhones;
    }

    public String getEmail2() {
        return email2;
    }
    public String getEmail3() {
        return email3;
    }
    public String getAllEmails() {
        return allEmails;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }


}
