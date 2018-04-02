package com.twilio.credential;

import com.twilio.exception.AuthenticationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.LinkedList;
import java.util.List;

public class TwilioCredentialsProviderChain implements TwilioCredentialsProvider {

    private static final Log log = LogFactory.getLog(TwilioCredentialsProviderChain.class);

    private final List<TwilioCredentialsProvider> credentialsProviders = new LinkedList<>();

    public TwilioCredentialsProviderChain(List<? extends TwilioCredentialsProvider> credentialsProviders) {
        if (credentialsProviders == null || credentialsProviders.size() == 0) {
            throw new IllegalArgumentException("No credential providers specified");
        }
        this.credentialsProviders.addAll(credentialsProviders);
    }

    public TwilioCredentialsProviderChain(TwilioCredentialsProvider... credentialsProviders) {
        if (credentialsProviders == null || credentialsProviders.length == 0) {
            throw new IllegalArgumentException("No credential providers specified");
        }

        for (TwilioCredentialsProvider provider : credentialsProviders) {
            this.credentialsProviders.add(provider);
        }
    }

    @Override
    public TwilioCredentials getCredentials() {

        for (TwilioCredentialsProvider provider : credentialsProviders) {
            try {
                TwilioCredentials credentials = provider.getCredentials();

                if (credentials.getAccountSid() != null && credentials.getAuthToken() != null) {
                    log.debug("Loading credentials from " + provider.toString());
                    return credentials;
                }
            } catch (Exception e) {
                // Ignore any exceptions and move onto the next provider
                log.debug("Unable to load credentials from " + provider.toString() +
                        ": " + e.getMessage());
            }
        }

        throw new AuthenticationException("Unable to load Twillio credentials from any provider in the chain");

    }

    @Override
    public void refresh() {
        for (TwilioCredentialsProvider provider : credentialsProviders) {
            provider.refresh();
        }
    }

}
