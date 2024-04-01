/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.twilio.rest;

public enum Domains {
    ACCOUNTS("accounts"),
    API("api"),
    BULKEXPORTS("bulkexports"),
    CHAT("chat"),
    CONTENT("content"),
    CONVERSATIONS("conversations"),
    EVENTS("events"),
    FLEXAPI("flex-api"),
    FRONTLINEAPI("frontline-api"),
    INSIGHTS("insights"),
    INTELLIGENCE("intelligence"),
    IPMESSAGING("ip-messaging"),
    LOOKUPS("lookups"),
    PREVIEWMESSAGING("preview.messaging"),
    MESSAGING("messaging"),
    MICROVISOR("microvisor"),
    MONITOR("monitor"),
    NOTIFY("notify"),
    NUMBERS("numbers"),
    OAUTH("oauth"),
    PREVIEW("preview"),
    PRICING("pricing"),
    PROXY("proxy"),
    ROUTES("routes"),
    SERVERLESS("serverless"),
    STUDIO("studio"),
    SUPERSIM("supersim"),
    SYNC("sync"),
    TASKROUTER("taskrouter"),
    TRUNKING("trunking"),
    TRUSTHUB("trusthub"),
    VERIFY("verify"),
    VIDEO("video"),
    VOICE("voice"),
    WIRELESS("wireless");

    private final String value;
  
    private Domains(final String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
