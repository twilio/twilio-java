package com.twilio.example;

import com.twilio.Twilio;
import com.twilio.constant.EnumConstants.ContentType;
import com.twilio.http.HttpMethod;
import com.twilio.http.IRequest.FormParmeters;
import com.twilio.http.IRequest.FormParmeters.Type;
import com.twilio.http.NetworkHttpClient;
import com.twilio.http.Request;
import com.twilio.rest.trusthub.v3.UploadComplianceDocument;
import com.twilio.rest.trusthub.v3.UploadComplianceDocumentCreator;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SendMessageExample {

    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
    public static void main(String[] args) throws FileNotFoundException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        UploadComplianceDocumentCreator uploadComplianceDocumentCreator = new UploadComplianceDocumentCreator("tri1.dev-us1.account..registration.");
        uploadComplianceDocumentCreator.setDocumentContent(new java.io.File("/Users/manisingh/Downloads/png.png"));
        uploadComplianceDocumentCreator.setDocumentRequirementMachineName("business_info");
        uploadComplianceDocumentCreator.setDocumentType("business_registration");

        uploadComplianceDocumentCreator.create();

    }

}
