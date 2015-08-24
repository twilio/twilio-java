package com.twilio.sdk;

import com.twilio.sdk.pojo.SMSCriteria;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.doThrow;

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
    public void sendPinToInvalidPhoneNumber()
    {
        TwilioRestException expectedException = new TwilioRestException("The 'To' number +15005550001 is not a valid phone number.", 21211);
        try
        {
            doThrow(expectedException).when(mockclient).safePOSTWithException(Mockito.eq(TEST_SMS_URL), Mockito.anyMap());

            SMSCriteria criteria = new SMSCriteria(TEST_SMS_URL,"+15005550001",VALID_FROM_NUMBER,"test");

            mockclient.sendSms(criteria);
            assertFalse(true);
        }
        catch (TwilioRestException e)
        {
            assertEquals(21211, e.getErrorCode());
            assertEquals("The 'To' number +15005550001 is not a valid phone number.", e.getMessage());
        }
    }

    @Test
    public void cannotRouteToNumber()
    {
        TwilioRestException expectedException = new TwilioRestException("twilio cannot route to number", 21612);
        try
        {
            doThrow(expectedException).when(mockclient).safePOSTWithException(Mockito.eq(TEST_SMS_URL), Mockito.anyMap());

            SMSCriteria criteria = new SMSCriteria(TEST_SMS_URL,"+15005550001",VALID_FROM_NUMBER,"test");

            mockclient.sendSms(criteria);
            assertFalse(true);
        }
        catch (TwilioRestException e)
        {
            assertEquals(21612, e.getErrorCode());
            assertEquals("twilio cannot route to number", e.getMessage());
        }
    }

    @Test
    public void internationalPermissionsNeeded() throws TwilioUnreachableUrlException
    {
        TwilioRestException expectedException = new TwilioRestException("international permissioning needed", 21408);
        try
        {
            doThrow(expectedException).when(mockclient).safePOSTWithException(Mockito.eq(TEST_SMS_URL), Mockito.anyMap());

            SMSCriteria criteria = new SMSCriteria(TEST_SMS_URL,"+15005550001",VALID_FROM_NUMBER,"test");

            mockclient.sendSms(criteria);
            assertFalse(true);
        }
        catch (TwilioRestException e)
        {
            assertEquals(21408, e.getErrorCode());
            assertEquals("international permissioning needed", e.getMessage());
        }
    }

}
