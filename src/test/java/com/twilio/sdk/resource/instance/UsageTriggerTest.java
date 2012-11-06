package com.twilio.sdk.resource.instance;

import com.twilio.sdk.resource.list.UsageTriggerList;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import static com.twilio.sdk.resource.instance.Recurrence.daily;
import static org.junit.Assert.assertEquals;

public class UsageTriggerTest extends BasicRequestTester {

    @Test
    public void testListingTriggerRecords() throws Exception {
        setExpectedServerAnswer("usagetriggertestanswer.xml");
        UsageTriggerList usageTriggers = client.getAccount().getUsageTriggers();
        Iterator<UsageTrigger> usageRecordIterator = usageTriggers.iterator();

        UsageTrigger current;
        current = usageRecordIterator.next();
        assertEquals(current.getCallbackMethod(), "POST");
        assertEquals(current.getCallbackUrl(), "http://www.google.com");
        assertEquals(current.getCurrentValue(), new BigDecimal(3));
        assertEquals(current.getDateCreated(), new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z").parse("Sun, 23 Sep 2012 23:07:29 +0000"));
        assertEquals(current.getDateFired(), null);
        assertEquals(current.getDateUpdated(), new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z").parse("Sat, 29 Sep 2012 19:42:57 +0000"));
        assertEquals(current.getFriendlyName(), "a trigger");
        assertEquals(current.getRecurring(), daily);

    }

}
