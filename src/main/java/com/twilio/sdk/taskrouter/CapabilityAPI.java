package com.twilio.sdk.taskrouter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.twilio.sdk.CapabilityToken;

public class CapabilityAPI extends CapabilityToken {
    protected String accountSid;
    protected String authToken;
    protected List<Policy> policies;
    protected String version;
    protected String friendlyName;

    public CapabilityAPI(final String accountSid, final String authToken, final String version, final String friendlyName) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.version = version;
        this.friendlyName = friendlyName;
        this.policies = new ArrayList<Policy>();
    }

    public void addPolicy(final String url, final String method, final Map<String, FilterRequirement> queryFilter, final Map<String, FilterRequirement> postFilter, final boolean allow) {
        final Policy policy = new Policy(url, method, queryFilter, postFilter, allow);
        this.policies.add(policy);

    }

    public void allow(final String url, final String method, final Map<String, FilterRequirement> queryFilter, final Map<String, FilterRequirement> postFilter) {
        final Policy policy = new Policy(url, method, queryFilter, postFilter, true);
        this.policies.add(policy);
    }

    public void disAllow(final String url, final String method, final Map<String, FilterRequirement> queryFilter, final Map<String, FilterRequirement> postFilter) {
        final Policy policy = new Policy(url, method, queryFilter, postFilter, false);
        this.policies.add(policy);
    }

}
