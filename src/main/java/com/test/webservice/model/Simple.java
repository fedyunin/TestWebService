package com.test.webservice.model;

/**
 * Container for send simple response to client.
 *
 * @author Alexey Fedyunin (alexey@cranfan.ru)
 */
public class Simple {

    String message;

    public Simple() {
    }

    public Simple(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
