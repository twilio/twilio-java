/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.wireless.v1.sim;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.base.Resource;
import com.twilio.converter.Converter;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataSession extends Resource {
    private static final long serialVersionUID = 255654490833479L;

    /**
     * Create a DataSessionReader to execute read.
     * 
     * @param pathSimSid The sim_sid
     * @return DataSessionReader capable of executing the read
     */
    public static DataSessionReader reader(final String pathSimSid) {
        return new DataSessionReader(pathSimSid);
    }

    /**
     * Converts a JSON String into a DataSession object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return DataSession object represented by the provided JSON
     */
    public static DataSession fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, DataSession.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a DataSession object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return DataSession object represented by the provided JSON
     */
    public static DataSession fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, DataSession.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String simSid;
    private final String accountSid;
    private final String radioLink;
    private final String operatorMcc;
    private final String operatorMnc;
    private final String operatorCountry;
    private final String operatorName;
    private final String cellId;
    private final Map<String, Object> cellLocationEstimate;
    private final Integer packetsUploaded;
    private final Integer packetsDownloaded;
    private final DateTime lastUpdated;
    private final DateTime start;
    private final DateTime end;
    private final String imeisv;

    @JsonCreator
    private DataSession(@JsonProperty("sid")
                        final String sid, 
                        @JsonProperty("sim_sid")
                        final String simSid, 
                        @JsonProperty("account_sid")
                        final String accountSid, 
                        @JsonProperty("radio_link")
                        final String radioLink, 
                        @JsonProperty("operator_mcc")
                        final String operatorMcc, 
                        @JsonProperty("operator_mnc")
                        final String operatorMnc, 
                        @JsonProperty("operator_country")
                        final String operatorCountry, 
                        @JsonProperty("operator_name")
                        final String operatorName, 
                        @JsonProperty("cell_id")
                        final String cellId, 
                        @JsonProperty("cell_location_estimate")
                        final Map<String, Object> cellLocationEstimate, 
                        @JsonProperty("packets_uploaded")
                        final Integer packetsUploaded, 
                        @JsonProperty("packets_downloaded")
                        final Integer packetsDownloaded, 
                        @JsonProperty("last_updated")
                        final String lastUpdated, 
                        @JsonProperty("start")
                        final String start, 
                        @JsonProperty("end")
                        final String end, 
                        @JsonProperty("imeisv")
                        final String imeisv) {
        this.sid = sid;
        this.simSid = simSid;
        this.accountSid = accountSid;
        this.radioLink = radioLink;
        this.operatorMcc = operatorMcc;
        this.operatorMnc = operatorMnc;
        this.operatorCountry = operatorCountry;
        this.operatorName = operatorName;
        this.cellId = cellId;
        this.cellLocationEstimate = cellLocationEstimate;
        this.packetsUploaded = packetsUploaded;
        this.packetsDownloaded = packetsDownloaded;
        this.lastUpdated = DateConverter.iso8601DateTimeFromString(lastUpdated);
        this.start = DateConverter.iso8601DateTimeFromString(start);
        this.end = DateConverter.iso8601DateTimeFromString(end);
        this.imeisv = imeisv;
    }

    /**
     * Returns The The unique id of the Data Session resource that this Data Record
     * is for..
     * 
     * @return The unique id of the Data Session resource that this Data Record is
     *         for.
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The The unique id of the SIM resource that this Data Session is for..
     * 
     * @return The unique id of the SIM resource that this Data Session is for.
     */
    public final String getSimSid() {
        return this.simSid;
    }

    /**
     * Returns The The unique id of the Account that the SIM belongs to..
     * 
     * @return The unique id of the Account that the SIM belongs to.
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The The generation of wireless technology that the device was
     * attached to the cellular tower using..
     * 
     * @return The generation of wireless technology that the device was attached
     *         to the cellular tower using.
     */
    public final String getRadioLink() {
        return this.radioLink;
    }

    /**
     * Returns The The 'mobile country code' is the unique id of the home country
     * where the Data Session took place..
     * 
     * @return The 'mobile country code' is the unique id of the home country where
     *         the Data Session took place.
     */
    public final String getOperatorMcc() {
        return this.operatorMcc;
    }

    /**
     * Returns The The 'mobile network code' is the unique id specific to the mobile
     * operator network where the Data Session took place..
     * 
     * @return The 'mobile network code' is the unique id specific to the mobile
     *         operator network where the Data Session took place.
     */
    public final String getOperatorMnc() {
        return this.operatorMnc;
    }

    /**
     * Returns The The three letter country code representing where the device's
     * Data Session took place..
     * 
     * @return The three letter country code representing where the device's Data
     *         Session took place.
     */
    public final String getOperatorCountry() {
        return this.operatorCountry;
    }

    /**
     * Returns The The friendly name of the mobile operator network that the
     * SIM-connected device is attached to..
     * 
     * @return The friendly name of the mobile operator network that the
     *         SIM-connected device is attached to.
     */
    public final String getOperatorName() {
        return this.operatorName;
    }

    /**
     * Returns The The unique id of the cellular tower that the device was attached
     * to at the moment when the Data Session was last updated..
     * 
     * @return The unique id of the cellular tower that the device was attached to
     *         at the moment when the Data Session was last updated.
     */
    public final String getCellId() {
        return this.cellId;
    }

    /**
     * Returns The An object representing the estimated location where the device's
     * Data Session took place..
     * 
     * @return An object representing the estimated location where the device's
     *         Data Session took place.
     */
    public final Map<String, Object> getCellLocationEstimate() {
        return this.cellLocationEstimate;
    }

    /**
     * Returns The The number of packets uploaded by the device between the start
     * time and when the Data Session was last updated..
     * 
     * @return The number of packets uploaded by the device between the start time
     *         and when the Data Session was last updated.
     */
    public final Integer getPacketsUploaded() {
        return this.packetsUploaded;
    }

    /**
     * Returns The The number of packets downloaded by the device between the start
     * time and when the Data Session was last updated..
     * 
     * @return The number of packets downloaded by the device between the start
     *         time and when the Data Session was last updated.
     */
    public final Integer getPacketsDownloaded() {
        return this.packetsDownloaded;
    }

    /**
     * Returns The The date that this resource was last updated, given as GMT in ISO
     * 8601 format..
     * 
     * @return The date that this resource was last updated, given as GMT in ISO
     *         8601 format.
     */
    public final DateTime getLastUpdated() {
        return this.lastUpdated;
    }

    /**
     * Returns The The date that this Data Session started, given as GMT in ISO 8601
     * format..
     * 
     * @return The date that this Data Session started, given as GMT in ISO 8601
     *         format.
     */
    public final DateTime getStart() {
        return this.start;
    }

    /**
     * Returns The The date that this record ended, given as GMT in ISO 8601
     * format..
     * 
     * @return The date that this record ended, given as GMT in ISO 8601 format.
     */
    public final DateTime getEnd() {
        return this.end;
    }

    /**
     * Returns The The unique id of the device using the SIM to connect..
     * 
     * @return The unique id of the device using the SIM to connect.
     */
    public final String getImeisv() {
        return this.imeisv;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DataSession other = (DataSession) o;

        return Objects.equals(sid, other.sid) && 
               Objects.equals(simSid, other.simSid) && 
               Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(radioLink, other.radioLink) && 
               Objects.equals(operatorMcc, other.operatorMcc) && 
               Objects.equals(operatorMnc, other.operatorMnc) && 
               Objects.equals(operatorCountry, other.operatorCountry) && 
               Objects.equals(operatorName, other.operatorName) && 
               Objects.equals(cellId, other.cellId) && 
               Objects.equals(cellLocationEstimate, other.cellLocationEstimate) && 
               Objects.equals(packetsUploaded, other.packetsUploaded) && 
               Objects.equals(packetsDownloaded, other.packetsDownloaded) && 
               Objects.equals(lastUpdated, other.lastUpdated) && 
               Objects.equals(start, other.start) && 
               Objects.equals(end, other.end) && 
               Objects.equals(imeisv, other.imeisv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            simSid,
                            accountSid,
                            radioLink,
                            operatorMcc,
                            operatorMnc,
                            operatorCountry,
                            operatorName,
                            cellId,
                            cellLocationEstimate,
                            packetsUploaded,
                            packetsDownloaded,
                            lastUpdated,
                            start,
                            end,
                            imeisv);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("sid", sid)
                          .add("simSid", simSid)
                          .add("accountSid", accountSid)
                          .add("radioLink", radioLink)
                          .add("operatorMcc", operatorMcc)
                          .add("operatorMnc", operatorMnc)
                          .add("operatorCountry", operatorCountry)
                          .add("operatorName", operatorName)
                          .add("cellId", cellId)
                          .add("cellLocationEstimate", cellLocationEstimate)
                          .add("packetsUploaded", packetsUploaded)
                          .add("packetsDownloaded", packetsDownloaded)
                          .add("lastUpdated", lastUpdated)
                          .add("start", start)
                          .add("end", end)
                          .add("imeisv", imeisv)
                          .toString();
    }
}