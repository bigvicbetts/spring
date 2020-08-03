package com.Betts.userdatabase.Models;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private UUID publicID;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String encryptedPassword;

    public User() {
    }

    public User(String firstName, String lastName, String emailAddress, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.encryptedPassword = password;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEndryptedPassword() {
        return encryptedPassword;
    }

    public UUID getPublicID() {
        return publicID;
    }

    public void setPublicID() {
        this.publicID = publicID.randomUUID();
    }

    public void setEncryptedPassword(String password) {
        this.encryptedPassword = password;
    }
}
