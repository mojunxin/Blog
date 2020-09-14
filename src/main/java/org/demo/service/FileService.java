package org.demo.service;

import org.demo.model.File;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface FileService {
    void save(File file);

    Integer count();

    File findByFileName(String fileName);

    void doSaveFile(MultipartFile file, HttpServletRequest request);

    List<File> findFile();
}
