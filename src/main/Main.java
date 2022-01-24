package main;

import connection.DBConnection;
import connection.PostService;
import post_create.Post;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static int CLIENT_NUMBER = 0;
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(4444);
            System.out.println("Server running on port: 4444");
            while(true){
                System.out.println("Waiting for client requests:");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected...");
                CLIENT_NUMBER++;
                System.out.println("Total clients: " + CLIENT_NUMBER);
                Thread t = new Thread(new HandleRequest(socket));
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
