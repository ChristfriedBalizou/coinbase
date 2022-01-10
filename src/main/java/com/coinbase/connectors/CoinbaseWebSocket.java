package com.coinbase.connectors;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.coinbase.models.CoinbaseWebSocketRequest;
import com.coinbase.models.UpdateResponse;
import com.coinbase.utils.ICommand;


/**
 * This class aim to connect to coinbase API Pro websocket
 * endpoint.
 **/
public class CoinbaseWebSocket extends WebSocketClient {

    private CoinbaseWebSocketRequest request;
    private Map<String, ICommand> commands;
    private int tickLimit;

    public CoinbaseWebSocket(URI serverUri) {
        super(serverUri);
    }
    
    public CoinbaseWebSocket(
        URI serverUri,
        CoinbaseWebSocketRequest request,
        Map<String, ICommand> commands
    ) {
        super(serverUri);
        this.request = request;
        this.commands = commands;
    }

    public CoinbaseWebSocket(
        URI serverUri,
        CoinbaseWebSocketRequest request,
        int tickLimit,
        Map<String, ICommand> commands
    ) {
        super(serverUri);
        this.request = request;
        this.tickLimit = tickLimit;
        this.commands = commands;
    }

    public void subscribe() {
        // THis function will be use to push message to the 
        // websocket server.
        if(this.request != null) {
            send(request.toJson());
        }
    }

    public int getTickLimit() {
        return this.tickLimit;
    }

    public void setTickLimit(int tickLimit) {
        this.tickLimit = tickLimit;
    }

    public CoinbaseWebSocketRequest getRequest() {
        // Usefull to debug in case we want to check why we are pull 
        // what we are seeing.
        return this.request;
    }

    public void setRequest(CoinbaseWebSocketRequest request) {
        this.request = request;
        subscribe();
    }

    public Map<String, ICommand> getCommands() {
        return this.commands;
    }

    public void setCommand(Map<String, ICommand> commands) {
        this.commands = commands;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        // Trigger when connection is open
        // we don't really need that function but due to
        // interface contract we have to override this function
        subscribe();
    }

    @Override
    public void onMessage(String message) {
        // Trigger when e receive a message from the websocket server

        // There should be a way to avoid casting when already given
        // the class as argument. Probably a Java limitation
        UpdateResponse response = (UpdateResponse) UpdateResponse.fromJson(
            message, UpdateResponse.class
        );

        if (!response.getType().equals("l2update")) {
            // we only process l2update type message
            // YOLO: we can also use an enum here
            return;
        }
    
        if(this.commands.containsKey(response.getChanges().getWay())) {
            // each stack should handle his own process
            this.commands.get(response.getChanges().getWay()).process(response);
        }
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
