package org.example.server;

import common.Message;
import common.FileMessage;
import java.util.ArrayList;
import java.util.List;

public class ServerBroadcaster {
    private static final List<ClientHandler> clients = new ArrayList<>();

    public static synchronized void addClient(ClientHandler client) {
        clients.add(client);
    }

    public static synchronized void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    public static synchronized void broadcast(Message message) {
        for (ClientHandler client : clients) {
            try {
                client.sendMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized void broadcast(FileMessage fileMessage) {
        for (ClientHandler client : clients) {
            try {
                client.sendFile(fileMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}