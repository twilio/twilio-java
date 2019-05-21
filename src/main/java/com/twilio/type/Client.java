package com.twilio.type;

import java.util.Objects;

public class Client implements Endpoint {

    public static final String PREFIX = "client:";

    private final String client;

    public Client(String client) {
        if (!client.toLowerCase().startsWith(PREFIX)) {
            client = PREFIX + client;
        }

        this.client = client;
    }

    @Override
    public String getEndpoint() {
        return this.client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Client other = (Client) o;
        return Objects.equals(this.client, other.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.client);
    }

    @Override
    public String toString() {
        return this.client;
    }
}
