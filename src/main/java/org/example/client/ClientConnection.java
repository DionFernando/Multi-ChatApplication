package org.example.client;

import common.Message;
import common.FileMessage;
import java.io.*;
import java.net.Socket;

public class ClientConnection implements Runnable {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ClientController controller;

    public ClientConnection(String host, int port, ClientController controller) {
        try {
            this.socket = new Socket(host, port);
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
            this.controller = controller;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object obj = in.readObject();
                if (obj instanceof Message) {
                    Message message = (Message) obj;
                    controller.appendMessage(message);
                } else if (obj instanceof FileMessage) {
                    FileMessage fileMessage = (FileMessage) obj;
                    controller.appendMessage(new Message("System", "File received: " + fileMessage.getFileName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message) {
        try {
            out.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFile(FileMessage fileMessage) {
        try {
            out.writeObject(fileMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
