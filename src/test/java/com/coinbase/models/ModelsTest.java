package com.coinbase.models;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.net.URL;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import com.coinbase.models.Message;
import com.coinbase.models.Security;
import com.coinbase.models.CoinbaseWebSocketRequest;
import com.coinbase.models.UpdateResponse;


/**
 * Unit test for Models package.
 */
public class ModelsTest {
    @Test
    public void jsonMessageshouldBeTrue() {
        
        List<String> productIds = new ArrayList<String>();
        productIds.add("P1");
        productIds.add("P2");

        Message message = new Message("test", productIds);

        String expected = readFile("fixtures/message.json");
        String actual = message.toJson();

        assertTrue( expected.equals(actual) );
    }

    @Test
    public void jsonCoinbaseWebSocketRequestshouldBeTrue() {
        
        List<String> channelDescriptionProductIds = new ArrayList<String>();
        channelDescriptionProductIds.add("P3");
        channelDescriptionProductIds.add("P4");

        Message channelDescriptions = new Message(
            "something", channelDescriptionProductIds
        );
        
        List<Object> channels = new ArrayList<Object>();
        channels.add("l1");
        channels.add("l2");
        channels.add(channelDescriptions);

        List<String> productIds = new ArrayList<String>();
        productIds.add("P1");
        productIds.add("P2");

        CoinbaseWebSocketRequest request = new CoinbaseWebSocketRequest(
            "test", productIds, channels
        );

        String expected = readFile("fixtures/coinbaseWebSocketRequest.json");
        String actual = request.toJson();
        
        assertTrue( expected.equals(actual) );
    }

    @Test
    public void jsonSecurityhouldBeTrue() {
        
        List<String> productIds = new ArrayList<String>();
        productIds.add("P1");
        productIds.add("P2");

        Security sec = new Security("foobar", productIds);

        String expected = readFile("fixtures/security.json");
        String actual = sec.toJson();

        assertTrue( expected.equals(actual) );
    }

    @Test
    public void jsonUpdateResponseShouldBeTrue() {
        
        UpdateResponse expected = (UpdateResponse) UpdateResponse.fromJson(
            readFile("fixtures/updateResponse.json"), UpdateResponse.class
        );

        UpdateResponse actual = new UpdateResponse(
            "l2update",
            "ETH-USD",
            new Change("buy", "3148.48", "0.00000000"),
            LocalDateTime.now()
        );

        assertTrue( expected.equals(actual) );
    }

    private String readFile(String filename) {
        // YOLO: there is no elegant or simple way in my knowledge
        // to read a file without pulling the whole java libs
        // bare with me on this one "hold my beer".
        URL resource = getClass().getClassLoader().getResource(filename);
        try {
            List<String> reader = Files.readAllLines(Paths.get(resource.toURI()), StandardCharsets.UTF_8);
            return String.join("", reader).trim().replaceAll("[\n\r ]", "");
        } catch ( Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
