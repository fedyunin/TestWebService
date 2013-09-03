package com.test.webservice.model;

import com.googlecode.objectify.annotation.Embed;

/**
 * Object model for simple contact item entry (email, link, phone etc)
 *
 * @author Alexey Fedyunin (alexey@cranfan.ru)
 */
@Embed
public class ContactItem {

    String value;

    String type;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "ContactItem{" +
                "value='" + value + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
