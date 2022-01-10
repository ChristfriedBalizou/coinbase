package com.coinbase.app;

import java.net.URI;
import java.util.Properties;
import java.util.List;
import java.util.ArrayList;
import java.lang.Thread;

import com.coinbase.connectors.CoinbaseWebSocket;
import com.coinbase.models.Message;
import com.coinbase.models.Security;
import com.coinbase.models.CoinbaseWebSocketRequest;


public class App {
    public static void main( String[] args ) throws Exception {
        Properties prop = new Properties();

        // This is really ugly but I want a native way to that.
        // issue with non-static in static
        prop.load(
            Thread.currentThread()
                  .getContextClassLoader()
                  .getResourceAsStream("config/settings.properties")
        );

        String productID = prop.getProperty("DEFAULT_PRODUCT_ID", null);
        String uri = prop.getProperty("COINBASE_WEBSOCKET_ENDPOINT", null);

        // YOLO: we are not pulling another lib for cli to get
        //       a single argument(product ids). We are going to loop
        //       on args to get the first value.
        if(args.length == 0 && productID == null) {
            System.out.println("Please specify a product ID e.i: ETC/USD");
            System.exit(-1);
        }

        if(args.length > 0) {
            // at this point if there is a value we want it
            productID = args[0];
        }

        if (uri == null) {
            System.out.println("Coinbase websocket uri is missing.");
            System.exit(-1);
        }

        // I am quite sure the String in ArrayList<String> is useless
        // in the doubt I will leave it as is.
        List<String> products = new ArrayList<String>();
        products.add(productID);

        List<Object> channels = new ArrayList<Object>();
        channels.add("level2");
        channels.add(new Security("ticker", products));

        CoinbaseWebSocketRequest request = new CoinbaseWebSocketRequest(
            "subscribe", products, channels
        );

        CoinbaseWebSocket client = new CoinbaseWebSocket(
            new URI(uri),
            request,
            Integer.parseInt(prop.getProperty("TICK_LIMIT", "10"))
        );

        client.connect();
    }
}
