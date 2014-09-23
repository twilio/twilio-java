package com.twilio.sdk.ivr;

import com.google.common.xml.XmlEscapers;

import java.util.Map;

public class Back extends Action {
    protected Menu menu;

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String execute(Map<String, String> context) {
        context.remove(menu.getKey());

        String longestKey = "";
        for (String key : context.keySet()) {
            if (!key.startsWith(CONTEXT_PREFIX)) {
                continue;
            }

            if (longestKey.length() < key.length()) {
                longestKey = key;
            }
        }

        context.remove(longestKey);

        String payload = menu.getContextualizedAction(context);

        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Response>\n" +
                "\t<Redirect>" + XmlEscapers.xmlContentEscaper().escape(payload) + "</Redirect>\n" +
                "</Response>";
    }


}
