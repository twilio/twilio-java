/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.twiml.voice;

import com.twilio.converter.Promoter;
import com.twilio.http.HttpMethod;
import com.twilio.twiml.TwiML;

import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * TwiML wrapper for {@code <Gather>}
 */
public class Gather extends TwiML {
    public enum Input {
        DTMF("dtmf"),
        SPEECH("speech");

        private final String value;

        private Input(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }
    }

    public enum Language {
        AF_ZA("af-ZA"),
        AM_ET("am-ET"),
        AR_AE("ar-AE"),
        AR_BH("ar-BH"),
        AR_DZ("ar-DZ"),
        AR_EG("ar-EG"),
        AR_IL("ar-IL"),
        AR_IQ("ar-IQ"),
        AR_JO("ar-JO"),
        AR_KW("ar-KW"),
        AR_LB("ar-LB"),
        AR_MA("ar-MA"),
        AR_OM("ar-OM"),
        AR_PS("ar-PS"),
        AR_QA("ar-QA"),
        AR_SA("ar-SA"),
        AR_TN("ar-TN"),
        AZ_AZ("az-AZ"),
        BG_BG("bg-BG"),
        BN_BD("bn-BD"),
        BN_IN("bn-IN"),
        CA_ES("ca-ES"),
        CS_CZ("cs-CZ"),
        DA_DK("da-DK"),
        DE_DE("de-DE"),
        EL_GR("el-GR"),
        EN_AU("en-AU"),
        EN_CA("en-CA"),
        EN_GB("en-GB"),
        EN_GH("en-GH"),
        EN_IE("en-IE"),
        EN_IN("en-IN"),
        EN_KE("en-KE"),
        EN_NG("en-NG"),
        EN_NZ("en-NZ"),
        EN_PH("en-PH"),
        EN_SG("en-SG"),
        EN_TZ("en-TZ"),
        EN_US("en-US"),
        EN_ZA("en-ZA"),
        ES_AR("es-AR"),
        ES_BO("es-BO"),
        ES_CL("es-CL"),
        ES_CO("es-CO"),
        ES_CR("es-CR"),
        ES_DO("es-DO"),
        ES_EC("es-EC"),
        ES_ES("es-ES"),
        ES_GT("es-GT"),
        ES_HN("es-HN"),
        ES_MX("es-MX"),
        ES_NI("es-NI"),
        ES_PA("es-PA"),
        ES_PE("es-PE"),
        ES_PR("es-PR"),
        ES_PY("es-PY"),
        ES_SV("es-SV"),
        ES_US("es-US"),
        ES_UY("es-UY"),
        ES_VE("es-VE"),
        ET_EE("et-EE"),
        EU_ES("eu-ES"),
        FA_IR("fa-IR"),
        FI_FI("fi-FI"),
        FIL_PH("fil-PH"),
        FR_CA("fr-CA"),
        FR_FR("fr-FR"),
        GL_ES("gl-ES"),
        GU_IN("gu-IN"),
        HE_IL("he-IL"),
        HI_IN("hi-IN"),
        HR_HR("hr-HR"),
        HU_HU("hu-HU"),
        HY_AM("hy-AM"),
        ID_ID("id-ID"),
        IS_IS("is-IS"),
        IT_IT("it-IT"),
        JA_JP("ja-JP"),
        JV_ID("jv-ID"),
        KA_GE("ka-GE"),
        KM_KH("km-KH"),
        KN_IN("kn-IN"),
        KO_KR("ko-KR"),
        LO_LA("lo-LA"),
        LT_LT("lt-LT"),
        LV_LV("lv-LV"),
        MK_MK("mk-MK"),
        ML_IN("ml-IN"),
        MN_MN("mn-MN"),
        MR_IN("mr-IN"),
        MS_MY("ms-MY"),
        MY_MM("my-MM"),
        NAR_IQ("nar-IQ"),
        NB_NO("nb-NO"),
        NE_NP("ne-NP"),
        NL_BE("nl-BE"),
        NL_NL("nl-NL"),
        PA_GURU_IN("pa-guru-IN"),
        PL_PL("pl-PL"),
        PT_BR("pt-BR"),
        PT_PT("pt-PT"),
        RO_RO("ro-RO"),
        RU_RU("ru-RU"),
        SI_LK("si-LK"),
        SK_SK("sk-SK"),
        SL_SI("sl-SI"),
        SQ_AL("sq-AL"),
        SR_RS("sr-RS"),
        SU_ID("su-ID"),
        SV_SE("sv-SE"),
        SW_KE("sw-KE"),
        SW_TZ("sw-TZ"),
        TA_IN("ta-IN"),
        TA_LK("ta-LK"),
        TA_MY("ta-MY"),
        TA_SG("ta-SG"),
        TE_IN("te-IN"),
        TH_TH("th-TH"),
        TR_TR("tr-TR"),
        UK_UA("uk-UA"),
        UR_IN("ur-IN"),
        UR_PK("ur-PK"),
        UZ_UZ("uz-UZ"),
        VI_VN("vi-VN"),
        YUE_HANT_HK("yue-Hant-HK"),
        ZH("zh"),
        CMN_HANS_CN("cmn-Hans-CN"),
        ZH_TW("zh-TW"),
        CMN_HANT_TW("cmn-Hant-TW"),
        ZU_ZA("zu-ZA");

        private final String value;

        private Language(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }
    }

    public enum SpeechModel {
        DEFAULT("default"),
        NUMBERS_AND_COMMANDS("numbers_and_commands"),
        PHONE_CALL("phone_call");

        private final String value;

        private SpeechModel(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }
    }

    private final List<Gather.Input> input;
    private final URI action;
    private final HttpMethod method;
    private final Integer timeout;
    private final String speechTimeout;
    private final Integer maxSpeechTime;
    private final Boolean profanityFilter;
    private final String finishOnKey;
    private final Integer numDigits;
    private final URI partialResultCallback;
    private final HttpMethod partialResultCallbackMethod;
    private final Gather.Language language;
    private final String hints;
    private final Boolean bargeIn;
    private final Boolean debug;
    private final Boolean actionOnEmptyResult;
    private final Gather.SpeechModel speechModel;
    private final Boolean enhanced;

    /**
     * For XML Serialization/Deserialization
     */
    private Gather() {
        this(new Builder());
    }

    /**
     * Create a new {@code <Gather>} element
     */
    private Gather(Builder b) {
        super("Gather", b);
        this.input = b.input;
        this.action = b.action;
        this.method = b.method;
        this.timeout = b.timeout;
        this.speechTimeout = b.speechTimeout;
        this.maxSpeechTime = b.maxSpeechTime;
        this.profanityFilter = b.profanityFilter;
        this.finishOnKey = b.finishOnKey;
        this.numDigits = b.numDigits;
        this.partialResultCallback = b.partialResultCallback;
        this.partialResultCallbackMethod = b.partialResultCallbackMethod;
        this.language = b.language;
        this.hints = b.hints;
        this.bargeIn = b.bargeIn;
        this.debug = b.debug;
        this.actionOnEmptyResult = b.actionOnEmptyResult;
        this.speechModel = b.speechModel;
        this.enhanced = b.enhanced;
    }

    /**
     * Attributes to set on the generated XML element
     *
     * @return A Map of attribute keys to values
     */
    protected Map<String, String> getElementAttributes() {
        // Preserve order of attributes
        Map<String, String> attrs = new HashMap<>();

        if (this.getInputs() != null) {
            attrs.put("input", this.getInputsAsString());
        }
        if (this.getAction() != null) {
            attrs.put("action", this.getAction().toString());
        }
        if (this.getMethod() != null) {
            attrs.put("method", this.getMethod().toString());
        }
        if (this.getTimeout() != null) {
            attrs.put("timeout", this.getTimeout().toString());
        }
        if (this.getSpeechTimeout() != null) {
            attrs.put("speechTimeout", this.getSpeechTimeout());
        }
        if (this.getMaxSpeechTime() != null) {
            attrs.put("maxSpeechTime", this.getMaxSpeechTime().toString());
        }
        if (this.isProfanityFilter() != null) {
            attrs.put("profanityFilter", this.isProfanityFilter().toString());
        }
        if (this.getFinishOnKey() != null) {
            attrs.put("finishOnKey", this.getFinishOnKey());
        }
        if (this.getNumDigits() != null) {
            attrs.put("numDigits", this.getNumDigits().toString());
        }
        if (this.getPartialResultCallback() != null) {
            attrs.put("partialResultCallback", this.getPartialResultCallback().toString());
        }
        if (this.getPartialResultCallbackMethod() != null) {
            attrs.put("partialResultCallbackMethod", this.getPartialResultCallbackMethod().toString());
        }
        if (this.getLanguage() != null) {
            attrs.put("language", this.getLanguage().toString());
        }
        if (this.getHints() != null) {
            attrs.put("hints", this.getHints());
        }
        if (this.isBargeIn() != null) {
            attrs.put("bargeIn", this.isBargeIn().toString());
        }
        if (this.isDebug() != null) {
            attrs.put("debug", this.isDebug().toString());
        }
        if (this.isActionOnEmptyResult() != null) {
            attrs.put("actionOnEmptyResult", this.isActionOnEmptyResult().toString());
        }
        if (this.getSpeechModel() != null) {
            attrs.put("speechModel", this.getSpeechModel().toString());
        }
        if (this.isEnhanced() != null) {
            attrs.put("enhanced", this.isEnhanced().toString());
        }

        return attrs;
    }

    /**
     * Input type Twilio should accept
     *
     * @return Input type Twilio should accept
     */
    public List<Gather.Input> getInputs() {
        return input;
    }

    protected String getInputsAsString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Gather.Input> iter = this.getInputs().iterator();
        while (iter.hasNext()) {
            sb.append(iter.next().toString());
            if (iter.hasNext()) {
                sb.append(" ");
            }
        }
        return sb.toString();
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
     * Time to wait to gather input
     *
     * @return Time to wait to gather input
     */
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * Time to wait to gather speech input and it should be either auto or a
     * positive integer.
     *
     * @return Time to wait to gather speech input and it should be either auto or
     *         a positive integer.
     */
    public String getSpeechTimeout() {
        return speechTimeout;
    }

    /**
     * Max allowed time for speech input
     *
     * @return Max allowed time for speech input
     */
    public Integer getMaxSpeechTime() {
        return maxSpeechTime;
    }

    /**
     * Profanity Filter on speech
     *
     * @return Profanity Filter on speech
     */
    public Boolean isProfanityFilter() {
        return profanityFilter;
    }

    /**
     * Finish gather on key
     *
     * @return Finish gather on key
     */
    public String getFinishOnKey() {
        return finishOnKey;
    }

    /**
     * Number of digits to collect
     *
     * @return Number of digits to collect
     */
    public Integer getNumDigits() {
        return numDigits;
    }

    /**
     * Partial result callback URL
     *
     * @return Partial result callback URL
     */
    public URI getPartialResultCallback() {
        return partialResultCallback;
    }

    /**
     * Partial result callback URL method
     *
     * @return Partial result callback URL method
     */
    public HttpMethod getPartialResultCallbackMethod() {
        return partialResultCallbackMethod;
    }

    /**
     * Language to use
     *
     * @return Language to use
     */
    public Gather.Language getLanguage() {
        return language;
    }

    /**
     * Speech recognition hints
     *
     * @return Speech recognition hints
     */
    public String getHints() {
        return hints;
    }

    /**
     * Stop playing media upon speech
     *
     * @return Stop playing media upon speech
     */
    public Boolean isBargeIn() {
        return bargeIn;
    }

    /**
     * Allow debug for gather
     *
     * @return Allow debug for gather
     */
    public Boolean isDebug() {
        return debug;
    }

    /**
     * Force webhook to the action URL event if there is no input
     *
     * @return Force webhook to the action URL event if there is no input
     */
    public Boolean isActionOnEmptyResult() {
        return actionOnEmptyResult;
    }

    /**
     * Specify the model that is best suited for your use case
     *
     * @return Specify the model that is best suited for your use case
     */
    public Gather.SpeechModel getSpeechModel() {
        return speechModel;
    }

    /**
     * Use enhanced speech model
     *
     * @return Use enhanced speech model
     */
    public Boolean isEnhanced() {
        return enhanced;
    }

    /**
     * Create a new {@code <Gather>} element
     */
    public static class Builder extends TwiML.Builder<Builder> {
        private List<Gather.Input> input;
        private URI action;
        private HttpMethod method;
        private Integer timeout;
        private String speechTimeout;
        private Integer maxSpeechTime;
        private Boolean profanityFilter;
        private String finishOnKey;
        private Integer numDigits;
        private URI partialResultCallback;
        private HttpMethod partialResultCallbackMethod;
        private Gather.Language language;
        private String hints;
        private Boolean bargeIn;
        private Boolean debug;
        private Boolean actionOnEmptyResult;
        private Gather.SpeechModel speechModel;
        private Boolean enhanced;

        /**
         * Input type Twilio should accept
         */
        public Builder inputs(List<Gather.Input> input) {
            this.input = input;
            return this;
        }

        /**
         * Input type Twilio should accept
         */
        public Builder inputs(Gather.Input input) {
            this.input = Promoter.listOfOne(input);
            return this;
        }

        /**
         * Action URL
         */
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
        public Builder method(HttpMethod method) {
            this.method = method;
            return this;
        }

        /**
         * Time to wait to gather input
         */
        public Builder timeout(Integer timeout) {
            this.timeout = timeout;
            return this;
        }

        /**
         * Time to wait to gather speech input and it should be either auto or a
         * positive integer.
         */
        public Builder speechTimeout(String speechTimeout) {
            this.speechTimeout = speechTimeout;
            return this;
        }

        /**
         * Max allowed time for speech input
         */
        public Builder maxSpeechTime(Integer maxSpeechTime) {
            this.maxSpeechTime = maxSpeechTime;
            return this;
        }

        /**
         * Profanity Filter on speech
         */
        public Builder profanityFilter(Boolean profanityFilter) {
            this.profanityFilter = profanityFilter;
            return this;
        }

        /**
         * Finish gather on key
         */
        public Builder finishOnKey(String finishOnKey) {
            this.finishOnKey = finishOnKey;
            return this;
        }

        /**
         * Number of digits to collect
         */
        public Builder numDigits(Integer numDigits) {
            this.numDigits = numDigits;
            return this;
        }

        /**
         * Partial result callback URL
         */
        public Builder partialResultCallback(URI partialResultCallback) {
            this.partialResultCallback = partialResultCallback;
            return this;
        }

        /**
         * Partial result callback URL
         */
        public Builder partialResultCallback(String partialResultCallback) {
            this.partialResultCallback = Promoter.uriFromString(partialResultCallback);
            return this;
        }

        /**
         * Partial result callback URL method
         */
        public Builder partialResultCallbackMethod(HttpMethod partialResultCallbackMethod) {
            this.partialResultCallbackMethod = partialResultCallbackMethod;
            return this;
        }

        /**
         * Language to use
         */
        public Builder language(Gather.Language language) {
            this.language = language;
            return this;
        }

        /**
         * Speech recognition hints
         */
        public Builder hints(String hints) {
            this.hints = hints;
            return this;
        }

        /**
         * Stop playing media upon speech
         */
        public Builder bargeIn(Boolean bargeIn) {
            this.bargeIn = bargeIn;
            return this;
        }

        /**
         * Allow debug for gather
         */
        public Builder debug(Boolean debug) {
            this.debug = debug;
            return this;
        }

        /**
         * Force webhook to the action URL event if there is no input
         */
        public Builder actionOnEmptyResult(Boolean actionOnEmptyResult) {
            this.actionOnEmptyResult = actionOnEmptyResult;
            return this;
        }

        /**
         * Specify the model that is best suited for your use case
         */
        public Builder speechModel(Gather.SpeechModel speechModel) {
            this.speechModel = speechModel;
            return this;
        }

        /**
         * Use enhanced speech model
         */
        public Builder enhanced(Boolean enhanced) {
            this.enhanced = enhanced;
            return this;
        }

        /**
         * Add a child {@code <Say>} element
         */
        public Builder say(Say say) {
            this.children.add(say);
            return this;
        }

        /**
         * Add a child {@code <Pause>} element
         */
        public Builder pause(Pause pause) {
            this.children.add(pause);
            return this;
        }

        /**
         * Add a child {@code <Play>} element
         */
        public Builder play(Play play) {
            this.children.add(play);
            return this;
        }

        /**
         * Create and return resulting {@code <Gather>} element
         */
        public Gather build() {
            return new Gather(this);
        }
    }
}