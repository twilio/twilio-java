package com.twilio.sdk.verbs;

import java.util.ArrayList;


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

public class Dial extends Verb {
    
    public Dial() {
        super(V_DIAL, null);
        this.allowedVerbs = new ArrayList<String>();
        this.allowedVerbs.add(Verb.V_NUMBER);
        this.allowedVerbs.add(Verb.V_CONFERENCE);
    }
    
    public Dial(String number) {
        super(V_DIAL, number);
        this.allowedVerbs = new ArrayList<String>();
        this.allowedVerbs.add(Verb.V_NUMBER);
        this.allowedVerbs.add(Verb.V_CONFERENCE);
    }

    public void setAction(String url){
       this.set("action", url);   
    }
    
    public void setMethod(String method){
       this.set("method", method);   
    }
    
    public void setTimeout(int i){
       this.set("timeout", Integer.toString(i));   
    }
    
    public void setHangupOnStar(boolean f){
        if(f)
            this.set("hangupOnStar", "true");  
        else
            this.set("hangupOnStar", "false");          
    }
    
    public void setTimeLimit(int i){
        this.set("timeLimit", Integer.toString(i));   
    }
    
    public void setCallerid(String callerid){
       this.set("callerId", callerid);   
    }

}

