package com.twilio.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.twilio.http.IRequest.FormParameters;
import com.twilio.http.IRequest.FormParameters.Type;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class IRequestTest {

    @Test
    public void testAddTextFormParameter() {
        IRequest request = new IRequest(HttpMethod.POST, "http://example.com");
        request.addFormParameter("name", Type.TEXT, "John Doe");

        List<FormParameters> params = request.getFormParameters();
        assertEquals(1, params.size());

        FormParameters param = params.get(0);
        assertEquals("name", param.getName());
        assertEquals(Type.TEXT, param.getType());
        assertEquals("John Doe", param.getValue());
    }

    @Test
    public void testAddFileFormParameter() {
        IRequest request = new IRequest(HttpMethod.POST, "http://example.com");
        String filePath = "/path/to/file.jpg";
        request.addFormParameter("file", Type.FILE, filePath);

        List<FormParameters> params = request.getFormParameters();
        assertEquals(1, params.size());

        FormParameters param = params.get(0);
        assertEquals("file", param.getName());
        assertEquals(Type.FILE, param.getType());
        assertEquals(filePath, param.getValue());
    }

    @Test
    public void testAddMultipleFormParameters() {
        IRequest request = new IRequest(HttpMethod.POST, "http://example.com");
        request.addFormParameter("name", Type.TEXT, "John Doe");
        request.addFormParameter("email", Type.TEXT, "john@example.com");
        request.addFormParameter("file", Type.FILE, "/path/to/file.jpg");

        List<FormParameters> params = request.getFormParameters();
        assertEquals(3, params.size());

        // Verify parameters were added in the correct order
        assertEquals("name", params.get(0).getName());
        assertEquals("email", params.get(1).getName());
        assertEquals("file", params.get(2).getName());
    }

    @Test
    public void testSetFormParameters() {
        IRequest request = new IRequest(HttpMethod.POST, "http://example.com");

        List<FormParameters> paramsList = new ArrayList<>();
        paramsList.add(new FormParameters("name", Type.TEXT, "John Doe"));
        paramsList.add(new FormParameters("file", Type.FILE, "/path/to/file.jpg"));

        request.setFormParameters(paramsList);

        List<FormParameters> params = request.getFormParameters();
        assertEquals(2, params.size());

        assertEquals("name", params.get(0).getName());
        assertEquals(Type.TEXT, params.get(0).getType());
        assertEquals("John Doe", params.get(0).getValue());

        assertEquals("file", params.get(1).getName());
        assertEquals(Type.FILE, params.get(1).getType());
        assertEquals("/path/to/file.jpg", params.get(1).getValue());
    }

    @Test
    public void testSetFormParametersAppends() {
        IRequest request = new IRequest(HttpMethod.POST, "http://example.com");
        request.addFormParameter("existing", Type.TEXT, "Existing Value");

        List<FormParameters> additionalParams = new ArrayList<>();
        additionalParams.add(new FormParameters("new", Type.TEXT, "New Value"));

        request.setFormParameters(additionalParams);

        List<FormParameters> params = request.getFormParameters();
        assertEquals(2, params.size());

        assertEquals("existing", params.get(0).getName());
        assertEquals("new", params.get(1).getName());
    }

    @Test
    public void testSetFormParametersNull() {
        IRequest request = new IRequest(HttpMethod.POST, "http://example.com");

        try {
            request.setFormParameters(null);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Form parameters cannot be null", e.getMessage());
        }
    }

    @Test
    public void testFormParametersInitializedInConstructor() {
        IRequest request = new IRequest(HttpMethod.POST, "http://example.com");

        List<FormParameters> params = request.getFormParameters();
        assertNotNull("FormParameters should be initialized", params);
        assertTrue("FormParameters should be empty initially", params.isEmpty());
    }

    @Test
    public void testFormParametersDomainConstructor() {
        IRequest request = new IRequest(HttpMethod.POST, "api", "/v1/resource");

        List<FormParameters> params = request.getFormParameters();
        assertNotNull("FormParameters should be initialized", params);
        assertTrue("FormParameters should be empty initially", params.isEmpty());
    }

    @Test
    public void testFormParametersRegionConstructor() {
        IRequest request = new IRequest(HttpMethod.POST, "api", "/v1/resource", "us1");

        List<FormParameters> params = request.getFormParameters();
        assertNotNull("FormParameters should be initialized", params);
        assertTrue("FormParameters should be empty initially", params.isEmpty());
    }
}
