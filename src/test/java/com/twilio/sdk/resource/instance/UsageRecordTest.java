package com.twilio.sdk.resource.instance;

import com.twilio.sdk.resource.list.UsageRecordList;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class UsageRecordTest extends BasicRequestTester {

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	@Before
	public void setup() throws Exception {
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

	private void validateCurrentDates(UsageRecord usageRecord) throws ParseException {
		assertEquals(format.parse("2012-10-26"), usageRecord.getStartDate());
		assertEquals(format.parse("2012-11-05"), usageRecord.getEndDate());
	}

	@Test
	public void testListingUsageRecords() throws Exception {

		try {
			setExpectedServerAnswer("recordtestanswer.xml");
			UsageRecordList usageRecords = client.getAccount().getUsageRecords();

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
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.calls);
			assertEquals(current.getUsage(), new BigDecimal(50));
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.calls_client);
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.calls_inbound);
			assertEquals(current.getUsage(), new BigDecimal(24));
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.calls_inbound_local);
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.calls_inbound_tollfree);
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.calls_outbound);
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.calls_sip);
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.phonenumbers_local);
			assertEquals(current.getUsage(), new BigDecimal(1));
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.phonenumbers);
			assertEquals(current.getUsage(), new BigDecimal(1));
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.phonenumbers_tollfree);
			assertEquals(current.getUsage(), new BigDecimal(0));
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.shortcodes);
			assertEquals(current.getUsage(), new BigDecimal(0));
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.shortcodes_customerowned);
			assertEquals(current.getUsage(), new BigDecimal(0));
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.shortcodes_random);
			assertEquals(current.getUsage(), new BigDecimal(0));
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.shortcodes_vanity);
			assertEquals(current.getUsage(), new BigDecimal(0));
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.sms);
			assertEquals(current.getUsage(), new BigDecimal(7));
			assertEquals(current.getPrice(), new BigDecimal("0.07"));
			assertEquals(current.getPriceUnit(), "usd");
			assertEquals(current.getUsageUnit(), "messages");
			assertEquals(current.getCountUnit(), "messages");
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.sms_inbound);
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.sms_inbound_longcode);
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.sms_inbound_shortcode);
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.sms_outbound);
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.sms_outbound_longcode);
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.sms_outbound_shortcode);
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.recordings);
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.recordingstorage);
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.transcriptions);
			validateCurrentDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.totalprice);
			validateCurrentDates(current);

			// Non-enumerated UsageCategories should be returned
			// to the user as null. This protectes the client from
			// changes in the API.
			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), null);
			validateCurrentDates(current);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private void validateCurrentForSubResourceDates(UsageRecord usageRecord) throws ParseException {
		assertEquals(format.parse("2010-01-10"), usageRecord.getStartDate());
		assertEquals(format.parse("2014-01-10"), usageRecord.getEndDate());
	}

	@Test
	public void testListingUsageRecordsSubResource() throws Exception {
		try {
			setExpectedServerAnswer("recordalltimetestanswer.xml");
			UsageRecordList usageRecords = client.getAccount().getUsageRecords(UsageRecordList.Type.ALL_TIME);

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
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.calls);
			assertEquals(current.getUsage(), new BigDecimal(50));
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.calls_client);
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.calls_inbound);
			assertEquals(current.getUsage(), new BigDecimal(24));
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.calls_inbound_local);
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.calls_inbound_tollfree);
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.calls_outbound);
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.calls_sip);
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.phonenumbers_local);
			assertEquals(current.getUsage(), new BigDecimal(1));
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.phonenumbers);
			assertEquals(current.getUsage(), new BigDecimal(1));
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.phonenumbers_tollfree);
			assertEquals(current.getUsage(), new BigDecimal(0));
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.shortcodes);
			assertEquals(current.getUsage(), new BigDecimal(0));
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.shortcodes_customerowned);
			assertEquals(current.getUsage(), new BigDecimal(0));
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.shortcodes_random);
			assertEquals(current.getUsage(), new BigDecimal(0));
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.shortcodes_vanity);
			assertEquals(current.getUsage(), new BigDecimal(0));
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.sms);
			assertEquals(current.getUsage(), new BigDecimal(7));
			assertEquals(current.getPrice(), new BigDecimal("0.07"));
			assertEquals(current.getPriceUnit(), "usd");
			assertEquals(current.getUsageUnit(), "messages");
			assertEquals(current.getCountUnit(), "messages");
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.sms_inbound);
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.sms_inbound_longcode);
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.sms_inbound_shortcode);
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.sms_outbound);
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.sms_outbound_longcode);
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.sms_outbound_shortcode);
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.recordings);
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.recordingstorage);
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.transcriptions);
			validateCurrentForSubResourceDates(current);

			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), UsageCategory.totalprice);
			validateCurrentForSubResourceDates(current);

			// Non-enumerated UsageCategories should be returned
			// to the user as null. This protectes the client from
			// changes in the API.
			current = usageRecordIterator.next();
			assertEquals(current.getCategory(), null);
			validateCurrentForSubResourceDates(current);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
