package com.twilio.sdk.verbs;



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

public class Conference extends Verb {
    
    public Conference(String name) {
        super(V_CONFERENCE , name);
        this.allowedVerbs = null;
    }
     
    private void setBoolean(String attr, Boolean bool){
        if(bool)
            this.set(attr,"true");
        else
            this.set(attr,"false");
    }
        
    public void setMuted(Boolean bool){
        this.setBoolean("muted",bool);
    }
    
    public void setBeep(Boolean bool){
        this.setBoolean("beep",bool);
    }
    
    public void setStartConferenceOnEnter(Boolean bool){
        this.setBoolean("startConferenceOnEnter",bool);
    }
    
    public void setEndConferenceOnExit(Boolean bool){
        this.setBoolean("endConferenceOnExit",bool);
    }
        
    public void setWaitMethod(String method){
        this.set("waitMethod", method);   
    }
    
    public void setWaitUrl(String url){
        this.set("waitUrl",url);
    }

}

