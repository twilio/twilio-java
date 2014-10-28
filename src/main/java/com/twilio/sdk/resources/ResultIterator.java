package com.twilio.sdk.resources;

import java.util.Iterator;

public class ResultIterator<E> implements Iterator<E> {
    private Result<E> result;
    private Iterator<E> iterator;


    public ResultIterator(Result<E> result, Iterator<E> iterator) {
        this.result = result;
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        if (!this.iterator.hasNext()) {
            this.iterator = this.result.fetchNextPage();
        }

        return this.iterator != null && this.iterator.hasNext();
    }

    @Override
    public E next() {
        if (this.iterator != null) {
            return this.iterator.next();
        }
        return null;
    }

    @Override
    public void remove() {
        if (this.iterator != null) {
            this.iterator.remove();
        }
    }
}
