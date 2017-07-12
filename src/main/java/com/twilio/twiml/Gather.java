package com.twilio.twiml;

import com.google.common.collect.Lists;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/gather.
 */
@XmlRootElement(name = "Gather")
public class Gather extends TwiML {

    @XmlAttribute
    private final Integer timeout;

    @XmlAttribute
    private final Integer numDigits;

    @XmlAttribute
    private final String action;

    @XmlAttribute
    private final Method method;

    @XmlAttribute
    private final String finishOnKey;

    @XmlAttribute
    private final String partialResultCallback;

    @XmlAttribute
    private final Method partialResultCallbackMethod;

    @XmlAttribute
    private final Language language;

    @XmlAttribute
    private final String hints;

    @XmlAttribute
    private final Boolean bargeIn;

    @XmlAttribute
    private final String acknowledgeSoundUrl;

    @XmlAttribute
    private final String input;

    @SuppressWarnings("checkstyle:indentation")
    @XmlElements({
        @XmlElement(name = "Say", type = Say.class),
        @XmlElement(name = "Play", type = Play.class),
        @XmlElement(name = "Pause", type = Pause.class)
    })
    private final List<TwiML> actions;

    // For XML Serialization
    private Gather() {
        this(new Builder());
    }

    private Gather(Builder b) {
        this.timeout = b.timeout;
        this.numDigits = b.numDigits;
        this.action = b.action;
        this.method = b.method;
        this.finishOnKey = b.finishOnKey;
        this.actions = Lists.newArrayList(b.actions);
        this.partialResultCallback = b.partialResultCallback;
        this.partialResultCallbackMethod = b.partialResultCallbackMethod;
        this.language = b.language;
        this.hints = b.hints;
        this.bargeIn = b.bargeIn;
        this.acknowledgeSoundUrl = b.acknowledgeSoundUrl;
        this.input = b.input;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public Integer getNumDigits() {
        return numDigits;
    }

    public String getAction() {
        return action;
    }

    public Method getMethod() {
        return method;
    }

    public String getFinishOnKey() {
        return finishOnKey;
    }

    public List<TwiML> getActions() {
        return actions;
    }

    public String getPartialResultCallback() {
        return partialResultCallback;
    }

    public Method getPartialResultCallbackMethod() {
        return partialResultCallbackMethod;
    }

    public Language getLanguage() {
        return language;
    }

    public String getHints() {
        return hints;
    }

    public Boolean getBargeIn() {
        return bargeIn;
    }

    public String getAcknowledgeSoundUrl() {
        return acknowledgeSoundUrl;
    }

    public String getInput() {
        return input;
    }

    public static class Builder {
        private Integer timeout;
        private Integer numDigits;
        private String action;
        private Method method;
        private String finishOnKey;
        private List<TwiML> actions = Lists.newArrayList();
        private String partialResultCallback;
        private Method partialResultCallbackMethod;
        private Language language;
        private String hints;
        private Boolean bargeIn;
        private String acknowledgeSoundUrl;
        private String input;

        public Builder timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder numDigits(int numDigits) {
            this.numDigits = numDigits;
            return this;
        }

        public Builder action(String action) {
            this.action = action;
            return this;
        }

        public Builder method(Method method) {
            this.method = method;
            return this;
        }

        public Builder finishOnKey(String finishOnKey) {
            this.finishOnKey = finishOnKey;
            return this;
        }

        public Builder say(Say say) {
            this.actions.add(say);
            return this;
        }

        public Builder play(Play play) {
            this.actions.add(play);
            return this;
        }

        public Builder pause(Pause pause) {
            this.actions.add(pause);
            return this;
        }

        public Builder partialResultCallback(String partialResultCallback) {
            this.partialResultCallback = partialResultCallback;
            return this;
        }

        public Builder partialResultCallbackMethod(Method partialResultCallbackMethod) {
            this.partialResultCallbackMethod = partialResultCallbackMethod;
            return this;
        }

        public Builder language(Language language) {
            this.language = language;
            return this;
        }

        public Builder hints(String hints) {
            this.hints = hints;
            return this;
        }

        public Builder bargeIn(Boolean bargeIn) {
            this.bargeIn = bargeIn;
            return this;
        }

        public Builder acknowledgeSoundUrl(String acknowledgeSoundUrl) {
            this.acknowledgeSoundUrl = acknowledgeSoundUrl;
            return this;
        }

        public Builder input(String input) {
            this.input = input;
            return this;
        }

        public Gather build() {
            return new Gather(this);
        }
    }
}
