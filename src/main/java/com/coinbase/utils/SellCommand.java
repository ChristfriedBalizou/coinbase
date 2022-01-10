package com.coinbase.utils;

import com.coinbase.utils.ICommand;
import com.coinbase.utils.CircularQueue;
import com.coinbase.models.UpdateResponse;

public class SellCommand implements ICommand {

    private CircularQueue<UpdateResponse> store;

    public SellCommand(int tickLimit) {
        this.store = new CircularQueue<UpdateResponse>(tickLimit);
    }

    public void process(UpdateResponse response) {
        
        if (!(response.getChanges().getValue() > 0)) {
            // we only process non-zero values
            // we sometimes have trade_id that does not move a lot
            return;
        }

        if(store.add(response)) {
            // We only print out values that are inserted/updated 
            // to the store.
            System.out.println(response);
        }
    }
}
