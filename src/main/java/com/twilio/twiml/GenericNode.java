package com.twilio.twiml;


public class GenericNode extends TwiML {
    protected GenericNode(Builder builder) {
        super(builder.tag, builder);
    }

    public static class Builder extends TwiML.Builder<Builder> {
        private String tag;

        public Builder(String tag) {
            this.tag = tag;
        }

        /**
         * Create and return resulting {@code <Response>} element
         */
        public GenericNode build() {
            return new GenericNode(this);
        }
    }
}
