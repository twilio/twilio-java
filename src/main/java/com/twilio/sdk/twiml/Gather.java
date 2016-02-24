package com.twilio.sdk.twiml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement
public class Gather extends TwiML {

    @JacksonXmlProperty(isAttribute = true)
    private final int timeout;

    @JacksonXmlProperty(isAttribute = true)
    private final int numDigits;

    @JacksonXmlProperty(isAttribute = true)
    private final String action;

    @JacksonXmlProperty(isAttribute = true)
    private final String method;

    @JacksonXmlProperty(isAttribute = true)
    private final String finishOnKey;

    @JacksonXmlProperty(localName = "Say")
    private final Say say;

    @JacksonXmlProperty(localName = "Play")
    private final Play play;

    @JacksonXmlProperty(localName = "Pause")
    private final Pause pause;

    private Gather(
        int timeout,
        int numDigits,
        String action,
        String method,
        String finishOnKey,
        Say say,
        Play play,
        Pause pause
    ) {
        this.timeout = timeout;
        this.numDigits = numDigits;
        this.action = action;
        this.method = method;
        this.finishOnKey = finishOnKey;
        this.say = say;
        this.play = play;
        this.pause = pause;
    }

    public int getTimeout() {
        return timeout;
    }

    public int getNumDigits() {
        return numDigits;
    }

    public String getAction() {
        return action;
    }

    public String getMethod() {
        return method;
    }

    public String getFinishOnKey() {
        return finishOnKey;
    }

    public Say getSay() {
        return say;
    }

    public Play getPlay() {
        return play;
    }

    public Pause getPause() {
        return pause;
    }

    public static class Builder {
        private int timeout;
        private int numDigits;
        private String action;
        private String method;
        private String finishOnKey;
        private Say say;
        private Play play;
        private Pause pause;

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

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder finishOnKey(String finishOnKey) {
            this.finishOnKey = finishOnKey;
            return this;
        }

        public Builder say(Say say) {
            this.say = say;
            return this;
        }

        public Builder play(Play play) {
            this.play = play;
            return this;
        }

        public Builder pause(Pause pause) {
            this.pause = pause;
            return this;
        }

        public Gather build() {
            return new Gather(timeout, numDigits, action, method, finishOnKey, say, play, pause);
        }
    }
}
