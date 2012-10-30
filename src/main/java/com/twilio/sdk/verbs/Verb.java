package com.twilio.sdk.verbs;


/*
Copyright (c) 2008-2012 Twilio, Inc.

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

// TODO: Auto-generated Javadoc
/**
 * The Class Verb.
 */
public class Verb {
    
    
    /** The tag. */
    protected String tag;
    
    /** The body. */
    protected String body;
    
    /** The attributes. */
    protected HashMap<String, String> attributes;
    
    /** The children. */
    protected ArrayList<Verb> children;
    
    /** The allowed verbs. */
    protected ArrayList<String> allowedVerbs;
    
    /** Constants */
    public static final String V_CLIENT = "Client";

    public static final String V_CONFERENCE = "Conference";

    public static final String V_DIAL = "Dial";

    public static final String V_ENQUEUE = "Enqueue";

    public static final String V_GATHER = "Gather";

    public static final String V_HANGUP = "Hangup";

    public static final String V_LEAVE = "Leave";

    public static final String V_NUMBER = "Number";

    public static final String V_PAUSE = "Pause";

    public static final String V_PLAY = "Play";

    public static final String V_QUEUE = "Queue";

    public static final String V_RECORD = "Record";

    public static final String V_REDIRECT = "Redirect";

    public static final String V_RESPONSE = "Response";

    public static final String V_SAY = "Say";
    
    /** The Constant V_SMS. */
    public static final String V_SMS = "Sms";

	public static final String V_REJECT = "Reject";

    /**
     * Instantiates a new verb.
     *
     * @param tag the tag
     * @param body the body
     */
    public Verb(String tag, String body) {
        this.tag = tag;
        this.body = body;
        this.attributes = new HashMap<String, String>();
        this.children = new ArrayList<Verb>();
    }
    
    /**
     * Append.
     *
     * @param verb the verb
     * @return the verb
     * @throws TwiMLException the twiml exception
     */
    public Verb append(Verb verb) throws TwiMLException {
       if(this.allowedVerbs != null && this.allowedVerbs.contains(verb.getTag())) {
           this.children.add(verb);
           return verb;  
       } else {
           throw new TwiMLException("This is not a supported verb");    
       }
    }
    
    /**
     * To xml.
     *
     * @return the string
     */
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
    
    /**
     * As url.
     *
     * @return the string
     */
    public String asURL(){
        try {
            return URLEncoder.encode(this.toXML(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Sets the.
     *
     * @param key the key
     * @param value the value
     */
    public void set (String key, String value){
       attributes.put(key,value);
    }
    
    /**
     * Gets the body.
     *
     * @return the body
     */
    public String getBody() {
       return this.body;    
    }
    
    /**
     * Gets the tag.
     *
     * @return the tag
     */
    public String getTag() {
       return this.tag;    
    }
    
    /**
     * Gets the children.
     *
     * @return the children
     */
    public ArrayList<Verb> getChildren() {
       return this.children;     
    }
    
    /**
     * Gets the attributes.
     *
     * @return the attributes
     */
    public HashMap<String, String> getAttributes() {
       return this.attributes;
    }
}
