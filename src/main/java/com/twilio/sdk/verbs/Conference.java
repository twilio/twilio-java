package com.twilio.sdk.verbs;



// TODO: Auto-generated Javadoc
/*
Copyright (c) 2008 Twilio, Inc.

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
*/

/**
 * The Class Conference.
 */
public class Conference extends Verb {

    /* Constants */ 
  
    public static final String BEEP_TRUE = "true";
    public static final String BEEP_FALSE = "false";
    public static final String BEEP_ONEXIT = "onExit";
    public static final String BEEP_ONENTER = "onEnter";

    /**
     * Values for the record attribute
     */
    public enum Record {
        DO_NOT_RECORD("do-not-record"),
        RECORD_FROM_START("record-from-start");

        private final String value;

        private Record(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    /**
     * Values for the trim attribute
     */
    public enum Trim {
        TRIM_SILENCE("trim-silence"),
        DO_NOT_TRIM("do-not-trim");

        private final String value;

        private Trim(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
  
    /**
     * Instantiates a new conference.
     *
     * @param name the name
     */
    public Conference(String name) {
        super(V_CONFERENCE, name);
        this.allowedVerbs = null;
    }

    /**
     * Sets the boolean.
     *
     * @param attr the attr
     * @param bool the bool
     */
    private void setBoolean(String attr, Boolean bool){
        if(bool)
            this.set(attr,"true");
        else
            this.set(attr,"false");
    }

    /**
     * Sets the muted.
     *
     * @param bool the new muted
     */
    public void setMuted(Boolean bool){
        this.setBoolean("muted",bool);
    }

    /**
     * Sets the beep behavior.
     * <ul>
     * <li>"true" - beep on enter and exit</li>
     * <li>"false" - no beep</li>
     * <li>"onEnter" - beep on enter</li>
     * <li>"onExit" - beep on exit</li>
     * </ul>
     *
     * @param value the new beep behavior
     */
    public void setBeep(String value){
        this.set("beep",value);
    }

    /**
     * Sets the beep behavior (the legacy way)
     * <ul>
     * <li>true - beep on enter and exit</li>
     * <li>false - no beep</li>
     * </ul>
     *
     * @param bool the new beep behavior
     */
    public void setBeep(Boolean bool) {
        this.set("beep", bool.toString());
    }

    /**
     * Sets the start conference on enter.
     *
     * @param bool the new start conference on enter
     */
    public void setStartConferenceOnEnter(Boolean bool){
        this.setBoolean("startConferenceOnEnter",bool);
    }

    /**
     * Sets the end conference on exit.
     *
     * @param bool the new end conference on exit
     */
    public void setEndConferenceOnExit(Boolean bool){
        this.setBoolean("endConferenceOnExit",bool);
    }

    /**
     * Sets the wait method.
     *
     * @param method the new wait method
     */
    public void setWaitMethod(String method){
        this.set("waitMethod", method);
    }

    /**
     * Sets the wait url.
     *
     * @param url the new wait url
     */
    public void setWaitUrl(String url){
        this.set("waitUrl",url);
    }

    /**
     * Sets the maximum number of participants you want to allow
     *
     * @param i the new maxParticipants value
     */
    public void setMaxParticipants(int i){
       this.set("maxParticipants", Integer.toString(i));
    }

    /**
     * Sets the record attribute on the conference.
     *
     * @param record one of the {@link Record} values
     */
    public void setRecord(final Record record) {
        this.setRecord(record.toString());
    }

    /**
     * Sets the record attribute on the conference.
     *
     * @param record value of the record attribute
     */
    public void setRecord(final String record) {
        this.set("record", record);
    }

    /**
     * Sets the trim value for recordings of the conference.
     *
     * @param trim one of the {@link Trim} values
     */
    public void setTrim(final Trim trim) {
        this.setTrim(trim.toString());
    }

    /**
     * Sets the trim value for recordings of the conference.
     *
     * @param trim value of the trim attribute
     */
    public void setTrim(final String trim) {
        this.set("trim", trim);
    }

    /**
     * Sets a URL that Twilio will POST to when the conference ends.
     *
     * @param eventCallbackUrl
     */
    public void setEventCallbackUrl(final String eventCallbackUrl) {
        this.set("eventCallbackUrl", eventCallbackUrl);
    }

}

