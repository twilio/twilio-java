package com.twilio.sdk.updaters;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.ConnectApp;
import com.twilio.sdk.resources.RestException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class ConnectAppUpdater extends Updater<ConnectApp> {

    private final String sid;
    private URI authorizeRedirectUrl;
    private String description;
    private String friendlyName;
    private URI deauthorizeCallbackUrl;
    private URI homepageUrl;
    private String companyName;
    private HttpMethod deauthorizeCallbackMethod;
    private List<ConnectApp.Permission> permissions;

    public ConnectAppUpdater(final String sid) {
        this.sid = sid;
    }

    public ConnectAppUpdater(final ConnectApp target) {
        this(target.getSid());
    }

    public ConnectAppUpdater setAuthorizeRedirectUrl(final URI authorizeRedirectUrl) {
        this.authorizeRedirectUrl = authorizeRedirectUrl;
        return this;
    }

    public ConnectAppUpdater setAuthorizeRedirectUrl(final String authorizeRedirectUrl) {
        try {
            this.authorizeRedirectUrl = new URI(authorizeRedirectUrl);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    public ConnectAppUpdater setDescription(final String description) {
        this.description = description;
        return this;
    }

    public ConnectAppUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    public ConnectAppUpdater setDeauthorizeCallbackUrl(final URI deauthorizeCallbackUrl) {
        this.deauthorizeCallbackUrl = deauthorizeCallbackUrl;
        return this;
    }

    public ConnectAppUpdater setDeauthorizeCallbackUrl(final String deauthorizeCallbackUrl) {
        try {
            this.deauthorizeCallbackUrl = new URI(deauthorizeCallbackUrl);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    public ConnectAppUpdater setHomepageUrl(final URI homepageUrl) {
        this.homepageUrl = homepageUrl;
        return this;
    }

    public ConnectAppUpdater setHomepageUrl(final String homepageUrl) {
        try {
            this.homepageUrl = new URI(homepageUrl);
        } catch (final URISyntaxException e) {
            throw new ApiException("Invalid URI", e);
        }
        return this;
    }

    public ConnectAppUpdater setCompanyName(final String companyName) {
        this.companyName = companyName;
        return this;
    }

    public ConnectAppUpdater setDeauthorizeCallbackMethod(final HttpMethod deauthorizeCallbackMethod) {
        this.deauthorizeCallbackMethod = deauthorizeCallbackMethod;
        return this;
    }

    public ConnectAppUpdater setPermissions(final List<ConnectApp.Permission> permissions) {
        this.permissions = permissions;
        return this;
    }

    @Override
    public ConnectApp execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.POST, "/Accounts/{AccountSid}/ConnectApps/" + sid + ".json");
        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("ConnectApp update failed: Unable to connect to server");
        } else if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            throw new ApiException(restException.getMessage(), restException.getCode(), restException.getMoreInfo(),
                                   restException.getStatus(), null);
        }

        return ConnectApp.fromJson(response.getStream(), client.getObjectMapper());
    }

    private void addPostParams(final Request request) {

        if (authorizeRedirectUrl != null) {
            request.addPostParam("AuthorizeRedirectUrl", authorizeRedirectUrl.toString());
        }

        if (description != null) {
            request.addPostParam("Description", description);
        }

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (deauthorizeCallbackUrl != null) {
            request.addPostParam("DeauthorizeCallbackUrl", deauthorizeCallbackUrl.toString());
        }

        if (homepageUrl != null) {
            request.addPostParam("HomepageUrl", homepageUrl.toString());
        }

        if (companyName != null) {
            request.addPostParam("CompanyName", companyName);
        }

        if (deauthorizeCallbackMethod != null) {
            request.addPostParam("DeauthorizeCallbackMethod", deauthorizeCallbackMethod.toString());
        }

        if (permissions != null) {
            request.addPostParam("Permissions", permissions.toString());
        }

    }
}
