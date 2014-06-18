package com.twilio.sdk;

/*
 This file is based on work originally published here, under a
 BSD 3-clause license. See the LICENSE file for more details.

 https://github.com/OpenRefine/OpenRefine
*/

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SchemeSocketFactory;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class AppEngineClientConnectionManager implements ClientConnectionManager {

	private final SchemeRegistry schemes;

	class NoOpSocketFactory implements SchemeSocketFactory {
		@Override
		public Socket connectSocket(final Socket sock, final InetSocketAddress remoteAddress,
		                            final InetSocketAddress localAddress, final HttpParams params) throws IOException,
		                                                                                                  UnknownHostException,
		                                                                                                  ConnectTimeoutException {
			return null;
		}

		@Override
		public Socket createSocket(final HttpParams params) throws IOException {
			return null;
		}

		@Override
		public boolean isSecure(final Socket sock) {
			return false;
		}
	}

	public AppEngineClientConnectionManager() {
		SchemeSocketFactory noOpSocketFactory = new NoOpSocketFactory();
		schemes = new SchemeRegistry();
		schemes.register(new Scheme("http", 80, noOpSocketFactory));
		schemes.register(new Scheme("https", 443, noOpSocketFactory));
	}

	public void closeExpiredConnections() {
	}

	public void closeIdleConnections(final long idleTime, final TimeUnit timeUnit) {
	}

	public ManagedClientConnection getConnection(final HttpRoute route, final Object state) {
		return new AppEngineClientConnection(this, route, state);
	}

	public SchemeRegistry getSchemeRegistry() {
		return schemes;
	}

	public void releaseConnection(final ManagedClientConnection conn, final long valid, final TimeUnit timeUnit) {
	}

	public ClientConnectionRequest requestConnection(final HttpRoute route, final Object state) {
		return new ClientConnectionRequest() {
			public void abortRequest() {
			}

			public ManagedClientConnection getConnection(final long idleTime, final TimeUnit timeUnit) {
				return AppEngineClientConnectionManager.this.getConnection(route, state);
			}
		};
	}

	public void shutdown() {
	}
}
