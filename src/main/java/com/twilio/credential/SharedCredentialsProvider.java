package com.twilio.credential;

import com.twilio.exception.AuthenticationException;
import org.apache.commons.configuration2.*;

import java.io.FileReader;


public class SharedCredentialsProvider implements TwilioCredentialsProvider {

    String configurationFile;
    String section;

    public SharedCredentialsProvider() {

        this.configurationFile = System.getProperty("user.home") + "/.twilio/credentials";
        this.section = "default";

    }

    public SharedCredentialsProvider(String configurationFile, String section ) {

        this.configurationFile = configurationFile;
        this.section = section;

    }

    @Override
    public TwilioCredentials getCredentials() {

        String accountSid = "";
        String authToken  = "";

        try {
            INIConfiguration configuration = new INIConfiguration();
            configuration.read(new FileReader(this.configurationFile));
            SubnodeConfiguration section = configuration.getSection(this.section);

            accountSid = section.getString("accountSid");
            authToken = section.getString("authToken");

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (accountSid.isEmpty() || authToken.isEmpty()) {
            throw new AuthenticationException("Unable to set Credentials from " + this.configurationFile);
        }
        return new BasicTwilioCredentials(accountSid, authToken);
    }

    private static String validateFilePath(String filePath) {
        if (filePath == null) {
            throw new IllegalArgumentException(
                    "Unable to load profiles: specified file path " + filePath + " is null.");
        }
        return filePath;
    }

    @Override
    public void refresh() {

    }

}
