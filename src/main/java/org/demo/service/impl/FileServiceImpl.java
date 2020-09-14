package org.demo.service.impl;

import org.apache.commons.io.FilenameUtils;
import org.blog.model.User;
import org.demo.dao.FileDao;
import org.demo.model.File;
import org.demo.service.FileService;
import org.demo.utils.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileDao fileDao;

    @Override
    public void save(File file) {
        fileDao.save(file);
    }

    @Override
    public Integer count() {
        return fileDao.count();
    }

    @Override
    public File findByFileName(String fileName) {
        return fileDao.findByFileName(fileName);
    }

    @Override
    public void doSaveFile(MultipartFile file, HttpServletRequest request) {
        String path = this.saveFile(file, request);
        if (path == null) {
            return;
        }
        String[] split = path.split("/");
        String fileName = split[split.length - 1];

        File saveFile = new File();
        saveFile.setCreateDate(new Date());
        saveFile.setFilePath(path);
        saveFile.setFileName(fileName);
        System.out.println(fileName);
        saveFile.setFileType(fileName.split("\\.")[1]);
        User loginUser = (User) request.getSession().getAttribute("LoginUser");
        saveFile.setCreateBy(loginUser.getUsername());
        this.save(saveFile);
    }

    @Override
    public List<File> findFile() {
        return fileDao.findFile();
    }

    public String saveFile(MultipartFile file, HttpServletRequest request) {
        String[] split = file.getOriginalFilename().split("");
        if (split.length > 1) {
            String filename = file.getOriginalFilename();

            File byFileName = this.findByFileName(filename);
            if (byFileName != null) {
                String extension = FilenameUtils.getExtension(filename);
                filename = byFileName.getFileName()+"(new)" + "." + extension;
            }
            String img = request.getServletContext().getRealPath("/fileload/file");
            //设置保存文件的路径
            String path = img + "/" + filename;
            try {
                file.transferTo(new java.io.File(path));
                return path;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
