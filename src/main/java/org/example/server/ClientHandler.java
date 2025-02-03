package org.example.server;

import java.io.*;
import java.net.Socket;
import common.*;
import org.example.common.FileMessage;
import org.example.common.Message;

public class ClientHandler extends Thread {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            while (true) {
                Object obj = in.readObject();
                if (obj instanceof Message) {
                    Message message = (Message) obj;
                    ServerBroadcaster.broadcast(message);
                } else if (obj instanceof FileMessage) {
                    FileMessage fileMessage = (FileMessage) obj;
                    ServerBroadcaster.broadcast(fileMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message) throws IOException {
        out.writeObject(message);
    }

    public void sendFile(FileMessage fileMessage) throws IOException {
        out.writeObject(fileMessage);
    }
}
