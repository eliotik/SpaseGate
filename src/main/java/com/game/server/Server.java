package com.game.server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Server(Socket s) throws IOException {
        socket = s;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        start();
    }

    public void run() {
        try {

            String str = in.readLine();
            System.out.println("from client: " + str);
            if (str.equals("test")) {
                out.println("Test word");
            }
            else {
                out.println("some other word");
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
