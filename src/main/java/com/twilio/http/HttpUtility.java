package com.twilio.http;

import com.twilio.Twilio;
import com.twilio.exception.InvalidRequestException;
import lombok.experimental.UtilityClass;

import java.net.URI;
import java.util.List;

@UtilityClass
public class HttpUtility {
    public String getUserAgentString(final List<String> userAgentExtensions) {
        StringBuilder userAgentString = new StringBuilder();
        userAgentString.append("twilio-java/")
                .append(Twilio.VERSION)
                .append(" (")
                .append(Twilio.OS_NAME)
                .append(" ")
                .append(Twilio.OS_ARCH)
                .append(") ")
                .append("java/")
                .append(Twilio.JAVA_VERSION);

        if (userAgentExtensions != null && !userAgentExtensions.isEmpty()) {
            userAgentExtensions.stream().forEach(userAgentExtension -> {
                userAgentString.append(" ");
                userAgentString.append(userAgentExtension);
            });
        }

        return userAgentString.toString();
    }

    public String getUserAgentString(final List<String> userAgentExtensions, final boolean isCustomClient) {
        return isCustomClient ? getUserAgentString(userAgentExtensions) + " custom"
                : getUserAgentString(userAgentExtensions);
    }

    /**
     * Validates that a URL is from a trusted Twilio domain.
     * This prevents SSRF attacks by ensuring credentials are only sent to Twilio-owned domains.
     *
     * @param url the URL to validate
     * @return true if the URL is from a Twilio domain, false otherwise
     */
    public boolean isValidTwilioUrl(final String url) {
        if (url == null || url.isEmpty()) {
            return false;
        }
        try {
            URI uri = new URI(url);
            String host = uri.getHost();
            if (host == null) {
                return false;
            }
            host = host.toLowerCase();
            // Allow only Twilio domains to prevent credential leakage
            return host.equals("twilio.com") || host.endsWith(".twilio.com");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validates a path parameter so it always stays a single URL path segment.
     * Rejects URL-structural characters ("/", "?", "#") and dot segments that could
     * redirect an SDK call to a different endpoint (path-parameter injection).
     * Other characters are percent-encoded later by {@link Request#buildURL()}.
     *
     * @param value the raw path parameter
     * @return the same value, if valid
     * @throws InvalidRequestException if the value is null, empty, a dot segment or contains "/", "?" or "#"
     */
    public String validatePathParam(final String value) {
        if (value == null || value.isEmpty() || value.equals(".") || value.equals("..")
                || value.indexOf('/') >= 0 || value.indexOf('?') >= 0 || value.indexOf('#') >= 0) {
            throw new InvalidRequestException("Invalid path parameter: must not be empty, '.', '..' or contain '/', '?', '#'", value);
        }
        return value;
    }
}
