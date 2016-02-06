package com.twilio.sdk;

import com.twilio.sdk.pojo.SMSCriteria;

/**
 * Created by will on 06/02/2016.
 */
public interface ITwilioClient {

    TwilioRestResponse sendSms(SMSCriteria smsCriteria) throws TwilioRestException;
}
