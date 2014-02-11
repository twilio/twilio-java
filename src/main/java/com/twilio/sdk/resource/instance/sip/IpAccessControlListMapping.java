package com.twilio.sdk.resource.instance.sip;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.InstanceResource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class IpAccessControlListMapping extends InstanceResource {

    /** The Constant SID_PROPERTY. */
    private static final String SID_PROPERTY = "sid";
    private String requestSipDomainSid;

    /**
     * Instantiates a new IpAccessControlListMapping.
     *
     * @param client the client
     */
     public IpAccessControlListMapping(TwilioRestClient client) {
         super(client);
     }

     /**
      * Instantiates a new IpAccessControlListMapping.
      *
      * @param client the client
      * @param sid the sid
      */
     public IpAccessControlListMapping(TwilioRestClient client, String sipDomainSid, String sid) {
         super(client);
         if (sid == null) {
             throw new IllegalStateException("The Sid for a IpAccessControlListMapping can not be null");
         }
         this.setProperty(SID_PROPERTY, sid);
         this.requestSipDomainSid = sipDomainSid;
     }

	/**
	 * Instantiates a new IpAccessControlListMapping.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public IpAccessControlListMapping(TwilioRestClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#getResourceLocation()
	 */
	@Override
	protected String getResourceLocation() {
		return "/" + TwilioRestClient.DEFAULT_VERSION
            + "/Accounts/" + this.getRequestAccountSid()
            + "/SIP/Domains/" + this.getRequestSipDomainSid()
            + "/IpAccessControlListMappings/" + this.getSid() + ".json";
	}

	/*
	 * Property getters
	 */

	/**
	 * Gets the sid.
	 *
	 * @return the sid
	 */
	public String getSid() {
		return this.getProperty(SID_PROPERTY);
	}

	/**
	 * Gets the sid of the sip domain owning this mapping.
	 *
	 * @return the sid
	 */
	public String getRequestSipDomainSid() {
		return this.requestSipDomainSid;
	}

	/**
	 * Gets the date created.
	 *
	 * @return the date created
	 */
	public Date getDateCreated() {
		SimpleDateFormat format = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss Z");
		try {
			return format.parse(this.getProperty("date_created"));
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Gets the date updated.
	 *
	 * @return the date updated
	 */
	public Date getDateUpdated() {
		SimpleDateFormat format = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss Z");
		try {
			return format.parse(this.getProperty("date_updated"));
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Gets the account sid.
	 *
	 * @return the account sid
	 */
	public String getAccountSid() {
		return this.getProperty("account_sid");
	}

	/**
	 * Gets the friendly name
	 *
	 * @return the friendly name
	 */
	public String getFriendlyName() {
		return this.getProperty("friendly_name");
	}


    /**
     * Delete this {@link IpAccessControlList}.
     * @throws TwilioRestException
     *             if there is an error in the request
     * @return true, if successful
     *
     */
    public boolean delete() throws TwilioRestException {
        TwilioRestResponse response = this.getClient().safeRequest(
                this.getResourceLocation(), "DELETE", (Map) null);

        return !response.isError();
    }
}
