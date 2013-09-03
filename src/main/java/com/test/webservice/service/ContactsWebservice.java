package com.test.webservice.service;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.repackaged.com.google.api.client.util.Strings;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.test.webservice.model.Company;
import com.test.webservice.model.ContactItem;
import com.test.webservice.model.Person;
import com.test.webservice.model.Simple;
import com.test.webservice.model.search.BaseSearchCriteria;
import com.test.webservice.model.search.CompanySearchCriteria;
import com.test.webservice.model.search.PersonSearchCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

import static com.test.webservice.service.OfyService.ofy;

/**
 * Contacts GoogleCloudEndpoint service for to add/list Persons and Companies
 *
 * @author Alexey Fedyunin (alexey@cranfan.ru)
 */
@Api(name = "contacts", version = "v1", description = "Contacts Web Service for add/list Persons and Companies")
public class ContactsWebservice {

    /* Max amount of records for fetch from datastore per request. */
    public static final int LIMIT = 1000;

    protected final Logger log = Logger.getLogger(this.getClass().getName());

    @ApiMethod(name = "addPerson", httpMethod = "POST")
    public Person addPerson(Person person) {
        log.info("Add person to datastore: " + person.toString());
        ofy().save().entity(person).now();
        return person;
    }

    @ApiMethod(name = "addCompany", httpMethod = "POST")
    public Company addCompany(Company company) {
        log.info("Add company to datastore: " + company.toString());
        ofy().save().entity(company).now();
        return company;
    }

    @ApiMethod(name = "listPerson", httpMethod = "POST")
    public List<Person> listPerson(PersonSearchCriteria searchCriteria) {
        log.info("Retrieve list of persons for search criteria: " + searchCriteria.toString());
        // initial query build
        Query query = ofy().load().type(Person.class).limit(LIMIT);

        if (!Strings.isNullOrEmpty(searchCriteria.getFirstname())) {
            query = startsWith(query, "firstName", searchCriteria.getFirstname());
        }
        if (!Strings.isNullOrEmpty(searchCriteria.getLastName())) {
            query = startsWith(query, "lastName", searchCriteria.getLastName());
        }
        query = getTagsQuery(searchCriteria, query);

        return query.list();
    }

    @ApiMethod(name = "listCompany", httpMethod = "POST")
    public List<Company> listCompany(CompanySearchCriteria searchCriteria) {
        log.info("Retrieve list of companies for search criteria: " + searchCriteria.toString());
        // initial query build
        Query query = ofy().load().type(Company.class).limit(LIMIT);

        if (!Strings.isNullOrEmpty(searchCriteria.getCompanyName())) {
            query = startsWith(query, "companyName", searchCriteria.getCompanyName());
        }
        query = getTagsQuery(searchCriteria, query);

        return query.list();
    }

    /**
     * Prepares initialQuery for filter by tags.
     *
     * @param searchCriteria exists criteria,
     * @param initialQuery   init query.
     * @return initialQuery with filleting by tags.
     */
    private Query getTagsQuery(BaseSearchCriteria searchCriteria, Query initialQuery) {
        if (searchCriteria.getTags() != null && !searchCriteria.getTags().isEmpty()) {
            initialQuery = initialQuery.filter("tags in", searchCriteria.getTags());
        }
        return initialQuery;
    }

    /**
     * Prepares query for filtering string field (analog of sql LIKE % %)
     *
     * @param initialQuery init query,
     * @param fieldName    name of field for filtering,
     * @param value        filtering value.
     * @return query with filtering by string field expression.
     */
    private Query startsWith(Query initialQuery, String fieldName, String value) {
        return initialQuery.filter(fieldName + " >=", value).filter(fieldName + " <", value + "\uFFFD");
    }


    @ApiMethod(name = "generateData", httpMethod = "GET")
    public Simple generateData() {
        log.info("Generate test data");
        for (int i = 0; i < 100; i++) {
            Company company = new Company();
            company.setCompanyName(UUID.randomUUID().toString());
            company.setAddress(UUID.randomUUID().toString());
            company.setCode(UUID.randomUUID().toString());
            company.setCountry(UUID.randomUUID().toString());
            company.setLocal(UUID.randomUUID().toString());
            company.setPostalCode(UUID.randomUUID().toString());
            company.setEmailList(getRandomContactItems());
            company.setLinkList(getRandomContactItems());
            company.setPhoneNumberList(getRandomContactItems());
            company.setTags(getRandomTagList());
            ofy().save().entity(company).now();

            Person Person = new Person();
            Person.setFirstName(UUID.randomUUID().toString());
            Person.setLastName(UUID.randomUUID().toString());
            Person.setAddress(UUID.randomUUID().toString());
            Person.setCode(UUID.randomUUID().toString());
            Person.setCountry(UUID.randomUUID().toString());
            Person.setLocal(UUID.randomUUID().toString());
            Person.setPostalCode(UUID.randomUUID().toString());
            Person.setEmailList(getRandomContactItems());
            Person.setLinkList(getRandomContactItems());
            Person.setPhoneNumberList(getRandomContactItems());
            Person.setTags(getRandomTagList());
            ofy().save().entity(Person).now();

        }
        return new Simple("Generated test data");
    }


    @ApiMethod(name = "clearData", httpMethod = "GET")
    public Simple clearData() {
        Iterable<Key<Company>> allKeys = ofy().load().type(Company.class).keys();
        ofy().delete().keys(allKeys);

        Iterable<Key<Person>> allKeys2 = ofy().load().type(Person.class).keys();
        ofy().delete().keys(allKeys2);

        return new Simple("Removed all data");
    }


    private List<String> getRandomTagList() {
        Random random = new Random();
        int size = random.nextInt(10);
        List<String> randomTags = new ArrayList<>(size);
        for (int j = 0; j < size; j++) {
            randomTags.add(String.valueOf(random.nextInt(100)));
        }
        return randomTags;

    }

    private List<ContactItem> getRandomContactItems() {
        Random random = new Random();
        int size = random.nextInt(10);
        List<ContactItem> randomContactItems = new ArrayList<>(size);
        for (int j = 0; j < size; j++) {
            ContactItem contactItem = new ContactItem();
            contactItem.setType(UUID.randomUUID().toString());
            contactItem.setValue(UUID.randomUUID().toString());
            randomContactItems.add(contactItem);
        }
        return randomContactItems;
    }
}
