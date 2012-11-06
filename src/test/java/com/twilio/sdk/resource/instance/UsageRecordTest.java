package com.twilio.sdk.resource.instance;

import com.twilio.sdk.resource.list.UsageRecordList;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import static org.junit.Assert.*;

public class UsageRecordTest  extends BasicRequestTester {

    @Test
    public void testUnmarshal() throws Exception {
        setExpectedServerAnswer("recordtestanswer.xml");
        UsageRecordList usageRecords = client.getAccount().getUsageRecords();

        TreeSet<UsageRecord> answers = new TreeSet<UsageRecord>(new Comparator<UsageRecord>(){
            @Override
            public int compare(UsageRecord o1, UsageRecord o2) {
                return o1.getCategory().compareTo(o2.getCategory());
            }
        });
        Iterator<UsageRecord> usageRecordsIter = usageRecords.iterator();

        while(usageRecordsIter.hasNext()) { // iter is of type Iterator<String>
            answers.add(usageRecordsIter.next());
        }

        Iterator<UsageRecord> usageRecordIterator = answers.iterator();

        UsageRecord current;
        current = usageRecordIterator.next();
        assertEquals(current.getCategory(), RecordCategory.calleridlookups);
        assertEquals(current.getDescription(), "Caller ID Lookups");

        current = usageRecordIterator.next();
        assertEquals(current.getCategory(), RecordCategory.calls);
        assertEquals(current.getUsage(), new BigDecimal(50));

        current = usageRecordIterator.next();
        assertEquals(current.getCategory(), RecordCategory.calls_client);

        current = usageRecordIterator.next();
        assertEquals(current.getCategory(), RecordCategory.calls_inbound);
        assertEquals(current.getUsage(), new BigDecimal(24));


        current = usageRecordIterator.next();
        assertEquals(current.getCategory(), RecordCategory.calls_inbound_local);

        current = usageRecordIterator.next();
        assertEquals(current.getCategory(), RecordCategory.calls_inbound_tollfree);

        current = usageRecordIterator.next();
        assertEquals(current.getCategory(), RecordCategory.calls_outbound);

        current = usageRecordIterator.next();
        assertEquals(current.getCategory(), RecordCategory.phonenumbers_local);
        assertEquals(current.getUsage(), new BigDecimal(1));

        current = usageRecordIterator.next();
        assertEquals(current.getCategory(), RecordCategory.phonenumbers);
        assertEquals(current.getUsage(), new BigDecimal(1));

        current = usageRecordIterator.next();
        assertEquals(current.getCategory(), RecordCategory.phonenumbers_tollfree);
        assertEquals(current.getUsage(), new BigDecimal(0));

        current = usageRecordIterator.next();
        assertEquals(current.getCategory(), RecordCategory.shortcodes);
        assertEquals(current.getUsage(), new BigDecimal(0));

        current = usageRecordIterator.next();
        assertEquals(current.getCategory(), RecordCategory.shortcodes_customerowned);
        assertEquals(current.getUsage(), new BigDecimal(0));

        current = usageRecordIterator.next();
        assertEquals(current.getCategory(), RecordCategory.shortcodes_random);
        assertEquals(current.getUsage(), new BigDecimal(0));

        current = usageRecordIterator.next();
        assertEquals(current.getCategory(), RecordCategory.shortcodes_vanity);
        assertEquals(current.getUsage(), new BigDecimal(0));

        current = usageRecordIterator.next();
        assertEquals(current.getCategory(), RecordCategory.sms);
        assertEquals(current.getUsage(), new BigDecimal(7));
        assertEquals(current.getPrice(), new BigDecimal("0.07"));
        assertEquals(current.getPriceUnit(), "usd");

    }

}
