# TestWebService

A small REST API built on **Google Cloud Endpoints** with **Objectify** over the App Engine Datastore. It manages contacts — people and companies — and exists to show the two libraries working together: annotated Java methods becoming a generated API, and Objectify handling the persistence.

> **Archived.** Written in 2013 for the Java 7 App Engine standard runtime. Both Cloud Endpoints v1 and that runtime are retired, so this won't deploy as-is. Kept for reference.

## The API

Endpoints turns annotated methods into a REST API plus generated client libraries. The whole surface is one class, [`ContactsWebservice.java`](src/main/java/com/test/webservice/service/ContactsWebservice.java), declared as:

```java
@Api(name = "contacts", version = "v1",
     description = "Contacts Web Service for add/list Persons and Companies")
```

| Method | HTTP | Purpose |
| --- | --- | --- |
| `addPerson` | POST | Store a person |
| `addCompany` | POST | Store a company |
| `listPerson` | POST | Query people by search criteria |
| `listCompany` | POST | Query companies by search criteria |
| `generateData` | GET | Populate the datastore with sample records |
| `clearData` | GET | Delete everything |

Queries take a criteria object rather than a pile of query parameters — [`PersonSearchCriteria`](src/main/java/com/test/webservice/model/search/PersonSearchCriteria.java) and [`CompanySearchCriteria`](src/main/java/com/test/webservice/model/search/CompanySearchCriteria.java), both extending a shared `BaseSearchCriteria` that carries paging. Results are capped at 1000 records.

## Data model

[`Person`](src/main/java/com/test/webservice/model/Person.java) and [`Company`](src/main/java/com/test/webservice/model/Company.java) are Objectify entities; each holds `ContactInfo`, which in turn holds a list of `ContactItem` (a typed value — phone, email, and so on). Entity registration lives in [`OfyService`](src/main/java/com/test/webservice/service/OfyService.java), the conventional Objectify pattern of a static initialiser that registers every class before first use.

[`SecureWebService`](src/main/java/com/test/webservice/service/SecureWebService.java) is a second API demonstrating an authenticated endpoint.

## Build

```
mvn package
```

Configuration sits in [`appengine-web.xml`](src/main/webapp/WEB-INF/appengine-web.xml) and [`web.xml`](src/main/webapp/WEB-INF/web.xml); set your own application ID there before deploying.
