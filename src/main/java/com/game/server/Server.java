package com.game.server;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {
    private Socket socket;
    private ObjectInputStream in;
    private InputStream inputStream;
    private OutputStream outputStream;
    private ObjectOutputStream out;

    public Server(Socket s) throws IOException {
        socket = s;
        outputStream = socket.getOutputStream();
        out = new ObjectOutputStream(outputStream);
        inputStream = socket.getInputStream();
        in =  new ObjectInputStream(inputStream);

        start();
    }

    public void run() {

        try {
            while (true) {
                ArrayList arrayList = new ArrayList();
                arrayList.add("Test from server");

                out.writeObject(arrayList);
                out.flush();

                if (inputStream.available() > 0) {
                    System.out.println(in.readObject());
                }

            }

        }
        catch (IOException e) {
            System.err.println("IO Exception");
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException");
        } finally {
            try {
                socket.close();
            }
            catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }
    }

//    public ArrayList getData() {
//        ArrayList data = new ArrayList();
//        data.add(somedata);
//        data.add(somedata2);
//        return data;
//    }

//    public void setData(String somedata, String somedata2) {
//
//    }
}
