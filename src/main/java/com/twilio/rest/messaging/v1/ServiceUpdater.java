/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.messaging.v1;

import com.twilio.base.Updater;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.net.URI;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
public class ServiceUpdater extends Updater<Service> {
    private final String pathSid;
    private String friendlyName;
    private URI inboundRequestUrl;
    private HttpMethod inboundMethod;
    private URI fallbackUrl;
    private HttpMethod fallbackMethod;
    private URI statusCallback;
    private Boolean stickySender;
    private Boolean mmsConverter;
    private Boolean smartEncoding;
    private Service.ScanMessageContent scanMessageContent;
    private Boolean fallbackToLongCode;
    private Boolean areaCodeGeomatch;
    private Integer validityPeriod;
    private Boolean synchronousValidation;

    /**
     * Construct a new ServiceUpdater.
     *
     * @param pathSid The SID that identifies the resource to update
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
     * The URL we should call using `inbound_method` when a message is received by
     * any phone number or short code in the Service. When this property is `null`,
     * receiving inbound messages is disabled..
     *
     * @param inboundRequestUrl The URL we call using inbound_method when a message
     *                          is received by any phone number or short code in the
     *                          Service
     * @return this
     */
    public ServiceUpdater setInboundRequestUrl(final URI inboundRequestUrl) {
        this.inboundRequestUrl = inboundRequestUrl;
        return this;
    }

    /**
     * The URL we should call using `inbound_method` when a message is received by
     * any phone number or short code in the Service. When this property is `null`,
     * receiving inbound messages is disabled..
     *
     * @param inboundRequestUrl The URL we call using inbound_method when a message
     *                          is received by any phone number or short code in the
     *                          Service
     * @return this
     */
    public ServiceUpdater setInboundRequestUrl(final String inboundRequestUrl) {
        return setInboundRequestUrl(Promoter.uriFromString(inboundRequestUrl));
    }

    /**
     * The HTTP method we should use to call `inbound_request_url`. Can be `GET` or
     * `POST` and the default is `POST`..
     *
     * @param inboundMethod The HTTP method we should use to call
     *                      inbound_request_url
     * @return this
     */
    public ServiceUpdater setInboundMethod(final HttpMethod inboundMethod) {
        this.inboundMethod = inboundMethod;
        return this;
    }

    /**
     * The URL that we should call using `fallback_method` if an error occurs while
     * retrieving or executing the TwiML from the Inbound Request URL..
     *
     * @param fallbackUrl The URL that we call using fallback_method if an error
     *                    occurs while retrieving or executing the TwiML from the
     *                    Inbound Request URL
     * @return this
     */
    public ServiceUpdater setFallbackUrl(final URI fallbackUrl) {
        this.fallbackUrl = fallbackUrl;
        return this;
    }

    /**
     * The URL that we should call using `fallback_method` if an error occurs while
     * retrieving or executing the TwiML from the Inbound Request URL..
     *
     * @param fallbackUrl The URL that we call using fallback_method if an error
     *                    occurs while retrieving or executing the TwiML from the
     *                    Inbound Request URL
     * @return this
     */
    public ServiceUpdater setFallbackUrl(final String fallbackUrl) {
        return setFallbackUrl(Promoter.uriFromString(fallbackUrl));
    }

    /**
     * The HTTP method we should use to call `fallback_url`. Can be: `GET` or
     * `POST`..
     *
     * @param fallbackMethod The HTTP method we should use to call fallback_url
     * @return this
     */
    public ServiceUpdater setFallbackMethod(final HttpMethod fallbackMethod) {
        this.fallbackMethod = fallbackMethod;
        return this;
    }

    /**
     * The URL we should call to <a
     * href="https://www.twilio.com/docs/sms/api/message-resource#message-status-values">pass
     * status updates</a> about message delivery..
     *
     * @param statusCallback The URL we should call to pass status updates about
     *                       message delivery
     * @return this
     */
    public ServiceUpdater setStatusCallback(final URI statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }

    /**
     * The URL we should call to <a
     * href="https://www.twilio.com/docs/sms/api/message-resource#message-status-values">pass
     * status updates</a> about message delivery..
     *
     * @param statusCallback The URL we should call to pass status updates about
     *                       message delivery
     * @return this
     */
    public ServiceUpdater setStatusCallback(final String statusCallback) {
        return setStatusCallback(Promoter.uriFromString(statusCallback));
    }

    /**
     * Whether to enable <a
     * href="https://www.twilio.com/docs/sms/services#sticky-sender">Sticky
     * Sender</a> on the Service instance..
     *
     * @param stickySender Whether to enable Sticky Sender on the Service instance
     * @return this
     */
    public ServiceUpdater setStickySender(final Boolean stickySender) {
        this.stickySender = stickySender;
        return this;
    }

    /**
     * Whether to enable the <a
     * href="https://www.twilio.com/docs/sms/services#mms-converter">MMS
     * Converter</a> for messages sent through the Service instance..
     *
     * @param mmsConverter Whether to enable the MMS Converter for messages sent
     *                     through the Service instance
     * @return this
     */
    public ServiceUpdater setMmsConverter(final Boolean mmsConverter) {
        this.mmsConverter = mmsConverter;
        return this;
    }

    /**
     * Whether to enable <a
     * href="https://www.twilio.com/docs/sms/services#smart-encoding">Smart
     * Encoding</a> for messages sent through the Service instance..
     *
     * @param smartEncoding Whether to enable Encoding for messages sent through
     *                      the Service instance
     * @return this
     */
    public ServiceUpdater setSmartEncoding(final Boolean smartEncoding) {
        this.smartEncoding = smartEncoding;
        return this;
    }

    /**
     * Reserved..
     *
     * @param scanMessageContent Reserved
     * @return this
     */
    public ServiceUpdater setScanMessageContent(final Service.ScanMessageContent scanMessageContent) {
        this.scanMessageContent = scanMessageContent;
        return this;
    }

    /**
     * Whether to enable <a
     * href="https://www.twilio.com/docs/sms/services#fallback-to-long-code">Fallback
     * to Long Code</a> for messages sent through the Service instance..
     *
     * @param fallbackToLongCode Whether to enable Fallback to Long Code for
     *                           messages sent through the Service instance
     * @return this
     */
    public ServiceUpdater setFallbackToLongCode(final Boolean fallbackToLongCode) {
        this.fallbackToLongCode = fallbackToLongCode;
        return this;
    }

    /**
     * Whether to enable <a
     * href="https://www.twilio.com/docs/sms/services#area-code-geomatch">Area Code
     * Geomatch</a> on the Service Instance..
     *
     * @param areaCodeGeomatch Whether to enable Area Code Geomatch on the Service
     *                         Instance
     * @return this
     */
    public ServiceUpdater setAreaCodeGeomatch(final Boolean areaCodeGeomatch) {
        this.areaCodeGeomatch = areaCodeGeomatch;
        return this;
    }

    /**
     * How long, in seconds, messages sent from the Service are valid. Can be an
     * integer from `1` to `14,400`..
     *
     * @param validityPeriod How long, in seconds, messages sent from the Service
     *                       are valid
     * @return this
     */
    public ServiceUpdater setValidityPeriod(final Integer validityPeriod) {
        this.validityPeriod = validityPeriod;
        return this;
    }

    /**
     * Reserved..
     *
     * @param synchronousValidation Reserved
     * @return this
     */
    public ServiceUpdater setSynchronousValidation(final Boolean synchronousValidation) {
        this.synchronousValidation = synchronousValidation;
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
            Domains.MESSAGING.toString(),
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

        if (inboundRequestUrl != null) {
            request.addPostParam("InboundRequestUrl", inboundRequestUrl.toString());
        }

        if (inboundMethod != null) {
            request.addPostParam("InboundMethod", inboundMethod.toString());
        }

        if (fallbackUrl != null) {
            request.addPostParam("FallbackUrl", fallbackUrl.toString());
        }

        if (fallbackMethod != null) {
            request.addPostParam("FallbackMethod", fallbackMethod.toString());
        }

        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback.toString());
        }

        if (stickySender != null) {
            request.addPostParam("StickySender", stickySender.toString());
        }

        if (mmsConverter != null) {
            request.addPostParam("MmsConverter", mmsConverter.toString());
        }

        if (smartEncoding != null) {
            request.addPostParam("SmartEncoding", smartEncoding.toString());
        }

        if (scanMessageContent != null) {
            request.addPostParam("ScanMessageContent", scanMessageContent.toString());
        }

        if (fallbackToLongCode != null) {
            request.addPostParam("FallbackToLongCode", fallbackToLongCode.toString());
        }

        if (areaCodeGeomatch != null) {
            request.addPostParam("AreaCodeGeomatch", areaCodeGeomatch.toString());
        }

        if (validityPeriod != null) {
            request.addPostParam("ValidityPeriod", validityPeriod.toString());
        }

        if (synchronousValidation != null) {
            request.addPostParam("SynchronousValidation", synchronousValidation.toString());
        }
    }
}