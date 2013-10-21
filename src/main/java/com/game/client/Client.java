package com.game.client;

import com.game.server.MultiServer;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;


public class Client extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private static int counter = 0;
    private int id = counter++;


    public Client(InetAddress addr) {
        System.out.println("Making client " + id);
        try {
            socket = new Socket(addr, MultiServer.PORT);
        }
        catch (IOException e) {
            System.err.println("Socket failed");
        }
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            start();
        }
        catch (IOException e) {
            try {
                socket.close();
            }
            catch (IOException e2) {
                System.err.println("Socket not closed");
            }
        }
    }

    public void run() {
        try {
            while(true) {
                out.println("test");
                String str = in.readLine();
                if (str != null) {
                    System.out.println("from server:" + str);
                }

            }

        }
        catch (IOException e) {
            System.err.println("IO Exception");
        }
        finally {
            try {
                socket.close();
            }
            catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }
    }



}
