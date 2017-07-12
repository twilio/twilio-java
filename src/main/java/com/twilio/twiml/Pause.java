package com.twilio.twiml;

import com.google.common.collect.Maps;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * TwiML wrapper for @see https://www.twilio.com/docs/api/twiml/pause.
 */
@XmlRootElement(name = "Pause")
public class Pause extends TwiML {

    @XmlAttribute
    private final Integer length;

    @XmlAnyAttribute
    private Map<QName, String> options;

    // For XML Serialization
    private Pause() {
        this(new Builder());
    }

    private Pause(Builder b) {
        this.length = b.length;
        this.options = Maps.newHashMap(b.options);
    }

    public Integer getLength() {
        return length;
    }

    /**
     * Convert options map to string map.
     * 
     * @return Converted options map
     */
    public Map<String, String> getOptions() {
        Map<String, String> convertedMap = new HashMap<>();

        for (Map.Entry<QName, String> entry : options.entrySet()) {
            convertedMap.put(entry.getKey().getNamespaceURI(), entry.getValue());
        }
        return convertedMap;
    }

    public static class Builder {
        private Integer length;
        private Map<QName, String> options = Maps.newHashMap();

        public Builder length(int length) {
            this.length = length;
            return this;
        }

        public Builder options(String key, String value) {
            this.options.put(new QName(key), value);
            return this;
        }

        public Pause build() {
            return new Pause(this);
        }
    }
}
