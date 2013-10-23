package com.game.server;

import tools.CustomLog;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {
    public static final int PORT = 4444;


    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Server Started");
        CustomLog.Log("server", "server started");
        try {
            while (true) {
                Socket socket = s.accept();
                try {
                    new Server(socket);
                }
                catch (IOException e) {
                    socket.close();
                }
            }
        }
        finally {
            s.close();
        }
    }
}
