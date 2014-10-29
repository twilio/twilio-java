package com.twilio.sdk.resources;

import com.twilio.sdk.clients.TwilioRestClient;

import java.util.Iterator;

public abstract class Result<E> implements Iterable<E> {
    protected Page<E> page;
    protected boolean autoPage;
    protected TwilioRestClient client;
    protected Iterator<E> iterator;

    public Result(TwilioRestClient client, Page<E> page) {
        this.client = client;
        this.page = page;
        this.iterator = page.getRecords().iterator();
        this.autoPage = true;
    }

    @Override
    public Iterator<E> iterator() {
        return new ResultIterator<E>(this);
    }

    protected abstract void fetchNextPage();

    public static class ResultIterator<E> implements Iterator<E> {
        private Result<E> result;

        public ResultIterator(Result<E> result) {
            this.result = result;
        }

        @Override
        public boolean hasNext() {
            if (!this.result.iterator.hasNext() && this.result.autoPage) {
                // The page is exhausted, attempt to fetch the next page of results
                System.out.println("Page is exhausted, fetching next page");
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
