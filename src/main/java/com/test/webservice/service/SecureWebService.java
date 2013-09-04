package com.test.webservice.service;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;
import com.test.webservice.model.Simple;

import java.io.IOException;

/**
 * Test service for evaluate Oauth security.
 * https://developers.google.com/appengine/docs/java/endpoints/getstarted/auth
 *
 * @author Alexey Fedyunin (alexey@cranfan.ru)
 */
@Api(name = "secureWebService", version = "v1", description = "Test service for evaluate Oauth security",
        clientIds = {com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID})
public class SecureWebService {

    @ApiMethod(name = "secureEcho", path = "secureEcho")
    public Simple secureEcho(Simple simple, User user) throws
            OAuthRequestException, IOException {
        if (user == null) {
            throw new OAuthRequestException("User not logged. Unable to execure method.");
        }
        simple.setMessage("Current user is " + user.getEmail() + " Message from client: " + simple.getMessage());
        return simple;
    }

}
