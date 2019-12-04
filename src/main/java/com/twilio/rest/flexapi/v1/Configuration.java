/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.flexapi.v1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.base.Resource;
import com.twilio.converter.Converter;
import com.twilio.converter.DateConverter;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import java.time.ZonedDateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Configuration extends Resource {
    private static final long serialVersionUID = 107853613982384L;

    public enum Status {
        OK("ok"),
        INPROGRESS("inprogress"),
        NOTSTARTED("notstarted");

        private final String value;

        private Status(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a Status from a string.
         * @param value string value
         * @return generated Status
         */
        @JsonCreator
        public static Status forValue(final String value) {
            return Promoter.enumFromString(value, Status.values());
        }
    }

    /**
     * Create a ConfigurationFetcher to execute fetch.
     *
     * @return ConfigurationFetcher capable of executing the fetch
     */
    public static ConfigurationFetcher fetcher() {
        return new ConfigurationFetcher();
    }

    /**
     * Create a ConfigurationCreator to execute create.
     *
     * @return ConfigurationCreator capable of executing the create
     */
    public static ConfigurationCreator creator() {
        return new ConfigurationCreator();
    }

    /**
     * Create a ConfigurationUpdater to execute update.
     *
     * @return ConfigurationUpdater capable of executing the update
     */
    public static ConfigurationUpdater updater() {
        return new ConfigurationUpdater();
    }

    /**
     * Converts a JSON String into a Configuration object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Configuration object represented by the provided JSON
     */
    public static Configuration fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Configuration.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Configuration object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Configuration object represented by the provided JSON
     */
    public static Configuration fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Configuration.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final ZonedDateTime dateCreated;
    private final ZonedDateTime dateUpdated;
    private final Map<String, Object> attributes;
    private final Configuration.Status status;
    private final String taskrouterWorkspaceSid;
    private final String taskrouterTargetWorkflowSid;
    private final String taskrouterTargetTaskqueueSid;
    private final List<Map<String, Object>> taskrouterTaskqueues;
    private final List<Map<String, Object>> taskrouterSkills;
    private final Map<String, Object> taskrouterWorkerChannels;
    private final Map<String, Object> taskrouterWorkerAttributes;
    private final String taskrouterOfflineActivitySid;
    private final URI runtimeDomain;
    private final String messagingServiceInstanceSid;
    private final String chatServiceInstanceSid;
    private final String uiLanguage;
    private final Map<String, Object> uiAttributes;
    private final String uiVersion;
    private final String serviceVersion;
    private final Boolean callRecordingEnabled;
    private final URI callRecordingWebhookUrl;
    private final Boolean crmEnabled;
    private final String crmType;
    private final URI crmCallbackUrl;
    private final URI crmFallbackUrl;
    private final Map<String, Object> crmAttributes;
    private final Map<String, Object> publicAttributes;
    private final Boolean pluginServiceEnabled;
    private final Map<String, Object> pluginServiceAttributes;
    private final List<Map<String, Object>> integrations;
    private final Map<String, Object> outboundCallFlows;
    private final List<String> serverlessServiceSids;
    private final URI url;

    @JsonCreator
    private Configuration(@JsonProperty("account_sid")
                          final String accountSid,
                          @JsonProperty("date_created")
                          final String dateCreated,
                          @JsonProperty("date_updated")
                          final String dateUpdated,
                          @JsonProperty("attributes")
                          final Map<String, Object> attributes,
                          @JsonProperty("status")
                          final Configuration.Status status,
                          @JsonProperty("taskrouter_workspace_sid")
                          final String taskrouterWorkspaceSid,
                          @JsonProperty("taskrouter_target_workflow_sid")
                          final String taskrouterTargetWorkflowSid,
                          @JsonProperty("taskrouter_target_taskqueue_sid")
                          final String taskrouterTargetTaskqueueSid,
                          @JsonProperty("taskrouter_taskqueues")
                          final List<Map<String, Object>> taskrouterTaskqueues,
                          @JsonProperty("taskrouter_skills")
                          final List<Map<String, Object>> taskrouterSkills,
                          @JsonProperty("taskrouter_worker_channels")
                          final Map<String, Object> taskrouterWorkerChannels,
                          @JsonProperty("taskrouter_worker_attributes")
                          final Map<String, Object> taskrouterWorkerAttributes,
                          @JsonProperty("taskrouter_offline_activity_sid")
                          final String taskrouterOfflineActivitySid,
                          @JsonProperty("runtime_domain")
                          final URI runtimeDomain,
                          @JsonProperty("messaging_service_instance_sid")
                          final String messagingServiceInstanceSid,
                          @JsonProperty("chat_service_instance_sid")
                          final String chatServiceInstanceSid,
                          @JsonProperty("ui_language")
                          final String uiLanguage,
                          @JsonProperty("ui_attributes")
                          final Map<String, Object> uiAttributes,
                          @JsonProperty("ui_version")
                          final String uiVersion,
                          @JsonProperty("service_version")
                          final String serviceVersion,
                          @JsonProperty("call_recording_enabled")
                          final Boolean callRecordingEnabled,
                          @JsonProperty("call_recording_webhook_url")
                          final URI callRecordingWebhookUrl,
                          @JsonProperty("crm_enabled")
                          final Boolean crmEnabled,
                          @JsonProperty("crm_type")
                          final String crmType,
                          @JsonProperty("crm_callback_url")
                          final URI crmCallbackUrl,
                          @JsonProperty("crm_fallback_url")
                          final URI crmFallbackUrl,
                          @JsonProperty("crm_attributes")
                          final Map<String, Object> crmAttributes,
                          @JsonProperty("public_attributes")
                          final Map<String, Object> publicAttributes,
                          @JsonProperty("plugin_service_enabled")
                          final Boolean pluginServiceEnabled,
                          @JsonProperty("plugin_service_attributes")
                          final Map<String, Object> pluginServiceAttributes,
                          @JsonProperty("integrations")
                          final List<Map<String, Object>> integrations,
                          @JsonProperty("outbound_call_flows")
                          final Map<String, Object> outboundCallFlows,
                          @JsonProperty("serverless_service_sids")
                          final List<String> serverlessServiceSids,
                          @JsonProperty("url")
                          final URI url) {
        this.accountSid = accountSid;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.attributes = attributes;
        this.status = status;
        this.taskrouterWorkspaceSid = taskrouterWorkspaceSid;
        this.taskrouterTargetWorkflowSid = taskrouterTargetWorkflowSid;
        this.taskrouterTargetTaskqueueSid = taskrouterTargetTaskqueueSid;
        this.taskrouterTaskqueues = taskrouterTaskqueues;
        this.taskrouterSkills = taskrouterSkills;
        this.taskrouterWorkerChannels = taskrouterWorkerChannels;
        this.taskrouterWorkerAttributes = taskrouterWorkerAttributes;
        this.taskrouterOfflineActivitySid = taskrouterOfflineActivitySid;
        this.runtimeDomain = runtimeDomain;
        this.messagingServiceInstanceSid = messagingServiceInstanceSid;
        this.chatServiceInstanceSid = chatServiceInstanceSid;
        this.uiLanguage = uiLanguage;
        this.uiAttributes = uiAttributes;
        this.uiVersion = uiVersion;
        this.serviceVersion = serviceVersion;
        this.callRecordingEnabled = callRecordingEnabled;
        this.callRecordingWebhookUrl = callRecordingWebhookUrl;
        this.crmEnabled = crmEnabled;
        this.crmType = crmType;
        this.crmCallbackUrl = crmCallbackUrl;
        this.crmFallbackUrl = crmFallbackUrl;
        this.crmAttributes = crmAttributes;
        this.publicAttributes = publicAttributes;
        this.pluginServiceEnabled = pluginServiceEnabled;
        this.pluginServiceAttributes = pluginServiceAttributes;
        this.integrations = integrations;
        this.outboundCallFlows = outboundCallFlows;
        this.serverlessServiceSids = serverlessServiceSids;
        this.url = url;
    }

    /**
     * Returns The The SID of the Account that created the resource.
     *
     * @return The SID of the Account that created the resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The The ISO 8601 date and time in GMT when the Configuration resource
     * was created.
     *
     * @return The ISO 8601 date and time in GMT when the Configuration resource
     *         was created
     */
    public final ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The The ISO 8601 date and time in GMT when the Configuration resource
     * was last updated.
     *
     * @return The ISO 8601 date and time in GMT when the Configuration resource
     *         was last updated
     */
    public final ZonedDateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The An object that contains application-specific data.
     *
     * @return An object that contains application-specific data
     */
    public final Map<String, Object> getAttributes() {
        return this.attributes;
    }

    /**
     * Returns The The status of the Flex onboarding.
     *
     * @return The status of the Flex onboarding
     */
    public final Configuration.Status getStatus() {
        return this.status;
    }

    /**
     * Returns The The SID of the TaskRouter Workspace.
     *
     * @return The SID of the TaskRouter Workspace
     */
    public final String getTaskrouterWorkspaceSid() {
        return this.taskrouterWorkspaceSid;
    }

    /**
     * Returns The The SID of the TaskRouter target Workflow.
     *
     * @return The SID of the TaskRouter target Workflow
     */
    public final String getTaskrouterTargetWorkflowSid() {
        return this.taskrouterTargetWorkflowSid;
    }

    /**
     * Returns The The SID of the TaskRouter Target TaskQueue.
     *
     * @return The SID of the TaskRouter Target TaskQueue
     */
    public final String getTaskrouterTargetTaskqueueSid() {
        return this.taskrouterTargetTaskqueueSid;
    }

    /**
     * Returns The The list of TaskRouter TaskQueues.
     *
     * @return The list of TaskRouter TaskQueues
     */
    public final List<Map<String, Object>> getTaskrouterTaskqueues() {
        return this.taskrouterTaskqueues;
    }

    /**
     * Returns The The Skill description for TaskRouter workers.
     *
     * @return The Skill description for TaskRouter workers
     */
    public final List<Map<String, Object>> getTaskrouterSkills() {
        return this.taskrouterSkills;
    }

    /**
     * Returns The The TaskRouter default channel capacities and availability for
     * workers.
     *
     * @return The TaskRouter default channel capacities and availability for
     *         workers
     */
    public final Map<String, Object> getTaskrouterWorkerChannels() {
        return this.taskrouterWorkerChannels;
    }

    /**
     * Returns The The TaskRouter Worker attributes.
     *
     * @return The TaskRouter Worker attributes
     */
    public final Map<String, Object> getTaskrouterWorkerAttributes() {
        return this.taskrouterWorkerAttributes;
    }

    /**
     * Returns The The TaskRouter SID of the offline activity.
     *
     * @return The TaskRouter SID of the offline activity
     */
    public final String getTaskrouterOfflineActivitySid() {
        return this.taskrouterOfflineActivitySid;
    }

    /**
     * Returns The The URL where the Flex instance is hosted.
     *
     * @return The URL where the Flex instance is hosted
     */
    public final URI getRuntimeDomain() {
        return this.runtimeDomain;
    }

    /**
     * Returns The The SID of the Messaging service instance.
     *
     * @return The SID of the Messaging service instance
     */
    public final String getMessagingServiceInstanceSid() {
        return this.messagingServiceInstanceSid;
    }

    /**
     * Returns The The SID of the chat service this user belongs to.
     *
     * @return The SID of the chat service this user belongs to
     */
    public final String getChatServiceInstanceSid() {
        return this.chatServiceInstanceSid;
    }

    /**
     * Returns The The primary language of the Flex UI.
     *
     * @return The primary language of the Flex UI
     */
    public final String getUiLanguage() {
        return this.uiLanguage;
    }

    /**
     * Returns The The object that describes Flex UI characteristics and settings.
     *
     * @return The object that describes Flex UI characteristics and settings
     */
    public final Map<String, Object> getUiAttributes() {
        return this.uiAttributes;
    }

    /**
     * Returns The The Pinned UI version.
     *
     * @return The Pinned UI version
     */
    public final String getUiVersion() {
        return this.uiVersion;
    }

    /**
     * Returns The The Flex Service version.
     *
     * @return The Flex Service version
     */
    public final String getServiceVersion() {
        return this.serviceVersion;
    }

    /**
     * Returns The Whether call recording is enabled.
     *
     * @return Whether call recording is enabled
     */
    public final Boolean getCallRecordingEnabled() {
        return this.callRecordingEnabled;
    }

    /**
     * Returns The The call recording webhook URL.
     *
     * @return The call recording webhook URL
     */
    public final URI getCallRecordingWebhookUrl() {
        return this.callRecordingWebhookUrl;
    }

    /**
     * Returns The Whether CRM is present for Flex.
     *
     * @return Whether CRM is present for Flex
     */
    public final Boolean getCrmEnabled() {
        return this.crmEnabled;
    }

    /**
     * Returns The The CRM Type.
     *
     * @return The CRM Type
     */
    public final String getCrmType() {
        return this.crmType;
    }

    /**
     * Returns The The CRM Callback URL.
     *
     * @return The CRM Callback URL
     */
    public final URI getCrmCallbackUrl() {
        return this.crmCallbackUrl;
    }

    /**
     * Returns The The CRM Fallback URL.
     *
     * @return The CRM Fallback URL
     */
    public final URI getCrmFallbackUrl() {
        return this.crmFallbackUrl;
    }

    /**
     * Returns The An object that contains the CRM attributes.
     *
     * @return An object that contains the CRM attributes
     */
    public final Map<String, Object> getCrmAttributes() {
        return this.crmAttributes;
    }

    /**
     * Returns The The list of public attributes.
     *
     * @return The list of public attributes
     */
    public final Map<String, Object> getPublicAttributes() {
        return this.publicAttributes;
    }

    /**
     * Returns The Whether the plugin service enabled.
     *
     * @return Whether the plugin service enabled
     */
    public final Boolean getPluginServiceEnabled() {
        return this.pluginServiceEnabled;
    }

    /**
     * Returns The The plugin service attributes.
     *
     * @return The plugin service attributes
     */
    public final Map<String, Object> getPluginServiceAttributes() {
        return this.pluginServiceAttributes;
    }

    /**
     * Returns The A list of objects that contain the configurations for the
     * Integrations supported in this configuration.
     *
     * @return A list of objects that contain the configurations for the
     *         Integrations supported in this configuration
     */
    public final List<Map<String, Object>> getIntegrations() {
        return this.integrations;
    }

    /**
     * Returns The The list of outbound call flows.
     *
     * @return The list of outbound call flows
     */
    public final Map<String, Object> getOutboundCallFlows() {
        return this.outboundCallFlows;
    }

    /**
     * Returns The The list of serverless service SIDs.
     *
     * @return The list of serverless service SIDs
     */
    public final List<String> getServerlessServiceSids() {
        return this.serverlessServiceSids;
    }

    /**
     * Returns The The absolute URL of the Configuration resource.
     *
     * @return The absolute URL of the Configuration resource
     */
    public final URI getUrl() {
        return this.url;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Configuration other = (Configuration) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(attributes, other.attributes) &&
               Objects.equals(status, other.status) &&
               Objects.equals(taskrouterWorkspaceSid, other.taskrouterWorkspaceSid) &&
               Objects.equals(taskrouterTargetWorkflowSid, other.taskrouterTargetWorkflowSid) &&
               Objects.equals(taskrouterTargetTaskqueueSid, other.taskrouterTargetTaskqueueSid) &&
               Objects.equals(taskrouterTaskqueues, other.taskrouterTaskqueues) &&
               Objects.equals(taskrouterSkills, other.taskrouterSkills) &&
               Objects.equals(taskrouterWorkerChannels, other.taskrouterWorkerChannels) &&
               Objects.equals(taskrouterWorkerAttributes, other.taskrouterWorkerAttributes) &&
               Objects.equals(taskrouterOfflineActivitySid, other.taskrouterOfflineActivitySid) &&
               Objects.equals(runtimeDomain, other.runtimeDomain) &&
               Objects.equals(messagingServiceInstanceSid, other.messagingServiceInstanceSid) &&
               Objects.equals(chatServiceInstanceSid, other.chatServiceInstanceSid) &&
               Objects.equals(uiLanguage, other.uiLanguage) &&
               Objects.equals(uiAttributes, other.uiAttributes) &&
               Objects.equals(uiVersion, other.uiVersion) &&
               Objects.equals(serviceVersion, other.serviceVersion) &&
               Objects.equals(callRecordingEnabled, other.callRecordingEnabled) &&
               Objects.equals(callRecordingWebhookUrl, other.callRecordingWebhookUrl) &&
               Objects.equals(crmEnabled, other.crmEnabled) &&
               Objects.equals(crmType, other.crmType) &&
               Objects.equals(crmCallbackUrl, other.crmCallbackUrl) &&
               Objects.equals(crmFallbackUrl, other.crmFallbackUrl) &&
               Objects.equals(crmAttributes, other.crmAttributes) &&
               Objects.equals(publicAttributes, other.publicAttributes) &&
               Objects.equals(pluginServiceEnabled, other.pluginServiceEnabled) &&
               Objects.equals(pluginServiceAttributes, other.pluginServiceAttributes) &&
               Objects.equals(integrations, other.integrations) &&
               Objects.equals(outboundCallFlows, other.outboundCallFlows) &&
               Objects.equals(serverlessServiceSids, other.serverlessServiceSids) &&
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            dateCreated,
                            dateUpdated,
                            attributes,
                            status,
                            taskrouterWorkspaceSid,
                            taskrouterTargetWorkflowSid,
                            taskrouterTargetTaskqueueSid,
                            taskrouterTaskqueues,
                            taskrouterSkills,
                            taskrouterWorkerChannels,
                            taskrouterWorkerAttributes,
                            taskrouterOfflineActivitySid,
                            runtimeDomain,
                            messagingServiceInstanceSid,
                            chatServiceInstanceSid,
                            uiLanguage,
                            uiAttributes,
                            uiVersion,
                            serviceVersion,
                            callRecordingEnabled,
                            callRecordingWebhookUrl,
                            crmEnabled,
                            crmType,
                            crmCallbackUrl,
                            crmFallbackUrl,
                            crmAttributes,
                            publicAttributes,
                            pluginServiceEnabled,
                            pluginServiceAttributes,
                            integrations,
                            outboundCallFlows,
                            serverlessServiceSids,
                            url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("attributes", attributes)
                          .add("status", status)
                          .add("taskrouterWorkspaceSid", taskrouterWorkspaceSid)
                          .add("taskrouterTargetWorkflowSid", taskrouterTargetWorkflowSid)
                          .add("taskrouterTargetTaskqueueSid", taskrouterTargetTaskqueueSid)
                          .add("taskrouterTaskqueues", taskrouterTaskqueues)
                          .add("taskrouterSkills", taskrouterSkills)
                          .add("taskrouterWorkerChannels", taskrouterWorkerChannels)
                          .add("taskrouterWorkerAttributes", taskrouterWorkerAttributes)
                          .add("taskrouterOfflineActivitySid", taskrouterOfflineActivitySid)
                          .add("runtimeDomain", runtimeDomain)
                          .add("messagingServiceInstanceSid", messagingServiceInstanceSid)
                          .add("chatServiceInstanceSid", chatServiceInstanceSid)
                          .add("uiLanguage", uiLanguage)
                          .add("uiAttributes", uiAttributes)
                          .add("uiVersion", uiVersion)
                          .add("serviceVersion", serviceVersion)
                          .add("callRecordingEnabled", callRecordingEnabled)
                          .add("callRecordingWebhookUrl", callRecordingWebhookUrl)
                          .add("crmEnabled", crmEnabled)
                          .add("crmType", crmType)
                          .add("crmCallbackUrl", crmCallbackUrl)
                          .add("crmFallbackUrl", crmFallbackUrl)
                          .add("crmAttributes", crmAttributes)
                          .add("publicAttributes", publicAttributes)
                          .add("pluginServiceEnabled", pluginServiceEnabled)
                          .add("pluginServiceAttributes", pluginServiceAttributes)
                          .add("integrations", integrations)
                          .add("outboundCallFlows", outboundCallFlows)
                          .add("serverlessServiceSids", serverlessServiceSids)
                          .add("url", url)
                          .toString();
    }
}
