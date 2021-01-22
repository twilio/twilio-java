/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.sync.v1.service.syncmap;

import com.twilio.base.Updater;
import com.twilio.converter.Converter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.util.Map;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
public class SyncMapItemUpdater extends Updater<SyncMapItem> {
    private final String pathServiceSid;
    private final String pathMapSid;
    private final String pathKey;
    private Map<String, Object> data;
    private Integer ttl;
    private Integer itemTtl;
    private Integer collectionTtl;
    private String ifMatch;

    /**
     * Construct a new SyncMapItemUpdater.
     *
     * @param pathServiceSid The SID of the Sync Service with the Sync Map Item
     *                       resource to update
     * @param pathMapSid The SID of the Sync Map with the Sync Map Item resource to
     *                   update
     * @param pathKey The key value of the Sync Map Item resource to update
     */
    public SyncMapItemUpdater(final String pathServiceSid,
                              final String pathMapSid,
                              final String pathKey) {
        this.pathServiceSid = pathServiceSid;
        this.pathMapSid = pathMapSid;
        this.pathKey = pathKey;
    }

    /**
     * A JSON string that represents an arbitrary, schema-less object that the Map
     * Item stores. Can be up to 16 KiB in length..
     *
     * @param data A JSON string that represents an arbitrary, schema-less object
     *             that the Map Item stores
     * @return this
     */
    public SyncMapItemUpdater setData(final Map<String, Object> data) {
        this.data = data;
        return this;
    }

    /**
     * An alias for `item_ttl`. If both parameters are provided, this value is
     * ignored..
     *
     * @param ttl An alias for item_ttl
     * @return this
     */
    public SyncMapItemUpdater setTtl(final Integer ttl) {
        this.ttl = ttl;
        return this;
    }

    /**
     * How long, in seconds, before the Map Item expires (time-to-live) and is
     * deleted.  Can be an integer from 0 to 31,536,000 (1 year). The default value
     * is `0`, which means the Map Item does not expire. The Map Item will be
     * deleted automatically after it expires, but there can be a delay between the
     * expiration time and the resources's deletion..
     *
     * @param itemTtl How long, in seconds, before the Map Item expires
     * @return this
     */
    public SyncMapItemUpdater setItemTtl(final Integer itemTtl) {
        this.itemTtl = itemTtl;
        return this;
    }

    /**
     * How long, in seconds, before the Map Item's parent Sync Map expires
     * (time-to-live) and is deleted.  Can be an integer from 0 to 31,536,000 (1
     * year). The default value is `0`, which means the parent Sync Map does not
     * expire. This parameter can only be used when the Map Item's `data` or `ttl`
     * is updated in the same request. The Sync Map will be deleted automatically
     * after it expires, but there can be a delay between the expiration time and
     * the resources's deletion..
     *
     * @param collectionTtl How long, in seconds, before the Map Item's parent Sync
     *                      Map expires and is deleted
     * @return this
     */
    public SyncMapItemUpdater setCollectionTtl(final Integer collectionTtl) {
        this.collectionTtl = collectionTtl;
        return this;
    }

    /**
     * The If-Match HTTP request header.
     *
     * @param ifMatch The If-Match HTTP request header
     * @return this
     */
    public SyncMapItemUpdater setIfMatch(final String ifMatch) {
        this.ifMatch = ifMatch;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated SyncMapItem
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public SyncMapItem update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.SYNC.toString(),
            "/v1/Services/" + this.pathServiceSid + "/Maps/" + this.pathMapSid + "/Items/" + this.pathKey + ""
        );

        addPostParams(request);
        addHeaderParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("SyncMapItem update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.test(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return SyncMapItem.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested header parameters to the Request.
     *
     * @param request Request to add header params to
     */
    private void addHeaderParams(final Request request) {
        if (ifMatch != null) {
            request.addHeaderParam("If-Match", ifMatch);
        }
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (data != null) {
            request.addPostParam("Data", Converter.mapToJson(data));
        }

        if (ttl != null) {
            request.addPostParam("Ttl", ttl.toString());
        }

        if (itemTtl != null) {
            request.addPostParam("ItemTtl", itemTtl.toString());
        }

        if (collectionTtl != null) {
            request.addPostParam("CollectionTtl", collectionTtl.toString());
        }
    }
}