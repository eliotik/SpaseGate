package com.game.server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    private int port;
    public static boolean isRunning = false;
    private BufferedReader in = null;
    private PrintWriter out= null;
    private ServerSocket servers = null;
    private Socket fromClient = null;

    public Server(int port) {
        this.port = port;
    }


    public synchronized void start() {
        if (isRunning) return;
        isRunning = true;
        new Thread(this).start();
    }


    public void run() {
        System.out.println("Welcome to Server side");
        while (isRunning) {
            try {
                servers = new ServerSocket(port);
            } catch (IOException e) {
                System.out.println("Couldn't listen to port " + port);
                System.exit(-1);
            }

            try {
                System.out.print("Waiting for a client...");
                fromClient= servers.accept();
                System.out.println("Client connected");
                in  = new BufferedReader(new InputStreamReader(fromClient.getInputStream()));
                out = new PrintWriter(fromClient.getOutputStream(),true);

                String input, output;
                while ((input = in.readLine()) != null) {
                    if (input.equalsIgnoreCase("exit")) break;
                    out.println("From server: "+input);
                    System.out.println(input);
                }
                out.close();
                in.close();
                fromClient.close();
                servers.close();

            } catch (IOException e) {
                System.out.println("Can't accept");
                System.exit(-1);
            }

        }
    }





    public static void main(String[] args) {

        Server server = new Server(4444);
        server.start();



    }
}
