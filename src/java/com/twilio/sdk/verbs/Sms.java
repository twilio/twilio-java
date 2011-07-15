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
 * The Class Sms.
 */
public class Sms extends Verb {
    
    /**
     * Instantiates a new sms.
     *
     * @param message the message
     */
    public Sms(String message) {
        super(V_SMS, message);
        this.allowedVerbs = null;
    }

    /**
     * Sets the to.
     *
     * @param str the new to
     */
    public void setTo(String str){
        this.set("to", str);
    }
    
    /**
     * Sets the from.
     *
     * @param str the new from
     */
    public void setFrom(String str){
        this.set("from", str);   
    }
    
    /**
     * Sets the method.
     *
     * @param method the new method
     */
    public void setMethod(String method){
       this.set("method", method);   
    }
    
    /**
     * Sets the action.
     *
     * @param url the new action
     */
    public void setAction(String url){
       this.set("action", url);
    }
    
    /**
     * Sets the status callback.
     *
     * @param url the new status callback
     */
    public void setStatusCallback(String url){
       this.set("statusCallback", url);   
    }

}

