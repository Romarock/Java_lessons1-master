package ru.stqa.pft.mantis.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "mantis_user_table")

public class User {

    @Column(name = "username")
    private String name;

    public User withName(String name) {
        this.name = name;
        return this;
    }



}
