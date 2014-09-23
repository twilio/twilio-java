package com.twilio.sdk.ivr;

import com.twilio.sdk.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class Menu extends Action {
    public static final String[] order = new String[]
            {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "*", "#"};
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

    public Menu(String name) {
        this(name, null);
    }

    public Menu(String name, String action) {
        this.name = name;
        this.action = action;
        this.method = METHOD_GET;
        this.retryMessage = DEFAULT_RETRY_MESSAGE;
        this.menu = new HashMap<String, Pair<String, Action>>();
        this.parentMenu = null;
    }

    public Menu register(String key, String prompt, Action action) {
        if (action instanceof Menu) {
            ((Menu) action).setParentMenu(this);
        } else if (action instanceof Back) {
            ((Back) action).setMenu(this);
        }


        this.menu.put(key, Pair.of(prompt, action));

        return this;
    }

    @Override
    public String execute(Map<String, String> context) {
        if (!context.containsKey(this.getKey())) {
            // Attempt to populate the key from the digits
            if (context.containsKey(DIGITS)) {
                String digits = context.remove(DIGITS);
                context.put(this.getKey(), digits);
            }
        }

        String state = context.get(this.getKey());

        if (state == null) {
            return render(context);
        }

        if (!menu.containsKey(state)) {
            return retryRender(context);
        }

        return menu.get(state).right.execute(context);
    }

    protected String render(Map<String, String> context) {
        return render(context, true);
    }

    protected String render(Map<String, String> context, boolean standalone) {
        StringBuilder result = new StringBuilder();

        if (standalone) {
            result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            result.append("<Response>\n");
        }

        result.append("\t<Gather numDigits=\"1\" action=\"");
        result.append(this.getContextualizedAction(context));
        result.append("\" method=\"");
        result.append(this.getMethod());
        result.append("\">\n");

        if (this.getPreRoll() != null && !this.getPreRoll().isEmpty()) {
            result.append("\t\t<Say>");
            result.append(this.getPreRoll());
            result.append("</Say>\n");
        }

        result.append("\t\t<Say>\n");

        for (String key : order) {
            if (!menu.containsKey(key)) {
                continue;
            }

            Pair<String, Action> pair = menu.get(key);
            String prompt = pair.left;

            if (prompt != null && !prompt.isEmpty()) {
                result.append("\t\t\tPress ");
                result.append(key);
                result.append(" ");
                result.append(prompt);
                result.append(".\n");
            }
        }

        result.append("\t\t<Say>\n");

        result.append("\t</Gather>\n");

        if (standalone) {
            result.append("</Response>");
        }

        return result.toString();
    }

    protected String retryRender(Map<String, String> context) {
        StringBuilder result = new StringBuilder();

        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        result.append("<Response>\n");

        if (this.getRetryMessage() != null && !this.getRetryMessage().isEmpty()) {
            result.append("\t<Say>");
            result.append(this.getRetryMessage());
            result.append("</Say>\n");
        }

        result.append(render(context, false));

        result.append("</Response>");

        return result.toString();
    }

    protected String getKey() {
        return CONTEXT_PREFIX + this.getName();
    }

    public String getName() {
        if (this.getParentMenu() == null) {
            return name;
        }

        return this.getParentMenu().getName() + "." + name;
    }

    public Menu setName(String name) {
        this.name = name;
        return this;
    }

    public String getContextualizedAction(Map<String, String> context) {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getAction());

        if (builder.toString().contains("?")) {
            builder.append("&");
        } else {
            builder.append("?");
        }

        for (String key : context.keySet()) {
            if ("Digits".equals(key)) {
                continue;
            }

            if (key.equals(this.getKey()) && !menu.containsKey(key)) {
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

        return result;
    }

    public String getAction() {
        if (action == null && this.getParentMenu() != null) {
            return this.getParentMenu().getAction();
        }
        return action;
    }

    public Menu setAction(String action) {
        this.action = action;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public Menu setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getRetryMessage() {
        return retryMessage;
    }

    public Menu setRetryMessage(String retryMessage) {
        this.retryMessage = retryMessage;
        return this;
    }

    public String getPreRoll() {
        return preRoll;
    }

    public Menu setPreRoll(String preRoll) {
        this.preRoll = preRoll;
        return this;
    }

    public Menu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }
}
