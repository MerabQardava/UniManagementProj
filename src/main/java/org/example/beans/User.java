package org.example.beans;

public class User {
    protected int id;
    protected String firstName;
    protected String lastName;

    public User(int id, String firstName, String lastName){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
    }

    public int getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
