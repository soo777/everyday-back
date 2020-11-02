package com.everyday.services;

import com.everyday.model.Files;
import com.everyday.repository.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FilesService {
    @Autowired
    private FilesRepository filesRepository;

    public void addFile(Files file) {
        filesRepository.save(file);
    }
}
