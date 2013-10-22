package com.game.client;

import com.game.server.MultiServer;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;


public class Client extends Thread {
    private Socket socket;
    private ObjectInputStream in;
    private InputStream inputStream;
    private OutputStream outputStream;
    private ObjectOutputStream out;
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
            outputStream = socket.getOutputStream();
            out = new ObjectOutputStream(outputStream);
            inputStream = socket.getInputStream();
            in =  new ObjectInputStream(inputStream);
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

                if (inputStream.available() > 0) {
                    ArrayList arrayList1 = (ArrayList)in.readObject();
                    System.out.println(arrayList1.toString());
                }

                ArrayList arrayList = new ArrayList();
                arrayList.add("test");
                arrayList.add("test2");

                out.writeObject(arrayList);
                out.flush();
            }

        }
        catch (IOException e) {
            System.err.println("IO Exception");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
