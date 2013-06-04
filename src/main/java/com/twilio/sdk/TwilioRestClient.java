package com.twilio.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.twilio.sdk.resource.factory.AccountFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.list.AccountList;

// TODO: Auto-generated Javadoc
/**
 * The Class TwilioRestClient.
 */
public class TwilioRestClient {

	/** The Constant VERSION. */
	private static final String VERSION = "3.3.15";

	/** The endpoint. */
	private String endpoint = "https://api.twilio.com";

	/** The Constant DEFAULT_VERSION. */
	public static final String DEFAULT_VERSION = "2010-04-01";

	/** The account sid. */
	private String accountSid;

	/** The auth token. */
	private String authToken;

	/** The num retries. */
	private int numRetries = 3;

	/**
	 * The default HTTP Connection timeout, also used for socket timeout.
	 */
	private static final int CONNECTION_TIMEOUT = 10000;

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

	/** The auth account. */
	private Account authAccount;

	/** The httpclient. */
	private HttpClient httpclient;

	public void setHttpclient(HttpClient httpclient) {
		this.httpclient = httpclient;
	}
	
	public HttpClient getHttpClient() {
		return httpclient;
	}

	/**
	 * Explcitly construct a TwilioRestClient with the given API credentials.
	 *
	 * @param accountSid
	 *            the 34 character Account identifier (starting with 'AC'). This
	 *            can be found on your Twilio dashboard page.
	 * @param authToken
	 *            the 32 character AuthToken. This can be found on your Twilio
	 *            dashboard page.
	 *
	 */
	public TwilioRestClient(String accountSid, String authToken) {
		this(accountSid, authToken, null);
	}

	/**
	 * Explcitly construct a TwilioRestClient with the given API credentials and
	 * endpoint.
	 *
	 * @param accountSid
	 *            the 34 character Account identifier (starting with 'AC'). This
	 *            can be found on your Twilio dashboard page.
	 * @param authToken
	 *            the 32 character AuthToken. This can be found on your Twilio
	 *            dashboard page.
	 * @param endpoint
	 *            the url of API endpoint you wish to use. (e.g. -
	 *            'https://api.twilio.com')
	 */
	public TwilioRestClient(String accountSid, String authToken, String endpoint) {

		validateAccountSid(accountSid);
		validateAuthToken(authToken);

		this.accountSid = accountSid;
		this.authToken = authToken;

		if ((endpoint != null) && (!endpoint.equals(""))) {
			this.endpoint = endpoint;
		}

		//Grab the proper connection manager, based on runtime environment
		ClientConnectionManager mgr = null;
		try {
			Class.forName("com.google.appengine.api.urlfetch.HTTPRequest");
			mgr = new AppEngineClientConnectionManager();
		} catch (ClassNotFoundException e) {
			//Not GAE
			mgr = new ThreadSafeClientConnManager();
			((ThreadSafeClientConnManager) mgr).setDefaultMaxPerRoute(10);
		}

		setHttpclient(new DefaultHttpClient(mgr));
		httpclient.getParams().setParameter("http.protocol.version",
				HttpVersion.HTTP_1_1);
		httpclient.getParams().setParameter("http.socket.timeout",
				new Integer(CONNECTION_TIMEOUT));
		httpclient.getParams().setParameter("http.connection.timeout",
				new Integer(CONNECTION_TIMEOUT));
		httpclient.getParams().setParameter("http.protocol.content-charset",
				"UTF-8");

		this.authAccount = new Account(this);
		this.authAccount.setSid(this.accountSid);
		this.authAccount.setAuthToken(this.authToken);

	}

	// Check for a valid 32 character auth token
	/**
	 * Validate auth token.
	 *
	 * @param authToken
	 *            the auth token
	 */
	private void validateAuthToken(String authToken) {
		if (authToken == null || authToken.length() != 32) {
			throw new IllegalArgumentException("AuthToken '" + authToken
					+ "' is not valid.");
		}
	}

	// Check for a valid 34 character account sid starting with 'AC'
	/**
	 * Validate account sid.
	 *
	 * @param accountSid
	 *            the account sid
	 */
	private void validateAccountSid(String accountSid) {
		if (accountSid == null || !accountSid.startsWith("AC")
				|| accountSid.length() != 34) {
			throw new IllegalArgumentException(
					"AccountSid '"
							+ accountSid
							+ "' is not valid.  It should be the 34 character unique identifier starting with 'AC'");
		}
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
	 * Generate post request.
	 *
	 * @param path
	 *            the path
	 * @param params
	 *            the params
	 * @return the http post
	 */
	private HttpPost generatePostRequest(String path, List<NameValuePair> params) {
		URI uri = buildUri(path);

		UrlEncodedFormEntity entity = buildEntityBody(params);

		HttpPost post = new HttpPost(uri);
		post.setEntity(entity);

		return post;
	}

	/**
	 * Generate put request.
	 *
	 * @param path
	 *            the path
	 * @param params
	 *            the params
	 * @return the http put
	 */
	private HttpPut generatePutRequest(String path, List<NameValuePair> params) {
		URI uri = buildUri(path);

		UrlEncodedFormEntity entity = buildEntityBody(params);

		HttpPut put = new HttpPut(uri);
		put.setEntity(entity);

		return put;
	}

	/**
	 * Generate delete request.
	 *
	 * @param path
	 *            the path
	 * @param params
	 *            the params
	 * @return the http delete
	 */
	private HttpDelete generateDeleteRequest(String path,
			List<NameValuePair> params) {
		URI uri = buildUri(path);

		HttpDelete delete = new HttpDelete(uri);
		return delete;
	}

	/*
	 *
	 * Helper functions for building methods
	 */
	/**
	 * Builds the entity body.
	 *
	 * @param params
	 *            the params
	 * @return the url encoded form entity
	 */
	private UrlEncodedFormEntity buildEntityBody(List<NameValuePair> params) {
		UrlEncodedFormEntity entity = null;
		try {
			entity = new UrlEncodedFormEntity(params, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		return entity;
	}

	/**
	 * Builds the uri.
	 *
	 * @param path
	 *            the path
	 * @return the uRI
	 */
	private URI buildUri(String path) {
		return buildUri(path, null);
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

		request.addHeader(new BasicHeader("X-Twilio-Client", "java-" + VERSION));
		request.addHeader(new BasicHeader("User-Agent", "twilio-java/"
				+ VERSION));
		request.addHeader(new BasicHeader("Accept", "application/json"));

		if (httpclient instanceof DefaultHttpClient) { // as DefaultHttpClient class has final method, I need httpClient to be a plain interface to be able to mock it
            ((DefaultHttpClient) httpclient).getCredentialsProvider()
				.setCredentials(
						new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
						new UsernamePasswordCredentials(this.accountSid,
        						this.authToken));
        }
		return request;
	}

	/**
	 * Make a request, handles retries + back-off for server/network errors
	 *
	 * @param path
	 *            the URL (absolute w.r.t. the endpoint URL - i.e.
	 *            /2010-04-01/Accounts)
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
	 * shortcut to {@link #request(String, String, Map)}
	 * with method "GET" and no parameters
	 *
	 * @param fullUri
	 *            The full uri, including protocol://hostname/path
	 * @return {@link TwilioRestResponse} the
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
	 *            The location of the endpoint (e.g. https://api.twilio.com)
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	/*
	 * Resource related methods
	 */
	/**
	 * Get a list of Account objects. For more info: {@link <a
	 * href="http://www.twilio.com/docs/api/rest/account"
	 * >http://www.twilio.com/docs/api/rest/account</a>}
	 *
	 * @param params
	 *            Filter the list with the given params. See the Twilio docs for
	 *            available filters.
	 * @return the list of accounts.
	 */
	public AccountList getAccounts(Map<String, String> params) {
		AccountList list = new AccountList(this, params);
		list.setRequestAccountSid(this.accountSid);
		return list;
	}

	/**
	 * Get all accounts. For more info: {@link <a
	 * href="http://www.twilio.com/docs/api/rest/account"
	 * >http://www.twilio.com/docs/api/rest/account</a>}
	 *
	 * @return the list of accounts.
	 */
	public AccountList getAccounts() {
		return this.getAccounts(new HashMap<String, String>());
	}

	/**
	 * Return an account factory to create new subaccounts
	 *
	 * @return the list of accounts
	 */
	public AccountFactory getAccountFactory() {
		return this.getAccounts();
	}

	/**
	 * A shortcut for the most common case, returning the Account object for
	 * this authenticated client.
	 *
	 * @return Account that maps to the authenticated account.
	 */
	public Account getAccount() {
		return this.authAccount;
	}

	/**
	 * Get an account by account sid.
	 *
	 * @param sid
	 *            The sid of the account you want to fetch.
	 * @return the account
	 */
	public Account getAccount(String sid) {
		Account account = new Account(this);
		account.setSid(sid);
		account.setRequestAccountSid(sid);

		return account;
	}

	public String getAccountSid() {
		return accountSid;
	}
}
