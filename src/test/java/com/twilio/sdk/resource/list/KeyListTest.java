package com.twilio.sdk.resource.list;

import static junit.framework.Assert.*;

import com.twilio.sdk.resource.instance.BasicRequestTester;
import com.twilio.sdk.resource.instance.Key;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class KeyListTest extends BasicRequestTester {

    @Test
    public void testGetKeys() throws Exception {
        setExpectedServerReturnCode(200);
        setExpectedServerContentType("application/json");
        setExpectedServerAnswer(
            File.separator + getClass().getPackage().getName().replace(".", File.separator) +
            File.separator + "key_list.json"
        );

        List<Key> resources = restClient.getAccount(accountSid).getKeys().getPageData();

        assertEquals(1, resources.size());
        
        Key key = resources.get(0);
        assertEquals("SK6b2a7465f6d403a91e0ec189f22b5204", key.getSid());
        assertNull(key.getFriendlyName());
        assertNotNull(key.getDateCreated());
        assertNotNull(key.getDateUpdated());

        ArgumentCaptor<HttpGet> captor = ArgumentCaptor.forClass(HttpGet.class);
        Mockito.verify(httpClient).execute(captor.capture());

        HttpGet request = captor.getValue();
        assertEquals(
            "https://api.twilio.com/2010-04-01/Accounts/" + accountSid + "/Keys.json",
            request.getURI().toURL().toString()
        );
        assertEquals("GET", request.getMethod());
    }

    @Test
    public void testCreateKey() throws Exception {
        setExpectedServerReturnCode(200);
        setExpectedServerContentType("application/json");
        setExpectedServerAnswer(
            File.separator + getClass().getPackage().getName().replace(".", File.separator) +
            File.separator + "key_instance.json"
        );

        Key key = restClient.getAccount(accountSid).getKeys().create(new HashMap<String, String>());
        assertEquals("SK6b2a7465f6d403a91e0ec189f22b5204", key.getSid());
        assertNull(key.getFriendlyName());
        assertNotNull(key.getDateCreated());
        assertNotNull(key.getDateUpdated());

        ArgumentCaptor<HttpPost> captor = ArgumentCaptor.forClass(HttpPost.class);
        Mockito.verify(httpClient).execute(captor.capture());

        HttpPost request = captor.getValue();
        assertEquals(
                "https://api.twilio.com/2010-04-01/Accounts/" + accountSid + "/Keys.json",
                request.getURI().toURL().toString()
        );
        assertEquals("POST", request.getMethod());
    }


}
