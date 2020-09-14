package org.blog.controller;

import org.blog.model.IpAction;
import org.blog.model.Paper;
import org.blog.service.IpActionService;
import org.blog.service.PaperService;
import org.blog.service.UserService;
import org.blog.vo.PageVo;
import org.blog.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/paperController")
public class IpActionController {
    @Autowired
    private IpActionService ipActionService;

}
