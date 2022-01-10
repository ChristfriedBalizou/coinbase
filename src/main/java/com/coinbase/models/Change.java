package com.coinbase.models;

import com.coinbase.serializers.Serializer;


public class Change extends Serializer {
    private String way;
    private String track;
    private String value;

    public Change() {}

    public Change(String way, String track, String value) {
        this.way = way;
        this.track = track;
        this.value = value;
    }

    public String getWay() {
        return this.way;
    } 

    public void getWay(String way) {
        this.way = way;
    }

    public String getTrack() {
        return this.track;
    } 

    public void getTrack(String track) {
        this.track = track;
    }

    public String getValue() {
        return this.value;
    } 

    public void getValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return (
            "Way: " + this.way + ", " + 
            "Trade ID: " + this.track + ", " +
            "Price: " + this.value
        );
    }

    @Override
    public boolean equals(Object obj) {
        final Change other = (Change) obj;
        return this.track.equals(other.track) && this.way.equals(other.way);
    }
    
    @Override
    public int hashCode() {
        return this.track.hashCode() + this.way.hashCode();
    }
}

