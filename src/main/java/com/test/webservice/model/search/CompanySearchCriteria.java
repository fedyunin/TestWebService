package com.test.webservice.model.search;

/**
 * Simple container for receive search parameters for list Companies from client.
 *
 * @author Alexey Fedyunin (alexey@cranfan.ru)
 */
public class CompanySearchCriteria extends BaseSearchCriteria {

    String companyName;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    @Override
    public String toString() {
        return super.toString() + "CompanySearchCriteria{" +
                "companyName='" + companyName + '\'' +
                '}';
    }
}
