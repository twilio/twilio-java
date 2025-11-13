package com.twilio.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.http.TwilioRestClient;
import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Unit tests for TokenPaginationPage class.
 * These tests focus on validating the TokenPaginationPage implementation which extends the Page class
 * and provides token-based pagination functionality. The tests cover:
 * 1. Construction and getters - Verifying object initialization and property access
 * 2. queryString() methods - Testing URL query string generation for tokens
 * 3. hasNextPage() behavior - Testing the availability of next pages
 * 4. fromJson() parsing - Testing JSON deserialization with various structures
 * 5. Builder pattern - Verifying the builder implementation works correctly
 * 6. Inheritance - Confirming proper extension of Page class
 * 7. Edge cases - Testing boundary conditions and error handling
 */
public class TokenPaginationPageTest {

    private ObjectMapper mapper;
    private JsonNodeFactory factory;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
        factory = JsonNodeFactory.instance;
    }

    /**
     * Test constructor and getter methods
     */
    @Test
    public void testConstructorAndGetters() {
        // Create a sample TokenPaginationPage using Builder
        List<String> records = Arrays.asList("Record1", "Record2");
        TokenPaginationPage<String> page = createSamplePage(
            records, "test_key", "next_token", "prev_token", 10);

        // Verify the fields are set correctly through getters
        Assert.assertEquals(records, page.getRecords());
        Assert.assertEquals("test_key", page.getKey());
        Assert.assertEquals("next_token", page.getNextToken());
        Assert.assertEquals("prev_token", page.getPreviousToken());
        Assert.assertEquals(10, page.getPageSize());
    }

    /**
     * Test nextQueryString() method
     */
    @Test
    public void testNextQueryString() {
        TokenPaginationPage<String> page = createSamplePage(
                Collections.singletonList("Record"), "key", "next_token", "prev_token", 25);

        String query = page.nextQueryString();
        Assert.assertEquals("?pageSize=25&pageToken=next_token", query);
    }

    /**
     * Test previousQueryString() method
     */
    @Test
    public void testPreviousQueryString() {
        TokenPaginationPage<String> page = createSamplePage(
                Collections.singletonList("Record"), "key", "next_token", "prev_token", 25);

        String query = page.previousQueryString();
        Assert.assertEquals("?pageSize=25&pageToken=prev_token", query);
    }

    /**
     * Test queryString() method with null page token
     */
    @Test
    public void testQueryStringWithNullToken() {
        TokenPaginationPage<String> page = createSamplePage(
                Collections.singletonList("Record"), "key", null, "prev_token", 25);

        String query = page.nextQueryString();
        Assert.assertEquals("?pageSize=25", query);
    }

    /**
     * Test queryString() method with empty page token
     */
    @Test
    public void testQueryStringWithEmptyToken() {
        TokenPaginationPage<String> page = createSamplePage(
                Collections.singletonList("Record"), "key", "", "prev_token", 25);

        String query = page.nextQueryString();
        Assert.assertEquals("?pageSize=25", query);
    }

    /**
     * Test queryString() method with zero page size
     */
    @Test
    public void testQueryStringWithZeroPageSize() {
        TokenPaginationPage<String> page = createSamplePage(
                Collections.singletonList("Record"), "key", "next_token", "prev_token", 0);

        String query = page.nextQueryString();
        Assert.assertEquals("?pageToken=next_token", query);
    }

    /**
     * Test hasNextPage() method with non-null token
     */
    @Test
    public void testHasNextPageWithToken() {
        TokenPaginationPage<String> page = createSamplePage(
                Collections.singletonList("Record"), "key", "next_token", "prev_token", 25);

        Assert.assertTrue(page.hasNextPage());
    }

    /**
     * Test hasNextPage() method with null token
     */
    @Test
    public void testHasNextPageWithNullToken() {
        TokenPaginationPage<String> page = createSamplePage(
                Collections.singletonList("Record"), "key", null, "prev_token", 25);

        Assert.assertFalse(page.hasNextPage());
    }

    /**
     * Test hasNextPage() method with empty token
     */
    @Test
    public void testHasNextPageWithEmptyToken() {
        TokenPaginationPage<String> page = createSamplePage(
                Collections.singletonList("Record"), "key", "", "prev_token", 25);

        Assert.assertFalse(page.hasNextPage());
    }

    /**
     * Test fromJson() method with valid JSON with meta structure
     */
    @Test
    public void testFromJsonWithMetaStructure() throws Exception {
        // Create mock JSON with meta structure
        ObjectNode rootNode = factory.objectNode();

        // Add meta information
        ObjectNode metaNode = factory.objectNode();
        metaNode.put("key", "services");
        metaNode.put("nextToken", "next_token_123");
        metaNode.put("previousToken", "prev_token_456");
        metaNode.put("pageSize", 2);
        rootNode.set("meta", metaNode);

        // Add sample records
        ArrayNode recordsNode = factory.arrayNode();
        ObjectNode record1 = factory.objectNode();
        record1.put("id", "id1");
        record1.put("friendlyName", "Test Service 1");
        recordsNode.add(record1);

        ObjectNode record2 = factory.objectNode();
        record2.put("id", "id2");
        record2.put("friendlyName", "Test Service 2");
        recordsNode.add(record2);

        rootNode.set("services", recordsNode);

        String json = mapper.writeValueAsString(rootNode);

        // Parse JSON using fromJson
        TokenPaginationPage<TestRecord> page = TokenPaginationPage.fromJson(
            "services", json, TestRecord.class, mapper);

        // Verify the page
        Assert.assertEquals("services", page.getKey());
        Assert.assertEquals("next_token_123", page.getNextToken());
        Assert.assertEquals("prev_token_456", page.getPreviousToken());
        Assert.assertEquals(2, page.getPageSize());
        Assert.assertEquals(2, page.getRecords().size());
        Assert.assertEquals("id1", page.getRecords().get(0).getId());
        Assert.assertEquals("Test Service 1", page.getRecords().get(0).getFriendlyName());
        Assert.assertEquals("id2", page.getRecords().get(1).getId());
        Assert.assertEquals("Test Service 2", page.getRecords().get(1).getFriendlyName());
    }

    /**
     * Test fromJson() method with invalid JSON
     */
    @Test(expected = ApiConnectionException.class)
    public void testFromJsonWithInvalidJson() {
        String invalidJson = "{invalid json}";
        TokenPaginationPage.fromJson("records", invalidJson, TestRecord.class, mapper);
    }

    /**
     * Test fromJson() method with missing key field
     */
    @Test(expected = ApiException.class)
    public void testFromJsonWithMissingKey() throws Exception {
        // Create mock JSON with missing key
        ObjectNode rootNode = factory.objectNode();
        ObjectNode metaNode = factory.objectNode();
        // No key field
        metaNode.put("nextToken", "next_token_123");
        metaNode.put("pageSize", 2);
        rootNode.set("meta", metaNode);

        // Add sample records
        ArrayNode recordsNode = factory.arrayNode();
        ObjectNode record1 = factory.objectNode();
        record1.put("id", "id1");
        recordsNode.add(record1);
        rootNode.set("services", recordsNode);

        String json = mapper.writeValueAsString(rootNode);

        // Parse JSON using fromJson
        TokenPaginationPage.fromJson(
            "services", json, TestRecord.class, mapper);
    }

    /**
     * Test fromJson() method with null token values
     */
    @Test
    public void testFromJsonWithNullTokens() throws Exception {
        // Create mock JSON with null tokens
        ObjectNode rootNode = factory.objectNode();
        ObjectNode metaNode = factory.objectNode();
        metaNode.put("key", "services");
        metaNode.putNull("nextToken");
        metaNode.putNull("previousToken");
        metaNode.put("pageSize", 2);
        rootNode.set("meta", metaNode);

        // Add sample records
        ArrayNode recordsNode = factory.arrayNode();
        recordsNode.add(factory.objectNode().put("id", "id1").put("friendlyName", "Test Service 1"));
        rootNode.set("services", recordsNode);

        String json = mapper.writeValueAsString(rootNode);

        // Parse JSON using fromJson
        TokenPaginationPage<TestRecord> page = TokenPaginationPage.fromJson(
            "services", json, TestRecord.class, mapper);

        // Verify null tokens are handled properly
        Assert.assertNull(page.getNextToken());
        Assert.assertNull(page.getPreviousToken());
        Assert.assertFalse(page.hasNextPage());
    }

    /**
     * Test fromJson() method with missing pageSize
     */
    @Test(expected = ApiException.class)
    public void testFromJsonWithMissingPageSize() throws Exception {
        // Create mock JSON without pageSize
        ObjectNode rootNode = factory.objectNode();
        ObjectNode metaNode = factory.objectNode();
        metaNode.put("key", "services");
        metaNode.put("nextToken", "next_token_123");
        // No pageSize field
        rootNode.set("meta", metaNode);

        // Add sample records
        ArrayNode recordsNode = factory.arrayNode();
        recordsNode.add(factory.objectNode().put("id", "id1"));
        recordsNode.add(factory.objectNode().put("id", "id2"));
        rootNode.set("services", recordsNode);

        String json = mapper.writeValueAsString(rootNode);

        // Parse JSON using fromJson
        TokenPaginationPage.fromJson(
            "services", json, TestRecord.class, mapper);
    }

    /**
     * Test fromJson() method with empty records array
     */
    @Test
    public void testFromJsonWithEmptyRecords() throws Exception {
        // Create mock JSON with empty records array
        ObjectNode rootNode = factory.objectNode();
        ObjectNode metaNode = factory.objectNode();
        metaNode.put("key", "services");
        metaNode.put("nextToken", "next_token_123");
        metaNode.put("pageSize", 0);
        rootNode.set("meta", metaNode);

        // Empty records array
        ArrayNode recordsNode = factory.arrayNode();
        rootNode.set("services", recordsNode);

        String json = mapper.writeValueAsString(rootNode);

        // Parse JSON using fromJson
        TokenPaginationPage<TestRecord> page = TokenPaginationPage.fromJson(
            "services", json, TestRecord.class, mapper);

        // Verify empty records are handled properly
        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(0, page.getRecords().size());
        Assert.assertEquals(0, page.getPageSize());
    }

    /**
     * Test handling of non-existent record key in JSON
     */
    @Test(expected = ApiException.class)
    public void testFromJsonWithNonExistentRecordKey() throws Exception {
        // Create mock JSON
        ObjectNode rootNode = factory.objectNode();
        ObjectNode metaNode = factory.objectNode();
        metaNode.put("key", "non_existent_key");
        metaNode.put("nextToken", "next_token");
        metaNode.put("pageSize", 5);
        rootNode.set("meta", metaNode);

        // Records under a different key than what will be requested
        ArrayNode recordsNode = factory.arrayNode();
        recordsNode.add(factory.objectNode().put("id", "id1"));
        rootNode.set("services", recordsNode);

        String json = mapper.writeValueAsString(rootNode);

        // Parse JSON using fromJson with wrong record key
        TokenPaginationPage<TestRecord> page = TokenPaginationPage.fromJson(
            "wrong_key", json, TestRecord.class, mapper);
    }

    /**
     * Test Builder pattern for TokenPaginationPage
     */
    @Test
    public void testBuilderPattern() {
        List<String> records = Arrays.asList("Record1", "Record2");

        // Create page using builder pattern
        TokenPaginationPage<String> page = new TokenPaginationPage.Builder<String>()
            .records(records)
            .key("test_key")
            .nextToken("next_token")
            .previousToken("prev_token")
            .pageSize(10)
            .build();

        // Verify the fields
        Assert.assertEquals(records, page.getRecords());
        Assert.assertEquals("test_key", page.getKey());
        Assert.assertEquals("next_token", page.getNextToken());
        Assert.assertEquals("prev_token", page.getPreviousToken());
        Assert.assertEquals(10, page.getPageSize());
    }

    /**
     * Test inheritance relationship - TokenPaginationPage extends Page
     */
    @Test
    public void testInheritanceRelationship() {
        List<String> records = Arrays.asList("Record1", "Record2");
        TokenPaginationPage<String> page = createSamplePage(
            records, "test_key", "next_token", "prev_token", 10);

        // Verify that TokenPaginationPage is a Page
        Assert.assertTrue(page instanceof Page);

        // Verify that a TokenPaginationPage can be used as a Page
        Page<String> basePage = page;
        Assert.assertEquals(records, basePage.getRecords());
        Assert.assertEquals(10, basePage.getPageSize());
    }

    /**
     * Test the overridden hasNextPage() method behaves correctly
     */
    @Test
    public void testOverriddenHasNextPageMethod() {
        // Create a page with nextToken - hasNextPage should return true
        TokenPaginationPage<String> pageWithToken = createSamplePage(
                Collections.singletonList("Record"), "key", "next_token", null, 10);
        Assert.assertTrue(pageWithToken.hasNextPage());

        TokenPaginationPage<String> pageWithoutToken = createSamplePage(
                Collections.singletonList("Record"), "key", null, "prev_token", 10);
        Assert.assertFalse(pageWithoutToken.hasNextPage());
    }

    /**
     * Test TokenPaginationPage with ResourceSet
     */
    @Test
    public void testTokenPaginationPageWithResourceSet() throws Exception {
        // Create a mock reader that will return our TokenPaginationPage
        Reader<TestRecord> mockReader = new Reader<TestRecord>() {
            @Override
            public ResourceSet<TestRecord> read(TwilioRestClient client) {
                return null;
            }

            @Override
            public Page<TestRecord> firstPage(TwilioRestClient client) {
                return null;
            }

            @Override
            public Page<TestRecord> getPage(String targetUrl, TwilioRestClient client) {
                return null;
            }

            @Override
            public Page<TestRecord> nextPage(Page<TestRecord> page, TwilioRestClient client) {
                return null;
            }

            @Override
            public Page<TestRecord> previousPage(Page<TestRecord> page, TwilioRestClient client) {
                return null;
            }
        };

        // Create TokenPaginationPage
        List<TestRecord> records = Arrays.asList(
                new TestRecord("id1", "test_name1"),
                new TestRecord("id2", "test_name2")
        );
        int pageSize = 2;
        int limit = 5;
        Page<TestRecord> page = createSamplePage(
            records, "test_key", "next_token", "prev_token", pageSize);

        mockReader.limit(limit);
        mockReader.pageSize(pageSize);
        // Create ResourceSet using TokenPaginationPage
        ResourceSet<TestRecord> resourceSet = new ResourceSet<>(mockReader, null, page);

        // Verify that ResourceSet works with TokenPaginationPage
        Assert.assertNotNull(resourceSet);
        Assert.assertEquals((long)(Math.ceil((double)limit/pageSize)), resourceSet.getPageLimit());
    }

    /**
     * Helper method to create a sample TokenPaginationPage
     */
    private <T> TokenPaginationPage<T> createSamplePage(
            List<T> records, String key, String nextToken, String prevToken, int pageSize) {
        return new TokenPaginationPage.Builder<T>()
            .records(records)
            .key(key)
            .nextToken(nextToken)
            .previousToken(prevToken)
            .pageSize(pageSize)
            .build();
    }

    /**
     * Helper class for testing JSON deserialization.
     *
     * This simple POJO represents a record that can be deserialized from JSON, allowing
     * us to test the TokenPaginationPage.fromJson() method with concrete types.
     */
    public static class TestRecord extends Resource {
        @Getter
        @Setter
        private String id;
        @Getter
        @Setter
        private String friendlyName;

        public TestRecord() {}

        public TestRecord(String id, String friendlyName) {
            this.id = id;
            this.friendlyName = friendlyName;
        }
    }
}
