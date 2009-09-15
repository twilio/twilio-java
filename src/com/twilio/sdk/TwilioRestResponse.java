package com.twilio.sdk;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * TwilioRestResponse holds all the REST response data 
 *         Before using the reponse, check IsError to see if an exception occurred with the data sent to Twilio
 *         ResponseXml will contain a SimpleXml object with the response xml
 *         ResponseText contains the raw string response
 *         Url and QueryString are from the request
 *         HttpStatus is the response code of the request
 */
public class TwilioRestResponse {

    private String responseText;
    private int httpStatus;
    private String url;
    private String queryString;
    private boolean error;
    
    
    public TwilioRestResponse(String url, String text, int status){
        Pattern p = Pattern.compile("([^?]+)\\??(.*)");
        Matcher m = p.matcher(url);
        m.matches();
        this.url = m.group(1);
        this.queryString = m.group(2);
        this.responseText = text;
        this.httpStatus=status;
        this.error = (status>=400);    
    }
    
    // getters and setters 
    public String getResponseText() {
        return responseText;
    }
    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }
    public int getHttpStatus() {
        return httpStatus;
    }
    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getQueryString() {
        return queryString;
    }
    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
    public boolean isError() {
        return error;
    }
    public void setError(boolean error) {
        this.error = error;
    }

    
    
    
}
