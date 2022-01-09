package com.coinbase.models;

import java.util.List;

/**
 * This class represent a basic coinbase message
 * e.i: {type: "type": product_ids: [""]}
 **/
public class Message {

    private String type;
    private List<String> productIds;

    public Message() {}

    public Message(String type, List<String> productIds) {
        this.type = type;
        this.productIds = productIds;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }

    public List<String> getProductIds() {
        return this.productIds;
    }
}
