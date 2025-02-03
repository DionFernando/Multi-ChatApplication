package org.example.client;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import common.*;

import java.io.File;

public class ClientController {
    @FXML private TextField usernameField;
    @FXML private TextField messageField;
    @FXML private TextArea chatArea;

    private ClientConnection clientConnection;

    @FXML
    public void initialize() {
        clientConnection = new ClientConnection("localhost", Constants.PORT, this);
        new Thread(clientConnection).start();
    }

    @FXML
    public void sendMessage() {
        String username = usernameField.getText();
        String message = messageField.getText();
        if (!message.isEmpty()) {
            clientConnection.sendMessage(new Message(username, message));
            messageField.clear();
        }
    }

    @FXML
    public void sendFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File to Send");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            String username = usernameField.getText();
            FileMessage fileMessage = FileTransfer.prepareFileForSending(file.getAbsolutePath(), username);
            clientConnection.sendFile(fileMessage);
            appendMessage(new Message("System", "File sent: " + file.getName()));
        }
    }

    @FXML
    public void disconnect() {
        System.exit(0);
    }

    public void appendMessage(Message message) {
        chatArea.appendText(message.getUsername() + ": " + message.getContent() + "\n");
    }
}