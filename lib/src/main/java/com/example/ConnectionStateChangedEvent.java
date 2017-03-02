package com.example;

/**
 * Created by fan-gk on 2017/3/2.
 */


public class ConnectionStateChangedEvent {
    private int type;
    private boolean isConnected;

    public ConnectionStateChangedEvent(int type, boolean isConnected) {
        this.type = type;
        this.isConnected = isConnected;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public int getType() {
        return type;
    }
}
