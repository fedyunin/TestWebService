package com.test.webservice.model.search;

/**
 * Simple container for receive search parameters for list Persons from client.
 *
 * @author Alexey Fedyunin (alexey@cranfan.ru)
 */
public class PersonSearchCriteria extends BaseSearchCriteria {

    String firstname;

    String lastName;


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public String toString() {
        return super.toString() + "PersonSearchCriteria{" +
                "firstname='" + firstname + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
