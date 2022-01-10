package com.coinbase.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.coinbase.serializers.Serializer;
import com.coinbase.models.Change;


/**
 * This class will have to serialize received message
 * from coinbase websocket in a human readable way.
 *
 * {
 *   "type":"l2update",
 *   "product_id":"ETH-USD",
 *   "changes":[["buy","3148.46","0.04000000"]],
 *   "time":"2022-01-10T00:08:49.116710Z"
 * }
 **/
public class UpdateResponse extends Serializer {
    private String type;
    private String productId;
    private Change changes;
    private LocalDateTime time;

    public UpdateResponse() {}

    public UpdateResponse(
        String type,
        String productId,
        Change changes,
        LocalDateTime time
    ) {
        this.type = type;
        this.productId = productId;
        this.changes = changes;
        this.time = time;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Change getChanges() {
        return this.changes;
    }

    public void setChanges(Change changes) {
        this.changes = changes;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @JsonProperty("changes")
    private void changesUnpack(List<List<String>> changes) {
        // Unpack for better human redability
        this.changes = new Change(
            changes.get(0).get(0),
            changes.get(0).get(1),
            changes.get(0).get(2)
        );
    }

    @Override
    public boolean equals(Object obj) {
        final UpdateResponse other = (UpdateResponse) obj;
        
        return (
            this.type.equals(other.type)
            && this.productId.equals(other.productId)
            && this.changes.equals(other.changes)
        );
    }

    @Override
    public int hashCode() {
        return (
            this.type.hashCode() 
            + this.productId.hashCode() 
            + this.changes.hashCode()
        );
    }
}

