/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.notify.v1.service;

import com.twilio.base.Creator;
import com.twilio.converter.Converter;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.util.List;
import java.util.Map;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
public class NotificationCreator extends Creator<Notification> {
    private final String pathServiceSid;
    private List<String> identity;
    private List<String> tag;
    private String body;
    private Notification.Priority priority;
    private Integer ttl;
    private String title;
    private String sound;
    private String action;
    private Map<String, Object> data;
    private Map<String, Object> apn;
    private Map<String, Object> gcm;
    private Map<String, Object> sms;
    private Map<String, Object> facebookMessenger;
    private Map<String, Object> fcm;
    private List<String> segment;
    private Map<String, Object> alexa;
    private List<String> toBinding;
    private String deliveryCallbackUrl;

    /**
     * Construct a new NotificationCreator.
     *
     * @param pathServiceSid The SID of the Service to create the resource under
     */
    public NotificationCreator(final String pathServiceSid) {
        this.pathServiceSid = pathServiceSid;
    }

    /**
     * The notification text. For FCM and GCM, translates to `data.twi_body`. For
     * APNS, translates to `aps.alert.body`. For SMS, translates to `body`. SMS
     * requires either this `body` value, or `media_urls` attribute defined in the
     * `sms` parameter of the notification..
     *
     * @param body The notification body text
     * @return this
     */
    public NotificationCreator setBody(final String body) {
        this.body = body;
        return this;
    }

    /**
     * The priority of the notification. Can be: `low` or `high` and the default is
     * `high`. A value of `low` optimizes the client app's battery consumption;
     * however, notifications may be delivered with unspecified delay. For FCM and
     * GCM, `low` priority is the same as `Normal` priority. For APNS `low`
     * priority is the same as `5`. A value of `high` sends the notification
     * immediately, and can wake up a sleeping device. For FCM and GCM, `high` is
     * the same as `High` priority. For APNS, `high` is a priority `10`. SMS does
     * not support this property..
     *
     * @param priority The priority of the notification
     * @return this
     */
    public NotificationCreator setPriority(final Notification.Priority priority) {
        this.priority = priority;
        return this;
    }

    /**
     * How long, in seconds, the notification is valid. Can be an integer between 0
     * and 2,419,200, which is 4 weeks, the default and the maximum supported time
     * to live (TTL). Delivery should be attempted if the device is offline until
     * the TTL elapses. Zero means that the notification delivery is attempted
     * immediately, only once, and is not stored for future delivery. SMS does not
     * support this property..
     *
     * @param ttl How long, in seconds, the notification is valid
     * @return this
     */
    public NotificationCreator setTtl(final Integer ttl) {
        this.ttl = ttl;
        return this;
    }

    /**
     * The notification title. For FCM and GCM, this translates to the
     * `data.twi_title` value. For APNS, this translates to the `aps.alert.title`
     * value. SMS does not support this property. This field is not visible on iOS
     * phones and tablets but appears on Apple Watch and Android devices..
     *
     * @param title The notification title
     * @return this
     */
    public NotificationCreator setTitle(final String title) {
        this.title = title;
        return this;
    }

    /**
     * The name of the sound to be played for the notification. For FCM and GCM,
     * this Translates to `data.twi_sound`.  For APNS, this translates to
     * `aps.sound`.  SMS does not support this property..
     *
     * @param sound The name of the sound to be played for the notification
     * @return this
     */
    public NotificationCreator setSound(final String sound) {
        this.sound = sound;
        return this;
    }

    /**
     * The actions to display for the notification. For APNS, translates to the
     * `aps.category` value. For GCM, translates to the `data.twi_action` value.
     * For SMS, this parameter is not supported and is omitted from deliveries to
     * those channels..
     *
     * @param action The actions to display for the notification
     * @return this
     */
    public NotificationCreator setAction(final String action) {
        this.action = action;
        return this;
    }

    /**
     * The custom key-value pairs of the notification's payload. For FCM and GCM,
     * this value translates to `data` in the FCM and GCM payloads. FCM and GCM <a
     * href="https://firebase.google.com/docs/cloud-messaging/http-server-ref">reserve
     * certain keys</a> that cannot be used in those channels. For APNS, attributes
     * of `data` are inserted into the APNS payload as custom properties outside of
     * the `aps` dictionary. In all channels, we reserve keys that start with
     * `twi_` for future use. Custom keys that start with `twi_` are not allowed
     * and are rejected as 400 Bad request with no delivery attempted. For SMS,
     * this parameter is not supported and is omitted from deliveries to those
     * channels..
     *
     * @param data The custom key-value pairs of the notification's payload
     * @return this
     */
    public NotificationCreator setData(final Map<String, Object> data) {
        this.data = data;
        return this;
    }

    /**
     * The APNS-specific payload that overrides corresponding attributes in the
     * generic payload for APNS Bindings. This property maps to the APNS `Payload`
     * item, therefore the `aps` key must be used to change standard attributes.
     * Adds custom key-value pairs to the root of the dictionary. See the <a
     * href="https://developer.apple.com/library/content/documentation/NetworkingInternet/Conceptual/RemoteNotificationsPG/CommunicatingwithAPNs.html">APNS
     * documentation</a> for more details. We reserve keys that start with `twi_`
     * for future use. Custom keys that start with `twi_` are not allowed..
     *
     * @param apn The APNS-specific payload that overrides corresponding attributes
     *            in a generic payload for APNS Bindings
     * @return this
     */
    public NotificationCreator setApn(final Map<String, Object> apn) {
        this.apn = apn;
        return this;
    }

    /**
     * The GCM-specific payload that overrides corresponding attributes in the
     * generic payload for GCM Bindings.  This property maps to the root JSON
     * dictionary. See the <a
     * href="https://firebase.google.com/docs/cloud-messaging/http-server-ref">GCM
     * documentation</a> for more details. Target parameters `to`,
     * `registration_ids`, and `notification_key` are not allowed. We reserve keys
     * that start with `twi_` for future use. Custom keys that start with `twi_`
     * are not allowed. GCM also <a
     * href="https://firebase.google.com/docs/cloud-messaging/http-server-ref">reserves
     * certain keys</a>..
     *
     * @param gcm The GCM-specific payload that overrides corresponding attributes
     *            in generic payload for GCM Bindings
     * @return this
     */
    public NotificationCreator setGcm(final Map<String, Object> gcm) {
        this.gcm = gcm;
        return this;
    }

    /**
     * The SMS-specific payload that overrides corresponding attributes in the
     * generic payload for SMS Bindings.  Each attribute in this value maps to the
     * corresponding `form` parameter of the Twilio <a
     * href="https://www.twilio.com/docs/sms/send-messages">Message</a> resource.
     * These parameters of the Message resource are supported in snake case format:
     * `body`, `media_urls`, `status_callback`, and `max_price`.  The
     * `status_callback` parameter overrides the corresponding parameter in the
     * messaging service, if configured. The `media_urls` property expects a JSON
     * array..
     *
     * @param sms The SMS-specific payload that overrides corresponding attributes
     *            in generic payload for SMS Bindings
     * @return this
     */
    public NotificationCreator setSms(final Map<String, Object> sms) {
        this.sms = sms;
        return this;
    }

    /**
     * Deprecated..
     *
     * @param facebookMessenger Deprecated
     * @return this
     */
    public NotificationCreator setFacebookMessenger(final Map<String, Object> facebookMessenger) {
        this.facebookMessenger = facebookMessenger;
        return this;
    }

    /**
     * The FCM-specific payload that overrides corresponding attributes in the
     * generic payload for FCM Bindings. This property maps to the root JSON
     * dictionary. See the <a
     * href="https://firebase.google.com/docs/cloud-messaging/http-server-ref#downstream">FCM
     * documentation</a> for more details. Target parameters `to`,
     * `registration_ids`, `condition`, and `notification_key` are not allowed in
     * this parameter. We reserve keys that start with `twi_` for future use.
     * Custom keys that start with `twi_` are not allowed. FCM also <a
     * href="https://firebase.google.com/docs/cloud-messaging/http-server-ref">reserves
     * certain keys</a>, which cannot be used in that channel..
     *
     * @param fcm The FCM-specific payload that overrides corresponding attributes
     *            in generic payload for FCM Bindings
     * @return this
     */
    public NotificationCreator setFcm(final Map<String, Object> fcm) {
        this.fcm = fcm;
        return this;
    }

    /**
     * The Segment resource is deprecated. Use the `tag` parameter, instead..
     *
     * @param segment A Segment to notify
     * @return this
     */
    public NotificationCreator setSegment(final List<String> segment) {
        this.segment = segment;
        return this;
    }

    /**
     * The Segment resource is deprecated. Use the `tag` parameter, instead..
     *
     * @param segment A Segment to notify
     * @return this
     */
    public NotificationCreator setSegment(final String segment) {
        return setSegment(Promoter.listOfOne(segment));
    }

    /**
     * Deprecated..
     *
     * @param alexa Deprecated
     * @return this
     */
    public NotificationCreator setAlexa(final Map<String, Object> alexa) {
        this.alexa = alexa;
        return this;
    }

    /**
     * The destination address specified as a JSON string.  Multiple `to_binding`
     * parameters can be included but the total size of the request entity should
     * not exceed 1MB. This is typically sufficient for 10,000 phone numbers..
     *
     * @param toBinding The destination address specified as a JSON string
     * @return this
     */
    public NotificationCreator setToBinding(final List<String> toBinding) {
        this.toBinding = toBinding;
        return this;
    }

    /**
     * The destination address specified as a JSON string.  Multiple `to_binding`
     * parameters can be included but the total size of the request entity should
     * not exceed 1MB. This is typically sufficient for 10,000 phone numbers..
     *
     * @param toBinding The destination address specified as a JSON string
     * @return this
     */
    public NotificationCreator setToBinding(final String toBinding) {
        return setToBinding(Promoter.listOfOne(toBinding));
    }

    /**
     * URL to send webhooks..
     *
     * @param deliveryCallbackUrl URL to send webhooks
     * @return this
     */
    public NotificationCreator setDeliveryCallbackUrl(final String deliveryCallbackUrl) {
        this.deliveryCallbackUrl = deliveryCallbackUrl;
        return this;
    }

    /**
     * The `identity` value that uniquely identifies the new resource's <a
     * href="https://www.twilio.com/docs/chat/rest/user-resource">User</a> within
     * the <a
     * href="https://www.twilio.com/docs/notify/api/service-resource">Service</a>.
     * Delivery will be attempted only to Bindings with an Identity in this list.
     * No more than 20 items are allowed in this list..
     *
     * @param identity The `identity` value that identifies the new resource's User
     * @return this
     */
    public NotificationCreator setIdentity(final List<String> identity) {
        this.identity = identity;
        return this;
    }

    /**
     * The `identity` value that uniquely identifies the new resource's <a
     * href="https://www.twilio.com/docs/chat/rest/user-resource">User</a> within
     * the <a
     * href="https://www.twilio.com/docs/notify/api/service-resource">Service</a>.
     * Delivery will be attempted only to Bindings with an Identity in this list.
     * No more than 20 items are allowed in this list..
     *
     * @param identity The `identity` value that identifies the new resource's User
     * @return this
     */
    public NotificationCreator setIdentity(final String identity) {
        return setIdentity(Promoter.listOfOne(identity));
    }

    /**
     * A tag that selects the Bindings to notify. Repeat this parameter to specify
     * more than one tag, up to a total of 5 tags. The implicit tag `all` is
     * available to notify all Bindings in a Service instance. Similarly, the
     * implicit tags `apn`, `fcm`, `gcm`, `sms` and `facebook-messenger` are
     * available to notify all Bindings in a specific channel..
     *
     * @param tag A tag that selects the Bindings to notify
     * @return this
     */
    public NotificationCreator setTag(final List<String> tag) {
        this.tag = tag;
        return this;
    }

    /**
     * A tag that selects the Bindings to notify. Repeat this parameter to specify
     * more than one tag, up to a total of 5 tags. The implicit tag `all` is
     * available to notify all Bindings in a Service instance. Similarly, the
     * implicit tags `apn`, `fcm`, `gcm`, `sms` and `facebook-messenger` are
     * available to notify all Bindings in a specific channel..
     *
     * @param tag A tag that selects the Bindings to notify
     * @return this
     */
    public NotificationCreator setTag(final String tag) {
        return setTag(Promoter.listOfOne(tag));
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created Notification
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Notification create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.NOTIFY.toString(),
            "/v1/Services/" + this.pathServiceSid + "/Notifications"
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Notification creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Notification.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (identity != null) {
            for (String prop : identity) {
                request.addPostParam("Identity", prop);
            }
        }

        if (tag != null) {
            for (String prop : tag) {
                request.addPostParam("Tag", prop);
            }
        }

        if (body != null) {
            request.addPostParam("Body", body);
        }

        if (priority != null) {
            request.addPostParam("Priority", priority.toString());
        }

        if (ttl != null) {
            request.addPostParam("Ttl", ttl.toString());
        }

        if (title != null) {
            request.addPostParam("Title", title);
        }

        if (sound != null) {
            request.addPostParam("Sound", sound);
        }

        if (action != null) {
            request.addPostParam("Action", action);
        }

        if (data != null) {
            request.addPostParam("Data", Converter.mapToJson(data));
        }

        if (apn != null) {
            request.addPostParam("Apn", Converter.mapToJson(apn));
        }

        if (gcm != null) {
            request.addPostParam("Gcm", Converter.mapToJson(gcm));
        }

        if (sms != null) {
            request.addPostParam("Sms", Converter.mapToJson(sms));
        }

        if (facebookMessenger != null) {
            request.addPostParam("FacebookMessenger", Converter.mapToJson(facebookMessenger));
        }

        if (fcm != null) {
            request.addPostParam("Fcm", Converter.mapToJson(fcm));
        }

        if (segment != null) {
            for (String prop : segment) {
                request.addPostParam("Segment", prop);
            }
        }

        if (alexa != null) {
            request.addPostParam("Alexa", Converter.mapToJson(alexa));
        }

        if (toBinding != null) {
            for (String prop : toBinding) {
                request.addPostParam("ToBinding", prop);
            }
        }

        if (deliveryCallbackUrl != null) {
            request.addPostParam("DeliveryCallbackUrl", deliveryCallbackUrl);
        }
    }
}