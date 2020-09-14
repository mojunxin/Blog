package org.demo.controller;

import net.sf.json.JSONObject;
import org.demo.model.File;
import org.demo.service.FileService;
import org.demo.utils.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class APIController {
    @Autowired
    private FileService fileService;

    @GetMapping("html/file/list")
    public JSONObject userList() {
        List<File> files = fileService.findFile();
        Integer count = fileService.count();
        JSONObject dataMap = utils.getReturn(files, "success", 200, count);
        return dataMap;
    }
}
