package ru.stqa.pft.mantis.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "mantis_user_table")

public class User {
    public User withId(int id) {
        this.id = id;
        return this;
    }

    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Column(name = "username")
    private String name;

    @Column(name =  "email")
    private String email;

    public String getEmail() {
        return email;
    }

    public User withEmail(String email) {
        this.email = email;
        return this;
    }

    public User withName(String name) {
        this.name = name;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
