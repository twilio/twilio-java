package com.twilio.sdk;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The abstract class TwilioClient.
 */
public abstract class TwilioClient {

	private static final int ACCOUNT_SID_LENGTH = 34;

	/** The Constant VERSION. */
	private static final String VERSION = "4.5.0";

	/** The endpoint. */
	private String endpoint = "https://api.twilio.com";

	/** The Constant DEFAULT_VERSION. */
	public static final String DEFAULT_VERSION = "2010-04-01";

	/** The account sid. */
	private final String accountSid;

	/** The auth token. */
	private final String authToken;

	/** The num retries. */
	private int numRetries = 3;

	/**
	 * The default HTTP Connection timeout
	 */
	private static final int CONNECTION_TIMEOUT = 10000;

	/**
	 * The default timeout to use for requests to Twilio
	 */
	private static final int READ_TIMEOUT = 30500;

	/** The httpclient. */
	private HttpClient httpclient;

	/**
	 * Gets the num retries.
	 *
	 * @return the num retries
	 */
	public int getNumRetries() {
		return numRetries;
	}

	/**
	 * Sets the num retries.
	 *
	 * @param numRetries the new num retries
	 */
	public void setNumRetries(final int numRetries) {
		this.numRetries = numRetries;
	}

	public void setHttpClient(final HttpClient httpclient) {
		this.httpclient = httpclient;
	}

	public HttpClient getHttpClient() {
		return httpclient;
	}

	/**
	 * Explicitly construct a TwilioClient with the given API credentials.
	 *
	 * @param accountSid the 34 character Account identifier (starting with 'AC'). This can be found on your Twilio
	 * dashboard page.
	 * @param authToken the 32 character AuthToken. This can be found on your Twilio dashboard page.
	 */
	public TwilioClient(final String accountSid, final String authToken) {
		this(accountSid, authToken, null);
	}

	/**
	 * Explicitly construct a TwilioClient with the given API credentials and endpoint.
	 *
	 * @param accountSid the 34 character Account identifier (starting with 'AC'). This can be found on your Twilio
	 * dashboard page.
	 * @param authToken the 32 character AuthToken. This can be found on your Twilio dashboard page.
	 * @param endpoint the url of API endpoint you wish to use. (e.g. - 'https://api.twilio.com')
	 */
	public TwilioClient(final String accountSid, final String authToken, final String endpoint) {

		validateAccountSid(accountSid);
		validateAuthToken(authToken);

		this.accountSid = accountSid;
		this.authToken = authToken;

		if ((endpoint != null) && (!endpoint.equals(""))) {
			this.endpoint = endpoint;
		}

		//Grab the proper connection manager, based on runtime environment
		ClientConnectionManager mgr;
		try {
			Class.forName("com.google.appengine.api.urlfetch.HTTPRequest");
			mgr = new AppEngineClientConnectionManager();
		} catch (final ClassNotFoundException e) {
			//Not GAE
			mgr = new PoolingClientConnectionManager();
			((PoolingClientConnectionManager) mgr).setDefaultMaxPerRoute(10);
		}

		setHttpClient(new DefaultHttpClient(mgr));
		httpclient.getParams().setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
		httpclient.getParams().setParameter("http.socket.timeout", new Integer(READ_TIMEOUT));
		httpclient.getParams().setParameter("http.connection.timeout", new Integer(CONNECTION_TIMEOUT));
		httpclient.getParams().setParameter("http.protocol.content-charset", "UTF-8");
	}

	/**
	 * Validate auth token.
	 *
	 * @param authToken the auth token
	 */
	private void validateAuthToken(final String authToken) {
		if (authToken == null) {
			throw new IllegalArgumentException("AuthToken must not be null.");
		}
	}

	// Check for a valid 34 character account sid starting with 'AC'

	/**
	 * Validate account sid.
	 *
	 * @param accountSid the account sid
	 */
	private void validateAccountSid(final String accountSid) {
		if (accountSid == null || !accountSid.startsWith("AC") || accountSid.length() != ACCOUNT_SID_LENGTH) {
			throw new IllegalArgumentException(
					"AccountSid '" + accountSid + "' is not valid.  It should be the 34 character unique identifier starting with 'AC'");
		}
	}

	/**
	 * Generate parameters.
	 *
	 * @param vars the vars
	 * @return the list
	 */
	private static List<NameValuePair> generateParameters(final Map<String, String> vars) {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();

		if (vars != null) {
			for (final String var : vars.keySet()) {
				qparams.add(new BasicNameValuePair(var, vars.get(var)));
			}
		}

		return qparams;
	}

	/*
	 *
	 * Method builders
	 */

	/**
	 * Builds the method.
	 *
	 * @param method the method
	 * @param path the path
	 * @param params the params
	 * @return the http uri request
	 */
	private HttpUriRequest buildMethod(final String method, final String path, final List<NameValuePair> params) {
		if (method.equalsIgnoreCase("GET")) {
			return generateGetRequest(path, params);
		} else if (method.equalsIgnoreCase("POST")) {
			return generatePostRequest(path, params);
		} else if (method.equalsIgnoreCase("PUT")) {
			return generatePutRequest(path, params);
		} else if (method.equalsIgnoreCase("DELETE")) {
			return generateDeleteRequest(path, params);
		} else {
			throw new IllegalArgumentException("Unknown Method: " + method);
		}
	}

	/**
	 * Generate get request.
	 *
	 * @param path the path
	 * @param params the params
	 * @return the http get
	 */
	private HttpGet generateGetRequest(final String path, final List<NameValuePair> params) {

		URI uri = buildUri(path, params);
		return new HttpGet(uri);
	}

	/**
	 * Generate post request.
	 *
	 * @param path the path
	 * @param params the params
	 * @return the http post
	 */
	private HttpPost generatePostRequest(final String path, final List<NameValuePair> params) {
		URI uri = buildUri(path);

		UrlEncodedFormEntity entity = buildEntityBody(params);

		HttpPost post = new HttpPost(uri);
		post.setEntity(entity);

		return post;
	}

	/**
	 * Generate put request.
	 *
	 * @param path the path
	 * @param params the params
	 * @return the http put
	 */
	private HttpPut generatePutRequest(final String path, final List<NameValuePair> params) {
		URI uri = buildUri(path);

		UrlEncodedFormEntity entity = buildEntityBody(params);

		HttpPut put = new HttpPut(uri);
		put.setEntity(entity);

		return put;
	}

	/**
	 * Generate delete request.
	 *
	 * @param path the path
	 * @param params the params
	 * @return the http delete
	 */
	private HttpDelete generateDeleteRequest(final String path, final List<NameValuePair> params) {
		URI uri = buildUri(path);
		return new HttpDelete(uri);
	}

	/*
	 *
	 * Helper functions for building methods
	 */

	/**
	 * Builds the entity body.
	 *
	 * @param params the params
	 * @return the url encoded form entity
	 */
	private UrlEncodedFormEntity buildEntityBody(final List<NameValuePair> params) {
		UrlEncodedFormEntity entity;
		try {
			entity = new UrlEncodedFormEntity(params, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		return entity;
	}

	/**
	 * Builds the uri.
	 *
	 * @param path the path
	 * @return the uRI
	 */
	private URI buildUri(final String path) {
		return buildUri(path, null);
	}

	/**
	 * Builds the uri.
	 *
	 * @param path the path
	 * @param queryStringParams the query string params
	 * @return the uRI
	 */
	private URI buildUri(final String path, final List<NameValuePair> queryStringParams) {
		StringBuilder sb = new StringBuilder();
		sb.append(path);

		if (queryStringParams != null && queryStringParams.size() > 0) {
			sb.append("?");
			sb.append(URLEncodedUtils.format(queryStringParams, "UTF-8"));
		}

		URI uri;
		try {
			uri = new URI(sb.toString());
		} catch (final URISyntaxException e) {
			throw new IllegalStateException("Invalid uri", e);
		}

		return uri;
	}

	/**
	 * sendRequst Sends a REST Request to the Twilio REST API.
	 *
	 * @param path the URL (absolute w.r.t. the endpoint URL - i.e. /2010-04-01/Accounts)
	 * @param method the HTTP method to use, defaults to GET
	 * @param paramMap for POST or PUT, a map of data to send, for GET will be appended to the URL as querystring
	 * params
	 * <p/>
	 * This method is public for backwards compatibility with the old twilio helper library
	 * @return the twilio rest response
	 */
	public TwilioRestResponse request(final String path, final String method, final Map<String, String> paramMap) throws
	                                                                                                              TwilioRestException {

		List<NameValuePair> paramList = generateParameters(paramMap);
		return request(path, method, paramList);
	}

	public TwilioRestResponse request(final String path, final String method,
	                                  final List<NameValuePair> paramList) throws TwilioRestException {

		HttpUriRequest request = setupRequest(path, method, paramList);

		HttpResponse response;
		try {
			response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			Header[] contentTypeHeaders = response.getHeaders("Content-Type");
			String responseBody = "";

			if (entity != null) {
				responseBody = EntityUtils.toString(entity);
			}

			StatusLine status = response.getStatusLine();
			int statusCode = status.getStatusCode();

			TwilioRestResponse restResponse = new TwilioRestResponse(request.getURI().toString(), responseBody,
			                                                         statusCode);

			// For now we only set the first content type seen
			for (final Header h : contentTypeHeaders) {
				restResponse.setContentType(h.getValue());
				break;
			}

			return restResponse;

		} catch (final ClientProtocolException e1) {
			throw new RuntimeException(e1);
		} catch (final IOException e1) {
			throw new RuntimeException(e1);
		}
	}

	/**
	 * Request stream.
	 *
	 * @param path the path
	 * @param method the method
	 * @param vars the vars
	 * @return the input stream
	 */
	public InputStream requestStream(final String path, final String method, final Map<String, String> vars) {

		List<NameValuePair> paramList = generateParameters(vars);
		return requestStream(path, method, paramList);
	}

	/**
	 * Request stream.
	 *
	 * @param path the path
	 * @param method the method
	 * @param paramList the list of POST params
	 * @return the input stream
	 */
	public InputStream requestStream(final String path, final String method, final List<NameValuePair> paramList) {

		HttpUriRequest request = setupRequest(path, method, paramList);

		HttpResponse response;
		try {
			response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			return entity.getContent();

		} catch (final ClientProtocolException e1) {
			throw new RuntimeException(e1);
		} catch (final IOException e1) {
			throw new RuntimeException(e1);
		}
	}

	/**
	 * Setup request.
	 *
	 * @param path the path
	 * @param method the method
	 * @param params the vars
	 * @return the http uri request
	 */
	private HttpUriRequest setupRequest(String path, final String method, final List<NameValuePair> params) {

		String normalizedPath = path.toLowerCase();
		StringBuilder sb = new StringBuilder();

		// If we've given a fully qualified uri then skip building the endpoint
		if (normalizedPath.startsWith("http://") || normalizedPath.startsWith("https://")) {
			sb.append(path);
		} else {
			sb.append(getEndpoint());

			if (!normalizedPath.startsWith("/")) {
				sb.append("/");
			}
			sb.append(path);
		}

		path = sb.toString();

		HttpUriRequest request = buildMethod(method, path, params);

		request.addHeader(new BasicHeader("X-Twilio-Client", "java-" + VERSION));
		request.addHeader(new BasicHeader("User-Agent", "twilio-java/" + VERSION));
		request.addHeader(new BasicHeader("Accept", "application/json"));
		request.addHeader(new BasicHeader("Accept-Charset", "utf-8"));

		if (httpclient instanceof DefaultHttpClient) { // as DefaultHttpClient class has final method, I need httpClient to be a plain interface to be able to mock it
			((DefaultHttpClient) httpclient).getCredentialsProvider()
			                                .setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
			                                                new UsernamePasswordCredentials(accountSid, authToken));
		}
		return request;
	}

	/**
	 * Make a request, handles retries + back-off for server/network errors
	 *
	 * @param path the URL (absolute w.r.t. the endpoint URL - i.e. /2010-04-01/Accounts)
	 * @param method the HTTP method to use, defaults to GET
	 * @param vars for POST or PUT, a map of data to send, for GET will be appended to the URL as querystring params
	 * @return The response
	 * @throws TwilioRestException if there's an client exception returned by the TwilioApi
	 */
	public TwilioRestResponse safeRequest(final String path, final String method, final Map<String, String> vars) throws
	                                                                                                              TwilioRestException {

		List<NameValuePair> paramList = generateParameters(vars);
		return safeRequest(path, method, paramList);
	}

	/**
	 * Make a request, handles retries + back-off for server/network errors
	 *
	 * @param path the URL (absolute w.r.t. the endpoint URL - i.e. /2010-04-01/Accounts)
	 * @param method the HTTP method to use, defaults to GET
	 * @param paramList for POST or PUT, a list of data to send, for GET will be appended to the URL as querystring
	 * params
	 * @return The response
	 * @throws TwilioRestException if there's an client exception returned by the TwilioApi
	 */
	public TwilioRestResponse safeRequest(final String path, final String method,
	                                      final List<NameValuePair> paramList) throws TwilioRestException {

		TwilioRestResponse response = null;
		for (int retry = 0; retry < numRetries; retry++) {
			response = request(path, method, paramList);
			if (response.isClientError()) {
				throw TwilioRestException.parseResponse(response);
			} else if (response.isServerError()) {
				try {
					Thread.sleep(100 * retry); // Backoff on our sleep
				} catch (final InterruptedException e) {
				}
				continue;
			}

			return response;
		}
		int errorCode = response == null ? -1 : response.getHttpStatus();
		throw new TwilioRestException("Cannot fetch: " + method + " " + path, errorCode);
	}

	/**
	 * Perform a GET request against the given fully qualified uri. This is a shortcut to {@link #request(String,
	 * String, Map)} with method "GET" and no parameters
	 *
	 * @param fullUri The full uri, including protocol://hostname/path
	 * @return {@link TwilioRestResponse} the response from the query
	 * @throws TwilioRestException the twilio rest exception
	 */
	public TwilioRestResponse get(final String fullUri) throws TwilioRestException {
		TwilioRestResponse response = null;

		for (int retry = 0; retry < numRetries; retry++) {
			response = request(fullUri, "GET", (Map) null);
			if (response.isClientError()) {
				throw TwilioRestException.parseResponse(response);
			} else if (response.isServerError()) {
				try {
					Thread.sleep(100 * retry); // Backoff on our sleep
				} catch (final InterruptedException e) {
				}
				continue;
			}

			return response;
		}
		int errorCode = response == null ? -1 : response.getHttpStatus();
		throw new TwilioRestException("Cannot fetch: " + fullUri + " ", errorCode);
	}

	/**
	 * Get the current endpoint this client is pointed at.
	 *
	 * @return String the api endpoint
	 */
	public String getEndpoint() {
		return endpoint;
	}

	public String getAccountSid() {
		return accountSid;
	}
}
