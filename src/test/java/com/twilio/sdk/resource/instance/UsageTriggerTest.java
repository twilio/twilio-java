package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.UsageTriggerFactory;
import com.twilio.sdk.resource.list.UsageTriggerList;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

import static com.twilio.sdk.resource.instance.Recurrence.daily;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UsageTriggerTest extends BasicRequestTester {

    @Test
    public void testListingUsageTrigger() throws Exception {
        setExpectedServerAnswer("usagetriggertestanswer.xml");
        UsageTriggerList usageTriggers = restClient.getAccount().getUsageTriggers();
        Iterator<UsageTrigger> usageRecordIterator = usageTriggers.iterator();

        UsageTrigger current;
        current = usageRecordIterator.next();
        assertEquals(current.getCallbackMethod(), "POST");
        assertEquals(current.getCallbackUrl(), "http://www.google.com");
        assertEquals(current.getCurrentValue(), new BigDecimal(3));
        assertEquals(current.getDateCreated(), new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH).parse("Sun, 23 Sep 2012 23:07:29 +0000"));
        assertEquals(current.getDateFired(), null);
        assertEquals(current.getDateUpdated(), new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH).parse("Sat, 29 Sep 2012 19:42:57 +0000"));
        assertEquals(current.getFriendlyName(), "a trigger");
        assertEquals(current.getRecurring(), daily);
    }

    @Test
    public void testCreateUsageTrigger() throws Exception {
        setExpectedServerAnswer("createusagetriggerresponse.xml");
        Account mainAccount = restClient.getAccount();
        UsageTriggerFactory usageTriggerFactory = mainAccount.getUsageTriggerFactory();
        HashMap<String, String> trigger = new HashMap<String, String>();
        trigger.put("FriendlyName", "blah");
        trigger.put("UsageCategory", "sms");
        trigger.put("TriggerValue", "10");
        trigger.put("CallbackUrl", "http://www.domain.net");
        UsageTrigger usageTrigger = usageTriggerFactory.create(trigger);
        assertEquals(usageTrigger.getFriendlyName(), "blah");
    }

    @Test
    public void testDeleteUsageTrigger() throws Exception {
        setExpectedServerAnswer(null);
        setExpectedServerReturnCode(204);
        UsageTrigger ut = new UsageTrigger(restClient, "UT5f539674e9b84c2ba39a4156f264a347");
        assertTrue(ut.delete());
    }

    @Test(expected = TwilioRestException.class)
    public void testDeleteErrorUsageTrigger() throws Exception {
        setExpectedServerAnswer("404onUsageTrigger.xml"); // err this is actually json but this stupid classloader wont take the .json extension
        setExpectedServerReturnCode(404);
        setExpectedServerContentType("application/json");
        UsageTrigger ut = new UsageTrigger(restClient, "UT0123456789abcdef0123456789abcdef");
        ut.delete();
    }

}
