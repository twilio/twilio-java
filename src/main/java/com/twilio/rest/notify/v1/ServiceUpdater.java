/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.notify.v1;

import com.twilio.base.Updater;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
public class ServiceUpdater extends Updater<Service> {
    private final String pathSid;
    private String friendlyName;
    private String apnCredentialSid;
    private String gcmCredentialSid;
    private String messagingServiceSid;
    private String facebookMessengerPageId;
    private String defaultApnNotificationProtocolVersion;
    private String defaultGcmNotificationProtocolVersion;
    private String fcmCredentialSid;
    private String defaultFcmNotificationProtocolVersion;
    private Boolean logEnabled;
    private String alexaSkillId;
    private String defaultAlexaNotificationProtocolVersion;
    private String deliveryCallbackUrl;
    private Boolean deliveryCallbackEnabled;

    /**
     * Construct a new ServiceUpdater.
     *
     * @param pathSid The unique string that identifies the resource
     */
    public ServiceUpdater(final String pathSid) {
        this.pathSid = pathSid;
    }

    /**
     * A descriptive string that you create to describe the resource. It can be up
     * to 64 characters long..
     *
     * @param friendlyName A string to describe the resource
     * @return this
     */
    public ServiceUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The SID of the
     * [Credential](https://www.twilio.com/docs/notify/api/credential-resource) to
     * use for APN Bindings..
     *
     * @param apnCredentialSid The SID of the Credential to use for APN Bindings
     * @return this
     */
    public ServiceUpdater setApnCredentialSid(final String apnCredentialSid) {
        this.apnCredentialSid = apnCredentialSid;
        return this;
    }

    /**
     * The SID of the
     * [Credential](https://www.twilio.com/docs/notify/api/credential-resource) to
     * use for GCM Bindings..
     *
     * @param gcmCredentialSid The SID of the Credential to use for GCM Bindings
     * @return this
     */
    public ServiceUpdater setGcmCredentialSid(final String gcmCredentialSid) {
        this.gcmCredentialSid = gcmCredentialSid;
        return this;
    }

    /**
     * The SID of the [Messaging
     * Service](https://www.twilio.com/docs/sms/send-messages#messaging-services) to
     * use for SMS Bindings. This parameter must be set in order to send SMS
     * notifications..
     *
     * @param messagingServiceSid The SID of the Messaging Service to use for SMS
     *                            Bindings
     * @return this
     */
    public ServiceUpdater setMessagingServiceSid(final String messagingServiceSid) {
        this.messagingServiceSid = messagingServiceSid;
        return this;
    }

    /**
     * Deprecated..
     *
     * @param facebookMessengerPageId Deprecated
     * @return this
     */
    public ServiceUpdater setFacebookMessengerPageId(final String facebookMessengerPageId) {
        this.facebookMessengerPageId = facebookMessengerPageId;
        return this;
    }

    /**
     * The protocol version to use for sending APNS notifications. Can be overridden
     * on a Binding by Binding basis when creating a
     * [Binding](https://www.twilio.com/docs/notify/api/binding-resource) resource..
     *
     * @param defaultApnNotificationProtocolVersion The protocol version to use for
     *                                              sending APNS notifications
     * @return this
     */
    public ServiceUpdater setDefaultApnNotificationProtocolVersion(final String defaultApnNotificationProtocolVersion) {
        this.defaultApnNotificationProtocolVersion = defaultApnNotificationProtocolVersion;
        return this;
    }

    /**
     * The protocol version to use for sending GCM notifications. Can be overridden
     * on a Binding by Binding basis when creating a
     * [Binding](https://www.twilio.com/docs/notify/api/binding-resource) resource..
     *
     * @param defaultGcmNotificationProtocolVersion The protocol version to use for
     *                                              sending GCM notifications
     * @return this
     */
    public ServiceUpdater setDefaultGcmNotificationProtocolVersion(final String defaultGcmNotificationProtocolVersion) {
        this.defaultGcmNotificationProtocolVersion = defaultGcmNotificationProtocolVersion;
        return this;
    }

    /**
     * The SID of the
     * [Credential](https://www.twilio.com/docs/notify/api/credential-resource) to
     * use for FCM Bindings..
     *
     * @param fcmCredentialSid The SID of the Credential to use for FCM Bindings
     * @return this
     */
    public ServiceUpdater setFcmCredentialSid(final String fcmCredentialSid) {
        this.fcmCredentialSid = fcmCredentialSid;
        return this;
    }

    /**
     * The protocol version to use for sending FCM notifications. Can be overridden
     * on a Binding by Binding basis when creating a
     * [Binding](https://www.twilio.com/docs/notify/api/binding-resource) resource..
     *
     * @param defaultFcmNotificationProtocolVersion The protocol version to use for
     *                                              sending FCM notifications
     * @return this
     */
    public ServiceUpdater setDefaultFcmNotificationProtocolVersion(final String defaultFcmNotificationProtocolVersion) {
        this.defaultFcmNotificationProtocolVersion = defaultFcmNotificationProtocolVersion;
        return this;
    }

    /**
     * Whether to log notifications. Can be: `true` or `false` and the default is
     * `true`..
     *
     * @param logEnabled Whether to log notifications
     * @return this
     */
    public ServiceUpdater setLogEnabled(final Boolean logEnabled) {
        this.logEnabled = logEnabled;
        return this;
    }

    /**
     * Deprecated..
     *
     * @param alexaSkillId Deprecated
     * @return this
     */
    public ServiceUpdater setAlexaSkillId(final String alexaSkillId) {
        this.alexaSkillId = alexaSkillId;
        return this;
    }

    /**
     * Deprecated..
     *
     * @param defaultAlexaNotificationProtocolVersion Deprecated
     * @return this
     */
    public ServiceUpdater setDefaultAlexaNotificationProtocolVersion(final String defaultAlexaNotificationProtocolVersion) {
        this.defaultAlexaNotificationProtocolVersion = defaultAlexaNotificationProtocolVersion;
        return this;
    }

    /**
     * URL to send delivery status callback..
     *
     * @param deliveryCallbackUrl Webhook URL
     * @return this
     */
    public ServiceUpdater setDeliveryCallbackUrl(final String deliveryCallbackUrl) {
        this.deliveryCallbackUrl = deliveryCallbackUrl;
        return this;
    }

    /**
     * Callback configuration that enables delivery callbacks, default false.
     *
     * @param deliveryCallbackEnabled Enable delivery callbacks
     * @return this
     */
    public ServiceUpdater setDeliveryCallbackEnabled(final Boolean deliveryCallbackEnabled) {
        this.deliveryCallbackEnabled = deliveryCallbackEnabled;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Service
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Service update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.NOTIFY.toString(),
            "/v1/Services/" + this.pathSid + ""
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Service update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Service.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (apnCredentialSid != null) {
            request.addPostParam("ApnCredentialSid", apnCredentialSid);
        }

        if (gcmCredentialSid != null) {
            request.addPostParam("GcmCredentialSid", gcmCredentialSid);
        }

        if (messagingServiceSid != null) {
            request.addPostParam("MessagingServiceSid", messagingServiceSid);
        }

        if (facebookMessengerPageId != null) {
            request.addPostParam("FacebookMessengerPageId", facebookMessengerPageId);
        }

        if (defaultApnNotificationProtocolVersion != null) {
            request.addPostParam("DefaultApnNotificationProtocolVersion", defaultApnNotificationProtocolVersion);
        }

        if (defaultGcmNotificationProtocolVersion != null) {
            request.addPostParam("DefaultGcmNotificationProtocolVersion", defaultGcmNotificationProtocolVersion);
        }

        if (fcmCredentialSid != null) {
            request.addPostParam("FcmCredentialSid", fcmCredentialSid);
        }

        if (defaultFcmNotificationProtocolVersion != null) {
            request.addPostParam("DefaultFcmNotificationProtocolVersion", defaultFcmNotificationProtocolVersion);
        }

        if (logEnabled != null) {
            request.addPostParam("LogEnabled", logEnabled.toString());
        }

        if (alexaSkillId != null) {
            request.addPostParam("AlexaSkillId", alexaSkillId);
        }

        if (defaultAlexaNotificationProtocolVersion != null) {
            request.addPostParam("DefaultAlexaNotificationProtocolVersion", defaultAlexaNotificationProtocolVersion);
        }

        if (deliveryCallbackUrl != null) {
            request.addPostParam("DeliveryCallbackUrl", deliveryCallbackUrl);
        }

        if (deliveryCallbackEnabled != null) {
            request.addPostParam("DeliveryCallbackEnabled", deliveryCallbackEnabled.toString());
        }
    }
}