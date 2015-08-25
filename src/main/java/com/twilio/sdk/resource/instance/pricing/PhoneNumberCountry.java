package com.twilio.sdk.resource.instance.pricing;

import com.twilio.sdk.TwilioPricingClient;
import com.twilio.sdk.resource.NextGenInstanceResource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Pricing information for Phone Numbers in a specific country.
 * <p/>
 * For more information see <a href="FIXME">the Twilio REST API documentation.</a>
 */
public class PhoneNumberCountry extends NextGenInstanceResource<TwilioPricingClient> {

	public PhoneNumberCountry(final TwilioPricingClient client) {
		super(client);
	}

	public PhoneNumberCountry(final TwilioPricingClient client, final Map<String, Object> properties) {
		super(client, properties);
	}

	public PhoneNumberCountry(final TwilioPricingClient client, final String isoCountry) {
		super(client);
		if (isoCountry == null || "".equals(isoCountry)) {
			throw new IllegalArgumentException("isoCountry cannot be null");
		}
		setProperty("iso_country", isoCountry);
	}

	/**
	 * Get an abbreviated identifier for the country this pricing information applies to.
	 *
	 * @return the ISO 3166-1 alpha-2 country code
	 */
	public String getIsoCountry() {
		return getProperty("iso_country");
	}

	/**
	 * Get the name of the country this pricing information applies to.
	 *
	 * @return the country name
	 */
	public String getCountry() {
		return getProperty("country");
	}

	/**
	 * Get the currency unit for this pricing information.
	 *
	 * @return A string representing the currency information, e.g. "USD" for US Dollars.
	 */
	public String getPriceUnit() {
		return getProperty("price_unit");
	}

	/**
	 * Get a list of prices for available phone number types in this country.
	 * <p/>
	 * Twilio Phone Numbers are priced per month, and each price object will contain the price for a specific type of
	 * phone number (e.g. mobile, local, toll-free) both before and after any discounts available for the requesting
	 * account are applied.
	 *
	 * @return A list of objects with phone number pricing information.
	 */
	public List<NumberPrice> getPhoneNumberPrices() {
		List<Map<String, String>> priceData = (List<Map<String, String>>) getObject("phone_number_prices");
		List<NumberPrice> prices = new ArrayList<NumberPrice>();

		for (Map<String, String> p : priceData) {
			prices.add(new NumberPrice(NumberType.valueOf(p.get("number_type")
			                                               .toUpperCase()), new BigDecimal(p.get("base_price")),
			                           new BigDecimal(p.get("current_price"))));
		}
		return prices;
	}

	protected String getResourceLocation() {
		return "/" + TwilioPricingClient.DEFAULT_VERSION + "/PhoneNumbers/Countries/" + getIsoCountry();
	}

	/**
	 * Holds pricing information for a specific type of Twilio Phone Number.
	 */
	public class NumberPrice {

		private final NumberType numberType;
		private final BigDecimal basePrice;
		private final BigDecimal currentPrice;

		public NumberPrice(final NumberType numberType, final BigDecimal basePrice, final BigDecimal currentPrice) {
			this.numberType = numberType;
			this.basePrice = basePrice;
			this.currentPrice = currentPrice;
		}

		/**
		 * The type of phone number these prices apply to.
		 *
		 * @return A value of the NumberType enum
		 */
		public NumberType getNumberType() {
			return numberType;
		}

		/**
		 * The list price for this type of phone number.
		 *
		 * @return Price in fixed-point decimal units
		 */
		public BigDecimal getBasePrice() {
			return basePrice;
		}

		/**
		 * The price for this type of phone number after applying any discounts available for your account.
		 *
		 * @return Price in fixed-point decimal units
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

			NumberPrice that = (NumberPrice) o;

			if (!basePrice.equals(that.basePrice)) {
				return false;
			}
			if (!currentPrice.equals(that.currentPrice)) {
				return false;
			}
			if (numberType != that.numberType) {
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
