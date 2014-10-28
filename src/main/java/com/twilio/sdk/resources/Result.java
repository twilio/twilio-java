package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.sdk.clients.TwilioRestClient;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@JsonIgnoreProperties({ "end", "start", "last_page_uri", "num_pages", "page", "page_size", "start", "total" })
abstract public class Result<T> implements Iterable<T> {
    protected List<T> records;
    protected String firstPageUri;
    protected String nextPageUri;
    protected String previousPageUri;
    protected String uri;
    protected boolean autoPage = true;
    protected TwilioRestClient client;

    public String getFirstPageUri() {
        return firstPageUri;
    }

    public String getNextPageUri() {
        return nextPageUri;
    }

    public String getPreviousPageUri() {
        return previousPageUri;
    }

    public String getUri() {
        return uri;
    }

    public Result<T> setAutoPage(boolean autoPage) {
        this.autoPage = autoPage;
        return this;
    }

    public Result<T> setRestClient(TwilioRestClient client) {
        this.client = client;
        return this;
    }

    @Override
    public Iterator<T> iterator() {
        return new ResultIterator<T>(this, this.records.iterator());
    }

    public static <S> S fromJson(String json, Class<S> valueType) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(json, valueType);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e.getMessage());
        } catch (JsonParseException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public abstract Iterator<T> fetchNextPage();
}
