package com.twilio.sdk.resources;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.exceptions.InvalidRequestException;
import com.twilio.sdk.readers.Reader;

import java.util.Iterator;

public class ResourceSet<E extends Resource> implements Iterable<E> {
    protected Page<E> page;
    protected boolean autoPaging;
    protected final Reader<E> reader;
    protected final TwilioRestClient client;
    protected Iterator<E> iterator;

    public ResourceSet(final Reader<E> reader, final TwilioRestClient client, final Page<E> page) {
        this.reader = reader;
        this.client = client;
        this.page = page;
        iterator = page.getRecords().iterator();
        autoPaging = true;
    }

    public boolean isAutoPaging() {
        return autoPaging;
    }

    public void setAutoPaging(final boolean autoPaging) {
        this.autoPaging = autoPaging;
    }

    public int getPageSize() {
        return page.getPageSize();
    }

    public void setPageSize(final int pageSize) {
        reader.pageSize(pageSize);
    }

    @Override
    public Iterator<E> iterator() {
        return new ResourceSetIterator<E>(this);
    }

    protected void fetchNextPage() {
        page = reader.nextPage(page.getNextPageUri(), client);
        if (page != null) {
            iterator = page.getRecords().iterator();
        }
    }

    public static class ResourceSetIterator<E extends Resource> implements Iterator<E> {
        private final ResourceSet<E> resourceSet;

        public ResourceSetIterator(final ResourceSet<E> resourceSet) {
            this.resourceSet = resourceSet;
        }

        @Override
        public boolean hasNext() {
            if (!resourceSet.iterator.hasNext() && resourceSet.isAutoPaging()) {
                // The page is exhausted, fetch the next page
                try {
                    resourceSet.fetchNextPage();
                } catch (final InvalidRequestException | ApiConnectionException | ApiException e) {
                    throw new RuntimeException(e);
                }
            }

            return resourceSet.iterator != null && resourceSet.iterator.hasNext();
        }

        @Override
        public E next() {
            if (resourceSet.iterator != null) {
                return resourceSet.iterator.next();
            }
            return null;
        }

        @Override
        public void remove() {
            if (resourceSet.iterator != null) {
                resourceSet.iterator.remove();
            }
        }

    }
}
