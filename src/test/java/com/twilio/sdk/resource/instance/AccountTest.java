package com.twilio.sdk.resource.instance;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.parser.ResponseParser;
import com.twilio.sdk.resource.list.UsageRecordList;
import com.twilio.sdk.resource.list.UsageRecordList.Type;

@SuppressWarnings("unchecked")
public class AccountTest {
    final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

    final String accountSid = "AC12345678901234567890123456789012";
    final String otherAccountSid = "ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    final String authToken = "abcd1234";
    TwilioRestClient client = mock(TwilioRestClient.class);
    TwilioRestResponse resp = mock(TwilioRestResponse.class);
    String formattedDate;
    HashMap<String, Object> map;

    @Before
    public void setupMocks() {
        map = new HashMap<String, Object>();
        stub(resp.toMap()).toReturn(map);
        formattedDate = dateFormat.format(new Date());

        map.put("date_created", formattedDate);
        map.put("date_updated", formattedDate);
        map.put("owner_account_sid", accountSid);
        map.put("friendly_name", "second account");
        map.put("status", "active");
        map.put("auth_token", authToken);
        map.put("sid", otherAccountSid);

        ResponseParser mockParser = Mockito.mock(ResponseParser.class);
        Mockito.when(resp.getParser()).thenReturn(mockParser);
    }

    /**
     * This test creates a {@link Account} object by interacting with a {@link TwilioRestClient}.
     *
     * @throws com.twilio.sdk.TwilioRestException
     */
    @Test
    public void testCreation() throws TwilioRestException {

        stub(
                client.safeRequest(Matchers.eq("/2010-04-01/Accounts/" + otherAccountSid + ".json"),
                        Matchers.eq("GET"), Matchers.any(Map.class)))
                .toReturn(resp);
        Account a = new Account(client, map);
        a.setRequestAccountSid(accountSid);

        assertTrue(a.getSid().equals(otherAccountSid));
    }

    @Test
    public void testDailyUsageRecords() throws TwilioRestException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("UsageRecords", getUsageRecords(2));

        Mockito.when(resp.toMap()).thenReturn(map);

        Mockito.when(
                client.safeRequest(Mockito.anyString(), Mockito.anyString(),
                        Mockito.anyMap())).thenReturn(resp);

        Account account = new Account(client, map);
        UsageRecordList usageRecordList = account.getUsageRecords(Type.DAILY);
        Assert.assertNotNull(usageRecordList);
        Assert.assertEquals(2, usageRecordList.getPageData().size());
    }

    @Test
    public void testDailyUsageRecordsWithParams() throws TwilioRestException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("UsageRecords", getUsageRecords(1));

        Mockito.when(resp.toMap()).thenReturn(map);

        Mockito.when(
                client.safeRequest(Mockito.anyString(), Mockito.anyString(),
                        Mockito.anyMap())).thenReturn(resp);

        Account account = new Account(client, map);
        Map<String, String> params = new HashMap<String, String>();
        params.put("Category", "sms");
		UsageRecordList usageRecordList = account.getUsageRecords(params,
				Type.DAILY);
        Assert.assertNotNull(usageRecordList);
        Assert.assertEquals(1, usageRecordList.getPageData().size());
    }

    @Test
    public void testMonthlyUsageRecords() throws TwilioRestException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("UsageRecords", getUsageRecords(2));

        Mockito.when(resp.toMap()).thenReturn(map);

        Mockito.when(
                client.safeRequest(Mockito.anyString(), Mockito.anyString(),
                        Mockito.anyMap())).thenReturn(resp);

        Account account = new Account(client, map);
        UsageRecordList usageRecordList = account.getUsageRecords(Type.MONTHLY);
        Assert.assertNotNull(usageRecordList);
        Assert.assertEquals(2, usageRecordList.getPageData().size());
    }

    @Test
    public void testMonthlyUsageRecordsWithParams() throws TwilioRestException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("UsageRecords", getUsageRecords(1));

        Mockito.when(resp.toMap()).thenReturn(map);

        Mockito.when(
                client.safeRequest(Mockito.anyString(), Mockito.anyString(),
                        Mockito.anyMap())).thenReturn(resp);

        Account account = new Account(client, map);
        Map<String, String> params = new HashMap<String, String>();
        params.put("Category", "sms");
		UsageRecordList usageRecordList = account.getUsageRecords(params,
				Type.MONTHLY);
        Assert.assertNotNull(usageRecordList);
        Assert.assertEquals(1, usageRecordList.getPageData().size());
    }

    @Test
    public void testYearlyUsageRecords() throws TwilioRestException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("UsageRecords", getUsageRecords(2));

        Mockito.when(resp.toMap()).thenReturn(map);

        Mockito.when(
                client.safeRequest(Mockito.anyString(), Mockito.anyString(),
                        Mockito.anyMap())).thenReturn(resp);

        Account account = new Account(client, map);
        UsageRecordList usageRecordList = account.getUsageRecords(Type.YEARLY);
        Assert.assertNotNull(usageRecordList);
        Assert.assertEquals(2, usageRecordList.getPageData().size());
    }

    @Test
    public void testYearlyUsageRecordsWithParams() throws TwilioRestException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("UsageRecords", getUsageRecords(1));

        Mockito.when(resp.toMap()).thenReturn(map);

        Mockito.when(
                client.safeRequest(Mockito.anyString(), Mockito.anyString(),
                        Mockito.anyMap())).thenReturn(resp);

        Account account = new Account(client, map);
        Map<String, String> params = new HashMap<String, String>();
        params.put("Category", "sms");
		UsageRecordList usageRecordList = account.getUsageRecords(params,
				Type.YEARLY);
        Assert.assertNotNull(usageRecordList);
        Assert.assertEquals(1, usageRecordList.getPageData().size());
    }

    @Test
    public void testTodaysUsageRecords() throws TwilioRestException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("UsageRecords", getUsageRecords(2));

        Mockito.when(resp.toMap()).thenReturn(map);

        Mockito.when(
                client.safeRequest(Mockito.anyString(), Mockito.anyString(),
                        Mockito.anyMap())).thenReturn(resp);

        Account account = new Account(client, map);
        UsageRecordList usageRecordList = account.getUsageRecords(Type.TODAY);
        Assert.assertNotNull(usageRecordList);
        Assert.assertEquals(2, usageRecordList.getPageData().size());
    }

    @Test
    public void testTodaysUsageRecordsWithParams() throws TwilioRestException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("UsageRecords", getUsageRecords(1));

        Mockito.when(resp.toMap()).thenReturn(map);

        Mockito.when(
                client.safeRequest(Mockito.anyString(), Mockito.anyString(),
                        Mockito.anyMap())).thenReturn(resp);

        Account account = new Account(client, map);
        Map<String, String> params = new HashMap<String, String>();
        params.put("Category", "sms");
		UsageRecordList usageRecordList = account.getUsageRecords(params,
				Type.TODAY);
        Assert.assertNotNull(usageRecordList);
        Assert.assertEquals(1, usageRecordList.getPageData().size());
    }

    @Test
    public void testYesterdaysUsageRecords() throws TwilioRestException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("UsageRecords", getUsageRecords(2));

        Mockito.when(resp.toMap()).thenReturn(map);

        Mockito.when(
                client.safeRequest(Mockito.anyString(), Mockito.anyString(),
                        Mockito.anyMap())).thenReturn(resp);

        Account account = new Account(client, map);
		UsageRecordList usageRecordList = account
				.getUsageRecords(Type.YESTERDAY);
        Assert.assertNotNull(usageRecordList);
        Assert.assertEquals(2, usageRecordList.getPageData().size());
    }

    @Test
    public void testYesterdaysUsageRecordsWithParams() throws TwilioRestException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("UsageRecords", getUsageRecords(1));

        Mockito.when(resp.toMap()).thenReturn(map);

        Mockito.when(
                client.safeRequest(Mockito.anyString(), Mockito.anyString(),
                        Mockito.anyMap())).thenReturn(resp);

        Account account = new Account(client, map);
        Map<String, String> params = new HashMap<String, String>();
        params.put("Category", "sms");
		UsageRecordList usageRecordList = account.getUsageRecords(params,
				Type.YESTERDAY);
        Assert.assertNotNull(usageRecordList);
        Assert.assertEquals(1, usageRecordList.getPageData().size());
    }

    @Test
    public void testThisMonthsUsageRecords() throws TwilioRestException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("UsageRecords", getUsageRecords(2));

        Mockito.when(resp.toMap()).thenReturn(map);

        Mockito.when(
                client.safeRequest(Mockito.anyString(), Mockito.anyString(),
                        Mockito.anyMap())).thenReturn(resp);

        Account account = new Account(client, map);
		UsageRecordList usageRecordList = account
				.getUsageRecords(Type.THIS_MONTH);
        Assert.assertNotNull(usageRecordList);
        Assert.assertEquals(2, usageRecordList.getPageData().size());
    }

    @Test
    public void testThisMonthsUsageRecordsWithParams() throws TwilioRestException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("UsageRecords", getUsageRecords(1));

        Mockito.when(resp.toMap()).thenReturn(map);

        Mockito.when(
                client.safeRequest(Mockito.anyString(), Mockito.anyString(),
                        Mockito.anyMap())).thenReturn(resp);

        Account account = new Account(client, map);
        Map<String, String> params = new HashMap<String, String>();
        params.put("Category", "sms");
		UsageRecordList usageRecordList = account.getUsageRecords(params,
				Type.THIS_MONTH);
        Assert.assertNotNull(usageRecordList);
        Assert.assertEquals(1, usageRecordList.getPageData().size());
    }

    @Test
    public void testLastMonthsUsageRecords() throws TwilioRestException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("UsageRecords", getUsageRecords(2));

        Mockito.when(resp.toMap()).thenReturn(map);

        Mockito.when(
                client.safeRequest(Mockito.anyString(), Mockito.anyString(),
                        Mockito.anyMap())).thenReturn(resp);

        Account account = new Account(client, map);
		UsageRecordList usageRecordList = account
				.getUsageRecords(Type.LAST_MONTH);
        Assert.assertNotNull(usageRecordList);
        Assert.assertEquals(2, usageRecordList.getPageData().size());
    }

    @Test
    public void testLastMonthsUsageRecordsWithParams() throws TwilioRestException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("UsageRecords", getUsageRecords(1));

        Mockito.when(resp.toMap()).thenReturn(map);

        Mockito.when(
                client.safeRequest(Mockito.anyString(), Mockito.anyString(),
                        Mockito.anyMap())).thenReturn(resp);

        Account account = new Account(client, map);
        Map<String, String> params = new HashMap<String, String>();
        params.put("Category", "sms");
		UsageRecordList usageRecordList = account.getUsageRecords(params,
				Type.LAST_MONTH);
        Assert.assertNotNull(usageRecordList);
        Assert.assertEquals(1, usageRecordList.getPageData().size());
    }

    /**
     * Returns a dummy List of Usage Record properties.
     *
     * @param count number of dummy objects to be set
     * @return List of Usage Record properties.
     */
    private List<Map<String, Object>> getUsageRecords(int count) {
		List<Map<String, Object>> paramsList = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = null;
        for (int i = 0; i < count; i++) {
            params = new HashMap<String, Object>();
            paramsList.add(params);
        }
        return paramsList;
    }
}