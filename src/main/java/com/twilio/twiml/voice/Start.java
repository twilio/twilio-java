/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.twiml.voice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.twilio.converter.Promoter;
import com.twilio.http.HttpMethod;
import com.twilio.twiml.TwiML;
import com.twilio.twiml.TwiMLException;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * TwiML wrapper for {@code <Start>}
 */
@JsonDeserialize(builder = Start.Builder.class)
public class Start extends TwiML {
    private final URI action;
    private final HttpMethod method;

    /**
     * For XML Serialization/Deserialization
     */
    private Start() {
        this(new Builder());
    }

    /**
     * Create a new {@code <Start>} element
     */
    private Start(Builder b) {
        super("Start", b);
        this.action = b.action;
        this.method = b.method;
    }

    /**
     * Attributes to set on the generated XML element
     *
     * @return A Map of attribute keys to values
     */
    protected Map<String, String> getElementAttributes() {
        // Preserve order of attributes
        Map<String, String> attrs = new HashMap<>();

        if (this.getAction() != null) {
            attrs.put("action", this.getAction().toString());
        }
        if (this.getMethod() != null) {
            attrs.put("method", this.getMethod().toString());
        }

        return attrs;
    }

    /**
     * Action URL
     *
     * @return Action URL
     */
    public URI getAction() {
        return action;
    }

    /**
     * Action URL method
     *
     * @return Action URL method
     */
    public HttpMethod getMethod() {
        return method;
    }

    /**
     * Create a new {@code <Start>} element
     */
    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder extends TwiML.Builder<Builder> {
        /**
         * Create and return a {@code <Start.Builder>} from an XML string
         */
        public static Builder fromXml(final String xml) throws TwiMLException {
            try {
                return OBJECT_MAPPER.readValue(xml, Builder.class);
            } catch (final JsonProcessingException jpe) {
                throw new TwiMLException(
                    "Failed to deserialize a Start.Builder from the provided XML string: " + jpe.getMessage());
            } catch (final Exception e) {
                throw new TwiMLException("Unhandled exception: " + e.getMessage());
            }
        }

        private URI action;
        private HttpMethod method;

        /**
         * Action URL
         */
        @JacksonXmlProperty(isAttribute = true, localName = "action")
        public Builder action(URI action) {
            this.action = action;
            return this;
        }

        /**
         * Action URL
         */
        public Builder action(String action) {
            this.action = Promoter.uriFromString(action);
            return this;
        }

        /**
         * Action URL method
         */
        @JacksonXmlProperty(isAttribute = true, localName = "method")
        public Builder method(HttpMethod method) {
            this.method = method;
            return this;
        }

        /**
         * Add a child {@code <Stream>} element
         */
        @JacksonXmlProperty(isAttribute = false, localName = "Stream")
        public Builder stream(Stream stream) {
            this.children.add(stream);
            return this;
        }

        /**
         * Add a child {@code <Siprec>} element
         */
        @JacksonXmlProperty(isAttribute = false, localName = "Siprec")
        public Builder siprec(Siprec siprec) {
            this.children.add(siprec);
            return this;
        }

        /**
         * Add a child {@code <Transcription>} element
         */
        @JacksonXmlProperty(isAttribute = false, localName = "Transcription")
        public Builder transcription(Transcription transcription) {
            this.children.add(transcription);
            return this;
        }

        /**
         * Create and return resulting {@code <Start>} element
         */
        public Start build() {
            return new Start(this);
        }
    }
}