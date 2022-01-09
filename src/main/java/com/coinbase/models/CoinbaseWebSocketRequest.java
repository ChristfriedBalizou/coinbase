package com.coinbase.models;

import java.util.List;
import java.util.ArrayList;

import com.coinbase.models.Message;


/**
 * Representation of a WebSocket subscribe request
 **/
public class CoinbaseWebSocketRequest extends Message {

    private List<Object> channels;
    // Note that channel here is a list of object because
    // it accept String and Message type

    public CoinbaseWebSocketRequest() {
        super();
    }

    public CoinbaseWebSocketRequest(
        String type,
        List<String> productIds,
        List<Object> channels
    ) {
        super(type, productIds);
        this.channels = channels;
    }

    public List<Object> getChannels() {
        return this.channels;
    }

    public void setChannels(List<Object> channels) {
        this.channels = channels;
    }
}
