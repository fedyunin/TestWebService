package com.test.webservice.service;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.test.webservice.model.Company;
import com.test.webservice.model.ContactInfo;
import com.test.webservice.model.Person;

/**
 * Objectify initialization and access helper class.
 *
 * @author Alexey Fedyunin (alexey@cranfan.ru)
 */
public class OfyService {
    static {
        factory().register(ContactInfo.class);
        factory().register(Company.class);
        factory().register(Person.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
