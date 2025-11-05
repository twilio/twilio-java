package com.twilio.type;

import java.util.Objects;

public class App implements Endpoint {

    public static final String PREFIX = "app:";

    private final String app;

    public App(String app) {
        if (!app.toLowerCase().startsWith(PREFIX)) {
            app = PREFIX + app;
        }

        this.app = app;
    }

    @Override
    public String getEndpoint() {
        return this.app;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        App other = (App) o;
        return Objects.equals(this.app, other.app);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.app);
    }

    @Override
    public String toString() {
        return this.app;
    }
}