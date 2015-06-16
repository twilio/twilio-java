package com.twilio.sdk.taskrouter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class WorkflowBuilder {

	/**
	 * Converts a workflow configuration to JSON
	 * @param configuration workflow config object
	 * @return JSON for workflow configuration
	 */
	public static String parse(WorkflowConfiguration configuration) {
		ObjectMapper mapper = new ObjectMapper();
		final ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            mapper.writeValue(out, configuration);
        } catch (final JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        final String workflowJSON = out.toString();
		
        return workflowJSON;
	}
	
	/**
	 * Converts a JSON workflow configuration to a workflow configuration object
	 * @param configurationJSON JSON for workflow configuration
	 * @return a workflow configuration object
	 */
	public static WorkflowConfiguration parse(String configurationJSON) {
		ObjectMapper mapper = new ObjectMapper();

		WorkflowConfiguration configuration = null;
		try {
			configuration = mapper.readValue(configurationJSON, WorkflowConfiguration.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return configuration;
	}
}
