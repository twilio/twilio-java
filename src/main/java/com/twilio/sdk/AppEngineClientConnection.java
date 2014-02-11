package com.twilio.sdk;

/*
 This file is based on work originally published here, under a
 BSD 3-clause license. See the LICENSE file for more details.

 https://github.com/OpenRefine/OpenRefine
*/

import org.apache.http.*;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@SuppressWarnings({ "unchecked", "rawtypes" })
class AppEngineClientConnection implements ManagedClientConnection {
	// Managed is the composition of ConnectionReleaseTrigger,
    //     HttpClientConnection, HttpConnection, HttpInetConnection

	private ClientConnectionManager connManager;
	private HttpRoute route;
	private Object state;
	private boolean reusable;

	private Object request;
	private Object response;
	private boolean closed;

	private static Object urlFS;

	//GAE Classes defined via reflection
	static Class FetchOptions;
	static Class FetchOptionsBuilder;
	static Class HTTPHeader;
	static Class HTTPMethod;
	static Class HTTPRequest;
	static Class HTTPResponse;
	static Class URLFetchService;
	static Class URLFetchServiceFactory;

	static {
		//Initialize GAE classes
		try {
			FetchOptions = Class.forName("com.google.appengine.api.urlfetch.FetchOptions");
			HTTPHeader = Class.forName("com.google.appengine.api.urlfetch.HTTPHeader");
			HTTPMethod = Class.forName("com.google.appengine.api.urlfetch.HTTPMethod");
			HTTPRequest = Class.forName("com.google.appengine.api.urlfetch.HTTPRequest");
			HTTPResponse = Class.forName("com.google.appengine.api.urlfetch.HTTPResponse");
			URLFetchService = Class.forName("com.google.appengine.api.urlfetch.URLFetchService");
			URLFetchServiceFactory = Class.forName("com.google.appengine.api.urlfetch.URLFetchServiceFactory");
			FetchOptionsBuilder = FetchOptions.getClasses()[0];
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		//initialize the URL fetch service
		try {
			urlFS = URLFetchServiceFactory.getMethod("getURLFetchService", new Class[0]).invoke(null, new Object[0]);
		} catch (Exception e) {
			System.out.println("error initializing URLFetch service");
			e.printStackTrace();
		}
	}

	public AppEngineClientConnection(ClientConnectionManager cm, HttpRoute route,Object state) {
		this.connManager = cm;
		this.route = route;
		this.state = state;
		this.closed = true;
	}

	// ManagedClientConnection methods

	public boolean isSecure() {
		return route.isSecure();
	}

	public HttpRoute getRoute() {
		return route;
	}

	public javax.net.ssl.SSLSession getSSLSession() {
		return null;
	}

	public void open(HttpRoute route, HttpContext context, HttpParams params) throws IOException {
		close();
		this.route = route;
	}

	public void tunnelTarget(boolean secure, HttpParams params) throws IOException {
		throw new IOException("tunnelTarget unusable");
	}

	public void tunnelProxy(HttpHost next, boolean secure, HttpParams params) throws IOException {
		throw new IOException("tunnelProxy unusable");
	}

	public void layerProtocol(HttpContext context, HttpParams params) throws IOException {
		throw new IOException("layerProtocol unusable");
	}

	public void markReusable() {
		reusable = true;
	}

	public void unmarkReusable() {
		reusable = false;
	}

	public boolean isMarkedReusable() {
		return reusable;
	}

	public void setState(Object state) {
		this.state = state;
	}

	public Object getState() {
		return state;
	}

	public void setIdleDuration(long duration, TimeUnit unit) {}

	public boolean isOpen() {
		return request != null || response != null;
	}

	public boolean isStale() {
		return !isOpen() && !closed;
	}

	public void setSocketTimeout(int timeout) {
	}

	public int getSocketTimeout() {
		return -1;
	}

	public void shutdown() throws IOException {
		close();
	}

	public HttpConnectionMetrics getMetrics() {
		return null;
	}

	public InetAddress getLocalAddress() {
		return null;
	}

	public int getLocalPort() {
		return 0;
	}

	public InetAddress getRemoteAddress() {
		return null;
	}

	public int getRemotePort() {
		HttpHost host = route.getTargetHost();
		return connManager.getSchemeRegistry().getScheme(host).resolvePort(host.getPort());
	}

	public void releaseConnection() throws IOException {
		connManager.releaseConnection(this, Long.MAX_VALUE, TimeUnit.MILLISECONDS);
	}

	public void abortConnection() throws IOException {
		unmarkReusable();
		shutdown();
	}

	// HttpClientConnection methods

	public void flush() throws IOException {
		if (request != null) {
			try {
				Class[] parameterTypes = new Class[1];
				parameterTypes[0] = HTTPRequest;

				Method fetchMethod = URLFetchService.getMethod("fetch", parameterTypes);
				response = fetchMethod.invoke(urlFS, request);
				request = null;
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new IOException("Error flushing content in Google App Engine fetch");
			}
		} else {
			response = null;
		}
	}

	public boolean isResponseAvailable(int timeout) throws IOException {
		return response != null;
	}

	public void close() throws IOException {
		request = null;
		response = null;
		closed = true;
	}

	public void receiveResponseEntity(HttpResponse response) throws HttpException, IOException {
		if (this.response == null) {
			throw new IOException("receiveResponseEntity() called when closed");
		}

		ByteArrayEntity bae;

		try {
			Method getContentMethod = this.response.getClass().getMethod("getContent", new Class[0]);
			bae = new ByteArrayEntity((byte[]) getContentMethod.invoke(this.response, new Object[0]));
			bae.setContentType(response.getFirstHeader("Content-Type"));
			response.setEntity(bae);
			response = null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpException("Error occurred while using Google App Engine URL fetch");
		}
	}

	public HttpResponse receiveResponseHeader() throws HttpException, IOException {
		if (this.response == null) {
			flush();
		}

		HttpResponse response;

		try {
			Method getResponseCode = HTTPResponse.getMethod("getResponseCode", new Class[0]);
			response = new BasicHttpResponse(new ProtocolVersion("HTTP", 1, 1), (Integer) getResponseCode.invoke(this.response, new Object[0]), null);

			Method getHeaders = HTTPResponse.getMethod("getHeaders", new Class[0]);
			for (Object h : (Iterable) getHeaders.invoke(this.response, new Object[0])) {
				Method getName = HTTPHeader.getMethod("getName", new Class[0]);
				Method getValue = HTTPHeader.getMethod("getValue", new Class[0]);
				response.addHeader((String) getName.invoke(h, new Object[0]), (String) getValue.invoke(h, new Object[0]));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException("error processing response headers: "+e.getMessage(), e);
		}

		return response;
	}

	public void sendRequestHeader(HttpRequest request) throws HttpException, IOException {
		try {
			HttpHost host = route.getTargetHost();

			URI uri = new URI(host.getSchemeName()
					+ "://" + host.getHostName()
					+ ((host.getPort() == -1) ? "" : (":" + host.getPort()))
					+ request.getRequestLine().getUri());

			Class[] requestConstructorTypes = new Class[3];
			requestConstructorTypes[0] = URL.class;
			requestConstructorTypes[1] = HTTPMethod;
			requestConstructorTypes[2] = FetchOptions;

			Constructor requestConstructor = HTTPRequest.getConstructor(requestConstructorTypes);

			Class[] disallowTruncateTypes = new Class[0];
			Method disallowTruncate = FetchOptionsBuilder.getMethod("disallowTruncate", disallowTruncateTypes);

			this.request = requestConstructor.newInstance(
				uri.toURL(),
				Enum.valueOf(
					(Class<? extends Enum>)Class.forName("com.google.appengine.api.urlfetch.HTTPMethod"),
					request.getRequestLine().getMethod()
				),
				disallowTruncate.invoke(FetchOptionsBuilder, new Object[0])
			);

			Class[] addHeaderParameterTypes = new Class[1];
			addHeaderParameterTypes[0] = HTTPHeader;
			Method addHeader = HTTPRequest.getMethod("addHeader", addHeaderParameterTypes);

			Class[] httpHeaderConstructorTypes = new Class[2];
			httpHeaderConstructorTypes[0] = String.class;
			httpHeaderConstructorTypes[1] = String.class;

			Constructor httpHeaderConstructor = HTTPHeader.getConstructor(httpHeaderConstructorTypes);

			for (Header h : request.getAllHeaders()) {
				addHeader.invoke(this.request, httpHeaderConstructor.newInstance(h.getName(), h.getValue()));
			}

		} catch (Exception e) {
			throw new IOException("Error during invocation: " + e.getMessage(), e);
		}
	}

	@Override
	public void sendRequestEntity(HttpEntityEnclosingRequest request) throws HttpException, IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		if (request.getEntity() != null) {
			request.getEntity().writeTo(baos);
		}

		Class[] setPayloadParameterTypes = new Class[1];
		setPayloadParameterTypes[0] = byte[].class;

		try {
			Method setPayload = HTTPRequest.getMethod("setPayload", setPayloadParameterTypes);
			setPayload.invoke(this.request, baos.toByteArray());

		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException("error while sending GAE request", e);
		}
	}
}
