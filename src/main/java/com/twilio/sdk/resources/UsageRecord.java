package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.readers.UsageRecordReader;
import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsageRecord extends Resource {

    private static final long serialVersionUID = -5732541214023360255L;

    private final UsageCategory category;
    private final String count;
    private final Currency priceUnit;
    private final Map<String, String> subresourceUris;
    private final String description;
    private final LocalDate endDate;
    private final String usageUnit;
    private final BigDecimal price;
    private final String uri;
    private final String accountSid;
    private final String usage;
    private final LocalDate startDate;
    private final String countUnit;

    @JsonCreator
    private UsageRecord(@JsonProperty("category") final UsageCategory category,
                        @JsonProperty("count") final String count, @JsonProperty("price_unit") final String priceUnit,
                        @JsonProperty("subresource_uris") final Map<String, String> subresourceUris,
                        @JsonProperty("description") final String description,
                        @JsonProperty("end_date") final String endDate,
                        @JsonProperty("usage_unit") final String usageUnit,
                        @JsonProperty("price") final BigDecimal price, @JsonProperty("uri") final String uri,
                        @JsonProperty("account_sid") final String accountSid, @JsonProperty("usage") final String usage,
                        @JsonProperty("start_date") final String startDate,
                        @JsonProperty("count_unit") final String countUnit) {
        this.category = category;
        this.count = count;
        if (priceUnit != null) {
            this.priceUnit = Currency.getInstance(priceUnit.toUpperCase());
        } else {
            this.priceUnit = null;
        }
        this.subresourceUris = subresourceUris;
        this.description = description;
        this.endDate = safeDateConvert(endDate);
        this.usageUnit = usageUnit;
        this.price = price;
        this.uri = uri;
        this.accountSid = accountSid;
        this.usage = usage;
        this.startDate = safeDateConvert(startDate);
        this.countUnit = countUnit;

    }

    public static UsageRecordReader list() {
        return new UsageRecordReader();
    }

    public final UsageCategory getCategory() {
        return category;
    }

    public final String getCount() {
        return count;
    }

    public final Currency getPriceUnit() {
        return priceUnit;
    }

    public final Map<String, String> getSubresourceUris() {
        return subresourceUris;
    }

    public final String getDescription() {
        return description;
    }

    public final LocalDate getEndDate() {
        return endDate;
    }

    public final String getUsageUnit() {
        return usageUnit;
    }

    public final BigDecimal getPrice() {
        return price;
    }

    public final String getUri() {
        return uri;
    }

    public final String getAccountSid() {
        return accountSid;
    }

    public final String getUsage() {
        return usage;
    }

    public final LocalDate getStartDate() {
        return startDate;
    }

    public final String getCountUnit() {
        return countUnit;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UsageRecord self = (UsageRecord) o;

        return (Objects.equals(category, self.category) &&
                Objects.equals(count, self.count) &&
                Objects.equals(priceUnit, self.priceUnit) &&
                Objects.equals(subresourceUris, self.subresourceUris) &&
                Objects.equals(description, self.description) &&
                Objects.equals(endDate, self.endDate) &&
                Objects.equals(usageUnit, self.usageUnit) &&
                Objects.equals(price, self.price) &&
                Objects.equals(uri, self.uri) &&
                Objects.equals(accountSid, self.accountSid) &&
                Objects.equals(usage, self.usage) &&
                Objects.equals(startDate, self.startDate) &&
                Objects.equals(countUnit, self.countUnit));
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, count, priceUnit, subresourceUris, description, endDate, usageUnit, price, uri,
                            accountSid, usage, startDate, countUnit);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("category", category)
                          .add("count", count)
                          .add("priceUnit", priceUnit)
                          .add("subresourceUris", subresourceUris)
                          .add("description", description)
                          .add("endDate", endDate)
                          .add("usageUnit", usageUnit)
                          .add("price", price)
                          .add("uri", uri)
                          .add("accountSid", accountSid)
                          .add("usage", usage)
                          .add("startDate", startDate)
                          .add("countUnit", countUnit)
                          .toString();
    }

}
