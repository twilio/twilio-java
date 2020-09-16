/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account;

import com.twilio.base.Creator;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import com.twilio.type.Endpoint;
import com.twilio.type.Twiml;

import java.net.URI;
import java.util.List;

public class CallCreator extends Creator<Call> {
    private String pathAccountSid;
    private final com.twilio.type.Endpoint to;
    private final com.twilio.type.Endpoint from;
    private URI url;
    private com.twilio.type.Twiml twiml;
    private String applicationSid;
    private HttpMethod method;
    private URI fallbackUrl;
    private HttpMethod fallbackMethod;
    private URI statusCallback;
    private List<String> statusCallbackEvent;
    private HttpMethod statusCallbackMethod;
    private String sendDigits;
    private Integer timeout;
    private Boolean record;
    private String recordingChannels;
    private String recordingStatusCallback;
    private HttpMethod recordingStatusCallbackMethod;
    private String sipAuthUsername;
    private String sipAuthPassword;
    private String machineDetection;
    private Integer machineDetectionTimeout;
    private List<String> recordingStatusCallbackEvent;
    private String trim;
    private String callerId;
    private Integer machineDetectionSpeechThreshold;
    private Integer machineDetectionSpeechEndThreshold;
    private Integer machineDetectionSilenceTimeout;
    private String asyncAmd;
    private URI asyncAmdStatusCallback;
    private HttpMethod asyncAmdStatusCallbackMethod;
    private String byoc;
    private String callReason;

    /**
     * Construct a new CallCreator.
     *
     * @param to Phone number, SIP address, or client identifier to call
     * @param from Twilio number from which to originate the call
     * @param url The absolute URL that returns TwiML for this call
     */
    public CallCreator(final com.twilio.type.Endpoint to,
                       final com.twilio.type.Endpoint from,
                       final URI url) {
        this.to = to;
        this.from = from;
        this.url = url;
    }

    /**
     * Construct a new CallCreator.
     *
     * @param pathAccountSid The SID of the Account that will create the resource
     * @param to Phone number, SIP address, or client identifier to call
     * @param from Twilio number from which to originate the call
     * @param url The absolute URL that returns TwiML for this call
     */
    public CallCreator(final String pathAccountSid,
                       final com.twilio.type.Endpoint to,
                       final com.twilio.type.Endpoint from,
                       final URI url) {
        this.pathAccountSid = pathAccountSid;
        this.to = to;
        this.from = from;
        this.url = url;
    }

    /**
     * Construct a new CallCreator.
     *
     * @param to Phone number, SIP address, or client identifier to call
     * @param from Twilio number from which to originate the call
     * @param twiml TwiML instructions for the call
     */
    public CallCreator(final com.twilio.type.Endpoint to,
                       final com.twilio.type.Endpoint from,
                       final com.twilio.type.Twiml twiml) {
        this.to = to;
        this.from = from;
        this.twiml = twiml;
    }

    /**
     * Construct a new CallCreator.
     *
     * @param pathAccountSid The SID of the Account that will create the resource
     * @param to Phone number, SIP address, or client identifier to call
     * @param from Twilio number from which to originate the call
     * @param twiml TwiML instructions for the call
     */
    public CallCreator(final String pathAccountSid,
                       final com.twilio.type.Endpoint to,
                       final com.twilio.type.Endpoint from,
                       final com.twilio.type.Twiml twiml) {
        this.pathAccountSid = pathAccountSid;
        this.to = to;
        this.from = from;
        this.twiml = twiml;
    }

    /**
     * Construct a new CallCreator.
     *
     * @param to Phone number, SIP address, or client identifier to call
     * @param from Twilio number from which to originate the call
     * @param applicationSid The SID of the Application resource that will handle
     *                       the call
     */
    public CallCreator(final com.twilio.type.Endpoint to,
                       final com.twilio.type.Endpoint from,
                       final String applicationSid) {
        this.to = to;
        this.from = from;
        this.applicationSid = applicationSid;
    }

    /**
     * Construct a new CallCreator.
     *
     * @param pathAccountSid The SID of the Account that will create the resource
     * @param to Phone number, SIP address, or client identifier to call
     * @param from Twilio number from which to originate the call
     * @param applicationSid The SID of the Application resource that will handle
     *                       the call
     */
    public CallCreator(final String pathAccountSid,
                       final com.twilio.type.Endpoint to,
                       final com.twilio.type.Endpoint from,
                       final String applicationSid) {
        this.pathAccountSid = pathAccountSid;
        this.to = to;
        this.from = from;
        this.applicationSid = applicationSid;
    }

    /**
     * The HTTP method we should use when calling the `url` parameter's value. Can
     * be: `GET` or `POST` and the default is `POST`. If an `application_sid`
     * parameter is present, this parameter is ignored..
     *
     * @param method HTTP method to use to fetch TwiML
     * @return this
     */
    public CallCreator setMethod(final HttpMethod method) {
        this.method = method;
        return this;
    }

    /**
     * The URL that we call using the `fallback_method` if an error occurs when
     * requesting or executing the TwiML at `url`. If an `application_sid`
     * parameter is present, this parameter is ignored..
     *
     * @param fallbackUrl Fallback URL in case of error
     * @return this
     */
    public CallCreator setFallbackUrl(final URI fallbackUrl) {
        this.fallbackUrl = fallbackUrl;
        return this;
    }

    /**
     * The URL that we call using the `fallback_method` if an error occurs when
     * requesting or executing the TwiML at `url`. If an `application_sid`
     * parameter is present, this parameter is ignored..
     *
     * @param fallbackUrl Fallback URL in case of error
     * @return this
     */
    public CallCreator setFallbackUrl(final String fallbackUrl) {
        return setFallbackUrl(Promoter.uriFromString(fallbackUrl));
    }

    /**
     * The HTTP method that we should use to request the `fallback_url`. Can be:
     * `GET` or `POST` and the default is `POST`. If an `application_sid` parameter
     * is present, this parameter is ignored..
     *
     * @param fallbackMethod HTTP Method to use with fallback_url
     * @return this
     */
    public CallCreator setFallbackMethod(final HttpMethod fallbackMethod) {
        this.fallbackMethod = fallbackMethod;
        return this;
    }

    /**
     * The URL we should call using the `status_callback_method` to send status
     * information to your application. If no `status_callback_event` is specified,
     * we will send the `completed` status. If an `application_sid` parameter is
     * present, this parameter is ignored. URLs must contain a valid hostname
     * (underscores are not permitted)..
     *
     * @param statusCallback The URL we should call to send status information to
     *                       your application
     * @return this
     */
    public CallCreator setStatusCallback(final URI statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }

    /**
     * The URL we should call using the `status_callback_method` to send status
     * information to your application. If no `status_callback_event` is specified,
     * we will send the `completed` status. If an `application_sid` parameter is
     * present, this parameter is ignored. URLs must contain a valid hostname
     * (underscores are not permitted)..
     *
     * @param statusCallback The URL we should call to send status information to
     *                       your application
     * @return this
     */
    public CallCreator setStatusCallback(final String statusCallback) {
        return setStatusCallback(Promoter.uriFromString(statusCallback));
    }

    /**
     * The call progress events that we will send to the `status_callback` URL. Can
     * be: `initiated`, `ringing`, `answered`, and `completed`. If no event is
     * specified, we send the `completed` status. If you want to receive multiple
     * events, specify each one in a separate `status_callback_event` parameter.
     * See the code sample for <a
     * href="https://www.twilio.com/docs/voice/api/call-resource?code-sample=code-create-a-call-resource-and-specify-a-statuscallbackevent&amp;code-sdk-version=json">monitoring
     * call progress</a>. If an `application_sid` is present, this parameter is
     * ignored..
     *
     * @param statusCallbackEvent The call progress events that we send to the
     *                            `status_callback` URL.
     * @return this
     */
    public CallCreator setStatusCallbackEvent(final List<String> statusCallbackEvent) {
        this.statusCallbackEvent = statusCallbackEvent;
        return this;
    }

    /**
     * The call progress events that we will send to the `status_callback` URL. Can
     * be: `initiated`, `ringing`, `answered`, and `completed`. If no event is
     * specified, we send the `completed` status. If you want to receive multiple
     * events, specify each one in a separate `status_callback_event` parameter.
     * See the code sample for <a
     * href="https://www.twilio.com/docs/voice/api/call-resource?code-sample=code-create-a-call-resource-and-specify-a-statuscallbackevent&amp;code-sdk-version=json">monitoring
     * call progress</a>. If an `application_sid` is present, this parameter is
     * ignored..
     *
     * @param statusCallbackEvent The call progress events that we send to the
     *                            `status_callback` URL.
     * @return this
     */
    public CallCreator setStatusCallbackEvent(final String statusCallbackEvent) {
        return setStatusCallbackEvent(Promoter.listOfOne(statusCallbackEvent));
    }

    /**
     * The HTTP method we should use when calling the `status_callback` URL. Can
     * be: `GET` or `POST` and the default is `POST`. If an `application_sid`
     * parameter is present, this parameter is ignored..
     *
     * @param statusCallbackMethod HTTP Method to use with status_callback
     * @return this
     */
    public CallCreator setStatusCallbackMethod(final HttpMethod statusCallbackMethod) {
        this.statusCallbackMethod = statusCallbackMethod;
        return this;
    }

    /**
     * A string of keys to dial after connecting to the number, maximum of 32
     * digits. Valid digits in the string include: any digit (`0`-`9`), '`#`',
     * '`*`' and '`w`', to insert a half second pause. For example, if you
     * connected to a company phone number and wanted to pause for one second, and
     * then dial extension 1234 followed by the pound key, the value of this
     * parameter would be `ww1234#`. Remember to URL-encode this string, since the
     * '`#`' character has special meaning in a URL. If both `SendDigits` and
     * `MachineDetection` parameters are provided, then `MachineDetection` will be
     * ignored..
     *
     * @param sendDigits The digits to dial after connecting to the number
     * @return this
     */
    public CallCreator setSendDigits(final String sendDigits) {
        this.sendDigits = sendDigits;
        return this;
    }

    /**
     * The integer number of seconds that we should allow the phone to ring before
     * assuming there is no answer. The default is `60` seconds and the maximum is
     * `600` seconds. For some call flows, we will add a 5-second buffer to the
     * timeout value you provide. For this reason, a timeout value of 10 seconds
     * could result in an actual timeout closer to 15 seconds. You can set this to
     * a short time, such as `15` seconds, to hang up before reaching an answering
     * machine or voicemail..
     *
     * @param timeout Number of seconds to wait for an answer
     * @return this
     */
    public CallCreator setTimeout(final Integer timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * Whether to record the call. Can be `true` to record the phone call, or
     * `false` to not. The default is `false`. The `recording_url` is sent to the
     * `status_callback` URL..
     *
     * @param record Whether to record the call
     * @return this
     */
    public CallCreator setRecord(final Boolean record) {
        this.record = record;
        return this;
    }

    /**
     * The number of channels in the final recording. Can be: `mono` or `dual`. The
     * default is `mono`. `mono` records both legs of the call in a single channel
     * of the recording file. `dual` records each leg to a separate channel of the
     * recording file. The first channel of a dual-channel recording contains the
     * parent call and the second channel contains the child call..
     *
     * @param recordingChannels The number of channels in the final recording
     * @return this
     */
    public CallCreator setRecordingChannels(final String recordingChannels) {
        this.recordingChannels = recordingChannels;
        return this;
    }

    /**
     * The URL that we call when the recording is available to be accessed..
     *
     * @param recordingStatusCallback The URL that we call when the recording is
     *                                available to be accessed
     * @return this
     */
    public CallCreator setRecordingStatusCallback(final String recordingStatusCallback) {
        this.recordingStatusCallback = recordingStatusCallback;
        return this;
    }

    /**
     * The HTTP method we should use when calling the `recording_status_callback`
     * URL. Can be: `GET` or `POST` and the default is `POST`..
     *
     * @param recordingStatusCallbackMethod The HTTP method we should use when
     *                                      calling the `recording_status_callback`
     *                                      URL
     * @return this
     */
    public CallCreator setRecordingStatusCallbackMethod(final HttpMethod recordingStatusCallbackMethod) {
        this.recordingStatusCallbackMethod = recordingStatusCallbackMethod;
        return this;
    }

    /**
     * The username used to authenticate the caller making a SIP call..
     *
     * @param sipAuthUsername The username used to authenticate the caller making a
     *                        SIP call
     * @return this
     */
    public CallCreator setSipAuthUsername(final String sipAuthUsername) {
        this.sipAuthUsername = sipAuthUsername;
        return this;
    }

    /**
     * The password required to authenticate the user account specified in
     * `sip_auth_username`..
     *
     * @param sipAuthPassword The password required to authenticate the user
     *                        account specified in `sip_auth_username`.
     * @return this
     */
    public CallCreator setSipAuthPassword(final String sipAuthPassword) {
        this.sipAuthPassword = sipAuthPassword;
        return this;
    }

    /**
     * Whether to detect if a human, answering machine, or fax has picked up the
     * call. Can be: `Enable` or `DetectMessageEnd`. Use `Enable` if you would like
     * us to return `AnsweredBy` as soon as the called party is identified. Use
     * `DetectMessageEnd`, if you would like to leave a message on an answering
     * machine. If `send_digits` is provided, this parameter is ignored. For more
     * information, see <a
     * href="https://www.twilio.com/docs/voice/answering-machine-detection">Answering
     * Machine Detection</a>..
     *
     * @param machineDetection Enable machine detection or end of greeting detection
     * @return this
     */
    public CallCreator setMachineDetection(final String machineDetection) {
        this.machineDetection = machineDetection;
        return this;
    }

    /**
     * The number of seconds that we should attempt to detect an answering machine
     * before timing out and sending a voice request with `AnsweredBy` of
     * `unknown`. The default timeout is 30 seconds..
     *
     * @param machineDetectionTimeout Number of seconds to wait for machine
     *                                detection
     * @return this
     */
    public CallCreator setMachineDetectionTimeout(final Integer machineDetectionTimeout) {
        this.machineDetectionTimeout = machineDetectionTimeout;
        return this;
    }

    /**
     * The recording status events that will trigger calls to the URL specified in
     * `recording_status_callback`. Can be: `in-progress`, `completed` and
     * `absent`. Defaults to `completed`. Separate  multiple values with a space..
     *
     * @param recordingStatusCallbackEvent The recording status events that will
     *                                     trigger calls to the URL specified in
     *                                     `recording_status_callback`
     * @return this
     */
    public CallCreator setRecordingStatusCallbackEvent(final List<String> recordingStatusCallbackEvent) {
        this.recordingStatusCallbackEvent = recordingStatusCallbackEvent;
        return this;
    }

    /**
     * The recording status events that will trigger calls to the URL specified in
     * `recording_status_callback`. Can be: `in-progress`, `completed` and
     * `absent`. Defaults to `completed`. Separate  multiple values with a space..
     *
     * @param recordingStatusCallbackEvent The recording status events that will
     *                                     trigger calls to the URL specified in
     *                                     `recording_status_callback`
     * @return this
     */
    public CallCreator setRecordingStatusCallbackEvent(final String recordingStatusCallbackEvent) {
        return setRecordingStatusCallbackEvent(Promoter.listOfOne(recordingStatusCallbackEvent));
    }

    /**
     * Whether to trim any leading and trailing silence from the recording. Can be:
     * `trim-silence` or `do-not-trim` and the default is `trim-silence`..
     *
     * @param trim Set this parameter to control trimming of silence on the
     *             recording.
     * @return this
     */
    public CallCreator setTrim(final String trim) {
        this.trim = trim;
        return this;
    }

    /**
     * The phone number, SIP address, or Client identifier that made this call.
     * Phone numbers are in <a
     * href="https://wwnw.twilio.com/docs/glossary/what-e164">E.164 format</a>
     * (e.g., +16175551212). SIP addresses are formatted as `name@company.com`..
     *
     * @param callerId The phone number, SIP address, or Client identifier that
     *                 made this call. Phone numbers are in E.164 format (e.g.,
     *                 +16175551212). SIP addresses are formatted as
     *                 `name@company.com`.
     * @return this
     */
    public CallCreator setCallerId(final String callerId) {
        this.callerId = callerId;
        return this;
    }

    /**
     * The number of milliseconds that is used as the measuring stick for the
     * length of the speech activity, where durations lower than this value will be
     * interpreted as a human and longer than this value as a machine. Possible
     * Values: 1000-6000. Default: 2400..
     *
     * @param machineDetectionSpeechThreshold Number of milliseconds for measuring
     *                                        stick for the length of the speech
     *                                        activity
     * @return this
     */
    public CallCreator setMachineDetectionSpeechThreshold(final Integer machineDetectionSpeechThreshold) {
        this.machineDetectionSpeechThreshold = machineDetectionSpeechThreshold;
        return this;
    }

    /**
     * The number of milliseconds of silence after speech activity at which point
     * the speech activity is considered complete. Possible Values: 500-5000.
     * Default: 1200..
     *
     * @param machineDetectionSpeechEndThreshold Number of milliseconds of silence
     *                                           after speech activity
     * @return this
     */
    public CallCreator setMachineDetectionSpeechEndThreshold(final Integer machineDetectionSpeechEndThreshold) {
        this.machineDetectionSpeechEndThreshold = machineDetectionSpeechEndThreshold;
        return this;
    }

    /**
     * The number of milliseconds of initial silence after which an `unknown`
     * AnsweredBy result will be returned. Possible Values: 2000-10000. Default:
     * 5000..
     *
     * @param machineDetectionSilenceTimeout Number of milliseconds of initial
     *                                       silence
     * @return this
     */
    public CallCreator setMachineDetectionSilenceTimeout(final Integer machineDetectionSilenceTimeout) {
        this.machineDetectionSilenceTimeout = machineDetectionSilenceTimeout;
        return this;
    }

    /**
     * Select whether to perform answering machine detection in the background.
     * Default, blocks the execution of the call until Answering Machine Detection
     * is completed. Can be: `true` or `false`..
     *
     * @param asyncAmd Enable asynchronous AMD
     * @return this
     */
    public CallCreator setAsyncAmd(final String asyncAmd) {
        this.asyncAmd = asyncAmd;
        return this;
    }

    /**
     * The URL that we should call using the `async_amd_status_callback_method` to
     * notify customer application whether the call was answered by human, machine
     * or fax..
     *
     * @param asyncAmdStatusCallback The URL we should call to send amd status
     *                               information to your application
     * @return this
     */
    public CallCreator setAsyncAmdStatusCallback(final URI asyncAmdStatusCallback) {
        this.asyncAmdStatusCallback = asyncAmdStatusCallback;
        return this;
    }

    /**
     * The URL that we should call using the `async_amd_status_callback_method` to
     * notify customer application whether the call was answered by human, machine
     * or fax..
     *
     * @param asyncAmdStatusCallback The URL we should call to send amd status
     *                               information to your application
     * @return this
     */
    public CallCreator setAsyncAmdStatusCallback(final String asyncAmdStatusCallback) {
        return setAsyncAmdStatusCallback(Promoter.uriFromString(asyncAmdStatusCallback));
    }

    /**
     * The HTTP method we should use when calling the `async_amd_status_callback`
     * URL. Can be: `GET` or `POST` and the default is `POST`..
     *
     * @param asyncAmdStatusCallbackMethod HTTP Method to use with
     *                                     async_amd_status_callback
     * @return this
     */
    public CallCreator setAsyncAmdStatusCallbackMethod(final HttpMethod asyncAmdStatusCallbackMethod) {
        this.asyncAmdStatusCallbackMethod = asyncAmdStatusCallbackMethod;
        return this;
    }

    /**
     * The SID of a BYOC (Bring Your Own Carrier) trunk to route this call with.
     * Note that `byoc` is only meaningful when `to` is a phone number; it will
     * otherwise be ignored. (Beta).
     *
     * @param byoc BYOC trunk SID (Beta)
     * @return this
     */
    public CallCreator setByoc(final String byoc) {
        this.byoc = byoc;
        return this;
    }

    /**
     * The Reason for the outgoing call. Use it to specify the purpose of the call
     * that is presented on the called party's phone. (Branded Calls Beta).
     *
     * @param callReason Reason for the call (Branded Calls Beta)
     * @return this
     */
    public CallCreator setCallReason(final String callReason) {
        this.callReason = callReason;
        return this;
    }

    /**
     * The absolute URL that returns the TwiML instructions for the call. We will
     * call this URL using the `method` when the call connects. For more
     * information, see the <a
     * href="https://www.twilio.com/docs/voice/make-calls#specify-a-url-parameter">Url
     * Parameter</a> section in <a
     * href="https://www.twilio.com/docs/voice/make-calls">Making Calls</a>..
     *
     * @param url The absolute URL that returns TwiML for this call
     * @return this
     */
    public CallCreator setUrl(final URI url) {
        this.url = url;
        return this;
    }

    /**
     * The absolute URL that returns the TwiML instructions for the call. We will
     * call this URL using the `method` when the call connects. For more
     * information, see the <a
     * href="https://www.twilio.com/docs/voice/make-calls#specify-a-url-parameter">Url
     * Parameter</a> section in <a
     * href="https://www.twilio.com/docs/voice/make-calls">Making Calls</a>..
     *
     * @param url The absolute URL that returns TwiML for this call
     * @return this
     */
    public CallCreator setUrl(final String url) {
        return setUrl(Promoter.uriFromString(url));
    }

    /**
     * TwiML instructions for the call Twilio will use without fetching Twiml from
     * url parameter. If both `twiml` and `url` are provided then `twiml` parameter
     * will be ignored..
     *
     * @param twiml TwiML instructions for the call
     * @return this
     */
    public CallCreator setTwiml(final com.twilio.type.Twiml twiml) {
        this.twiml = twiml;
        return this;
    }

    /**
     * TwiML instructions for the call Twilio will use without fetching Twiml from
     * url parameter. If both `twiml` and `url` are provided then `twiml` parameter
     * will be ignored..
     *
     * @param twiml TwiML instructions for the call
     * @return this
     */
    public CallCreator setTwiml(final String twiml) {
        return setTwiml(Promoter.twimlFromString(twiml));
    }

    /**
     * The SID of the Application resource that will handle the call, if the call
     * will be handled by an application..
     *
     * @param applicationSid The SID of the Application resource that will handle
     *                       the call
     * @return this
     */
    public CallCreator setApplicationSid(final String applicationSid) {
        this.applicationSid = applicationSid;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created Call
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Call create(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.POST,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/Calls.json"
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Call creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Call.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (to != null) {
            request.addPostParam("To", to.getEndpoint());
        }

        if (from != null) {
            request.addPostParam("From", from.getEndpoint());
        }

        if (url != null) {
            request.addPostParam("Url", url.toString());
        }

        if (twiml != null) {
            request.addPostParam("Twiml", twiml.toString());
        }

        if (applicationSid != null) {
            request.addPostParam("ApplicationSid", applicationSid);
        }

        if (method != null) {
            request.addPostParam("Method", method.toString());
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

        if (statusCallbackEvent != null) {
            for (String prop : statusCallbackEvent) {
                request.addPostParam("StatusCallbackEvent", prop);
            }
        }

        if (statusCallbackMethod != null) {
            request.addPostParam("StatusCallbackMethod", statusCallbackMethod.toString());
        }

        if (sendDigits != null) {
            request.addPostParam("SendDigits", sendDigits);
        }

        if (timeout != null) {
            request.addPostParam("Timeout", timeout.toString());
        }

        if (record != null) {
            request.addPostParam("Record", record.toString());
        }

        if (recordingChannels != null) {
            request.addPostParam("RecordingChannels", recordingChannels);
        }

        if (recordingStatusCallback != null) {
            request.addPostParam("RecordingStatusCallback", recordingStatusCallback);
        }

        if (recordingStatusCallbackMethod != null) {
            request.addPostParam("RecordingStatusCallbackMethod", recordingStatusCallbackMethod.toString());
        }

        if (sipAuthUsername != null) {
            request.addPostParam("SipAuthUsername", sipAuthUsername);
        }

        if (sipAuthPassword != null) {
            request.addPostParam("SipAuthPassword", sipAuthPassword);
        }

        if (machineDetection != null) {
            request.addPostParam("MachineDetection", machineDetection);
        }

        if (machineDetectionTimeout != null) {
            request.addPostParam("MachineDetectionTimeout", machineDetectionTimeout.toString());
        }

        if (recordingStatusCallbackEvent != null) {
            for (String prop : recordingStatusCallbackEvent) {
                request.addPostParam("RecordingStatusCallbackEvent", prop);
            }
        }

        if (trim != null) {
            request.addPostParam("Trim", trim);
        }

        if (callerId != null) {
            request.addPostParam("CallerId", callerId);
        }

        if (machineDetectionSpeechThreshold != null) {
            request.addPostParam("MachineDetectionSpeechThreshold", machineDetectionSpeechThreshold.toString());
        }

        if (machineDetectionSpeechEndThreshold != null) {
            request.addPostParam("MachineDetectionSpeechEndThreshold", machineDetectionSpeechEndThreshold.toString());
        }

        if (machineDetectionSilenceTimeout != null) {
            request.addPostParam("MachineDetectionSilenceTimeout", machineDetectionSilenceTimeout.toString());
        }

        if (asyncAmd != null) {
            request.addPostParam("AsyncAmd", asyncAmd);
        }

        if (asyncAmdStatusCallback != null) {
            request.addPostParam("AsyncAmdStatusCallback", asyncAmdStatusCallback.toString());
        }

        if (asyncAmdStatusCallbackMethod != null) {
            request.addPostParam("AsyncAmdStatusCallbackMethod", asyncAmdStatusCallbackMethod.toString());
        }

        if (byoc != null) {
            request.addPostParam("Byoc", byoc);
        }

        if (callReason != null) {
            request.addPostParam("CallReason", callReason);
        }
    }
}