package com.test.webservice.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.ArrayList;
import java.util.List;

/**
 * Object model for Contact Info. Parent class for contacts.
 *
 * @author Alexey Fedyunin (alexey@cranfan.ru)
 */
@Entity
public class ContactInfo {

    @Id
    Long id;

    String code;

    Boolean isThisClient;

    List<ContactItem> emailList = new ArrayList<>();

    List<ContactItem> phoneNumberList = new ArrayList<>();

    List<ContactItem> linkList = new ArrayList<>();

    String address;

    String postalCode;

    String local;

    String country;

    @Index
    List<String> tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getThisClient() {
        return isThisClient;
    }

    public void setThisClient(Boolean thisClient) {
        isThisClient = thisClient;
    }

    public List<ContactItem> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<ContactItem> emailList) {
        this.emailList = emailList;
    }

    public List<ContactItem> getPhoneNumberList() {
        return phoneNumberList;
    }

    public void setPhoneNumberList(List<ContactItem> phoneNumberList) {
        this.phoneNumberList = phoneNumberList;
    }

    public List<ContactItem> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<ContactItem> linkList) {
        this.linkList = linkList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", isThisClient=" + isThisClient +
                ", emailList=" + emailList +
                ", phoneNumberList=" + phoneNumberList +
                ", linkList=" + linkList +
                ", address='" + address + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", local='" + local + '\'' +
                ", country='" + country + '\'' +
                ", tags=" + tags +
                '}';
    }
}
