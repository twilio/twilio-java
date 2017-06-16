package com.twilio.twiml;

/**
 * Twilio Languages
 */
public enum Language {
    /**
     *  Constants compatible with Say verb when `voice`
     *  is not set or is set to 'man' or 'woman':
     */

    // German
    DE("de"),
    // English (default)
    EN("en"),
    // Spanish
    ES("es"),
    // French
    FR("fr"),
    // English (United Kingdom)
    GB("en-gb"),

    /**
     * Constants compatible with Say verb when `voice`
     * is 'alice'.
     */
    // Chinese (Mandarin)
    ZH_CN("zh-CN"),
    // Chinese (Cantonese)
    ZH_HK("zh-HK"),
    // Chinese (Taiwanese Mandarin)
    ZH_TW("zh-TW"),

    /**
     * Constants compatible with Say verb when `voice`
     * is 'alice' and with Gather verb.
     */
    // Catalan (Spain)
    CA_ES("ca-ES"),
    // Danish (Denmark)
    DA_DK("da-DK"),
    // German (Germany)
    DE_DE("de-DE"),
    // English (Australia)
    EN_AU("en-AU"),
    // English (Canada)
    EN_CA("en-CA"),
    // English (United Kingdom)
    EN_GB("en-GB"),
    // English (India)
    EN_IN("en-IN"),
    // English (United States)
    EN_US("en-US"),
    // Spanish (Spain)
    ES_ES("es-ES"),
    // Spanish (Mexico)
    ES_MX("es-MX"),
    // Finnish (Finland)
    FI_FI("fi-FI"),
    // French (Canada)
    FR_CA("fr-CA"),
    // French (France)
    FR_FR("fr-FR"),
    // Italian (Italy)
    IT_IT("it-IT"),
    // Japanese (Japan)
    JA_JP("ja-JP"),
    // Korean (South Korea)
    KO_KR("ko-KR"),
    // Norwegian (Norway)
    NB_NO("nb-NO"),
    // Dutch (Netherlands)
    NL_NL("nl-NL"),
    // Polish (Poland)
    PL_PL("pl-PL"),
    // Portuguese (Brazil)
    PT_BR("pt-BR"),
    // Portuguese (Portugal)
    PT_PT("pt-PT"),
    // Russian (Russia)
    RU_RU("ru-RU"),
    // Swedish (Sweden)
    SV_SE("sv-SE"),

    /**
     * Constants compatible with Gather verb.
     */
    // Afrikaans (South Africa)
    AF_ZA("af-ZA"),
    // Arabic (United Arab Emirates)
    AR_AE("ar-AE"),
    // Arabic (Bahrain)
    AR_BH("ar-BH"),
    // Arabic (Algeria)
    AR_DZ("ar-DZ"),
    // Arabic (Egypt)
    AR_EG("ar-EG"),
    // Arabic (Israel)
    AR_IL("ar-IL"),
    // Arabic (Iraq)
    AR_IQ("ar-IQ"),
    // Arabic (Jordan)
    AR_JO("ar-JO"),
    // Arabic (Kuwait)
    AR_KW("ar-KW"),
    // Arabic (Lebanon)
    AR_LB("ar-LB"),
    // Arabic (Morocco)
    AR_MA("ar-MA"),
    // Arabic (Oman)
    AR_OM("ar-OM"),
    // Arabic (State of Palestine)
    AR_PS("ar-PS"),
    // Arabic (Qatar)
    AR_QA("ar-QA"),
    // Arabic (Saudi Arabia)
    AR_SA("ar-SA"),
    // Arabic (Tunisia)
    AR_TN("ar-TN"),
    // Bulgarian (Bulgaria)
    BG_BG("bg-BG"),
    // Czech (Czech Republic)
    CS_CZ("cs-CZ"),
    // Greek (Greece)
    EL_GR("el-GR"),
    // English (Ireland)
    EN_IE("en-IE"),
    // English (New Zealand)
    EN_NZ("en-NZ"),
    // English (Philippines)
    EN_PH("en-PH"),
    // English (South Africa)
    EN_ZA("en-ZA"),
    // Spanish (Argentina)
    ES_AR("es-AR"),
    // Spanish (Bolivia)
    ES_BO("es-BO"),
    // Spanish (Chile)
    ES_CL("es-CL"),
    // Spanish (Colombia)
    ES_CO("es-CO"),
    // Spanish (Costa Rica)
    ES_CR("es-CR"),
    // Spanish (Dominican Republic)
    ES_DO("es-DO"),
    // Spanish (Ecuador)
    ES_EC("es-EC"),
    // Spanish (Guatemala)
    ES_GT("es-GT"),
    // Spanish (Honduras)
    ES_HN("es-HN"),
    // Spanish (Nicaragua)
    ES_NI("es-NI"),
    // Spanish (Panama)
    ES_PA("es-PA"),
    // Spanish (Peru)
    ES_PE("es-PE"),
    // Spanish (Puerto Rico)
    ES_PR("es-PR"),
    // Spanish (Paraguay)
    ES_PY("es-PY"),
    // Spanish (El Salvador)
    ES_SV("es-SV"),
    // Spanish (United States)
    ES_US("es-US"),
    // Spanish (Uruguay)
    ES_UY("es-UY"),
    // Spanish (Venezuela)
    ES_VE("es-VE"),
    // Basque (Spain)
    EU_ES("eu-ES"),
    // Persian (Iran)
    FA_IR("fa-IR"),
    // Galician (Spain)
    GL_ES("gl-ES"),
    // Hebrew (Israel)
    HE_IL("he-IL"),
    // Hindi (India)
    HI_IN("hi-IN"),
    // Croatian (Croatia)
    HR_HR("hr-HR"),
    // Hungarian (Hungary)
    HU_HU("hu-HU"),
    // Indonesian (Indonesia)
    ID_ID("id-ID"),
    // Filipino (Philippines)
    IL_PH("il-PH"),
    // Icelandic (Iceland)
    IS_IS("is-IS"),
    // Lithuanian (Lithuania)
    LT_LT("lt-LT"),
    // Malay (Malaysia)
    MS_MY("ms-MY"),
    // Romanian (Romania)
    RO_RO("ro-RO"),
    // Slovak (Slovakia)
    SK_SK("sk-SK"),
    // Slovenian (Slovenia)
    SL_SI("sl-SI"),
    // Serbian (Serbia)
    SR_RS("sr-RS"),
    // Thai (Thailand)
    TH_TH("th-TH"),
    // Turkish (Turkey)
    TR_TR("tr-TR"),
    // Ukrainian (Ukraine)
    UK_UA("uk-UA"),
    // Vietnamese (Vietnam)
    VI_VN("vi-VN"),
    // Zulu (South Africa)
    ZU_ZA("zu-ZA"),
    // Chinese, Mandarin (Traditional, Taiwan)
    CMN_HANT_TW("cmn-Hant-TW"),
    // Chinese, Cantonese (Traditional, Hong Kong)
    YUE_HANT_HK("yue-Hant-HK"),
    // Chinese, Mandarin (Simplified, Hong Kong)
    CMN_HANS_HK("cmn-Hans-HK"),
    // Chinese, Mandarin (Simplified, China)
    CMN_HANS_CN("cmn-Hans-CN");


    private final String value;

    Language(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
