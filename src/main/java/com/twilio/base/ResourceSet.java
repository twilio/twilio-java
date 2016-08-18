package com.twilio.base;

import com.twilio.http.TwilioRestClient;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A collection of resources.
 *
 * @param <E> type of the resource
 */
public class ResourceSet<E extends Resource> implements Iterable<E> {

    private final Reader<E> reader;
    private final TwilioRestClient client;

    private boolean autoPaging;
    private long pages = 1;
    private long pageLimit = Long.MAX_VALUE;
    private long processed = 0;
    private Page<E> page;
    private Iterator<E> iterator;

    /**
     * Initialize the resource set.
     *
     * @param reader reader used to fetch next page
     * @param client client used to make requests
     * @param page page of data
     */
    public ResourceSet(final Reader<E> reader, final TwilioRestClient client, final Page<E> page) {
        this.reader = reader;
        this.client = client;
        this.page = page;
        this.iterator = page.getRecords().iterator();
        this.autoPaging = true;

        if (reader.getLimit() != null) {
            this.pageLimit = (long)(Math.ceil((double)reader.getLimit() / (double)page.getPageSize()));
        }
    }

    public boolean isAutoPaging() {
        return autoPaging;
    }

    public ResourceSet setAutoPaging(final boolean autoPaging) {
        this.autoPaging = autoPaging;
        return this;
    }

    public Integer getPageSize() {
        return page.getPageSize();
    }

    public ResourceSet<E> setPageSize(final int pageSize) {
        reader.pageSize(pageSize);
        return this;
    }

    public Long getLimit() {
        return reader.getLimit();
    }

    public ResourceSet<E> setLimit(final long limit) {
        reader.limit(limit);
        return this;
    }

    public long getPageLimit() {
        return pageLimit;
    }

    @Override
    public Iterator<E> iterator() {
        return new ResourceSetIterator<>(this);
    }

    private void fetchNextPage() {
        if (!page.hasNextPage() || pages >= pageLimit) {
            return;
        }

        pages++;
        page = reader.nextPage(page, client);
        iterator = page.getRecords().iterator();
    }

    private class ResourceSetIterator<E extends Resource> implements Iterator<E> {
        private final ResourceSet<E> resourceSet;

        public ResourceSetIterator(final ResourceSet<E> resourceSet) {
            this.resourceSet = resourceSet;
        }

        @Override
        public boolean hasNext() {
            if (resourceSet.getLimit() != null && resourceSet.processed >= resourceSet.getLimit()) {
                return false;
            }

            return resourceSet.iterator.hasNext();
        }

        @Override
        public E next() {
            if (resourceSet == null || resourceSet.iterator == null) {
                throw new NoSuchElementException();
            }

            E element = resourceSet.iterator.next();
            if (resourceSet.isAutoPaging() && !resourceSet.iterator.hasNext()) {
                resourceSet.fetchNextPage();
            }

            resourceSet.processed++;
            return element;
        }

        @Override
        public void remove() {
            if (resourceSet.iterator != null) {
                resourceSet.processed++;
                resourceSet.iterator.remove();
            }
        }

    }
}
