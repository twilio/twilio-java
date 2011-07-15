package com.twilio.sdk.parser;

import java.util.Map;

import com.twilio.sdk.TwilioRestResponse;

// TODO: Auto-generated Javadoc
/**
 * The Interface ResponseParser.
 */
public interface ResponseParser {
	
	/**
	 * The Enum PagingProperty.
	 */
	public enum PagingProperty {
		
		/** The NEX t_ pag e_ ur i_ key. */
		NEXT_PAGE_URI_KEY,
		
		/** The STAR t_ key. */
		START_KEY,
		
		/** The EN d_ key. */
		END_KEY,
		
		/** The PAG e_ key. */
		PAGE_KEY,
		
		/** The NU m_ page s_ key. */
		NUM_PAGES_KEY,
		
		/** The TOTA l_ key. */
		TOTAL_KEY,
	}
	
	/**
	 * Parses the.
	 *
	 * @param response the response
	 * @return the map
	 */
	public Map<String, Object> parse(TwilioRestResponse response); 
	
	/**
	 * Gets the paging property key.
	 *
	 * @param prop the prop
	 * @return the paging property key
	 */
	public String getPagingPropertyKey(PagingProperty prop);
}
