package com.game.client;

import java.io.IOException;
import java.net.InetAddress;

public class MultiClient {


    public static boolean isRunning = false;

    public static void main(String[] args) throws IOException, InterruptedException {
        InetAddress addr = InetAddress.getByName(null);
        MultiClient multiClient = new MultiClient();
        multiClient.start(addr);

    }

    public synchronized void start(InetAddress addr) {
        if (isRunning) return;
        isRunning = true;
        new Client(addr);
    }
}
