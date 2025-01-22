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
import com.twilio.twiml.TwiML;
import com.twilio.twiml.TwiMLException;

import java.util.HashMap;
import java.util.Map;

/**
 * TwiML wrapper for {@code <Language>}
 */
@JsonDeserialize(builder = Language.Builder.class)
public class Language extends TwiML {
    private final String code;
    private final String ttsProvider;
    private final String voice;
    private final String transcriptionProvider;
    private final String speechModel;

    /**
     * For XML Serialization/Deserialization
     */
    private Language() {
        this(new Builder());
    }

    /**
     * Create a new {@code <Language>} element
     */
    private Language(Builder b) {
        super("Language", b);
        this.code = b.code;
        this.ttsProvider = b.ttsProvider;
        this.voice = b.voice;
        this.transcriptionProvider = b.transcriptionProvider;
        this.speechModel = b.speechModel;
    }

    /**
     * Attributes to set on the generated XML element
     *
     * @return A Map of attribute keys to values
     */
    protected Map<String, String> getElementAttributes() {
        // Preserve order of attributes
        Map<String, String> attrs = new HashMap<>();

        if (this.getCode() != null) {
            attrs.put("code", this.getCode());
        }
        if (this.getTtsProvider() != null) {
            attrs.put("ttsProvider", this.getTtsProvider());
        }
        if (this.getVoice() != null) {
            attrs.put("voice", this.getVoice());
        }
        if (this.getTranscriptionProvider() != null) {
            attrs.put("transcriptionProvider", this.getTranscriptionProvider());
        }
        if (this.getSpeechModel() != null) {
            attrs.put("speechModel", this.getSpeechModel());
        }

        return attrs;
    }

    /**
     * Language code of this language setting is for
     *
     * @return Language code of this language setting is for
     */
    public String getCode() {
        return code;
    }

    /**
     * Provider to be used for text-to-speech of this language
     *
     * @return Provider to be used for text-to-speech of this language
     */
    public String getTtsProvider() {
        return ttsProvider;
    }

    /**
     * Voice to be used for text-to-speech of this language
     *
     * @return Voice to be used for text-to-speech of this language
     */
    public String getVoice() {
        return voice;
    }

    /**
     * Provider to be used for transcription of this language
     *
     * @return Provider to be used for transcription of this language
     */
    public String getTranscriptionProvider() {
        return transcriptionProvider;
    }

    /**
     * Speech model to be used for transcription of this language
     *
     * @return Speech model to be used for transcription of this language
     */
    public String getSpeechModel() {
        return speechModel;
    }

    /**
     * Create a new {@code <Language>} element
     */
    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder extends TwiML.Builder<Builder> {
        /**
         * Create and return a {@code <Language.Builder>} from an XML string
         */
        public static Builder fromXml(final String xml) throws TwiMLException {
            try {
                return OBJECT_MAPPER.readValue(xml, Builder.class);
            } catch (final JsonProcessingException jpe) {
                throw new TwiMLException(
                    "Failed to deserialize a Language.Builder from the provided XML string: " + jpe.getMessage());
            } catch (final Exception e) {
                throw new TwiMLException("Unhandled exception: " + e.getMessage());
            }
        }

        private String code;
        private String ttsProvider;
        private String voice;
        private String transcriptionProvider;
        private String speechModel;

        /**
         * Language code of this language setting is for
         */
        @JacksonXmlProperty(isAttribute = true, localName = "code")
        public Builder code(String code) {
            this.code = code;
            return this;
        }

        /**
         * Provider to be used for text-to-speech of this language
         */
        @JacksonXmlProperty(isAttribute = true, localName = "ttsProvider")
        public Builder ttsProvider(String ttsProvider) {
            this.ttsProvider = ttsProvider;
            return this;
        }

        /**
         * Voice to be used for text-to-speech of this language
         */
        @JacksonXmlProperty(isAttribute = true, localName = "voice")
        public Builder voice(String voice) {
            this.voice = voice;
            return this;
        }

        /**
         * Provider to be used for transcription of this language
         */
        @JacksonXmlProperty(isAttribute = true, localName = "transcriptionProvider")
        public Builder transcriptionProvider(String transcriptionProvider) {
            this.transcriptionProvider = transcriptionProvider;
            return this;
        }

        /**
         * Speech model to be used for transcription of this language
         */
        @JacksonXmlProperty(isAttribute = true, localName = "speechModel")
        public Builder speechModel(String speechModel) {
            this.speechModel = speechModel;
            return this;
        }

        /**
         * Create and return resulting {@code <Language>} element
         */
        public Language build() {
            return new Language(this);
        }
    }
}