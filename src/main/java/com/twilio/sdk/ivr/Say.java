package com.twilio.sdk.ivr;

import com.google.common.xml.XmlEscapers;

import java.util.Map;

public class Say extends Action {
    protected String payload;

    public Say(String payload) {
        this.payload = payload;
    }

    @Override
    public String execute(Map<String, String> context) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Response>\n" +
                "\t<Say>" + XmlEscapers.xmlContentEscaper().escape(this.payload) + "</Say>\n" +
                "</Response>";
    }
}
