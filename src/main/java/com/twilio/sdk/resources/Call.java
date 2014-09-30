package com.twilio.sdk.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class Call {
    private String accountSid;
    private String annotation;
    private String answeredBy;
    private String apiVersion;
    private String callerName;
    private String dateCreated;
    private String dateUpdated;
    private String direction;
    private Integer duration;
    private String endTime;
    private String forwardedFrom;
    private String from;
    private String fromFormatted;
    private String groupSid;
    private String parentCallSid;
    private String phoneNumberSid;
    private Double price;
    private String priceUnit;
    private String sid;
    private String startTime;
    private String status;
    private Map<String, String> subresourceUris;
    private String to;
    private String toFormatted;
    private String uri;

    @JsonCreator
    private Call(@JsonProperty("account_sid")       String accountSid,
                 @JsonProperty("annotation")        String annotation,
                 @JsonProperty("answered_by")       String answeredBy,
                 @JsonProperty("api_version")       String apiVersion,
                 @JsonProperty("caller_name")       String callerName,
                 @JsonProperty("date_created")      String dateCreated,
                 @JsonProperty("date_updated")      String dateUpdated,
                 @JsonProperty("direction")         String direction,
                 @JsonProperty("duration")          Integer duration,
                 @JsonProperty("end_time")          String endTime,
                 @JsonProperty("forwarded_from")    String forwardedFrom,
                 @JsonProperty("from")              String from,
                 @JsonProperty("from_formatted")    String fromFormatted,
                 @JsonProperty("group_sid")         String groupSid,
                 @JsonProperty("parent_call_sid")   String parentCallSid,
                 @JsonProperty("phone_number_sid")  String phoneNumberSid,
                 @JsonProperty("price")             Double price,
                 @JsonProperty("price_unit")        String priceUnit,
                 @JsonProperty("sid")               String sid,
                 @JsonProperty("start_time")        String startTime,
                 @JsonProperty("status")            String status,
                 @JsonProperty("subresource_uris")  Map<String, String> subresourceUris,
                 @JsonProperty("to")                String to,
                 @JsonProperty("to_formatted")      String toFormatted,
                 @JsonProperty("uri")               String uri) {
        this.accountSid = accountSid;
        this.annotation = annotation;
        this.answeredBy = answeredBy;
        this.apiVersion = apiVersion;
        this.callerName = callerName;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.direction = direction;
        this.duration = duration;
        this.endTime = endTime;
        this.forwardedFrom = forwardedFrom;
        this.from = from;
        this.fromFormatted = fromFormatted;
        this.groupSid = groupSid;
        this.parentCallSid = parentCallSid;
        this.phoneNumberSid = phoneNumberSid;
        this.price = price;
        this.priceUnit = priceUnit;
        this.sid = sid;
        this.startTime = startTime;
        this.status = status;
        this.subresourceUris = subresourceUris;
        this.to = to;
        this.toFormatted = toFormatted;
        this.uri = uri;

    }

    public static Call fromJson(String json) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(json, Call.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e.getMessage());
        } catch (JsonParseException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getAccountSid() {
        return accountSid;
    }

    public String getAnnotation() {
        return annotation;
    }

    public String getAnsweredBy() {
        return answeredBy;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getCallerName() {
        return callerName;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public String getDirection() {
        return direction;
    }

    public Integer getDuration() {
        return duration != null ? duration : 0;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getForwardedFrom() {
        return forwardedFrom;
    }

    public String getFrom() {
        return from;
    }

    public String getFromFormatted() {
        return fromFormatted;
    }

    public String getGroupSid() {
        return groupSid;
    }

    public String getParentCallSid() {
        return parentCallSid;
    }

    public String getPhoneNumberSid() {
        return phoneNumberSid;
    }

    public Double getPrice() {
        return price;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public String getSid() {
        return sid;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getStatus() {
        return status;
    }

    public Map<String, String> getSubresourceUris() {
        return subresourceUris;
    }

    public String getTo() {
        return to;
    }

    public String getToFormatted() {
        return toFormatted;
    }

    public String getUri() {
        return uri;
    }
}
