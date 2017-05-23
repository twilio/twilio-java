package com.twilio.twiml;

/**
 * Twilio Languages
 */
public enum Language {
    EN("en"),
    EN_GB("en-gb"),
    EN_AU("en-AU"),
    EN_CA("en-GB"),
    EN_IN("en-IN"),
    EN_US("en-US"),
    ES("es"),
    ES_ES("es-ES"),
    ES_MX("es-MX"),
    FR("fr"),
    FR_CA("fr-CA"),
    FR_FR("fr-FR"),
    DE("de"),
    DE_DE("de-DE"),
    DA_DK("da-DK"),
    CA_ES("ca-ES"),
    FI_FI("fi-FI"),
    IT_IT("it-IT"),
    JA_JP("ja-JP"),
    KO_KR("ko-KR"),
    NB_NO("nb-NO"),
    NL_NL("nl-NL"),
    PL_PL("pl-PL"),
    PT_BR("pt-BR"),
    PT_PT("pt-PT"),
    RU_RU("ru-RU"),
    SV_SE("sv-SE"),
    ZH_CN("zh-CN"),
    ZH_HK("zh-HK"),
    ZH_TW("zh-TW");

    private final String value;

    Language(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
