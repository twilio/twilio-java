/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.twiml.voice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.twilio.converter.Promoter;
import com.twilio.http.HttpMethod;
import com.twilio.twiml.TwiML;
import com.twilio.twiml.TwiMLException;

import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * TwiML wrapper for {@code <Sip>}
 */
@JsonDeserialize(builder = Sip.Builder.class)
public class Sip extends TwiML {
    public enum Event {
        INITIATED("initiated"),
        RINGING("ringing"),
        ANSWERED("answered"),
        COMPLETED("completed");

        private final String value;

        private Event(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }
    }

    private final String username;
    private final String password;
    private final URI url;
    private final HttpMethod method;
    private final List<Sip.Event> statusCallbackEvent;
    private final URI statusCallback;
    private final HttpMethod statusCallbackMethod;
    private final URI sipUrl;

    /**
     * For XML Serialization/Deserialization
     */
    private Sip() {
        this(new Builder((URI) null));
    }

    /**
     * Create a new {@code <Sip>} element
     */
    private Sip(Builder b) {
        super("Sip", b);
        this.username = b.username;
        this.password = b.password;
        this.url = b.url;
        this.method = b.method;
        this.statusCallbackEvent = b.statusCallbackEvent;
        this.statusCallback = b.statusCallback;
        this.statusCallbackMethod = b.statusCallbackMethod;
        this.sipUrl = b.sipUrl;
    }

    /**
     * The body of the TwiML element
     *
     * @return Element body as a string if present else null
     */
    protected String getElementBody() {
        return this.getSipUrl() == null ? null : this.getSipUrl().toString();
    }

    /**
     * Attributes to set on the generated XML element
     *
     * @return A Map of attribute keys to values
     */
    protected Map<String, String> getElementAttributes() {
        // Preserve order of attributes
        Map<String, String> attrs = new HashMap<>();

        if (this.getUsername() != null) {
            attrs.put("username", this.getUsername());
        }
        if (this.getPassword() != null) {
            attrs.put("password", this.getPassword());
        }
        if (this.getUrl() != null) {
            attrs.put("url", this.getUrl().toString());
        }
        if (this.getMethod() != null) {
            attrs.put("method", this.getMethod().toString());
        }
        if (this.getStatusCallbackEvents() != null) {
            attrs.put("statusCallbackEvent", this.getStatusCallbackEventsAsString());
        }
        if (this.getStatusCallback() != null) {
            attrs.put("statusCallback", this.getStatusCallback().toString());
        }
        if (this.getStatusCallbackMethod() != null) {
            attrs.put("statusCallbackMethod", this.getStatusCallbackMethod().toString());
        }

        return attrs;
    }

    /**
     * SIP Username
     *
     * @return SIP Username
     */
    public String getUsername() {
        return username;
    }

    /**
     * SIP Password
     *
     * @return SIP Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Action URL
     *
     * @return Action URL
     */
    public URI getUrl() {
        return url;
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
     * Status callback events
     *
     * @return Status callback events
     */
    public List<Sip.Event> getStatusCallbackEvents() {
        return statusCallbackEvent;
    }

    protected String getStatusCallbackEventsAsString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Sip.Event> iter = this.getStatusCallbackEvents().iterator();
        while (iter.hasNext()) {
            sb.append(iter.next().toString());
            if (iter.hasNext()) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    /**
     * Status callback URL
     *
     * @return Status callback URL
     */
    public URI getStatusCallback() {
        return statusCallback;
    }

    /**
     * Status callback URL method
     *
     * @return Status callback URL method
     */
    public HttpMethod getStatusCallbackMethod() {
        return statusCallbackMethod;
    }

    /**
     * SIP URL
     *
     * @return SIP URL
     */
    public URI getSipUrl() {
        return sipUrl;
    }

    /**
     * Create a new {@code <Sip>} element
     */
    public static class Builder extends TwiML.Builder<Builder> {
        /**
         * Create and return a {@code <Sip.Builder>} from an XML string
         */
        public static Builder fromXml(final String xml) throws TwiMLException {
            try {
                return OBJECT_MAPPER.readValue(xml, Builder.class);
            } catch (final JsonProcessingException jpe) {
                throw new TwiMLException(
                    "Failed to deserialize a Sip.Builder from the provided XML string: " + jpe.getMessage());
            } catch (final Exception e) {
                throw new TwiMLException("Unhandled exception: " + e.getMessage());
            }
        }

        private String username;
        private String password;
        private URI url;
        private HttpMethod method;
        private List<Sip.Event> statusCallbackEvent;
        private URI statusCallback;
        private HttpMethod statusCallbackMethod;
        private URI sipUrl;

        /**
         * Create a {@code <Sip>} with sipUrl
         */
        public Builder(URI sipUrl) {
            this.sipUrl = sipUrl;
        }

        /**
         * Create a {@code <Sip>} with sipUrl
         */
        public Builder(String sipUrl) {
            this.sipUrl = Promoter.uriFromString(sipUrl);
        }

        /**
         * Create a {@code <Sip>} (for XML deserialization)
         */
        private Builder() {
        }

        /**
         * SIP Username
         */
        @JacksonXmlProperty(isAttribute = true, localName = "username")
        public Builder username(String username) {
            this.username = username;
            return this;
        }

        /**
         * SIP Password
         */
        @JacksonXmlProperty(isAttribute = true, localName = "password")
        public Builder password(String password) {
            this.password = password;
            return this;
        }

        /**
         * Action URL
         */
        @JacksonXmlProperty(isAttribute = true, localName = "url")
        public Builder url(URI url) {
            this.url = url;
            return this;
        }

        /**
         * Action URL
         */
        public Builder url(String url) {
            this.url = Promoter.uriFromString(url);
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
         * Status callback events
         */
        @JacksonXmlProperty(isAttribute = true, localName = "statusCallbackEvent")
        public Builder statusCallbackEvents(List<Sip.Event> statusCallbackEvent) {
            this.statusCallbackEvent = statusCallbackEvent;
            return this;
        }

        /**
         * Status callback events
         */
        public Builder statusCallbackEvents(Sip.Event statusCallbackEvent) {
            this.statusCallbackEvent = Promoter.listOfOne(statusCallbackEvent);
            return this;
        }

        /**
         * Status callback URL
         */
        @JacksonXmlProperty(isAttribute = true, localName = "statusCallback")
        public Builder statusCallback(URI statusCallback) {
            this.statusCallback = statusCallback;
            return this;
        }

        /**
         * Status callback URL
         */
        public Builder statusCallback(String statusCallback) {
            this.statusCallback = Promoter.uriFromString(statusCallback);
            return this;
        }

        /**
         * Status callback URL method
         */
        @JacksonXmlProperty(isAttribute = true, localName = "statusCallbackMethod")
        public Builder statusCallbackMethod(HttpMethod statusCallbackMethod) {
            this.statusCallbackMethod = statusCallbackMethod;
            return this;
        }

        /**
         * Create and return resulting {@code <Sip>} element
         */
        public Sip build() {
            return new Sip(this);
        }
    }
}