package com.twilio.sdk.resource.list;

import com.twilio.sdk.TwilioRestClient;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * @since 2014-06-04
 */
public class UsageRecordListTest {

	private static final String ACCOUNT_SID = "AC12345678901234567890123456789012";

	private final TwilioRestClient client = mock(TwilioRestClient.class);

	@Test
	public void testGetResourceLocation() {
		UsageRecordList usageRecords = new UsageRecordList(client);
		usageRecords.setRequestAccountSid(ACCOUNT_SID);
		String expected = "/2010-04-01/Accounts/" + ACCOUNT_SID + "/Usage/Records";
		assertEquals(expected, usageRecords.getResourceLocation());
	}

	@Test
	public void testGetResourceLocationAllTime() {
		UsageRecordList usageRecords = new UsageRecordList(client, UsageRecordList.Type.ALL_TIME);
		usageRecords.setRequestAccountSid(ACCOUNT_SID);
		String expected = "/2010-04-01/Accounts/" + ACCOUNT_SID + "/Usage/Records/AllTime";
		assertEquals(expected, usageRecords.getResourceLocation());
	}

	@Test
	public void testGetResourceLocationDaily() {
		UsageRecordList usageRecords = new UsageRecordList(client, UsageRecordList.Type.DAILY);
		usageRecords.setRequestAccountSid(ACCOUNT_SID);
		String expected = "/2010-04-01/Accounts/" + ACCOUNT_SID + "/Usage/Records/Daily";
		assertEquals(expected, usageRecords.getResourceLocation());
	}

	@Test
	public void testGetResourceLocationLastMonth() {
		UsageRecordList usageRecords = new UsageRecordList(client, UsageRecordList.Type.LAST_MONTH);
		usageRecords.setRequestAccountSid(ACCOUNT_SID);
		String expected = "/2010-04-01/Accounts/" + ACCOUNT_SID + "/Usage/Records/LastMonth";
		assertEquals(expected, usageRecords.getResourceLocation());
	}

	@Test
	public void testGetResourceLocationMonthly() {
		UsageRecordList usageRecords = new UsageRecordList(client, UsageRecordList.Type.MONTHLY);
		usageRecords.setRequestAccountSid(ACCOUNT_SID);
		String expected = "/2010-04-01/Accounts/" + ACCOUNT_SID + "/Usage/Records/Monthly";
		assertEquals(expected, usageRecords.getResourceLocation());
	}

	@Test
	public void testGetResourceLocationThisMonth() {
		UsageRecordList usageRecords = new UsageRecordList(client, UsageRecordList.Type.THIS_MONTH);
		usageRecords.setRequestAccountSid(ACCOUNT_SID);
		String expected = "/2010-04-01/Accounts/" + ACCOUNT_SID + "/Usage/Records/ThisMonth";
		assertEquals(expected, usageRecords.getResourceLocation());
	}

	@Test
	public void testGetResourceLocationToday() {
		UsageRecordList usageRecords = new UsageRecordList(client, UsageRecordList.Type.TODAY);
		usageRecords.setRequestAccountSid(ACCOUNT_SID);
		String expected = "/2010-04-01/Accounts/" + ACCOUNT_SID + "/Usage/Records/Today";
		assertEquals(expected, usageRecords.getResourceLocation());
	}

	@Test
	public void testGetResourceLocationYearly() {
		UsageRecordList usageRecords = new UsageRecordList(client, UsageRecordList.Type.YEARLY);
		usageRecords.setRequestAccountSid(ACCOUNT_SID);
		String expected = "/2010-04-01/Accounts/" + ACCOUNT_SID + "/Usage/Records/Yearly";
		assertEquals(expected, usageRecords.getResourceLocation());
	}

	@Test
	public void testGetResourceLocationYesterday() {
		UsageRecordList usageRecords = new UsageRecordList(client, UsageRecordList.Type.YESTERDAY);
		usageRecords.setRequestAccountSid(ACCOUNT_SID);
		String expected = "/2010-04-01/Accounts/" + ACCOUNT_SID + "/Usage/Records/Yesterday";
		assertEquals(expected, usageRecords.getResourceLocation());
	}

}
