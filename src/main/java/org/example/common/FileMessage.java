package org.example.common;

import java.io.Serializable;

public class FileMessage implements Serializable {
    private String username;
    private String fileName;
    private byte[] fileContent;

    public FileMessage(String username, String fileName, byte[] fileContent) {
        this.username = username;
        this.fileName = fileName;
        this.fileContent = fileContent;
    }

    public String getUsername() {
        return username;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getFileContent() {
        return fileContent;
    }
}
