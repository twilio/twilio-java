package com.twilio.sdk.updaters;

import com.twilio.sdk.resources.Call;

public class CallUpdater extends Updater<Call> {
    private String friendlyName;

    public CallUpdater setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    @Override
    public Call build(Call target) {
        return null;
    }
}