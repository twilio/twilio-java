package com.twilio.sdk.client;

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

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONValue;

/**
 * This class represents a token that will grant someone access to resources
 * within Twilio.
 */
public class TwilioCapability {

	@SuppressWarnings("serial")
	public static class DomainException extends Exception {
		public DomainException(String message) {
			super(message);
		}

		public DomainException(Exception e) {
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

	// Outgoing Paramater holding until generate token time
	private boolean buildOutgoingScope = false;
	private String appSid = null;
	private String outgoingClientName = null;
	private Map<String, String> outgoingParams = null;

	/**
	 * Create a new TwilioCapability with zero permissions. Next steps are to
	 * grant access to resources by configuring this token through the functions
	 * allowXXXX.
	 * 
	 * @param accountSid
	 *            the account sid to which this token is granted access
	 * @param authToken
	 *            the secret key used to sign the token. Note, this auth token
	 *            is not visible to the user of the token.
	 */
	public TwilioCapability(String accountSid, String authToken) {
		this.accountSid = accountSid;
		this.authToken = authToken;
		this.scopes = new ArrayList<String>();

	}

	private String buildScopeString(String serivce, String priviledge,
			Map<String, String> params) {

		StringBuilder scope = new StringBuilder();

		scope.append("scope:");
		scope.append(serivce);
		scope.append(":");
		scope.append(priviledge);

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
	 * @param appSid
	 *            the application to which this token grants access
	 */
	public void allowClientOutgoing(String appSid) {
		allowClientOutgoing(appSid, null);
	}

	/**
	 * Allow the user of this token to make outgoing connections.
	 * 
	 * @param appSid
	 *            the application to which this token grants access
	 * @param params
	 *            signed parameters that the user of this token cannot
	 *            overwrite.
	 */
	public void allowClientOutgoing(String appSid, Map<String, String> params) {
		this.buildOutgoingScope = true;
		this.outgoingParams = params;
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

	private String generateParamString(Map<String, String> params) {
		List<String> keyValues = new ArrayList<String>();
		for (String key : params.keySet()) {
			String value = params.get(key);

			try {
				key = URLEncoder.encode(key, "UTF-8");
				value = URLEncoder.encode(value, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				continue;
			}

			keyValues.add(key + "=" + value);
		}
		String paramsJoined = StringUtils.join(keyValues, '&');
		return paramsJoined;
	}

	/**
	 * If the user of this token should be allowed to accept incoming
	 * connections then configure the TwilioCapability through this method and
	 * specify the client name.
	 * 
	 * @param clientName
	 */
	public void allowClientIncoming(String clientName) {
		// Save the default client name
		this.incomingClientName = clientName;
		this.buildIncomingScope = true;
	}

	/**
	 * Allow the user of this token to access their event stream.
	 * 
	 * @param filters
	 *            key/value filters to apply to the event stream
	 */
	public void allowEventStream(Map<String, String> filters) {
		Map<String, String> value = new LinkedHashMap<String, String>();
		value.put("path", "/2010-04-01/Events");
		if (filters != null) {
			String filterString = "";
			String paramsJoined = generateParamString(filters);

			try {
				filterString = (URLEncoder.encode(paramsJoined, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// need to warn here
			}
			value.put("params", filterString);
		}

		this.scopes.add(this.buildScopeString("stream", "subscribe", value));
	}

	/**
	 * Generates a new token based on the credentials and permissions that
	 * previously has been granted to this token.
	 * 
	 * @return the newly generated token that is valid for 3600 seconds
	 * @throws DomainException
	 */
	public String generateToken() throws DomainException {
		return generateToken(3600);
	}

	/**
	 * Generates a new token based on the credentials and permissions that
	 * previously has been granted to this token.
	 * 
	 * @param ttl the number of seconds before this token expires
	 * @return the newly generated token that is valid for ttl seconds
	 * @throws DomainException
	 */
	public String generateToken(long ttl) throws DomainException {

		// Build these scopes lazily when we generate tokens so we know
		// if we have a default or incoming client name to use
		buildIncomingScope();
		buildOutgoingScope();

		try {
			Map<String, Object> payload = new LinkedHashMap<String, Object>();
			payload.put("iss", this.accountSid);
			payload.put("exp", String.valueOf(((new Date()).getTime() / 1000) + ttl));
			payload.put("scope", StringUtils.join(this.scopes, ' '));

			return jwtEncode(payload, this.authToken);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DomainException(e);
		}
	}

	private void buildOutgoingScope() {
		if (this.buildOutgoingScope) {
			Map<String, String> values = new HashMap<String, String>();

			values.put("appSid", appSid);

			// Outgoing takes precedence over any incoming name which
			// takes precedence over the default client name. however,
			// we do accept a null clientName
			if (this.outgoingClientName != null) {
				values.put("clientName", this.outgoingClientName);
			} else if (this.incomingClientName != null) {
				values.put("clientName", this.incomingClientName);
			}

			// Build outgoing scopes
			if (this.outgoingParams != null) {
				String appParamString = "";
				String paramsJoined = generateParamString(this.outgoingParams);

				try {
					appParamString = (URLEncoder.encode(paramsJoined, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// need to warn here
				}

				values.put("appParams", appParamString);
			}

			this.scopes
					.add(this.buildScopeString("client", "outgoing", values));
		}
	}

	private void buildIncomingScope() {
		if (this.buildIncomingScope) {
			Map<String, String> value = new LinkedHashMap<String, String>();

			// incoming name which takes precedence over the default client
			// name. however, we do NOT accept a null clientName here
			if (this.incomingClientName != null) {
				value.put("clientName", this.incomingClientName);
			} else {
				throw new IllegalStateException("No client name set");
			}

			this.scopes.add(this.buildScopeString("client", "incoming", value));
		}
	}

	private static String jwtEncode(Map<String, Object> payload, String key)
			throws InvalidKeyException, NoSuchAlgorithmException,
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

	private static String jsonEncode(Object object) {
		String json = JSONValue.toJSONString(object);
		return json.replace("\\/", "/");
	}

	private static String encodeBase64(String data)
			throws UnsupportedEncodingException {
		return encodeBase64(data.getBytes("UTF-8"));
	}

	private static String encodeBase64(byte[] data)
			throws UnsupportedEncodingException {
		String encodedString = new String(Base64.encodeBase64(data));
		String safeString = encodedString.replace('+', '-').replace('/', '_')
				.replace("=", "");
		return safeString;
	}

	// see
	// http://discussion.forum.nokia.com/forum/showthread.php?130974-Help-required-How-to-generate-a-MAC-(HMAC-SHA1)-with-Java
	private static String sign(String data, String key)
			throws NoSuchAlgorithmException, InvalidKeyException,
			UnsupportedEncodingException {
		SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("UTF-8"),
				"HmacSHA256");
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(signingKey);
		byte[] rawHmac = mac.doFinal(data.getBytes("UTF-8"));
		String result = encodeBase64(rawHmac);
		return result;
	}

	/**
	 * Example usage
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out
					.println("usage: java com.twilio.client.TwilioCapability accountSid authToken");
			return;
		}

		TwilioCapability capability = new TwilioCapability(args[0], args[1]);

		capability.allowEventStream(null);
		capability.allowClientIncoming("Frank");
		Map<String, String> params = new HashMap<String, String>();
		params.put("foo", "fooval");
		capability.allowClientOutgoing("APabe7650f654fc34655fc81ae71caa3ff",
				params);

		try {
			String token = capability.generateToken();
			System.out.println(token);
		} catch (DomainException e) {
			e.printStackTrace();
		}
	}
}
