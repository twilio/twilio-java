package com.twilio.sdk.resource.instance.pricing;

import com.twilio.sdk.TwilioPricingClient;
import com.twilio.sdk.resource.NextGenInstanceResource;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Pricing information for Twilio Voice calls to a specific number.
 * <p/>
 * For more information, see <a href="FIXME">the Twilio REST API documentation</a>.
 */
public class VoiceNumber extends NextGenInstanceResource<TwilioPricingClient> {

	public VoiceNumber(final TwilioPricingClient client) {
		super(client);
	}

	public VoiceNumber(final TwilioPricingClient client, final Map<String, Object> properties) {
		super(client, properties);
	}

	public VoiceNumber(final TwilioPricingClient client, final String number) {
		super(client);
		if (number == null || "".equals(number)) {
			throw new IllegalArgumentException("'number' cannot be null");
		}
		setProperty("number", number);
	}

	/**
	 * The phone number this pricing information applies to, in E.164 format.
	 *
	 * @return E.164-formatted phone number, e.g. "+15105551234".
	 */
	public String getNumber() {
		return getProperty("number");
	}

	/**
	 * The name of the country this number is located in.
	 *
	 * @return Country name.
	 */
	public String getCountry() {
		return getProperty("country");
	}

	/**
	 * An abbreviated identifier for the country this number is located in.
	 *
	 * @return ISO 3166-1 alpha-2 country code, e.g. "US".
	 */
	public String getIsoCountry() {
		return getProperty("iso_country");
	}

	/**
	 *
	 * @return A string representing currency type, e.g. "USD".
	 */
	public String getPriceUnit() {
		return getProperty("price_unit");
	}

	/**
	 * Pricing information for outbound Twilio Voice calls to this phone number.
	 * <p/>
	 * Twilio Voice calls are priced per minute, and the price object will include prices both before and after any
	 * discounts available for the requesting account are applied.
	 *
	 * @return Object containing pricing information for outbound calls to this phone number.
	 */
	public OutboundCallPrice getOutboundCallPrice() {
		Map<String, String> prices = (Map<String, String>) getObject("outbound_call_price");
		return new OutboundCallPrice(new BigDecimal(prices.get("base_price")),
		                             new BigDecimal(prices.get("current_price")));
	}

	/**
	 * Pricing information for inbound Twilio Voice calls to this phone number, if it is a Twilio-hosted number.
	 * <p/>
	 * Twilio Voice calls are priced per minute, and the price object will include prices both before and after any
	 * discounts available for the requesting account are applied.
	 * <p/>
	 * If the number is not Twilio-hosted, this will return null.
	 *
	 * @return Object containing pricing information for inbound calls to this phone number, or null.
	 */
	public InboundCallPrice getInboundCallPrice() {
		Map<String, String> priceInfo = (Map<String, String>) getObject("inbound_call_price");
		if (priceInfo == null) {
			return null;
		}

		return new InboundCallPrice(NumberType.valueOf(priceInfo.get("number_type")
		                                                        .toUpperCase()),
		                            new BigDecimal(priceInfo.get("base_price")),
		                            new BigDecimal(priceInfo.get("current_price")));
	}

	protected String getResourceLocation() {
		return "/" + TwilioPricingClient.DEFAULT_VERSION + "/Voice/Numbers/" + getNumber();
	}

	/**
	 * Represents pricing information for outbound calls to a specific phone number.
	 * <p/>
	 * Price rates are per minute and reflect current Twilio list pricing and any discounts available for the requesting
	 * account at the time this object was requested.
	 */
	public class OutboundCallPrice {

		private final BigDecimal basePrice;
		private final BigDecimal currentPrice;

		public OutboundCallPrice(final BigDecimal basePrice, final BigDecimal currentPrice) {
			this.basePrice = basePrice;
			this.currentPrice = currentPrice;
		}

		/**
		 * Price per minute for outbound calls to a specific phone number, before any discounts have been applied.
		 *
		 * @return Decimal price rate for outbound calls.
		 */
		public BigDecimal getBasePrice() {
			return basePrice;
		}

		/**
		 * Price per minute for outbound calls to a specific phone number, after the requesting account's discounts, if
		 * any, have been applied.
		 *
		 * @return Decimal discounted price rate for outbound calls.
		 */
		public BigDecimal getCurrentPrice() {
			return currentPrice;
		}

		@Override
		public boolean equals(final Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			OutboundCallPrice that = (OutboundCallPrice) o;

			if (!basePrice.equals(that.basePrice)) {
				return false;
			}
			if (!currentPrice.equals(that.currentPrice)) {
				return false;
			}

			return true;
		}

		@Override
		public int hashCode() {
			int result = basePrice.hashCode();
			result = 31 * result + currentPrice.hashCode();
			return result;
		}
	}

	/**
	 * Represents pricing information for inbound calls to a specific Twilio-hosted phone number.
	 * <p/>
	 * Price rates are per minute and reflect current Twilio list pricing and any discounts available for the requesting
	 * account at the time this object was requested.
	 */
	public class InboundCallPrice {

		private final NumberType numberType;
		private final BigDecimal basePrice;
		private final BigDecimal currentPrice;

		public InboundCallPrice(final NumberType numberType, final BigDecimal basePrice,
		                        final BigDecimal currentPrice) {
			this.numberType = numberType;
			this.basePrice = basePrice;
			this.currentPrice = currentPrice;
		}

		/**
		 * The type of phone number this price information applies to.
		 *
		 * @return NumberType for this phone number
		 */
		public NumberType getNumberType() {
			return numberType;
		}

		/**
		 * Price per minute for inbound calls to a specific Twilio-hosted phone number, before any discounts have been
		 * applied.
		 *
		 * @return Decimal base price rate for inbound calls.
		 */
		public BigDecimal getBasePrice() {
			return basePrice;
		}

		/**
		 * Price per minute for outbound calls to a specific Twilio-hosted phone number, after any available discounts
		 * have been applied.
		 *
		 * @return Decimal disconutd price rate for inbound calls.
		 */
		public BigDecimal getCurrentPrice() {
			return currentPrice;
		}

		@Override
		public boolean equals(final Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			InboundCallPrice that = (InboundCallPrice) o;

			if (!basePrice.equals(that.basePrice)) {
				return false;
			}
			if (!currentPrice.equals(that.currentPrice)) {
				return false;
			}
			if (!numberType.equals(that.numberType)) {
				return false;
			}

			return true;
		}

		@Override
		public int hashCode() {
			int result = numberType.hashCode();
			result = 31 * result + basePrice.hashCode();
			result = 31 * result + currentPrice.hashCode();
			return result;
		}
	}

}
