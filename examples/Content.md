public class ContentExamples {
    public static void main {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        createTwilioText();
    }
    
    public static void createTwilioText() {
        Content.ContentCreateRequest createRequest = new Content.ContentCreateRequest("es", types);

        Content.TwilioText twilioText = new Content.TwilioText();
        twilioText.setBody("text body");

        Content.Types types = new Content.Types();
        types.setTwilioText(twilioText);

        Map<String, String> variables = new HashMap<>();
        variables.put("var1", "val1");

        createRequest.setVariables(variables);
        createRequest.setFriendlyName("name");

        Content newContent = Content.creator(createRequest).create();
    }

}