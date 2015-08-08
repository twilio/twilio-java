package com.twilio.sdk;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

/**
 * Author: wge
 * Date: 29/07/2015
 * Time: 21:31
 */
@RunWith(MockitoJUnitRunner.class)
public class TwilioMockRestSmsTest
{
    private static final String API = "https://api.twilio.com";
    private static final String TEST_ACCOUNT_SID = "AC0649d36d4016abb849d571b713bc65b3";
    private static final String TEST_SMS_URL = "https://api.twilio.com/2010-04-01/Accounts/" + TEST_ACCOUNT_SID + "/SMS/Messages";
    private static final String VALID_FROM_NUMBER = "+15005550006";

    @Mock
    TwilioClient mockclient;

    @Mock
    TwilioRestResponse response;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockclient = Mockito.mock(TwilioClient.class,Mockito.CALLS_REAL_METHODS);
        response = Mockito.mock(TwilioRestResponse.class);
    }

    @Test
    @Ignore //mockito not behaving itself
    public void sendPinToInvalidPhone() throws TwilioUnreachableUrlException
    {

        Map<String,Object> restException = new HashMap<String, Object>();
        restException.put("Code",21211);
        restException.put("Message", "The 'To' number +15005550001 is not a valid phone number.");

        Map<String,Object> errorMap = new HashMap();
        errorMap.put("RestException", restException);

        when(response.toMap()).thenReturn(errorMap);

        try
        {
            Map<String, String> map = new HashMap<String,String>();
         		 map.put("To", "+15005550001");
         		 map.put("From", VALID_FROM_NUMBER);
         		 map.put("Body", "test");

            TwilioRestException expectedException = new TwilioRestException("The 'To' number +15005550001 is not a valid phone number.", 21211);

            mockclient.setNumRetries(1);

            when(mockclient.safePOSTWithException(Mockito.anyString(), Mockito.anyMap())).thenThrow(expectedException);
//            when(mockclient.safePOSTWithException(Mockito.eq(TEST_SMS_URL), Mockito.anyMap())).thenThrow(expectedException);
//            when(mockclient.request(Mockito.eq(TEST_SMS_URL), Mockito.eq("POST"), Mockito.anyListOf(NameValuePair.class))).thenReturn(response);
            when(mockclient.request(Mockito.anyString(), Mockito.anyString(), Mockito.anyList())).thenReturn(response);
            mockclient.sendSms(TEST_SMS_URL, "+15005550001", VALID_FROM_NUMBER, "test");
            assertFalse(true);
        }
        catch (TwilioRestException e)
        {
            assertEquals(21211, e.getErrorCode());
            assertEquals("The 'To' number +15005550001 is not a valid phone number.", e.getMessage());
        }
    }

}
