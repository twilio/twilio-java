package com.twilio.jwt.validation;

import com.twilio.exception.InvalidRequestException;

import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.hash.HashFunction;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Creates a canonical string out of HTTP request components.
 * <p>
 * The process of generating the canonical request is described <a href="https://www.twilio
 * .com/docs/api/credentials/public-key-client-validation-getting-started#3-create-hash-of-the-canonical-request"
 * >here</a>.
 */
class RequestCanonicalizer {

    private static final String NEW_LINE = "\n";
    private static final Pattern TOKEN_REPLACE_PATTERN =
            Pattern.compile(String.format("%s|\\%s|\\%s|%s", "%7E", "+", "*", "%2F"));

    private final String method;
    private final String uri;
    private final String queryString;
    private final String requestBody;
    private final Header[] headers;

    public RequestCanonicalizer(String method, String uri, String queryString, String requestBody, Header[] headers) {
        this.method = method;
        this.uri = uri;
        this.queryString = queryString;
        this.requestBody = requestBody;
        this.headers = headers;
    }

    /**
     * Creates a canonical request string out of HTTP request components.
     *
     * @param sortedIncludedHeaders the headers that should be included into canonical request string
     * @param hashFunction          the hashing function applied to request payload
     * @return a string representing the canonical request
     */
    public String create(List<String> sortedIncludedHeaders, HashFunction hashFunction) {
        // Add the method and uri
        StringBuilder canonicalRequest = new StringBuilder();
        canonicalRequest.append(method).append(NEW_LINE);
        String canonicalUri = CANONICALIZE_PATH.apply(uri);
        canonicalRequest.append(canonicalUri).append(NEW_LINE);

        // Get the query args, replace whitespace and values that should be not encoded, sort and rejoin
        String canonicalQuery = CANONICALIZE_QUERY.apply(queryString);
        canonicalRequest.append(canonicalQuery).append(NEW_LINE);

        // Normalize all the headers
        Header[] lowercaseHeaders = LOWERCASE_KEYS.apply(headers);
        Map<String, List<String>> combinedHeaders = COMBINE_HEADERS.apply(lowercaseHeaders);

        // Add the headers that we care about
        for (String header : sortedIncludedHeaders) {
            String lowercase = header.toLowerCase().trim();

            if (combinedHeaders.containsKey(lowercase)) {
                List<String> values = combinedHeaders.get(lowercase);
                Collections.sort(values);

                canonicalRequest.append(lowercase)
                         .append(":")
                         .append(Joiner.on(',').join(values))
                         .append(NEW_LINE);
            }
        }
        canonicalRequest.append(NEW_LINE);

        // Mark the headers that we care about
        canonicalRequest.append(Joiner.on(";").join(sortedIncludedHeaders)).append(NEW_LINE);

        // Hash and hex the request payload
        if (!Strings.isNullOrEmpty(requestBody)) {
            String hashedPayload = hashFunction.hashString(requestBody, Charsets.UTF_8).toString();
            canonicalRequest.append(hashedPayload);
        }
        return canonicalRequest.toString();
    }

    private static Function<Header[], Map<String, List<String>>> COMBINE_HEADERS = new Function<Header[], Map<String, List<String>>>() {
        @Override
        public Map<String, List<String>> apply(Header[] headers) {
            Map<String, List<String>> combinedHeaders = new HashMap<>();

            for (Header header : headers)  {
                if (combinedHeaders.containsKey(header.getName())) {
                    combinedHeaders.get(header.getName()).add(header.getValue());
                } else {
                    combinedHeaders.put(header.getName(), Lists.newArrayList(header.getValue()));
                }
            }

            return combinedHeaders;
        }
    };

    /**
     * Creates a canonical string out of the given valid URI path by:
     * <ul>
     * <li> Normalizing the path (remove redundant path elements)
     * <li> URL-encodes the remaining path
     * <li> Replaces a set of control characters with the values defined in the contract
     *                 (e.g., space should be represented as %20 in the canonical request)
     * <li> When no path is provided, returns '/'
     * </ul>
     */
    private static Function<String, String> CANONICALIZE_PATH = new Function<String, String>() {
        @Override
        public String apply(String string) {
            if (Strings.isNullOrEmpty(string)) {
                return "/";
            }

            try {
                URI normalizedUri = new URI(string).normalize();
                String encoded = URLEncoder.encode(normalizedUri.getPath(), "UTF-8");
                return replace(encoded, true);
            } catch (URISyntaxException e) {
                throw new InvalidRequestException("Bad URI path: '" + string + "'", string, e);
            } catch (UnsupportedEncodingException e) {
                throw new InvalidRequestException("It must be possible to encode request path as ascii", string, e);
            }
        }
    };

    /**
     * Creates a canonical query string out of already URL encoded queryParams by:
     * <ul>
     * <li> Replaces a set of control characters with the values defined in the contract
     *                 (e.g., space should be represented as %20 in the canonical request)
     * <li> ASCII Sort the combined “key=value” strings (not just the ‘keys’)
     * <li> Join all key/value pairs with a ‘&’ in between
     * </ul>
     */
    private static Function<String, String> CANONICALIZE_QUERY = new Function<String, String>() {
        @Override
        public String apply(String string) {
            String replacedQueryString = replace(string, false);
            String[] queryArgs = replacedQueryString.split("&");
            Arrays.sort(queryArgs);

            return Joiner.on("&").join(queryArgs);
        }
    };

    private static Function<Header[], Header[]> LOWERCASE_KEYS = new Function<Header[], Header[]>() {
        @Override
        public Header[] apply(Header[] headers) {
            Header[] lowercaseHeaders = new Header[headers.length];
            for (int i = 0; i < headers.length; i++) {
                lowercaseHeaders[i] = new BasicHeader(headers[i].getName().toLowerCase(), headers[i].getValue());
            }

            return lowercaseHeaders;
        }
    };

    /**
     * Replaces the special characters in the URLEncoded string with the replacement values defined by the spec.
     *
     * Partially copied from https://github.com/aws/aws-sdk-java: com.amazonaws.util.SdkHttpUtils (2017-05-19)
     *
     * @param string the string to replace characters in
     * @param replaceSlash whether the encoded '/' should be replaced
     * @return the string after replacements
     */
    private static String replace(String string, boolean replaceSlash) {
        if (Strings.isNullOrEmpty(string)) {
            return string;
        }
        StringBuffer buffer = new StringBuffer(string.length());
        Matcher matcher = TOKEN_REPLACE_PATTERN.matcher(string);
        while (matcher.find()) {
            String replacement = matcher.group(0);

            if ("+".equals(replacement)) {
                replacement = "%20";
            } else if ("*".equals(replacement)) {
                replacement = "%2A";
            } else if ("%7E".equals(replacement)) {
                replacement = "~";
            } else if (replaceSlash && "%2F".equals(replacement)) {
                replacement = "/";
            }

            matcher.appendReplacement(buffer, replacement);
        }

        matcher.appendTail(buffer);
        return buffer.toString();
    }
}
