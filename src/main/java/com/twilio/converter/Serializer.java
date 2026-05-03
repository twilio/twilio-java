package com.twilio.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.constant.EnumConstants.ParameterType;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.http.Request;
import java.io.IOException;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

public class Serializer {

    public static <T> void toString(Request request, String key, T value, ParameterType parameterType) {
        if (value == null) return;

        String stringValue = convertToString(value);
        addParamToRequest(request, key, stringValue, parameterType);
    }

    public static <T> void toString(Request request, ObjectMapper mapper, T value) {
        if (value == null) return;

        if (mapper == null) {
            throw new IllegalArgumentException("ObjectMapper is required for JSON serialization");
        }

        String stringValue = toJson(value, mapper);
        request.setBody(stringValue);
    }

    public static String toJson(Object object, ObjectMapper mapper) {
        try {
            return mapper.writeValueAsString(object);
        } catch (final JsonMappingException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (JsonProcessingException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private static <T> String convertToString(T value) {
        if (value instanceof Map) {
            return Converter.mapToJson((Map<String, ? extends Object>) value);
        } else if (value instanceof ZonedDateTime) {
            return ((ZonedDateTime) value).format(
                DateTimeFormatter.ofPattern(Request.QUERY_STRING_DATE_TIME_FORMAT));
        }
        else {
            return String.valueOf(value);
        }
    }

    private static void addParamToRequest(Request request, String key, String value, ParameterType parameterType) {
        Objects.requireNonNull(parameterType, "ParameterType cannot be null");
        switch (parameterType) {
            case HEADER:
                request.addHeaderParam(key, value);
                break;
            case QUERY:
                request.addQueryParam(key, value);
                break;
            case URLENCODED:
                request.addPostParam(key, value);
                break;
            case JSON:
                request.setBody(value);
            default:
                throw new IllegalArgumentException("Unsupported ParameterType: " + parameterType);
        }
    }

    /*
        Inequality fields are only supported in Query parameters.
        dateBefore is upperBound and dateAfter is lowerBound
    */
    public static void toString(final Request request, final String key, LocalDate date, LocalDate dateBefore, LocalDate dateAfter) {
        if (date != null) {
            request.addQueryParam(key, date.format(DateTimeFormatter.ofPattern(Request.QUERY_STRING_DATE_FORMAT)));
        } else if (dateAfter != null || dateBefore != null) {
            request.addQueryDateRange(key, dateAfter, dateBefore);
        }
    }

    /*
        Inequality fields are only supported in Query parameters.
        dateBefore is upperBound and dateAfter is lowerBound
    */
    public static void toString(final Request request, final String key, ZonedDateTime date, ZonedDateTime dateBefore, ZonedDateTime dateAfter) {
        if (date != null) {
            request.addQueryParam(key, date.format(DateTimeFormatter.ofPattern(Request.QUERY_STRING_DATE_TIME_FORMAT)));
        } else if (dateAfter != null || dateBefore != null) {
            request.addQueryDateTimeRange(key, dateAfter, dateBefore);
        }
    }
}
