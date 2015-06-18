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
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public static String toJSON(final WorkflowConfiguration configuration) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
        mapper.writeValue(out, configuration);
        final String workflowJSON = out.toString();
        return workflowJSON;
	}
	
	/**
	 * Converts a JSON workflow configuration to a workflow configuration object
	 * @param configurationJSON JSON for workflow configuration
	 * @return a workflow configuration object
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static WorkflowConfiguration parse(final String configurationJSON) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(configurationJSON, WorkflowConfiguration.class);
	}
}
