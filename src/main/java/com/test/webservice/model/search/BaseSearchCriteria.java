package com.test.webservice.model.search;

import java.util.List;

/**
 * Simple container for receive search parameters from client.
 *
 * @author Alexey Fedyunin (alexey@cranfan.ru)
 */
public class BaseSearchCriteria {

    List<String> tags;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "BaseSearchCriteria{" +
                "tags=" + tags +
                '}';
    }
}
