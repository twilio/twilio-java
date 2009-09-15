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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;

public class TwilioRestClient {
    
    private String endpoint = "https://api.twilio.com";
    private String accountSid;
    private String authToken;
    

    public TwilioRestClient(String accountSid, String authToken, String endpoint) {

        this.accountSid = accountSid;
        this.authToken = authToken;
        if((endpoint!=null)&&(!endpoint.equals(""))){
            this.endpoint = endpoint;
        }
    }
    
    
    
    /*
     * sendRequst
     *   Sends a REST Request to the Twilio REST API
     *   $path : the URL (relative to the endpoint URL, after the /v1)
     *   $method : the HTTP method to use, defaults to GET
     *   $vars : for POST or PUT, a key/value associative array of data to send, for GET will be appended to the URL as query params
     */
    public TwilioRestResponse request(String path, String method , Map<String,String> vars) throws TwilioRestException {

        String encoded = "";
        if(vars!=null){
            for(String key: vars.keySet()){
                try {
                    encoded += "&"+key+"="+ URLEncoder.encode(vars.get(key),"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            encoded =encoded.substring(1);
        }
        
        // construct full url
        String url = this.endpoint+path;
        
        // if GET and vars, append them
        if(method.toUpperCase().equals("GET")) 
            url+= ((path.indexOf('?')==-1)?"?":"&")+encoded;
            
            
        try {
            URL resturl = new URL(url);
            
            HttpURLConnection con = (HttpURLConnection)resturl.openConnection();
            String userpass = this.accountSid+":"+this.authToken;
            String encodeuserpass = new String(Base64.encodeBase64(userpass.getBytes()));
            
            con.setRequestProperty ("Authorization", "Basic " + encodeuserpass);

            con.setDoOutput(true);
    
            // initialize a new curl object            
            if(method.toUpperCase().equals("GET")){
                con.setRequestMethod("GET");
            } else if(method.toUpperCase().equals("POST")){
                con.setRequestMethod("POST");
                OutputStreamWriter out = new OutputStreamWriter(
                        con.getOutputStream());
                out.write(encoded);
                out.close();
            } else if(method.toUpperCase().equals("PUT")){
                con.setRequestMethod("PUT");
                OutputStreamWriter out = new OutputStreamWriter(
                        con.getOutputStream());
                out.write(encoded);
                out.close();
            } else if(method.toUpperCase().equals("DELETE")){
                con.setRequestMethod("DELETE");
            } else {
                    throw new TwilioRestException("Unknown method "+method);
            }
            
            BufferedReader in = null;
            try {
                if(con.getInputStream()!=null){
                    in = new BufferedReader(
                            new InputStreamReader(
                            con.getInputStream()));
                } 
            } catch(IOException e){
                if(con.getErrorStream()!=null){
                    in = new BufferedReader(
                            new InputStreamReader(
                            con.getErrorStream()));
                }
            }
            
            if(in==null) {
                throw new TwilioRestException("Unable to read response from server");
            } 
            
            StringBuffer decodedString = new StringBuffer();
            String line;
            while ((line = in.readLine()) != null) {
                decodedString.append(line);
            }
            in.close();
            
            // get result code
            int responseCode = con.getResponseCode();
                
            return new TwilioRestResponse(url, decodedString.toString(), responseCode);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    } 

    public String getAccountSid() {
        return accountSid;
    }

    public void setAccountSid(String accountSid) {
        this.accountSid = accountSid;
    }
    
    
    
}
