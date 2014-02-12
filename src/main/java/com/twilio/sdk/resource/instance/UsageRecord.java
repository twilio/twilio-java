package com.twilio.sdk.resource.instance;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.InstanceResource;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * The Class Usage.
 * <p/>
 * For more information see <a
 * href="https://www.twilio.com/docs/api/rest/usage-records"
 * >https://www.twilio.com/docs/api/rest/usage-records</a>
 */
public class UsageRecord extends InstanceResource {

	/**
	 * The Constant SID_PROPERTY.
	 */
	private static final String SID_PROPERTY = "sid";

	/**
	 * Instantiates a new usageRecord.
	 *
	 * @param client the client
	 */
	public UsageRecord(TwilioRestClient client) {
		super(client);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
		protected String getResourceLocation() {
			return null;
		}

	/**
	 * Instantiates a new usageRecord.
	 *
	 * @param client the client
	 * @param sid    the sid
	 */
	public UsageRecord(TwilioRestClient client, String sid) {
		super(client);
		if (sid == null) {
			throw new IllegalStateException(
					"The Sid for a UsageRecord can not be null");
		}
		this.setProperty(SID_PROPERTY, sid);
	}

	/**
	 * Instantiates a new UsageRecord.
	 *
	 * @param client     the client
	 * @param properties the properties
	 */
	public UsageRecord(TwilioRestClient client, Map<String, Object> properties) {
		super(client, properties);
	}

	public UsageCategory getCategory() {
		try {
			return UsageCategory.valueOf(getProperty("Category").replace('-', '_'));
        } catch (IllegalArgumentException e) {
			return null;
		}

	}

	public String getDescription() {
		return getProperty("Description");
	}

	public Date getStartDate() {
		SimpleDateFormat format = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss Z");
		try {
			return format.parse(this.getProperty("StartDate"));
		} catch (ParseException e) {
			return null;
		}
	}

	public Date getEndDate() {
		SimpleDateFormat format = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss Z");
		try {
			return format.parse(this.getProperty("EndDate"));
		} catch (ParseException e) {
			return null;
		}
	}

	public BigDecimal getUsage() {
		return new BigDecimal(getProperty("Usage"));
	}

	@Deprecated
	public String getUsageUnits() {
		return getProperty("UsageUnits");
	}

	public String getUsageUnit() {
		return getProperty("UsageUnit");
	}

	public Long getCount() {
		String count = getProperty("Count");
		return count == null ? null : Long.parseLong(getProperty("Count"));
	}

	@Deprecated
	public String getCountUnits() {
		return getProperty("CountUnits");
	}

	public String getCountUnit() {
		return getProperty("CountUnit");
	}

	public BigDecimal getPrice() {
		return new BigDecimal(getProperty("Price"));
	}

	public String getPriceUnit() {
		return getProperty("PriceUnit");
	}

	public String getUri() {
		return getProperty("Uri");
	}

	public String[] getSubresourceUris() {
		return null;
	}
}
