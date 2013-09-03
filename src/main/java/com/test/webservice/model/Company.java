package com.test.webservice.model;

import com.googlecode.objectify.annotation.EntitySubclass;
import com.googlecode.objectify.annotation.Index;

/**
 * Object model for Company
 *
 * @author Alexey Fedyunin (alexey@cranfan.ru)
 */
@EntitySubclass(index = true)
public class Company extends ContactInfo {

    @Index
    String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    @Override
    public String toString() {
        return super.toString() + "Company{" +
                "companyName='" + companyName + '\'' +
                '}';
    }
}
