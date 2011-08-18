/**
 * 
 */
package com.twilio.stashboard.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.stashboard.sdk.resource.instance.Services;

/**
 * Client to talk to Twilio Stash board (http://status.twilio.com/api/v1/)
 * This Client is built completely based on {@link TwilioRestClient}
 */
public class TwilioServiceStatusReadRestClient {

	/** The endpoint. */
	private String endpoint = "http://status.twilio.com/api";

	/** The Constant DEFAULT_VERSION. */
	public static final String DEFAULT_VERSION = "v1";

	public static final String VERSION = "3.0";

	/** The Services */
	private Services services;

	/** The num retries. */
	private int numRetries = 3;

	/** The httpclient. */
	private DefaultHttpClient httpclient;

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
	 * @param numRetries
	 *            the new num retries
	 */
	public void setNumRetries(int numRetries) {
		this.numRetries = numRetries;
	}

	public void setHttpclient(DefaultHttpClient httpclient) {
		this.httpclient = httpclient;
	}

	/**
	 * Default Constructor
	 */
	public TwilioServiceStatusReadRestClient() {
		this(null);
	}
	
	/**
	 * Explcitly construct a Client with End Point
	 * @param endpoint
	 *            the url of API endpoint you wish to use. (e.g. -
	 *            'https://api.twilio.com')
	 */
	public TwilioServiceStatusReadRestClient(String endpoint) {

		if ((endpoint != null) && (!endpoint.equals(""))) {
			this.endpoint = endpoint;
		}

		ThreadSafeClientConnManager connMgr = new ThreadSafeClientConnManager();
		connMgr.setDefaultMaxPerRoute(10);
		this.httpclient = new DefaultHttpClient(connMgr);
		httpclient.getParams().setParameter("http.protocol.version",
				HttpVersion.HTTP_1_1);
		httpclient.getParams().setParameter("http.socket.timeout",
				new Integer(5000));
		httpclient.getParams().setParameter("http.connection.timeout",
				new Integer(1000));
		httpclient.getParams().setParameter("http.protocol.content-charset",
				"UTF-8");

		this.services = new Services(this);
	}

	/**
	 * Generate parameters.
	 * 
	 * @param vars
	 *            the vars
	 * @return the list
	 */
	private static List<NameValuePair> generateParameters(
			Map<String, String> vars) {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();

		if (vars != null) {
			for (String var : vars.keySet()) {
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
	 * @param method
	 *            the method
	 * @param path
	 *            the path
	 * @param params
	 *            the params
	 * @return the http uri request
	 */
	private HttpUriRequest buildMethod(String method, String path,
			List<NameValuePair> params) {
		if (method.equalsIgnoreCase("GET")) {
			return generateGetRequest(path, params);
		} else {
			throw new IllegalArgumentException("Unknown Method: " + method);
		}
	}

	/**
	 * Generate get request.
	 * 
	 * @param path
	 *            the path
	 * @param params
	 *            the params
	 * @return the http get
	 */
	private HttpGet generateGetRequest(String path, List<NameValuePair> params) {

		URI uri = buildUri(path, params);
		return new HttpGet(uri);
	}

	/**
	 * Builds the uri.
	 * 
	 * @param path
	 *            the path
	 * @param queryStringParams
	 *            the query string params
	 * @return the uRI
	 */
	private URI buildUri(String path, List<NameValuePair> queryStringParams) {
		StringBuilder sb = new StringBuilder();
		sb.append(path);

		if (queryStringParams != null && queryStringParams.size() > 0) {
			sb.append("?");
			sb.append(URLEncodedUtils.format(queryStringParams, "UTF-8"));
		}

		URI uri;
		try {
			uri = new URI(sb.toString());
		} catch (URISyntaxException e) {
			throw new IllegalStateException("Invalid uri", e);
		}

		return uri;
	}

	/**
	 * sendRequst Sends a REST Request to the Twilio REST API.
	 * 
	 * @param path
	 *            the URL (absolute w.r.t. the endpoint URL - i.e.
	 *            /2010-04-01/Accounts)
	 * @param method
	 *            the HTTP method to use, defaults to GET
	 * @param vars
	 *            for POST or PUT, a map of data to send, for GET will be
	 *            appended to the URL as querystring params
	 * 
	 *            This method is public for backwards compatibility with the old
	 *            twilio helper library
	 * @return the twilio rest response
	 */
	public TwilioRestResponse request(String path, String method,
			Map<String, String> vars) throws TwilioRestException {

		HttpUriRequest request = setupRequest(path, method, vars);

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

			TwilioRestResponse restResponse = new TwilioRestResponse(request
					.getURI().toString(), responseBody, statusCode);

			// For now we only set the first content type seen
			for (Header h : contentTypeHeaders) {
				restResponse.setContentType(h.getValue());
				break;
			}

			return restResponse;

		} catch (ClientProtocolException e1) {
			throw new RuntimeException(e1);
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
	}

	/**
	 * Request stream.
	 * 
	 * @param path
	 *            the path
	 * @param method
	 *            the method
	 * @param vars
	 *            the vars
	 * @return the input stream
	 */
	public InputStream requestStream(String path, String method,
			Map<String, String> vars) {

		HttpUriRequest request = setupRequest(path, method, vars);

		HttpResponse response;
		try {
			response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			return entity.getContent();

		} catch (ClientProtocolException e1) {
			throw new RuntimeException(e1);
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
	}

	/**
	 * Setup request.
	 * 
	 * @param path
	 *            the path
	 * @param method
	 *            the method
	 * @param vars
	 *            the vars
	 * @return the http uri request
	 */
	private HttpUriRequest setupRequest(String path, String method,
			Map<String, String> vars) {
		List<NameValuePair> params = generateParameters(vars);

		String normalizedPath = path.toLowerCase();
		StringBuilder sb = new StringBuilder();

		// If we've given a fully qualified uri then skip building the endpoint
		if (normalizedPath.startsWith("http://")
				|| normalizedPath.startsWith("https://")) {
			sb.append(path);
		} else {
			sb.append(this.getEndpoint());

			if (!normalizedPath.startsWith("/")) {
				sb.append("/");
			}
			sb.append(path);
		}

		path = sb.toString();

		HttpUriRequest request = buildMethod(method, path, params);

		request
				.addHeader(new BasicHeader("X-Twilio-Client", "java-" + VERSION));
		request.addHeader(new BasicHeader("User-Agent", "twilio-java-"
				+ VERSION));
		request.addHeader(new BasicHeader("Accept", "application/json"));

		return request;
	}

	/**
	 * Make a request, handles retries + back-off for server/network errors
	 * 
	 * @param path
	 *            the URL (absolute w.r.t. the endpoint URL - i.e.
	 *            /v1/services)
	 * @param method
	 *            the HTTP method to use, defaults to GET
	 * @param vars
	 *            for POST or PUT, a map of data to send, for GET will be
	 *            appended to the URL as querystring params
	 * @return The response
	 * @throws TwilioRestException
	 *             if there's an client exception returned by the TwilioApi
	 */
	public TwilioRestResponse safeRequest(String path, String method,
			Map<String, String> vars) throws TwilioRestException {
		TwilioRestResponse response = null;
		for (int retry = 0; retry < this.numRetries; retry++) {
			response = request(path, method, vars);
			if (response.isClientError()) {
				throw TwilioRestException.parseResponse(response);
			} else if (response.isServerError()) {
				try {
					Thread.sleep(100 * retry); // Backoff on our sleep
				} catch (InterruptedException e) {
				}
				continue;
			}

			return response;
		}
		int errorCode = response == null ? -1 : response.getHttpStatus();
		throw new TwilioRestException("Cannot fetch: " + method + " " + path,
				errorCode);
	}

	/**
	 * Perform a GET request against the given fully qualified uri. This is a
	 * shortcut to {@see <a
	 * href="this#request(String, String, Map)">this#request(String, String,
	 * Map)} with method "GET" and no parameters
	 * 
	 * @param fullUri
	 *            The full uri, including protocol://hostname/path
	 * @return {@see <a href="TwilioRestResponse">TwilioRestResponse} the
	 *         response from the query
	 * @throws TwilioRestException
	 *             the twilio rest exception
	 */
	public TwilioRestResponse get(String fullUri) throws TwilioRestException {
		TwilioRestResponse response = null;

		for (int retry = 0; retry < this.numRetries; retry++) {
			response = request(fullUri, "GET", null);
			if (response.isClientError()) {
				throw TwilioRestException.parseResponse(response);
			} else if (response.isServerError()) {
				try {
					Thread.sleep(100 * retry); // Backoff on our sleep
				} catch (InterruptedException e) {
				}
				continue;
			}

			return response;
		}
		int errorCode = response == null ? -1 : response.getHttpStatus();
		throw new TwilioRestException("Cannot fetch: " + fullUri + " ",
				errorCode);
	}

	/**
	 * Get the current endpoint this client is pointed at.
	 * 
	 * @return String the api endpoint
	 */
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * Set the endpoint this rest client uses.
	 * 
	 * @param endpoint
	 *            The location of the endpoint (e.g. http://status.twilio.com/api)
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public Services getServices() {
		return services;
	}
}
