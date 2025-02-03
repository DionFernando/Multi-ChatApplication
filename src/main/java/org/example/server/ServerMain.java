package org.example.server;

import org.example.common.Constants;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(Constants.PORT)) {
            System.out.println("Server started on port " + Constants.PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                new ClientHandler(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}