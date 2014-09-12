package com.twilio.sdk.resource.instance;

import com.twilio.sdk.resource.list.UsageRecordList;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class UsageRecordTest extends BasicRequestTester {

	@Test
	public void testListingUsageRecords() throws Exception {

		try {
			setExpectedServerAnswer("recordtestanswer.xml");
			UsageRecordList usageRecords = restClient.getAccount().getUsageRecords();

			TreeSet<UsageRecord> answers = new TreeSet<UsageRecord>(new Comparator<UsageRecord>() {
				@Override
				public int compare(UsageRecord o1, UsageRecord o2) {
					UsageCategory o1_category = o1.getCategory();
					UsageCategory o2_category = o2.getCategory();
					if (o1_category == null) {
						return 1;
					} else if (o2_category == null) {
						return -1;
					} else {
						return o1_category.compareTo(o2_category);
					}
				}
			});
			Iterator<UsageRecord> usageRecordsIter = usageRecords.iterator();

			while (usageRecordsIter.hasNext()) { // iter is of type Iterator<String>
				answers.add(usageRecordsIter.next());
			}

			Iterator<UsageRecord> usageRecordIterator = answers.iterator();

			UsageRecord current;
			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.calleridlookups);
			assertEquals(current.getDescription(), "Caller ID Lookups");

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.calls);
			assertEquals(current.getUsage(), new BigDecimal(50));

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.calls_client);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.calls_inbound);
			assertEquals(current.getUsage(), new BigDecimal(24));

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.calls_inbound_local);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.calls_inbound_tollfree);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.calls_outbound);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.calls_sip);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.phonenumbers_local);
			assertEquals(current.getUsage(), new BigDecimal(1));

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.phonenumbers);
			assertEquals(current.getUsage(), new BigDecimal(1));

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.phonenumbers_tollfree);
			assertEquals(current.getUsage(), new BigDecimal(0));

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.shortcodes);
			assertEquals(current.getUsage(), new BigDecimal(0));

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.shortcodes_customerowned);
			assertEquals(current.getUsage(), new BigDecimal(0));

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.shortcodes_random);
			assertEquals(current.getUsage(), new BigDecimal(0));

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.shortcodes_vanity);
			assertEquals(current.getUsage(), new BigDecimal(0));

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.sms);
			assertEquals(current.getUsage(), new BigDecimal(7));
			assertEquals(current.getPrice(), new BigDecimal("0.07"));
			assertEquals(current.getPriceUnit(), "usd");
			assertEquals(current.getUsageUnit(), "messages");
			assertEquals(current.getCountUnit(), "messages");

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.sms_inbound);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.sms_inbound_longcode);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.sms_inbound_shortcode);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.sms_outbound);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.sms_outbound_longcode);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.sms_outbound_shortcode);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.recordings);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.recordingstorage);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.transcriptions);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.totalprice);

			// Non-enumerated UsageCategories should be returned
			// to the user as null. This protectes the client from
			// changes in the API.
			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), null);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
