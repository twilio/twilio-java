package com.twilio.sdk.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a token that will grant someone access to resources within Twilio.
 */
public class TwilioCapability {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@SuppressWarnings("serial")
	public static class DomainException extends Exception {
		public DomainException(final String message) {
			super(message);
		}

		public DomainException(final Exception e) {
			super(e);
		}
	}

	private String accountSid;
	private String authToken;
	private List<String> scopes;

	// Default Data
	// private String defaultClientName = null;

	// Incoming Parameter holding until generate token time
	private boolean buildIncomingScope = false;
	private String incomingClientName = null;

	// Outgoing Parameter holding until generate token time
	private boolean buildOutgoingScope = false;
	private String appSid = null;
	private String outgoingClientName = null;
	private Map<String, String> outgoingParams = null;

	/**
	 * Create a new TwilioCapability with zero permissions. Next steps are to grant access to resources by configuring
	 * this token through the functions allowXXXX.
	 *
	 * @param accountSid the account sid to which this token is granted access
	 * @param authToken the secret key used to sign the token. Note, this auth token is not visible to the user of the
	 * token.
	 */
	public TwilioCapability(final String accountSid, final String authToken) {
		this.accountSid = accountSid;
		this.authToken = authToken;
		scopes = new ArrayList<String>();

	}

	private String buildScopeString(final String service, final String privilege, final Map<String, String> params) {

		StringBuilder scope = new StringBuilder();

		scope.append("scope:");
		scope.append(service);
		scope.append(":");
		scope.append(privilege);

		if (params != null && params.size() > 0) {
			String paramsJoined = generateParamString(params);

			scope.append("?");
			scope.append(paramsJoined);
		}

		return scope.toString();
	}

	/**
	 * Allow the user of this token to make outgoing connections.
	 *
	 * @param appSid the application to which this token grants access
	 */
	public void allowClientOutgoing(final String appSid) {
		allowClientOutgoing(appSid, null);
	}

	/**
	 * Allow the user of this token to make outgoing connections.
	 *
	 * @param appSid the application to which this token grants access
	 * @param params signed parameters that the user of this token cannot overwrite.
	 */
	public void allowClientOutgoing(final String appSid, final Map<String, String> params) {
		buildOutgoingScope = true;
		outgoingParams = params;
		this.appSid = appSid;
	}

	//
	// /**
	// * Allow the user of this token to make outgoing connections.
	// *
	// * @param applicationSid
	// * the application to which this token grants access
	// * @param clientName
	// * the name for this client
	// * @param params
	// * signed parameters that the user of this token cannot
	// * overwrite.
	// */
	// public void allowClientOutgoing(String appSid, String clientName,
	// Map<String, String> params) {
	//
	// Map<String, String> values = new LinkedHashMap<String, String>();
	//
	// this.outgoingClientName = clientName;
	// this.buildOutgoingScope = true;
	// this.outgoingParams = params;
	// this.appSid = appSid;
	//
	// }

	private String generateParamString(final Map<String, String> params) {
		List<String> keyValues = new ArrayList<String>();
		for (String key : params.keySet()) {
			String value = params.get(key);

			try {
				key = URLEncoder.encode(key, "UTF-8");
				value = URLEncoder.encode(value, "UTF-8");
			} catch (final UnsupportedEncodingException e) {
				e.printStackTrace();
				continue;
			}

			keyValues.add(key + "=" + value);
		}
		return StringUtils.join(keyValues, '&');
	}

	/**
	 * If the user of this token should be allowed to accept incoming connections then configure the TwilioCapability
	 * through this method and specify the client name.
	 *
	 * @param clientName
	 */
	public void allowClientIncoming(final String clientName) {
		// Save the default client name
		incomingClientName = clientName;
		buildIncomingScope = true;
	}

	/**
	 * Allow the user of this token to access their event stream.
	 *
	 * @param filters key/value filters to apply to the event stream
	 */
	public void allowEventStream(final Map<String, String> filters) {
		Map<String, String> value = new LinkedHashMap<String, String>();
		value.put("path", "/2010-04-01/Events");
		if (filters != null) {
			String paramsJoined = generateParamString(filters);
			value.put("params", paramsJoined);
		}

		scopes.add(buildScopeString("stream", "subscribe", value));
	}

	/**
	 * Generates a new token based on the credentials and permissions that previously has been granted to this token.
	 *
	 * @return the newly generated token that is valid for 3600 seconds
	 * @throws DomainException
	 */
	public String generateToken() throws DomainException {
		return generateToken(3600);
	}

	/**
	 * Generates a new token based on the credentials and permissions that previously has been granted to this token.
	 *
	 * @param ttl the number of seconds before this token expires
	 * @return the newly generated token that is valid for ttl seconds
	 * @throws DomainException
	 */
	public String generateToken(final long ttl) throws DomainException {

		// Build these scopes lazily when we generate tokens so we know
		// if we have a default or incoming client name to use
		buildIncomingScope();
		buildOutgoingScope();

		try {
			Map<String, Object> payload = new LinkedHashMap<String, Object>();
			payload.put("iss", accountSid);
			payload.put("exp", String.valueOf(((new Date()).getTime() / 1000) + ttl));
			payload.put("scope", StringUtils.join(scopes, ' '));

			return jwtEncode(payload, authToken);
		} catch (final Exception e) {
			e.printStackTrace();
			throw new DomainException(e);
		}
	}

	private void buildOutgoingScope() {
		if (buildOutgoingScope) {
			Map<String, String> values = new HashMap<String, String>();

			values.put("appSid", appSid);

			// Outgoing takes precedence over any incoming name which
			// takes precedence over the default client name. however,
			// we do accept a null clientName
			if (outgoingClientName != null) {
				values.put("clientName", outgoingClientName);
			} else if (incomingClientName != null) {
				values.put("clientName", incomingClientName);
			}

			// Build outgoing scopes
			if (outgoingParams != null) {
				String paramsJoined = generateParamString(outgoingParams);
				values.put("appParams", paramsJoined);
			}

			scopes.add(buildScopeString("client", "outgoing", values));
		}
	}

	private void buildIncomingScope() {
		if (buildIncomingScope) {
			Map<String, String> value = new LinkedHashMap<String, String>();

			// incoming name which takes precedence over the default client
			// name. however, we do NOT accept a null clientName here
			if (incomingClientName != null) {
				value.put("clientName", incomingClientName);
			} else {
				throw new IllegalStateException("No client name set");
			}

			scopes.add(buildScopeString("client", "incoming", value));
		}
	}

	private static String jwtEncode(final Map<String, Object> payload, final String key) throws InvalidKeyException,
	                                                                                            NoSuchAlgorithmException,
	                                                                                            UnsupportedEncodingException {

		Map<String, Object> header = new LinkedHashMap<String, Object>();
		header.put("typ", "JWT");
		header.put("alg", "HS256");

		List<String> segments = new ArrayList<String>();
		segments.add(encodeBase64(jsonEncode(header)));
		segments.add(encodeBase64(jsonEncode(payload)));

		String signingInput = StringUtils.join(segments, ".");
		String signature = sign(signingInput, key);
		segments.add(signature);

		return StringUtils.join(segments, ".");
	}

	private static String jsonEncode(final Object object) {
		String json = "";
		try {
			json = OBJECT_MAPPER.writeValueAsString(object);
			json = json.replace("\\/", "/");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	private static String encodeBase64(final String data) throws UnsupportedEncodingException {
		return encodeBase64(data.getBytes("UTF-8"));
	}

	private static String encodeBase64(final byte[] data) throws UnsupportedEncodingException {
		String encodedString = new String(Base64.encodeBase64(data));
		return encodedString.replace('+', '-').replace('/', '_').replace("=", "");
	}

	// see
	// http://discussion.forum.nokia.com/forum/showthread.php?130974-Help-required-How-to-generate-a-MAC-(HMAC-SHA1)-with-Java
	private static String sign(final String data, final String key) throws NoSuchAlgorithmException,
	                                                                       InvalidKeyException,
	                                                                       UnsupportedEncodingException {
		SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(signingKey);
		byte[] rawHmac = mac.doFinal(data.getBytes("UTF-8"));
		return encodeBase64(rawHmac);
	}

	/**
	 * Example usage
	 *
	 * @param args
	 */
	public static void main(final String[] args) {
		if (args.length < 2) {
			System.out.println("usage: java com.twilio.client.TwilioCapability accountSid authToken");
			return;
		}

		TwilioCapability capability = new TwilioCapability(args[0], args[1]);

		capability.allowEventStream(null);
		capability.allowClientIncoming("Frank");
		Map<String, String> params = new HashMap<String, String>();
		params.put("foo", "fooval");
		capability.allowClientOutgoing("APabe7650f654fc34655fc81ae71caa3ff", params);

		try {
			String token = capability.generateToken();
			System.out.println(token);
		} catch (final DomainException e) {
			e.printStackTrace();
		}
	}
}
