package com.twilio.base;

import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.CallReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import static org.mockito.Mockito.when;

public class ReaderTest {

    @Mock
    TwilioRestClient client;

    @Mock
    Page<Call> page;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testNoPagingDefaults() {
        Reader<Call> reader = new CallReader();
        assertNull(reader.getLimit());
        assertNull(reader.getPageSize());
    }

    @Test
    public void testSetPageSize() {
        Reader<Call> reader = new CallReader().pageSize(100);
        assertEquals(100, reader.getPageSize().intValue());
        assertNull(reader.getLimit());
    }

    @Test
    public void testMaxPageSize() {
        Reader<Call> reader = new CallReader().pageSize(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, reader.getPageSize().intValue());
        assertNull(reader.getLimit());
    }

    @Test
    public void testSetLimit() {
        Reader<Call> reader = new CallReader().limit(100);
        assertEquals(100, reader.getLimit().intValue());
        assertEquals(100, reader.getPageSize().intValue());
    }

    @Test
    public void testSetLimitMaxPageSize() {
        Reader<Call> reader = new CallReader().limit(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, reader.getLimit().intValue());
        assertEquals(Integer.MAX_VALUE, reader.getPageSize().intValue());
    }

    @Test
    public void testSetPageSizeLimit() {
        Reader<Call> reader = new CallReader().limit(1000).pageSize(5);
        assertEquals(1000, reader.getLimit().intValue());
        assertEquals(5, reader.getPageSize().intValue());
    }

    @Test
    public void testNoPageLimit() {
        when(page.getRecords()).thenReturn(Collections.emptyList());

        Reader<Call> reader = new CallReader();
        ResourceSet<Call> set = new ResourceSet<>(reader, client, page);
        assertEquals(Long.MAX_VALUE, set.getPageLimit());
    }


    @Test
    public void testHasPageLimit() {
        when(page.getRecords()).thenReturn(Collections.emptyList());
        when(page.getPageSize()).thenReturn(50);

        Reader<Call> reader = new CallReader().limit(100);
        ResourceSet<Call> set = new ResourceSet<>(reader, client, page);
        assertEquals(2, set.getPageLimit());
    }
    @Test
    public void testUnevenHasPageLimit() {
        when(page.getRecords()).thenReturn(Collections.emptyList());
        when(page.getPageSize()).thenReturn(50);
        Reader<Call> reader = new CallReader().limit(125);
        ResourceSet<Call> set = new ResourceSet<>(reader, client, page);
        assertEquals(3, set.getPageLimit());
    }

}
