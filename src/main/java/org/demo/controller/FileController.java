package org.demo.controller;

import org.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@CrossOrigin
@Controller
@RequestMapping("/fileController")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/doSaveFile")
    public String saveFile(MultipartFile file,HttpServletRequest request,HttpSession session) {
        fileService.doSaveFile(file,request);
        return "forward:/fileload/html/show-file.jsp";
    }
}
