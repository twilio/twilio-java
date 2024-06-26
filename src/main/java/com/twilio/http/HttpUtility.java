package com.twilio.http;

import com.twilio.Twilio;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
class HttpUtility {
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
}
