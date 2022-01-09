package com.coinbase.connectors;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

/**
 * This class aim to connect to coinbase API Pro websocket
 * endpoint.
 **/
public class CoinbaseWebSocket extends WebSocketClient {

    public CoinbaseWebSocket(URI serverUri) {
        super(serverUri);
    }

    public void sendMessage(String message) {
        // THis function will be use to push message to the 
        // websocket server.
        send(message);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        // Trigger when connection is open
        // we don't really need that function but due to
        // interface contract we have to override this function
        System.out.println("opened connection");
    }

    @Override
    public void onMessage(String message) {
        // Trigger when e receive a message from the websocket server
        System.out.println("received: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // Trigger when connection is close
        // we don't really need that function but due to
        // interface contract we have to override this function
        System.out.println("Connection closed: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }
}
