/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.wireless.v1;

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

public class SimUpdater extends Updater<Sim> {
    private final String pathSid;
    private String uniqueName;
    private HttpMethod callbackMethod;
    private URI callbackUrl;
    private String friendlyName;
    private String ratePlan;
    private Sim.Status status;
    private HttpMethod commandsCallbackMethod;
    private URI commandsCallbackUrl;
    private HttpMethod smsFallbackMethod;
    private URI smsFallbackUrl;
    private HttpMethod smsMethod;
    private URI smsUrl;
    private HttpMethod voiceFallbackMethod;
    private URI voiceFallbackUrl;
    private HttpMethod voiceMethod;
    private URI voiceUrl;
    private Sim.ResetStatus resetStatus;
    private String accountSid;

    /**
     * Construct a new SimUpdater.
     *
     * @param pathSid The SID of the Sim resource to update
     */
    public SimUpdater(final String pathSid) {
        this.pathSid = pathSid;
    }

    /**
     * An application-defined string that uniquely identifies the resource. It can
     * be used in place of the `sid` in the URL path to address the resource..
     *
     * @param uniqueName An application-defined string that uniquely identifies the
     *                   resource
     * @return this
     */
    public SimUpdater setUniqueName(final String uniqueName) {
        this.uniqueName = uniqueName;
        return this;
    }

    /**
     * The HTTP method we should use to call `callback_url`. Can be: `POST` or
     * `GET`. The default is `POST`..
     *
     * @param callbackMethod The HTTP method we should use to call callback_url
     * @return this
     */
    public SimUpdater setCallbackMethod(final HttpMethod callbackMethod) {
        this.callbackMethod = callbackMethod;
        return this;
    }

    /**
     * The URL we should call using the `callback_url` when the SIM has finished
     * updating. When the SIM transitions from `new` to `ready` or from any status
     * to `deactivated`, we call this URL when the status changes to an intermediate
     * status (`ready` or `deactivated`) and again when the status changes to its
     * final status (`active` or `canceled`)..
     *
     * @param callbackUrl The URL we should call when the Sim resource has finished
     *                    updating
     * @return this
     */
    public SimUpdater setCallbackUrl(final URI callbackUrl) {
        this.callbackUrl = callbackUrl;
        return this;
    }

    /**
     * The URL we should call using the `callback_url` when the SIM has finished
     * updating. When the SIM transitions from `new` to `ready` or from any status
     * to `deactivated`, we call this URL when the status changes to an intermediate
     * status (`ready` or `deactivated`) and again when the status changes to its
     * final status (`active` or `canceled`)..
     *
     * @param callbackUrl The URL we should call when the Sim resource has finished
     *                    updating
     * @return this
     */
    public SimUpdater setCallbackUrl(final String callbackUrl) {
        return setCallbackUrl(Promoter.uriFromString(callbackUrl));
    }

    /**
     * A descriptive string that you create to describe the Sim resource. It does
     * not need to be unique..
     *
     * @param friendlyName A string to describe the Sim resource
     * @return this
     */
    public SimUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The SID or unique name of the [RatePlan
     * resource](https://www.twilio.com/docs/wireless/api/rateplan-resource) to
     * which the Sim resource should be assigned..
     *
     * @param ratePlan The SID or unique name of the RatePlan resource to which the
     *                 Sim resource should be assigned
     * @return this
     */
    public SimUpdater setRatePlan(final String ratePlan) {
        this.ratePlan = ratePlan;
        return this;
    }

    /**
     * The new status of the Sim resource. Can be: `ready`, `active`, `suspended`,
     * or `deactivated`..
     *
     * @param status The new status of the Sim resource
     * @return this
     */
    public SimUpdater setStatus(final Sim.Status status) {
        this.status = status;
        return this;
    }

    /**
     * The HTTP method we should use to call `commands_callback_url`. Can be: `POST`
     * or `GET`. The default is `POST`..
     *
     * @param commandsCallbackMethod The HTTP method we should use to call
     *                               commands_callback_url
     * @return this
     */
    public SimUpdater setCommandsCallbackMethod(final HttpMethod commandsCallbackMethod) {
        this.commandsCallbackMethod = commandsCallbackMethod;
        return this;
    }

    /**
     * The URL we should call using the `commands_callback_method` when the SIM
     * sends a [Command](https://www.twilio.com/docs/wireless/api/command-resource).
     * Your server should respond with an HTTP status code in the 200 range; any
     * response body is ignored..
     *
     * @param commandsCallbackUrl The URL we should call when the SIM sends a
     *                            Command
     * @return this
     */
    public SimUpdater setCommandsCallbackUrl(final URI commandsCallbackUrl) {
        this.commandsCallbackUrl = commandsCallbackUrl;
        return this;
    }

    /**
     * The URL we should call using the `commands_callback_method` when the SIM
     * sends a [Command](https://www.twilio.com/docs/wireless/api/command-resource).
     * Your server should respond with an HTTP status code in the 200 range; any
     * response body is ignored..
     *
     * @param commandsCallbackUrl The URL we should call when the SIM sends a
     *                            Command
     * @return this
     */
    public SimUpdater setCommandsCallbackUrl(final String commandsCallbackUrl) {
        return setCommandsCallbackUrl(Promoter.uriFromString(commandsCallbackUrl));
    }

    /**
     * The HTTP method we should use to call `sms_fallback_url`. Can be: `GET` or
     * `POST`. Default is `POST`..
     *
     * @param smsFallbackMethod The HTTP method we should use to call
     *                          sms_fallback_url
     * @return this
     */
    public SimUpdater setSmsFallbackMethod(final HttpMethod smsFallbackMethod) {
        this.smsFallbackMethod = smsFallbackMethod;
        return this;
    }

    /**
     * The URL we should call using the `sms_fallback_method` when an error occurs
     * while retrieving or executing the TwiML requested from `sms_url`..
     *
     * @param smsFallbackUrl The URL we should call when an error occurs while
     *                       retrieving or executing the TwiML requested from
     *                       sms_url
     * @return this
     */
    public SimUpdater setSmsFallbackUrl(final URI smsFallbackUrl) {
        this.smsFallbackUrl = smsFallbackUrl;
        return this;
    }

    /**
     * The URL we should call using the `sms_fallback_method` when an error occurs
     * while retrieving or executing the TwiML requested from `sms_url`..
     *
     * @param smsFallbackUrl The URL we should call when an error occurs while
     *                       retrieving or executing the TwiML requested from
     *                       sms_url
     * @return this
     */
    public SimUpdater setSmsFallbackUrl(final String smsFallbackUrl) {
        return setSmsFallbackUrl(Promoter.uriFromString(smsFallbackUrl));
    }

    /**
     * The HTTP method we should use to call `sms_url`. Can be: `GET` or `POST`.
     * Default is `POST`..
     *
     * @param smsMethod The HTTP method we should use to call sms_url
     * @return this
     */
    public SimUpdater setSmsMethod(final HttpMethod smsMethod) {
        this.smsMethod = smsMethod;
        return this;
    }

    /**
     * The URL we should call using the `sms_method` when the SIM-connected device
     * sends an SMS message that is not a
     * [Command](https://www.twilio.com/docs/wireless/api/command-resource)..
     *
     * @param smsUrl The URL we should call when the SIM-connected device sends an
     *               SMS message that is not a Command
     * @return this
     */
    public SimUpdater setSmsUrl(final URI smsUrl) {
        this.smsUrl = smsUrl;
        return this;
    }

    /**
     * The URL we should call using the `sms_method` when the SIM-connected device
     * sends an SMS message that is not a
     * [Command](https://www.twilio.com/docs/wireless/api/command-resource)..
     *
     * @param smsUrl The URL we should call when the SIM-connected device sends an
     *               SMS message that is not a Command
     * @return this
     */
    public SimUpdater setSmsUrl(final String smsUrl) {
        return setSmsUrl(Promoter.uriFromString(smsUrl));
    }

    /**
     * The HTTP method we should use to call `voice_fallback_url`. Can be: `GET` or
     * `POST`..
     *
     * @param voiceFallbackMethod The HTTP method we should use to call
     *                            voice_fallback_url
     * @return this
     */
    public SimUpdater setVoiceFallbackMethod(final HttpMethod voiceFallbackMethod) {
        this.voiceFallbackMethod = voiceFallbackMethod;
        return this;
    }

    /**
     * The URL we should call using the `voice_fallback_method` when an error occurs
     * while retrieving or executing the TwiML requested from `voice_url`..
     *
     * @param voiceFallbackUrl The URL we should call when an error occurs while
     *                         retrieving or executing the TwiML requested from
     *                         voice_url
     * @return this
     */
    public SimUpdater setVoiceFallbackUrl(final URI voiceFallbackUrl) {
        this.voiceFallbackUrl = voiceFallbackUrl;
        return this;
    }

    /**
     * The URL we should call using the `voice_fallback_method` when an error occurs
     * while retrieving or executing the TwiML requested from `voice_url`..
     *
     * @param voiceFallbackUrl The URL we should call when an error occurs while
     *                         retrieving or executing the TwiML requested from
     *                         voice_url
     * @return this
     */
    public SimUpdater setVoiceFallbackUrl(final String voiceFallbackUrl) {
        return setVoiceFallbackUrl(Promoter.uriFromString(voiceFallbackUrl));
    }

    /**
     * The HTTP method we should use when we call `voice_url`. Can be: `GET` or
     * `POST`..
     *
     * @param voiceMethod The HTTP method we should use when we call voice_url
     * @return this
     */
    public SimUpdater setVoiceMethod(final HttpMethod voiceMethod) {
        this.voiceMethod = voiceMethod;
        return this;
    }

    /**
     * The URL we should call using the `voice_method` when the SIM-connected device
     * makes a voice call..
     *
     * @param voiceUrl The URL we should call when the SIM-connected device makes a
     *                 voice call
     * @return this
     */
    public SimUpdater setVoiceUrl(final URI voiceUrl) {
        this.voiceUrl = voiceUrl;
        return this;
    }

    /**
     * The URL we should call using the `voice_method` when the SIM-connected device
     * makes a voice call..
     *
     * @param voiceUrl The URL we should call when the SIM-connected device makes a
     *                 voice call
     * @return this
     */
    public SimUpdater setVoiceUrl(final String voiceUrl) {
        return setVoiceUrl(Promoter.uriFromString(voiceUrl));
    }

    /**
     * Initiate a connectivity reset on the SIM. Set to `resetting` to initiate a
     * connectivity reset on the SIM. No other value is valid..
     *
     * @param resetStatus Initiate a connectivity reset on a SIM
     * @return this
     */
    public SimUpdater setResetStatus(final Sim.ResetStatus resetStatus) {
        this.resetStatus = resetStatus;
        return this;
    }

    /**
     * The SID of the [Account](https://www.twilio.com/docs/iam/api/account) to
     * which the Sim resource should belong. The Account SID can only be that of the
     * requesting Account or that of a
     * [Subaccount](https://www.twilio.com/docs/iam/api/subaccounts) of the
     * requesting Account. Only valid when the Sim resource's status is `new`. For
     * more information, see the [Move SIMs between Subaccounts
     * documentation](https://www.twilio.com/docs/wireless/api/sim-resource#move-sims-between-subaccounts)..
     *
     * @param accountSid The SID of the Account to which the Sim resource should
     *                   belong
     * @return this
     */
    public SimUpdater setAccountSid(final String accountSid) {
        this.accountSid = accountSid;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Sim
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Sim update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.WIRELESS.toString(),
            "/v1/Sims/" + this.pathSid + ""
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Sim update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Sim.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (uniqueName != null) {
            request.addPostParam("UniqueName", uniqueName);
        }

        if (callbackMethod != null) {
            request.addPostParam("CallbackMethod", callbackMethod.toString());
        }

        if (callbackUrl != null) {
            request.addPostParam("CallbackUrl", callbackUrl.toString());
        }

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (ratePlan != null) {
            request.addPostParam("RatePlan", ratePlan.toString());
        }

        if (status != null) {
            request.addPostParam("Status", status.toString());
        }

        if (commandsCallbackMethod != null) {
            request.addPostParam("CommandsCallbackMethod", commandsCallbackMethod.toString());
        }

        if (commandsCallbackUrl != null) {
            request.addPostParam("CommandsCallbackUrl", commandsCallbackUrl.toString());
        }

        if (smsFallbackMethod != null) {
            request.addPostParam("SmsFallbackMethod", smsFallbackMethod.toString());
        }

        if (smsFallbackUrl != null) {
            request.addPostParam("SmsFallbackUrl", smsFallbackUrl.toString());
        }

        if (smsMethod != null) {
            request.addPostParam("SmsMethod", smsMethod.toString());
        }

        if (smsUrl != null) {
            request.addPostParam("SmsUrl", smsUrl.toString());
        }

        if (voiceFallbackMethod != null) {
            request.addPostParam("VoiceFallbackMethod", voiceFallbackMethod.toString());
        }

        if (voiceFallbackUrl != null) {
            request.addPostParam("VoiceFallbackUrl", voiceFallbackUrl.toString());
        }

        if (voiceMethod != null) {
            request.addPostParam("VoiceMethod", voiceMethod.toString());
        }

        if (voiceUrl != null) {
            request.addPostParam("VoiceUrl", voiceUrl.toString());
        }

        if (resetStatus != null) {
            request.addPostParam("ResetStatus", resetStatus.toString());
        }

        if (accountSid != null) {
            request.addPostParam("AccountSid", accountSid.toString());
        }
    }
}