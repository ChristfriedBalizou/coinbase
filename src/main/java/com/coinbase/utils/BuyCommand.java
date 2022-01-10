package com.coinbase.utils;

import com.coinbase.utils.ICommand;
import com.coinbase.utils.CircularQueue;
import com.coinbase.models.UpdateResponse;

public class BuyCommand implements ICommand {

    private CircularQueue<UpdateResponse> store;

    public BuyCommand(int tickLimit) {
        this.store = new CircularQueue<UpdateResponse>(tickLimit);
    }

    public void process(UpdateResponse response) {
        if(store.add(response)) {
            // We only print out values that are inserted/updated 
            // to the store.
            System.out.println(response);
        }
    }
}
