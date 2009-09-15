package com.twilio.sdk;

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

import java.util.HashMap;
import java.util.Map;

public class TwilioUtilsExample {
    
    public static void main(String[] args) {
        
        // Account details
        String accountSid = "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
        String authToken = "YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY";
        
        //This is the signature we expect
        String expected_sig = "SSSSSSSSSSSSSSSSSSSSSSSSSSSS";
       
        //This is the url that twilio requested
        String url = "http://UUUUUUUUUUUUUUU";

        //These are the post params twilio sent in its request
        Map<String,String> params = new HashMap<String,String>();
        
        TwilioUtils util = new TwilioUtils(accountSid, authToken);
        
        boolean result = util.validateRequest(expected_sig, url, params);

        if(result)
            System.out.print( "The signature is valid!\n" );
        else
            System.out.print( "The signature was NOT VALID.  It might have been spoofed!\n" );

    }
    
}
