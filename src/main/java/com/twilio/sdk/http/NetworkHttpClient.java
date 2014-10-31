package com.twilio.sdk.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkHttpClient extends HttpClient {

    public Response makeRequest(Request request) {
        try {
            URL url = request.constructURL();
            HttpMethod method = request.getMethod();
            // TODO If we support proxying, plumb it through here.
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setAllowUserInteraction(false);
            connection.addRequestProperty("Accept", "application/json");
            connection.addRequestProperty("Accept-Encoding", "utf-8");
            connection.setInstanceFollowRedirects(true);

            connection.setRequestMethod(method.toString());

            if (request.requiresAuthentication()) {
                addAuth(request, connection);
            }

            if (method == HttpMethod.POST) {
                connection.setDoOutput(true);
                connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            }

            // TODO set up timeouts, caching, etc
            connection.connect();

            if (method == HttpMethod.POST) {
                sendPostBody(request, connection);
            }

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

    private void sendPostBody(Request request, HttpURLConnection conn) {
        String postBody = request.encodeFormBody();
        try {
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(postBody);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("oops"); // XXX needs improvement
        }
    }

}
