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
 
 This file is based on work originally published here, under a
 BSD license:
 
 https://github.com/OpenRefine/OpenRefine

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
public class AppEngineClientConnectionManager implements ClientConnectionManager {

    private SchemeRegistry schemes;

	class NoopSocketFactory implements SocketFactory {
        public Socket connectSocket(Socket sock, String host, int port, InetAddress addr, int lport, HttpParams params) {
            return null;
        }

        public Socket createSocket() {
            return null;
        }

        public boolean isSecure(Socket sock) {
            return false;
        }
    }

    public AppEngineClientConnectionManager() {
        SocketFactory noop_sf = new NoopSocketFactory();
        schemes = new SchemeRegistry();
        schemes.register(new Scheme("http",  noop_sf, 80));
        schemes.register(new Scheme("https", noop_sf, 443));
    }

    public void closeExpiredConnections() {
        return;
    }

    public void closeIdleConnections(long idletime, TimeUnit tunit) {
        return;
    }

    public ManagedClientConnection getConnection(HttpRoute route, Object state) {
        return new AppEngineClientConnection(this, route, state);
    }

    public SchemeRegistry getSchemeRegistry() {
        return schemes;
    }

    public void releaseConnection(ManagedClientConnection conn, long valid, TimeUnit tuint) {
        return;
    }

    public ClientConnectionRequest requestConnection(final HttpRoute route, final Object state) {
        return new ClientConnectionRequest() {
            public void abortRequest() {
                return;
            }

            public ManagedClientConnection getConnection(long idletime, TimeUnit tunit) {
                return AppEngineClientConnectionManager.this.getConnection(route, state);
            }
        };
    }

    public void shutdown() {
        return;
    }
}