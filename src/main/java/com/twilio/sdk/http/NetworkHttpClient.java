package com.twilio.sdk.http;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetworkHttpClient extends HttpClient {

    public Response makeRequest(Request request) {
        try {
            URL url = request.constructURL();
            // TODO If we support proxying, plumb it through here.
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setAllowUserInteraction(false);
            connection.addRequestProperty("Accept", "application/json");
            connection.addRequestProperty("Accept-Encoding", "utf-8");
            connection.setInstanceFollowRedirects(true);

            connection.setRequestMethod(request.getMethod());

            if (request.requiresAuthentication()) {
                addAuth(request, connection);
            }

            if (request.getMethod().equals("POST")) {
                addPostBody(request, connection);
            }

            // TODO set up timeouts, caching, etc
            // TODO add querystring parameters
            // TODO add post body
            connection.connect();

            int responseCode = connection.getResponseCode();
            InputStream errorStream = connection.getErrorStream();

            if (errorStream != null) {
                return new Response(errorStream, responseCode);
            }
            InputStream stream = connection.getInputStream();

            return new Response(stream, responseCode);

        } catch(MalformedURLException e) {
            // XXX womp womp
            return null;
        } catch(IOException e) {
            return null;
        }
    }

    private void addAuth(Request request, HttpURLConnection conn) {
        String auth = this.authentication(request.getUsername(), request.getPassword());
        conn.setRequestProperty("Authorization", auth);
    }

    private void addPostBody(Request request, HttpURLConnection conn) {
        // XXX add post parameters

    }

}
