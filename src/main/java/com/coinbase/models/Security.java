package com.coinbase.models;

import java.util.List;

import com.coinbase.serializers.Serializer;


/**
 * This class represent a basic coinbase Security
 * e.i: {name: "type": product_ids: [""]}
 **/
public class Security extends Serializer {

    private String name; // YOLO: this could be an Enum
    private List<String> productIds;

    public Security() {}

    public Security(String name, List<String> productIds) {
        this.name = name;
        this.productIds = productIds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }

    public List<String> getProductIds() {
        return this.productIds;
    }
}
