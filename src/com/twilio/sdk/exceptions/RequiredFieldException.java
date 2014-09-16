package com.twilio.sdk.exceptions;

import java.util.List;

public class RequiredFieldException extends RuntimeException {
    private List<String> fields;

    public RequiredFieldException(List<String> fields) {
        this.fields = fields;
    }

    public List<String> getFields() {
        return fields;
    }

    public String getFieldMessage() {
        StringBuilder builder = new StringBuilder();

        builder.append("The following fields are required: ");
        for (String field : fields) {
            builder.append(field);
            builder.append(" ");
        }

        return builder.toString().trim();
    }
}
