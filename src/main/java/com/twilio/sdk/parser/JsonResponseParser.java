package com.twilio.sdk.parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.twilio.sdk.TwilioRestResponse;

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
	@SuppressWarnings("unchecked")
	protected Map<String, Object> parseJson(String jsonString) {
		Map<String, Object> ret = new HashMap<String, Object>();

		JSONObject json;
		try {
			json = new JSONObject(jsonString);
			Iterator<String> iter = json.keys();
			while (iter.hasNext()) {
				String name = iter.next();
				ret.put(name, json.get(name));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ret;
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
		case NUM_PAGES_KEY:
			return "num_pages";
		case TOTAL_KEY:
			return "total";
		}
		
		return null;
	}
}
