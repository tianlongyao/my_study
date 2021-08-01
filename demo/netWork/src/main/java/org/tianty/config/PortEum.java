package org.tianty.config;

public enum PortEum {
    UDP_SERVER_PORT(6666);

    private int port;

    PortEum(int i) {
        this.port = i;
    }

    public int getPort() {
        return port;
    }
}
