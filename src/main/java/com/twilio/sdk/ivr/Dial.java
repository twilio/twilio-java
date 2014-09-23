package com.twilio.sdk.ivr;

import java.util.Map;

public class Dial extends Action {

    protected String number;

    public Dial(String number) {
        this.number = number;
    }

    @Override
    public String execute(Map<String, String> context) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
               "<Response>\n" +
                    "\t<Dial>" + this.number + "</Dial>\n" +
                "</Response>";
    }
}
