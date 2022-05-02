package com.itbatia.app.controller;

import com.itbatia.app.model.File;
import com.itbatia.app.service.FileService;

import java.util.List;

public class FileController {
    private final FileService fileService = new FileService();

    public File createFile(String name, String content) {
        return fileService.createFile(name, content);
    }

    public File getFile(Integer id) {
        return fileService.getFile(id);
    }

    public List<File> getAllFiles() {
        return fileService.getAllFiles();
    }

    public File updateFile(File file) {
        return fileService.updateFile(file);
    }

    public void deleteFile(Integer id) {
        fileService.deleteFile(id);
    }
}
