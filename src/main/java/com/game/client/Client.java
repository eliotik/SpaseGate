package com.game.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client extends Thread {

    private Socket fromServer;
    private int port;
    private String address;
//    private InetAddress address;
    public static boolean isRunning = false;

    public Client(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public synchronized void start() {
        if (isRunning) return;
        isRunning = true;
        new Thread(this).start();
    }

    public void run() {
        while (isRunning) {
            try {
                fromServer = new Socket(address, port);
                BufferedReader in  = new BufferedReader(new InputStreamReader(fromServer.getInputStream()));
                PrintWriter out = new PrintWriter(fromServer.getOutputStream(),true);
                BufferedReader inu = new BufferedReader(new InputStreamReader(System.in));

                String fuser,fserver;

                while ((fuser = inu.readLine())!=null) {
                    out.println(fuser);
                    fserver = in.readLine();
                    System.out.println(fserver);
                    if (fuser.equalsIgnoreCase("close")) break;
                    if (fuser.equalsIgnoreCase("exit")) break;
                }
                out.close();
                in.close();
                inu.close();
                fromServer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    public static void main(String[] args) {

        Client client = new Client("localhost",4444);

        client.start();



    }

}
