package com.twilio.http;

import com.twilio.Twilio;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
class HttpUtility {
    String getUserAgentString(final List<String>... userAgentExtensions) {
        StringBuilder userAgentString = new StringBuilder();
        userAgentString.append("twilio-java/")
                .append(Twilio.VERSION)
                .append(" (")
                .append(Twilio.OS_NAME)
                .append(Twilio.OS_ARCH)
                .append(") ")
                .append("java/")
                .append(Twilio.JAVA_VERSION);

        if (userAgentExtensions.length > 0 && userAgentExtensions[0] != null && !userAgentExtensions[0].isEmpty()) {
            List<String> userAgentStringCopy = new ArrayList<>(userAgentExtensions[0]);
            userAgentStringCopy.stream().forEach(userAgentExtension -> {
                userAgentString.append(" ");
                userAgentString.append(userAgentExtension);
            });
        }

        return userAgentString.toString();
    }
}
