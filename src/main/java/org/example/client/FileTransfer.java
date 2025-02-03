package org.example.client;

import common.FileMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileTransfer {
    public static FileMessage prepareFileForSending(String filePath, String username) {
        File file = new File(filePath);
        byte[] fileContent = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FileMessage(username, file.getName(), fileContent);
    }
}
