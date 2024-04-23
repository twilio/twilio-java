class PreviewMessagingExamples {
    public static void main {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        sendBulkMessages();
    }

    private static void sendBulkMessages() {
        Message.CreateMessagesRequest createMessagesRequest = new Message.CreateMessagesRequest();
        List<String> toNumbers; // recipients numbers
        
        // Set from number
        PhoneNumber fromNumber;
        
        // Set list of number where you want to send bulk message.
        List<Message.MessagingV1Message> phoneList = new ArrayList<>();
        for (String to: toNumbers) {
            Message.MessagingV1Message phone = new Message.MessagingV1Message();
            phone.setTo(new PhoneNumber(to));
            phoneList.add(phone);
        }
        
        createMessagesRequest.setFrom(fromNumber);
        createMessagesRequest.setBody("Bulk message to send");
        createMessagesRequest.setMessages(phoneList);
        Message message = Message.creator(createMessagesRequest).create();
    }

}