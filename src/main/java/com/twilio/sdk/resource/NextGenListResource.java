package com.twilio.sdk.resource;

import com.twilio.sdk.TwilioClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class NextGenListResource<T extends NextGenInstanceResource, C extends TwilioClient> extends Resource<C> implements Iterable<T> {

	protected List<T> pageData;
	private String nextPageUrl = null;
	private int page = 0;
	private int pageSize = 0;
	private String previousPageUrl = null;
	private String url = null;

	public NextGenListResource(final C client) {
		this(client, new HashMap<String, String>());
	}

	public NextGenListResource(final C client, final Map<String, String> filters) {
		super(client);
		this.filters = filters;
	}

	public Iterator<T> iterator() {
		return new ListIterator(getPageData().iterator());
	}

	public List<T> getPageData() {
		if (!isLoaded()) {
			try {
				load(filters);
			} catch (TwilioRestException e) {
				throw new RuntimeException(e);
			}
		}

		return Collections.unmodifiableList(pageData);
	}

	public String getNextPageUrl() {
		return nextPageUrl;
	}

	public int getPage() {
		return page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public String getPreviousPageUrl() {
		return previousPageUrl;
	}

	public String getUrl() {
		return url;
	}

	protected boolean hasNextPage() {
		return (nextPageUrl != null && nextPageUrl.length() > 0);
	}

	protected void fetchNextPage() throws TwilioRestException {
		TwilioRestResponse response = getClient().get(nextPageUrl);
		parseResponse(response);
	}

	@Override
	protected void parseResponse(TwilioRestResponse response) {
		Map<String, Object> data = response.toMap();
		Map<String, Object> meta = (Map<String, Object>) data.get("meta");
		if (meta == null) {
			throw new RuntimeException("Paging metadata not found in Twilio response");
		}

		nextPageUrl = (String) meta.get("next_page_url");
		previousPageUrl = (String) meta.get("previous_page_url");
		page = getIntValue(meta.get("page"));
		pageSize = getIntValue(meta.get("page_size"));
		url = (String) meta.get("url");

		pageData = toList(response);
	}

	protected List<T> toList(TwilioRestResponse response) {
		List<T> returnList = new ArrayList<T>();

		Map<String, Object> data = response.toMap();
		Map<String, Object> meta = (Map<String, Object>) data.get("meta");

		String resourceKey = (String) meta.get("key");
		Object content = data.get(resourceKey);
		if (content instanceof List) {
			List<Object> objs = (List<Object>) content;

			for (Object o : objs) {
				extract_object(returnList, o);
			}
		} else if (content instanceof Map) {
			extract_object(returnList, content);
		}

		return returnList;
	}

	protected abstract T makeNew(C client, Map<String, Object> params);

	private void extract_object(List<T> returnList, Object o) {
		if (o instanceof Map) {
			T instance = makeNew(getClient(), (Map<String, Object>) o);
			returnList.add(instance);
		}
	}

	private int getIntValue(Object data) {
		if (data instanceof Integer) {
			return (Integer) data;
		}
		if (data instanceof String) {
			return Integer.parseInt((String) data);
		}

		return -1;
	}


	private class ListIterator implements Iterator<T> {
		private Iterator<T> iterator;

		public ListIterator(Iterator<T> iterator) {
			this.iterator = iterator;
		}

		public boolean hasNext() {
			return (iterator.hasNext() || hasNextPage());
		}

		public T next() {
			if (iterator.hasNext()) {
				return iterator.next();
			}

			try {
				fetchNextPage();
			} catch (TwilioRestException e) {
				throw new RuntimeException(e);
			}

			iterator = pageData.iterator();
			return iterator.next();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}


	}
}
