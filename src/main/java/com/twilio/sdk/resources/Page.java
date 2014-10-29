package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@JsonIgnoreProperties({ "end", "start", "last_page_uri", "num_pages", "page", "page_size", "start", "total" })
abstract public class Page<T> {
    protected List<T> records;
    protected String firstPageUri;
    protected String nextPageUri;
    protected String previousPageUri;
    protected String uri;

    public List<T> getRecords() {
        return records;
    }

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
}
