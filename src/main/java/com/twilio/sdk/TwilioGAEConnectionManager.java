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

import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.params.HttpParams;

@SuppressWarnings("deprecation")
public class TwilioGAEConnectionManager implements ClientConnectionManager {

	public TwilioGAEConnectionManager() {
		SocketFactory no_socket_factory = new SocketFactory() {
			public Socket connectSocket(Socket sock, String host, int port,
					InetAddress localAddress, int localPort, HttpParams params) {
				return null;
			}

			public Socket createSocket() {
				return null;
			}

			public boolean isSecure(Socket s) {
				return false;
			}
		};

		schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", no_socket_factory, 80));
		schemeRegistry.register(new Scheme("https", no_socket_factory, 443));
	}

	@Override
	public SchemeRegistry getSchemeRegistry() {
		return schemeRegistry;
	}

	@Override
	public ClientConnectionRequest requestConnection(final HttpRoute route,
			final Object state) {
		return new ClientConnectionRequest() {
			public void abortRequest() {
				// Nothing to do
			}

			public ManagedClientConnection getConnection(long timeout,
					TimeUnit tunit) {
				return TwilioGAEConnectionManager.this.getConnection(route,
						state);
			}
		};
	}

	@Override
	public void releaseConnection(ManagedClientConnection conn,
			long validDuration, TimeUnit timeUnit) {
	}

	@Override
	public void closeIdleConnections(long idletime, TimeUnit tunit) {
	}

	@Override
	public void closeExpiredConnections() {
	}

	@Override
	public void shutdown() {
	}

	private ManagedClientConnection getConnection(HttpRoute route, Object state) {
		return new TwilioGAEClientConnection(this, route, state);
	}

	private SchemeRegistry schemeRegistry;
}
