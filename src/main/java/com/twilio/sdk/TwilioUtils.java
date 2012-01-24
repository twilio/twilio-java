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

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class TwilioUtils {
    
    protected String authToken;
    protected String accountSid;
    
    public TwilioUtils(String authToken, String accountSid){
        this.authToken = authToken;
        this.accountSid = accountSid;
    }
    
    public boolean validateRequest(String expectedSignature, String url, Map<String,String> params){
        
        SecretKeySpec signingKey = new SecretKeySpec(this.authToken.getBytes(), "HmacSHA1");
        
        try {
            //initialize the hash algortihm
            Mac mac = Mac.getInstance("HmacSHA1");    
            mac.init(signingKey);
            
            //sort the params alphabetically, and append the key and value of each to the url
            StringBuffer data = new StringBuffer(url);        
            if(params!=null){
                List<String> sortedKeys = new ArrayList<String>( params.keySet());
                Collections.sort(sortedKeys);
               
                for(String s: sortedKeys){
                    data.append(s);
                    String v="";
                    if(params.get(s)!=null )
                            v=params.get(s);
                    data.append(v);
                }
            }
          
            //compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(data.toString().getBytes());
           
            //base64-encode the hmac
            String signature = new String(Base64.encodeBase64(rawHmac));
            
            return signature.equals(expectedSignature);
        } catch (NoSuchAlgorithmException e) {
        
            return false;
        } catch (InvalidKeyException e) {
          
            return false;
        }
    
    }    
}
