package com.test.webservice.model;


import com.googlecode.objectify.annotation.EntitySubclass;
import com.googlecode.objectify.annotation.Index;


/**
 * Object model for Person.
 *
 * @author Alexey Fedyunin (alexey@cranfan.ru)
 */
@EntitySubclass(index = true)
public class Person extends ContactInfo {

    @Index
    String firstName;

    @Index
    String lastName;

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


    @Override
    public String toString() {
        return super.toString() + "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
