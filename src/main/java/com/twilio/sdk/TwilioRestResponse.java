package com.twilio.sdk;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.twilio.sdk.parser.JsonResponseParser;
import com.twilio.sdk.parser.ResponseParser;
import com.twilio.sdk.parser.XmlResponseParser;

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
 * TwilioRestResponse holds all the REST response data Before using the
 * response, check IsError to see if an exception occurred with the data sent to
 * Twilio ResponseText contains the raw string response Url and QueryString are
 * from the request HttpStatus is the response code of the request.
 */
public class TwilioRestResponse {

	/** The response text. */
	private String responseText;
	
	/** The http status. */
	private int httpStatus;
	
	/** The url. */
	private String url;
	
	/** The query string. */
	private String queryString;
	
	/** The error. */
	private boolean error;
	
	/** The content type. */
	private String contentType;

	/**
	 * Instantiates a new twilio rest response.
	 *
	 * @param url the url
	 * @param text the text
	 * @param status the status
	 */
	public TwilioRestResponse(String url, String text, int status) {
		Pattern p = Pattern.compile("([^?]+)\\??(.*)");
		Matcher m = p.matcher(url);
		m.matches();
		this.url = m.group(1);
		this.queryString = m.group(2);
		this.responseText = text;
		this.httpStatus = status;
		this.error = (status >= 400);
	}


	/**
	 * Get the raw response body as a String
	 * 
	 * @return the response body
	 */
	public String getResponseText() {
		return responseText;
	}

	/**
	 * Sets the response text.
	 *
	 * @param responseText the new response text
	 */
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	/**

	 * Get the http status code associated with this response.
	 * 
	 * @return the int value of the response status
	 */
	public int getHttpStatus() {
		return httpStatus;
	}

	/**
	 * Sets the http status.
	 *
	 * @param httpStatus the new http status
	 */
	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	/**
	 * Get the url that resulted in this response
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Get the query string that resulted in this response
	 * 
	 */
	public String getQueryString() {
		return queryString;
	}

	/**
	 * Sets the query string.
	 *
	 * @param queryString the new query string
	 */
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	/**
	 * Determine if this request resulted in any kind of error
	 * 
	 * @return true if an error occured
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * Sets the error.
	 *
	 * @param error the new error
	 */
	public void setError(boolean error) {
		this.error = error;
	}

	/**
	 * Determines if the response was a client side error (HTTP 4XX status)
	 * 
	 * @return true if this was a client error
	 */
	public boolean isClientError() {
		return (this.getHttpStatus() >= 400 && this.getHttpStatus() < 500);
	}

	/**
	 * Determines if the response was a server side error (HTTP 5XX status)
	 * 
	 * @return true if this was a server error
	 */
	public boolean isServerError() {
		return this.getHttpStatus() >= 500;
	}

	/**
	 * Sets the content type.
	 *
	 * @param contentType the new content type
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * Method to determine if the response content type was a JSON type
	 * 
	 * @return true if this looks like a JSON response
	 */
	public boolean isJson() {
		return (this.contentType.equalsIgnoreCase("application/json"));
	}

	/**
	 * Method to determine if the response content type was an XML type
	 * 
	 * @return true if this looks like an XML response
	 */
	public boolean isXml() {
		return (this.contentType.equalsIgnoreCase("text/xml") || this.contentType
				.equalsIgnoreCase("application/xml"));
	}

	/**
	 * Get an appropriate response parser for this response type
	 * 
	 * @return a response parser capable of parsing this response body.
	 */
	public ResponseParser getParser() {
		if (this.isJson()) {
			return new JsonResponseParser();
		} else if (this.isXml()) {
			return new XmlResponseParser();
		}

		throw new UnsupportedOperationException(this.contentType
				+ " not a supported content type");
	}

	/**
	 * Helper method to convert the response to a canonical object map. This
	 * method will use the appropriate parser to map the response body to a Map
	 * of elements.
	 * 
	 * @return a normalized Map of objects. Repeated elements are List values,
	 *         sub-objects are Map values. All other types are String values.
	 */
	public Map<String, Object> toMap() {
		ResponseParser parser = this.getParser();
		return parser.parse(this);
	}

}
