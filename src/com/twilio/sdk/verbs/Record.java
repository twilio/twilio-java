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

public class Record extends Verb {
    
    public Record() {
        super(V_RECORD, null);
        this.allowedVerbs = null;
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
    
    public void setFinishOnKey(String key){
       this.set("finishOnKey", key);   
    }
    
    public void setMaxLength(int i){
       this.set("maxLength", Integer.toString(i));   
    }
    
    public void setTranscribe(boolean f){
       if(f)
           this.set("transcribe", "true");  
       else
           this.set("transcribe", "false"); 
    }
    
    public void setTranscribeCallback(String url){
       this.set("transcribeCallback", url);    
    }    

}

