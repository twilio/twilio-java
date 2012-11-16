package com.twilio.sdk.resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.parser.ResponseParser.PagingProperty;

// TODO: Auto-generated Javadoc
public abstract class ListResource<T> extends Resource implements Iterable<T> {
	
	/**
	 * The Class ListIterator.
	 */
	private class ListIterator implements Iterator<T> {

		/** The itr. */
		private Iterator<T> itr;

		/**
		 * Instantiates a new list iterator.
		 *
		 * @param itr the itr
		 */
		public ListIterator(Iterator<T> itr) {
			this.itr = itr;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */

		public boolean hasNext() {
			return itr.hasNext() || hasNextPage();
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		public T next() {
			// If we still have results on this page
			if (itr.hasNext()) {
				return itr.next();
			}

			// Otherwise fetch the next page
			try {
				fetchNextPage();
			} catch (TwilioRestException e) {
				throw new RuntimeException(e);
			}

			itr = pageData.iterator();
			return itr.next();
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<T> iterator() {
		return new ListIterator(getPageData().iterator());
	}

	/**
	 * Instantiates a new list resource.
	 *
	 * @param client the client
	 */
	public ListResource(TwilioRestClient client) {
		this(client, new HashMap<String, String>());
	}

	/**
	 * Instantiates a new list resource.
	 *
	 * @param client the client
	 * @param filters the filters
	 */
	public ListResource(TwilioRestClient client, Map<String, String> filters) {
		super(client);
		this.filters = filters;
	}

	/** The page data. */
	protected List<T> pageData;
	
	/** The next uri. */
	private String nextUri = null;
	
	/** The start. */
	private int start = 0;
	
	/** The end. */
	private int end = 0;
	
	/** The page. */
	private int page = 0;
	
	/** The num pages. */
	private int numPages = 0;
	
	/** The total. */
	private int total = 0;

	/**
	 * Gets the next uri.
	 *
	 * @return the next uri
	 */
	public String getNextUri() {
		return nextUri;
	}

	/**
	 * Gets the start.
	 *
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * Gets the end.
	 *
	 * @return the end
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * Gets the num pages.
	 *
	 * @return the num pages
	 */
	public int getNumPages() {
		return numPages;
	}

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * Gets the page.
	 *
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * Checks for next page.
	 *
	 * @return true, if successful
	 */
	protected boolean hasNextPage() {
		return nextUri != null && this.nextUri.length() > 0;
	}

	/**
	 * Fetch next page.
	 *
	 * @throws TwilioRestException the twilio rest exception
	 */
	protected void fetchNextPage() throws TwilioRestException {
		// Fetch the next page and reset accounts
		TwilioRestResponse response = this.getClient().get(nextUri);
		this.parseResponse(response);
	}

	/**
	 * Gets the page data.
	 *
	 * @return the page data
	 */
	public List<T> getPageData() {
		if (!this.isLoaded()) {
			try {
				this.load(this.filters);
			} catch (TwilioRestException e) {
				throw new RuntimeException(e);
			}
		}
		
		return Collections.unmodifiableList(this.pageData);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#parseResponse(com.twilio.sdk.TwilioRestResponse)
	 */
	@Override
	protected void parseResponse(TwilioRestResponse response) {
		this.nextUri = null;
		// Setup paging
		Map<String, Object> data = response.toMap();
		this.nextUri = (String) data.get(response.getParser()
				.getPagingPropertyKey(PagingProperty.NEXT_PAGE_URI_KEY));

		this.start = this.getIntValue(data.get(response.getParser()
				.getPagingPropertyKey(PagingProperty.START_KEY)));
		this.end = this.getIntValue(data.get(response.getParser()
				.getPagingPropertyKey(PagingProperty.END_KEY)));
		this.page = this.getIntValue(data.get(response.getParser()
				.getPagingPropertyKey(PagingProperty.PAGE_KEY)));
		this.numPages = this.getIntValue(data.get(response.getParser()
				.getPagingPropertyKey(PagingProperty.NUM_PAGES_KEY)));
		this.total = this.getIntValue(data.get(response.getParser()
				.getPagingPropertyKey(PagingProperty.TOTAL_KEY)));

		// Setup data
		this.pageData = this.toList(response);
	}

	/**
	 * Gets the int value.
	 *
	 * @param data the data
	 * @return the int value
	 */
	private int getIntValue(Object data) {
		if (data instanceof Integer) {
			return (Integer) data;
		}
		if (data instanceof String) {
			return Integer.parseInt((String) data);
		}

		return -1;
	}

	/**
	 * Create a new object of type T. Since we cannot construct new T() on a
	 * generic T we need to create a correctly typed object at runtime via this
	 * method call.
	 *
	 * @param client the client
	 * @param params the params
	 * @return a fully constructed object of type T
	 */
	protected abstract T makeNew(TwilioRestClient client,
			Map<String, Object> params);

	/**
	 * Returns the string key for finding this list of objects in the response.
	 * For example:
	 * 
	 * <TwilioResponse> <Accounts> <Account> </Account> <Account> </Account>
	 * </Accounts> </TwilioResponse>
	 * 
	 * this should return "Accounts"
	 * 
	 * @return the string key for finding this list objects in the response
	 */
	protected abstract String getListKey();

	/**
	 * To list.
	 *
	 * @param response the response
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	protected List<T> toList(TwilioRestResponse response) {
		List<T> returnList = new ArrayList<T>();

		// Right now only json responses are used
		Map<String, Object> list = response.toMap();
        Object content = list.get(this.getListKey());
		if (content instanceof List) {
			List<Object> objs = (List<Object>) list.get(this.getListKey());

			for (Object o : objs) {
                extract_object(returnList, o);
            }
		}
        else if (content instanceof Map) { /* Some filters on lists returns only one element, this makes the response consistent */
            extract_object(returnList, ((Map) content).values().iterator().next());
        }

		return returnList;
	}

    private void extract_object(List<T> returnList, Object o) {
        if (o instanceof Map) {
            T instance = this.makeNew(this.getClient(),
                    (Map<String, Object>) o);
            ((Resource)instance).setRequestAccountSid(this.getRequestAccountSid());
            returnList.add(instance);
        }
    }
}
