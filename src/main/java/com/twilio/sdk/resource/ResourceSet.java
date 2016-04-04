package com.twilio.sdk.resource;

import com.google.common.base.Strings;
import com.twilio.sdk.client.TwilioRestClient;
import com.twilio.sdk.exception.ApiConnectionException;
import com.twilio.sdk.exception.ApiException;
import com.twilio.sdk.exception.InvalidRequestException;
import com.twilio.sdk.reader.Reader;

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
    }

    public boolean isAutoPaging() {
        return autoPaging;
    }

    public ResourceSet setAutoPaging(final boolean autoPaging) {
        this.autoPaging = autoPaging;
        return this;
    }

    public int getPageSize() {
        return page.getPageSize();
    }

    public ResourceSet setPageSize(final int pageSize) {
        reader.pageSize(pageSize);
        return this;
    }

    @Override
    public Iterator<E> iterator() {
        return new ResourceSetIterator<>(this);
    }

    private void fetchNextPage() {
        if (Strings.isNullOrEmpty(page.getNextPageUri())) {
            return;
        }

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
            return resourceSet.iterator.hasNext();
        }

        @Override
        public E next() {
            if (resourceSet == null || resourceSet.iterator == null) {
                throw new NoSuchElementException();
            }

            E element = resourceSet.iterator.next();
            if (resourceSet.isAutoPaging() && !resourceSet.iterator.hasNext()) {
                try {
                    resourceSet.fetchNextPage();
                } catch (final InvalidRequestException | ApiConnectionException | ApiException e) {

                    // TODO: this should probably be a better exception
                    throw new RuntimeException(e);
                }
            }

            return element;
        }

        @Override
        public void remove() {
            if (resourceSet.iterator != null) {
                resourceSet.iterator.remove();
            }
        }

    }
}
