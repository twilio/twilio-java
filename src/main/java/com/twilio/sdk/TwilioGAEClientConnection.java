package com.twilio.sdk;

/*
Copyright (c) 2013 Twilio, Inc.

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

*/

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

@SuppressWarnings({ "unchecked", "rawtypes" })
class TwilioGAEClientConnection implements ManagedClientConnection {
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
			System.out.println(">>>"+URLFetchServiceFactory);
			urlFS = URLFetchServiceFactory.getMethod("getURLFetchService", new Class[0]).invoke(null, new Object[0]);
		} catch (Exception e) {
			System.out.println("error initializing URLFetch service");
			e.printStackTrace();
		}
	}

	public TwilioGAEClientConnection(ClientConnectionManager cm, HttpRoute route,Object state) {
		this.connManager = cm;
		this.route = route;
		this.state = state;
		this.closed = true;
	}

	@Override
	public boolean isSecure() {
		return route.isSecure();
	}

	@Override
	public HttpRoute getRoute() {
		return route;
	}

	@Override
	public javax.net.ssl.SSLSession getSSLSession() {
		return null;
	}

	@Override
	public void open(HttpRoute route, HttpContext context, HttpParams params) throws IOException {
		close();
		this.route = route;
	}

	@Override
	public void tunnelTarget(boolean secure, HttpParams params) throws IOException {
		throw new IOException("tunnelTarget unusable");
	}

	@Override
	public void tunnelProxy(HttpHost next, boolean secure, HttpParams params) throws IOException {
		throw new IOException("tunnelProxy unusable");
	}

	@Override
	public void layerProtocol(HttpContext context, HttpParams params) throws IOException {
		throw new IOException("layerProtocol unusable");
	}

	@Override
	public void markReusable() {
		reusable = true;
	}

	@Override
	public void unmarkReusable() {
		reusable = false;
	}

	@Override
	public boolean isMarkedReusable() {
		return reusable;
	}

	@Override
	public void setState(Object state) {
		this.state = state;
	}

	@Override
	public Object getState() {
		return state;
	}

	@Override
	public void setIdleDuration(long duration, TimeUnit unit) {
	}

	@Override
	public boolean isResponseAvailable(int timeout) throws IOException {
		return response != null;
	}

	@Override
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
			
			/*
			Class[] valueOfTypes = new Class[1];
			valueOfTypes[0] = HTTPMethod;
			Method valueOf = HTTPMethod.getMethod("valueOf", valueOfTypes);
			*/
			
			Class[] disallowTruncateTypes = new Class[0];
			Object[] disallowTruncateArgs = new Object[0];
			Method disallowTruncate = FetchOptionsBuilder.getMethod("disallowTruncate", disallowTruncateTypes);
			
			this.request = requestConstructor.newInstance(
				uri.toURL(),
				Enum.valueOf(
					(Class<? extends Enum>)Class.forName("com.google.appengine.api.urlfetch.HTTPMethod"), 
					request.getRequestLine().getMethod()
				),
				disallowTruncate.invoke(FetchOptionsBuilder, disallowTruncateArgs)
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
			
		} catch (URISyntaxException e) {
			throw new IOException("Malformed request URI: " + e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			throw new IOException("Unsupported HTTP method: " + e.getMessage(), e);
		} catch (Exception e) {
			throw new IOException("Error occurred during method invocation: " + e.getMessage(), e);
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

	@Override
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

	@Override
	public void receiveResponseEntity(HttpResponse response) throws HttpException, IOException {
		if (this.response == null) {
			throw new IOException("receiveResponseEntity() called when closed");
		}

		ByteArrayEntity bae;
		
		try {
			Class[] parameterTypes = new Class[0];
			Object[] invocationArgs = new Object[0];
			
			Method getContentMethod = this.response.getClass().getMethod("getContent", parameterTypes);
			bae = new ByteArrayEntity((byte[]) getContentMethod.invoke(this.response,invocationArgs));
			bae.setContentType(response.getFirstHeader("Content-Type"));
			response.setEntity(bae);
			response = null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpException("Error occurred while using Google App Engine URL fetch");
		}
	}

	@Override
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

	@Override
	public void close() throws IOException {
		request = null;
		response = null;
		closed = true;
	}

	@Override
	public boolean isOpen() {
		return request != null || response != null;
	}

	@Override
	public boolean isStale() {
		return !isOpen() && !closed;
	}

	@Override
	public void setSocketTimeout(int timeout) {
	}

	@Override
	public int getSocketTimeout() {
		return -1;
	}

	@Override
	public void shutdown() throws IOException {
		close();
	}

	@Override
	public HttpConnectionMetrics getMetrics() {
		return null;
	}

	@Override
	public InetAddress getLocalAddress() {
		return null;
	}

	@Override
	public int getLocalPort() {
		return 0;
	}

	@Override
	public InetAddress getRemoteAddress() {
		return null;
	}

	@Override
	public int getRemotePort() {
		HttpHost host = route.getTargetHost();
		return connManager.getSchemeRegistry().getScheme(host).resolvePort(host.getPort());
	}

	@Override
	public void releaseConnection() throws IOException {
		connManager.releaseConnection(this, Long.MAX_VALUE, TimeUnit.MILLISECONDS);
	}

	@Override
	public void abortConnection() throws IOException {
		unmarkReusable();
		shutdown();
	}
	
}
