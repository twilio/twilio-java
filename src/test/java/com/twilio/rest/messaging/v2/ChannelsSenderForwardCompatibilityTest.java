package com.twilio.rest.messaging.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ChannelsSenderForwardCompatibilityTest {

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testTopLevelUnknownFieldsIgnored() throws Exception {
        String json = "{"
            + "\"sid\": \"XE1234567890abcdef1234567890abcdef\","
            + "\"status\": \"ONLINE\","
            + "\"sender_id\": \"whatsapp:+15017122661\","
            + "\"unknown_top_level_field\": \"should be ignored\","
            + "\"another_new_field\": 12345"
            + "}";

        ChannelsSender sender = ChannelsSender.fromJson(json, objectMapper);

        assertNotNull(sender);
        assertEquals("XE1234567890abcdef1234567890abcdef", sender.getSid());
        assertEquals(ChannelsSender.Status.ONLINE, sender.getStatus());
        assertEquals("whatsapp:+15017122661", sender.getSenderId());
    }

    @Test
    public void testNestedProfileGenericResponseUnknownFieldsIgnored() throws Exception {
        String json = "{"
            + "\"sid\": \"XE1234567890abcdef1234567890abcdef\","
            + "\"status\": \"ONLINE\","
            + "\"sender_id\": \"whatsapp:+15017122661\","
            + "\"profile\": {"
            + "  \"name\": \"Test Business\","
            + "  \"about\": \"Test about\","
            + "  \"pending_display_name\": \"New Name Pending Approval\","
            + "  \"some_future_field\": true"
            + "}"
            + "}";

        ChannelsSender sender = ChannelsSender.fromJson(json, objectMapper);

        assertNotNull(sender);
        assertNotNull(sender.getProfile());
        assertEquals("Test Business", sender.getProfile().getName());
        assertEquals("Test about", sender.getProfile().getAbout());
    }

    @Test
    public void testNestedConfigurationUnknownFieldsIgnored() throws Exception {
        String json = "{"
            + "\"sid\": \"XE1234567890abcdef1234567890abcdef\","
            + "\"status\": \"ONLINE\","
            + "\"sender_id\": \"whatsapp:+15017122661\","
            + "\"configuration\": {"
            + "  \"waba_id\": \"12345678912345\","
            + "  \"verification_method\": \"sms\","
            + "  \"new_config_option\": \"future value\""
            + "}"
            + "}";

        ChannelsSender sender = ChannelsSender.fromJson(json, objectMapper);

        assertNotNull(sender);
        assertNotNull(sender.getConfiguration());
        assertEquals("12345678912345", sender.getConfiguration().getWabaId());
        assertEquals(ChannelsSender.VerificationMethod.SMS, sender.getConfiguration().getVerificationMethod());
    }

    @Test
    public void testNestedWebhookUnknownFieldsIgnored() throws Exception {
        String json = "{"
            + "\"sid\": \"XE1234567890abcdef1234567890abcdef\","
            + "\"status\": \"ONLINE\","
            + "\"sender_id\": \"whatsapp:+15017122661\","
            + "\"webhook\": {"
            + "  \"callback_url\": \"https://example.com/callback\","
            + "  \"callback_method\": \"POST\","
            + "  \"new_webhook_field\": \"retry_policy_v2\""
            + "}"
            + "}";

        ChannelsSender sender = ChannelsSender.fromJson(json, objectMapper);

        assertNotNull(sender);
        assertNotNull(sender.getWebhook());
        assertEquals("https://example.com/callback", sender.getWebhook().getCallbackUrl());
        assertEquals(ChannelsSender.CallbackMethod.POST, sender.getWebhook().getCallbackMethod());
    }

    @Test
    public void testNestedPropertiesUnknownFieldsIgnored() throws Exception {
        String json = "{"
            + "\"sid\": \"XE1234567890abcdef1234567890abcdef\","
            + "\"status\": \"ONLINE\","
            + "\"sender_id\": \"whatsapp:+15017122661\","
            + "\"properties\": {"
            + "  \"quality_rating\": \"HIGH\","
            + "  \"messaging_limit\": \"10K Customers/24hr\","
            + "  \"throughput_limit\": \"1000 msg/sec\""
            + "}"
            + "}";

        ChannelsSender sender = ChannelsSender.fromJson(json, objectMapper);

        assertNotNull(sender);
        assertNotNull(sender.getProperties());
        assertEquals("HIGH", sender.getProperties().getQualityRating());
        assertEquals("10K Customers/24hr", sender.getProperties().getMessagingLimit());
    }

    @Test
    public void testNestedComplianceUnknownFieldsIgnored() throws Exception {
        String json = "{"
            + "\"sid\": \"XE1234567890abcdef1234567890abcdef\","
            + "\"status\": \"ONLINE\","
            + "\"sender_id\": \"whatsapp:+15017122661\","
            + "\"compliance\": {"
            + "  \"registration_sid\": \"BUxxx\","
            + "  \"new_compliance_field\": \"audit_status_v2\","
            + "  \"countries\": [{"
            + "    \"country\": \"US\","
            + "    \"registration_sid\": \"BUyyy\","
            + "    \"status\": \"ONLINE\","
            + "    \"new_country_field\": \"region_code\","
            + "    \"carriers\": [{"
            + "      \"name\": \"Verizon\","
            + "      \"status\": \"APPROVED\","
            + "      \"new_carrier_field\": \"mcc_mnc\""
            + "    }]"
            + "  }]"
            + "}"
            + "}";

        ChannelsSender sender = ChannelsSender.fromJson(json, objectMapper);

        assertNotNull(sender);
        assertNotNull(sender.getCompliance());
        assertEquals("BUxxx", sender.getCompliance().getRegistrationSid());
        assertNotNull(sender.getCompliance().getCountries());
        assertEquals(1, sender.getCompliance().getCountries().size());
        assertEquals("US", sender.getCompliance().getCountries().get(0).getCountry());
        assertEquals(1, sender.getCompliance().getCountries().get(0).getCarriers().size());
        assertEquals("Verizon", sender.getCompliance().getCountries().get(0).getCarriers().get(0).getName());
    }

    @Test
    public void testNestedOfflineReasonsUnknownFieldsIgnored() throws Exception {
        String json = "{"
            + "\"sid\": \"XE1234567890abcdef1234567890abcdef\","
            + "\"status\": \"OFFLINE\","
            + "\"sender_id\": \"whatsapp:+15017122661\","
            + "\"offline_reasons\": [{"
            + "  \"code\": \"30008\","
            + "  \"message\": \"No delivery\","
            + "  \"more_info\": \"https://www.twilio.com/docs/api/errors/30008\","
            + "  \"severity\": \"critical\","
            + "  \"new_reason_field\": 42"
            + "}]"
            + "}";

        ChannelsSender sender = ChannelsSender.fromJson(json, objectMapper);

        assertNotNull(sender);
        assertNotNull(sender.getOfflineReasons());
        assertEquals(1, sender.getOfflineReasons().size());
        assertEquals("30008", sender.getOfflineReasons().get(0).getCode());
        assertEquals("No delivery", sender.getOfflineReasons().get(0).getMessage());
    }

    @Test
    public void testFullResponseWithMultipleUnknownFields() throws Exception {
        String json = "{"
            + "\"sid\": \"XE1234567890abcdef1234567890abcdef\","
            + "\"status\": \"ONLINE\","
            + "\"sender_id\": \"whatsapp:+15017122661\","
            + "\"friendly_name\": \"My Sender\","
            + "\"new_top_level_field\": \"v2_feature\","
            + "\"configuration\": {"
            + "  \"waba_id\": \"12345678912345\","
            + "  \"verification_method\": \"sms\","
            + "  \"new_config_flag\": true"
            + "},"
            + "\"webhook\": {"
            + "  \"callback_url\": \"https://example.com/callback\","
            + "  \"callback_method\": \"POST\","
            + "  \"retry_config\": {\"max_retries\": 3}"
            + "},"
            + "\"profile\": {"
            + "  \"name\": \"Test Business\","
            + "  \"about\": \"About text\","
            + "  \"pending_display_name\": \"New Pending Name\","
            + "  \"display_name_status\": \"PENDING_APPROVAL\","
            + "  \"websites\": [{\"website\": \"https://example.com\", \"label\": \"Site\", \"verified\": true}],"
            + "  \"emails\": [{\"email\": \"test@example.com\", \"label\": \"Email\", \"primary\": true}],"
            + "  \"phone_numbers\": [{\"phone_number\": \"+15017122661\", \"label\": \"Phone\", \"type\": \"mobile\"}]"
            + "},"
            + "\"properties\": {"
            + "  \"quality_rating\": \"HIGH\","
            + "  \"messaging_limit\": \"10K Customers/24hr\","
            + "  \"health_score\": 95"
            + "},"
            + "\"url\": \"https://messaging.twilio.com/v2/Channels/Senders/XE1234567890abcdef1234567890abcdef\""
            + "}";

        ChannelsSender sender = ChannelsSender.fromJson(json, objectMapper);

        assertNotNull(sender);
        assertEquals("XE1234567890abcdef1234567890abcdef", sender.getSid());
        assertEquals(ChannelsSender.Status.ONLINE, sender.getStatus());
        assertEquals("whatsapp:+15017122661", sender.getSenderId());
        assertNotNull(sender.getConfiguration());
        assertEquals("12345678912345", sender.getConfiguration().getWabaId());
        assertNotNull(sender.getWebhook());
        assertEquals("https://example.com/callback", sender.getWebhook().getCallbackUrl());
        assertNotNull(sender.getProfile());
        assertEquals("Test Business", sender.getProfile().getName());
        assertNotNull(sender.getProperties());
        assertEquals("HIGH", sender.getProperties().getQualityRating());
    }

    @Test
    public void testNullNestedObjectsStillWork() throws Exception {
        String json = "{"
            + "\"sid\": \"XE1234567890abcdef1234567890abcdef\","
            + "\"status\": \"CREATING\","
            + "\"sender_id\": \"whatsapp:+15017122661\","
            + "\"configuration\": null,"
            + "\"webhook\": null,"
            + "\"profile\": null,"
            + "\"properties\": null,"
            + "\"compliance\": null,"
            + "\"offline_reasons\": null,"
            + "\"future_field\": \"should not cause issues\""
            + "}";

        ChannelsSender sender = ChannelsSender.fromJson(json, objectMapper);

        assertNotNull(sender);
        assertEquals("XE1234567890abcdef1234567890abcdef", sender.getSid());
        assertEquals(ChannelsSender.Status.CREATING, sender.getStatus());
        assertNull(sender.getConfiguration());
        assertNull(sender.getWebhook());
        assertNull(sender.getProfile());
        assertNull(sender.getProperties());
        assertNull(sender.getCompliance());
        assertNull(sender.getOfflineReasons());
    }
}
