package com.coinbase.utils;

import com.coinbase.models.UpdateResponse;


public interface ICommand {
    public void process(UpdateResponse response);
}
