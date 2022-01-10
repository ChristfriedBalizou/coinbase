package com.coinbase.utils;

import java.util.LinkedList;


/**
 * This class will behave as a limit Queue.
 * What we want here is to push in the queue when full
 * remove the head to push the next value.
 *
 * With this datastruct we can keep track of the n values.
 **/
public class CircularQueue<E> extends LinkedList<E> {
    private int tickLimit;

    public CircularQueue(int tickLimit) {
        this.tickLimit = tickLimit;
    }

    @Override
    public boolean add(E element) {
        // Only is the queue still have place
        // otherwise we discard the value
        
        if(size() > this.tickLimit - 1) {
            // The -1 is because we don't want to exceed the tickLimit
            // we return a naive true we don't want the function to blow
            // this is a desired behavior
            return true;
        }

        int index = indexOf(element);

        if(index != -1) {
            // We just update the value
            set(index, element);
            return true;
        }

        return super.add(element);
    }

    public int getTickLimit() {
        return tickLimit;
    }

    public void setTickLimit(int tickLimit) {
        this.tickLimit = tickLimit;
    }
}
