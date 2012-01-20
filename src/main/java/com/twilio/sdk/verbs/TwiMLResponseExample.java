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
 * The Class TwiMLResponseExample.
 */
public class TwiMLResponseExample {
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args){
        // Say, Dial, and Play
        TwiMLResponse response = new TwiMLResponse();
        Say say = new Say("Hello World");
        say.setVoice("man");
        say.setLoop(5);
           
        try {
            response.append(say);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }

        System.out.println(response.toXML());
        
        // Gather, Redirect
        response = new TwiMLResponse();
        Gather gather = new Gather();
        gather.setNumDigits(10);
        say = new Say("Press 1");
        Redirect redirect = new Redirect();
        
        try {
            gather.append(say);
            response.append(gather);
            response.append(redirect);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }

        System.out.println(response.toXML());
        
        // Conference
        response = new TwiMLResponse();
        Dial dial = new Dial();
        dial.setCallerId("5555555555");
        dial.setAction("foo");
        dial.setHangupOnStar(true);
        Conference conf = new Conference("MyRoom");
        conf.setBeep(true); 
        
        try {
            dial.append(conf);
            response.append(dial);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }

        System.out.println(response.toXML());
        
        // Set an arbitrary attribute / value pair
        response = new TwiMLResponse();
        
        redirect = new Redirect();
        redirect.set("crazy", "delicious");
        
        try {
            response.append(redirect);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }

        System.out.println(response.toXML());

    }
}
