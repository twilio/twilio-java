package com.twilio.example;

import com.twilio.Twilio;
import com.twilio.constant.EnumConstants.ContentType;
import com.twilio.http.HttpMethod;
import com.twilio.http.IRequest.FormParmeters;
import com.twilio.http.IRequest.FormParmeters.Type;
import com.twilio.http.NetworkHttpClient;
import com.twilio.http.Request;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SendMessageExample {

    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
    public static void main(String[] args) throws FileNotFoundException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Request request = new Request(HttpMethod.POST,
            "trusthub",
            "/v3/ComplianceRegistrations/:registrationId/UploadComplianceDocument"
        );
        request.setAuth(ACCOUNT_SID, AUTH_TOKEN);
        request.setContentType(ContentType.MULTIPART_FORM_DATA);
        List<FormParmeters> formParmeters = new ArrayList<>();
        formParmeters.add(new FormParmeters("document_content", Type.FILE,"/Users/manisingh/Downloads/image_2.png"));
        formParmeters.add(new FormParmeters("document_requirement_machine_name", Type.TEXT, "[\"business_info\"]"));
        formParmeters.add(new FormParmeters("document_type", Type.TEXT, "business_info"));
        request.setFormParameters(formParmeters);


        System.out.println(request);

        NetworkHttpClient client = new NetworkHttpClient();
        var response = client.makeRequest(request);
        System.out.println(response.getContent());

    }

}
