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
import java.util.ArrayList;
import java.util.HashMap;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Verb {
    
    
    protected String tag;
    protected String body;
    protected HashMap<String, String> attributes;
    protected ArrayList<Verb> children;
    protected ArrayList<String> allowedVerbs;
    
    public static final String V_SAY = "Say";
    public static final String V_PLAY = "Play";
    public static final String V_GATHER = "Gather";
    public static final String V_RECORD = "Record";
    public static final String V_PAUSE = "Pause";
    public static final String V_HANGUP = "Hangup";
    public static final String V_DIAL = "Dial";
    public static final String V_NUMBER = "Number";
    public static final String V_REDIRECT = "Redirect";
    public static final String V_RESPONSE = "Response";
    public static final String V_CONFERENCE = "Conference";
    public static final String V_SMS = "Sms";
    public static final String V_REJECT = "Reject";
    
    public Verb(String tag, String body) {
        this.tag = tag;
        this.body = body;
        this.attributes = new HashMap<String, String>();
        this.children = new ArrayList<Verb>();
    }
    
    public Verb append(Verb verb) throws TwiMLException {
       if(this.allowedVerbs != null && this.allowedVerbs.contains(verb.getTag())) {
           this.children.add(verb);
           return verb;  
       } else {
           throw new TwiMLException("This is not a supported verb");    
       }
    }
    
    public String toXML(){
        String xml = "<" + this.tag;
        for (String key : attributes.keySet()) {
            xml += " " + key + "=\"" + attributes.get(key) + "\"";
        }
        xml += ">";
        if(this.body != null)
            xml += this.body;
        for (Verb child : children){
            xml += child.toXML();
        }
        return xml += "</" + this.tag + ">";
    }
    
    public String asURL(){
        try {
            return URLEncoder.encode(this.toXML(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void set (String key, String value){
       attributes.put(key,value);
    }
    
    public String getBody() {
       return this.body;    
    }
    
    public String getTag() {
       return this.tag;    
    }
    
    public ArrayList<Verb> getChildren() {
       return this.children;     
    }
    
    public HashMap<String, String> getAttributes() {
       return this.attributes;     
    }
}
