package com.twilio.sdk.resources;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.readers.Reader;

import java.util.Iterator;

public class Result<E extends Resource> implements Iterable<E> {
    protected Page<E> page;
    protected boolean autoPaging;
    protected Reader<E> reader;
    protected TwilioRestClient client;
    protected Iterator<E> iterator;

    public Result(Reader<E> reader, TwilioRestClient client, Page<E> page) {
        this.reader = reader;
        this.client = client;
        this.page = page;
        this.iterator = page.getRecords().iterator();
        this.autoPaging = true;
    }

    public boolean isAutoPaging() {
        return autoPaging;
    }

    public void setAutoPaging(boolean autoPaging) {
        this.autoPaging = autoPaging;
    }

    @Override
    public Iterator<E> iterator() {
        return new ResultIterator<E>(this);
    }

    protected void fetchNextPage() {
        this.page = this.reader.nextPage(this.page.getNextPageUri(), this.client);
        if (this.page != null) {
            this.iterator = this.page.getRecords().iterator();
        }
    }

    public static class ResultIterator<E extends Resource> implements Iterator<E> {
        private Result<E> result;

        public ResultIterator(Result<E> result) {
            this.result = result;
        }

        @Override
        public boolean hasNext() {
            if (!this.result.iterator.hasNext() && this.result.isAutoPaging()) {
                // The page is exhausted, fetch the next page
                this.result.fetchNextPage();
            }

            return this.result.iterator != null
                && this.result.iterator.hasNext();
        }

        @Override
        public E next() {
            if (this.result.iterator != null) {
                return this.result.iterator.next();
            }
            return null;
        }

        @Override
        public void remove() {
            if (this.result.iterator != null) {
                this.result.iterator.remove();
            }
        }

    }
}
