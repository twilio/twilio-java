package com.twilio.sdk.parser;

import java.util.Map;

import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.TwilioUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class JsonResponseParser.
 */
public class JsonResponseParser implements ResponseParser {
	public Map<String, Object> parse(TwilioRestResponse response) {		
		return this.parseJson(response.getResponseText());
	}

	/**
	 * Parses the json.
	 *
	 * @param jsonString the json string
	 * @return the map
	 */
	protected Map<String, Object> parseJson(String jsonString) {
		return TwilioUtils.jsonAsMap(jsonString);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.parser.ResponseParser#getPagingPropertyKey(com.twilio.sdk.parser.ResponseParser.PagingProperty)
	 */
	public String getPagingPropertyKey(PagingProperty prop) {
		switch (prop) {
		case NEXT_PAGE_URI_KEY:
			return "next_page_uri";
		case START_KEY:
			return "start";
		case END_KEY:
			return "end";
		case PAGE_KEY:
			return "page";
		}
		
		return null;
	}
}
