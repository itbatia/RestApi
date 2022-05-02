package com.itbatia.app.service;

import com.itbatia.app.model.File;
import com.itbatia.app.repositopy.FileRepository;
import com.itbatia.app.repositopy.db.DbFileRepositoryImpl;

import java.util.List;

public class FileService {
    private final FileRepository fileRepository = new DbFileRepositoryImpl();

    public File createFile(String name, String content) {
        File file = new File(null, name, content);
        return fileRepository.save(file);
    }

    public File getFile(Integer id) {
        return fileRepository.getById(id);
    }

    public List<File> getAllFiles() {
        return fileRepository.getAll();
    }

    public File updateFile(File file) {
        return fileRepository.update(file);
    }

    public void deleteFile(Integer id) {
        fileRepository.deleteById(id);
    }
}
