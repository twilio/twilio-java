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
    
    /**
     * Instantiates a new conference.
     *
     * @param name the name
     */
    public Conference(String name) {
        super(V_CONFERENCE , name);
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
     * Sets the beep.
     *
     * @param bool the new beep
     */
    public void setBeep(Boolean bool){
        this.setBoolean("beep",bool);
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

}

