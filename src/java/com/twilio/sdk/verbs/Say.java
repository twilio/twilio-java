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
 * The Class Say.
 */
public class Say extends Verb {
    
    /**
     * Instantiates a new say.
     *
     * @param body the body
     */
    public Say(String body) {
        super(V_SAY, body);
        this.allowedVerbs = null;
    }

    /**
     * Sets the loop.
     *
     * @param i the new loop
     */
    public void setLoop(int i){
        this.set("loop", Integer.toString(i));   
    }
    
    /**
     * Sets the language.
     *
     * @param str the new language
     */
    public void setLanguage(String str){
        this.set("language", str);   
    }
    
    /**
     * Sets the voice.
     *
     * @param str the new voice
     */
    public void setVoice(String str){
        this.set("voice", str);   
    }

}

