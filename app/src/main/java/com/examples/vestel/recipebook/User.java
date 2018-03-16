package com.examples.vestel.recipebook;

/**
 * Created by vestel on 15.03.2018.
 */

public class User {
    private String namesurname;
    private String email;
    private String pass;

    public User(String namesurname, String email, String pass, String userid) {
        this.namesurname = namesurname;
        this.email = email;
        this.pass = pass;
        this.userid = userid;
    }

    private String userid;


    public String getNamesurname() {
        return namesurname;
    }

    public void setNamesurname(String namesurname) {
        this.namesurname = namesurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
