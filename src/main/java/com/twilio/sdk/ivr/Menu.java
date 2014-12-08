package com.twilio.sdk.ivr;

import com.google.common.xml.XmlEscapers;
import com.twilio.sdk.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class Menu extends Action {

    public static final String[] order = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "*", "#"};
    public static final String DIGITS = "Digits";
    public static final String DEFAULT_RETRY_MESSAGE = "Sorry, that is not a valid option";
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    protected Map<String, Pair<String, Action>> menu;
    protected String name;
    protected String action;
    protected String method;
    protected String retryMessage;
    protected String preRoll;
    protected Menu parentMenu;

    public Menu(final String name) {
        this(name, null);
    }

    public Menu(final String name, final String action) {
        this.name = name;
        this.action = action;
        method = METHOD_GET;
        retryMessage = DEFAULT_RETRY_MESSAGE;
        menu = new HashMap<>();
        parentMenu = null;
    }

    public Menu register(final String key, final String prompt, final Action action) {
        if (action instanceof Menu) {
            ((Menu) action).setParentMenu(this);
        } else if (action instanceof Back) {
            ((Back) action).setMenu(this);
        }

        menu.put(key, Pair.of(prompt, action));

        return this;
    }

    @Override
    public String execute(final Map<String, String> context) {
        if (!context.containsKey(getKey())) {
            // Attempt to populate the key from the digits
            if (context.containsKey(DIGITS)) {
                String digits = context.remove(DIGITS);
                context.put(getKey(), digits);
            }
        }

        String state = context.get(getKey());

        if (state == null) {
            return render(context);
        }

        if (!menu.containsKey(state)) {
            return retryRender(context);
        }

        return menu.get(state).right.execute(context);
    }

    protected String render(final Map<String, String> context) {
        return render(context, true);
    }

    protected String render(final Map<String, String> context, final boolean standalone) {
        StringBuilder result = new StringBuilder();

        if (standalone) {
            result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            result.append("<Response>\n");
        }

        result.append("\t<Gather numDigits=\"1\" action=\"");
        result.append(getContextualizedAction(context));
        result.append("\" method=\"");
        result.append(getMethod());
        result.append("\">\n");

        if (getPreRoll() != null && !getPreRoll().isEmpty()) {
            result.append("\t\t<Say>");
            result.append(XmlEscapers.xmlContentEscaper()
                                     .escape(getPreRoll()));
            result.append("</Say>\n");
        }

        result.append("\t\t<Say>\n");

        StringBuilder menuText = new StringBuilder();

        for (final String key : order) {
            if (!menu.containsKey(key)) {
                continue;
            }

            Pair<String, Action> pair = menu.get(key);
            String prompt = pair.left;

            if (prompt != null && !prompt.isEmpty()) {
                menuText.append("\t\t\tPress ");
                menuText.append(key);
                menuText.append(" ");
                menuText.append(prompt);
                menuText.append(".\n");
            }
        }

        result.append(XmlEscapers.xmlContentEscaper()
                                 .escape(menuText.toString()));

        result.append("\t\t<Say>\n");

        result.append("\t</Gather>\n");

        if (standalone) {
            result.append("</Response>");
        }

        return result.toString();
    }

    protected String retryRender(final Map<String, String> context) {
        StringBuilder result = new StringBuilder();

        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        result.append("<Response>\n");

        if (getRetryMessage() != null && !getRetryMessage().isEmpty()) {
            result.append("\t<Say>");
            result.append(XmlEscapers.xmlContentEscaper()
                                     .escape(getRetryMessage()));
            result.append("</Say>\n");
        }

        result.append(render(context, false));

        result.append("</Response>");

        return result.toString();
    }

    protected String getKey() {
        return CONTEXT_PREFIX + getName();
    }

    public String getName() {
        if (getParentMenu() == null) {
            return name;
        }

        return getParentMenu().getName() + "." + name;
    }

    public Menu setName(final String name) {
        this.name = name;
        return this;
    }

    public String getContextualizedAction(final Map<String, String> context) {
        StringBuilder builder = new StringBuilder();
        builder.append(getAction());

        if (builder.toString()
                   .contains("?")) {
            builder.append("&");
        } else {
            builder.append("?");
        }

        for (final String key : context.keySet()) {
            if ("Digits".equals(key)) {
                continue;
            }

            if (key.equals(getKey()) && !menu.containsKey(key)) {
                continue;
            }

            builder.append(key);
            builder.append("=");
            builder.append(context.get(key));
            builder.append("&");
        }

        String result = builder.toString();

        if (result.endsWith("&")) {
            result = result.substring(0, result.length() - 1);
        }

        return XmlEscapers.xmlAttributeEscaper()
                          .escape(result);
    }

    public String getAction() {
        if (action == null && getParentMenu() != null) {
            return getParentMenu().getAction();
        }
        return action;
    }

    public Menu setAction(final String action) {
        this.action = action;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public Menu setMethod(final String method) {
        this.method = method;
        return this;
    }

    public String getRetryMessage() {
        return retryMessage;
    }

    public Menu setRetryMessage(final String retryMessage) {
        this.retryMessage = retryMessage;
        return this;
    }

    public String getPreRoll() {
        return preRoll;
    }

    public Menu setPreRoll(final String preRoll) {
        this.preRoll = preRoll;
        return this;
    }

    public Menu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(final Menu parentMenu) {
        this.parentMenu = parentMenu;
    }
}
