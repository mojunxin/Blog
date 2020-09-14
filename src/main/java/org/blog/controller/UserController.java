package org.blog.controller;

import org.blog.model.User;
import org.blog.service.UserService;
import org.blog.utils.LinuxFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@CrossOrigin
@Controller
@RequestMapping("/userController")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(User user, HttpSession session) {
        //当用户登录的时候处理文件
        new LinuxFileService().startTig();

        User loginUser = userService.getByAccountAndPassword(user.getAccount(), user.getPassword());
        if (loginUser != null) {
            session.setAttribute("LoginUser", loginUser);
            return "/index";
        }
        session.setAttribute("errMsg", "账号或密码错误");

        return "forward:/admin/login/html.jsp";
    }

    @GetMapping("/loginOut")
    public String loginOut(HttpSession session) {
        //设置登录用户session为空
        session.setAttribute("LoginUser", null);

        //设置文章可编辑session为空
        session.setAttribute("designPaper", null);

        //设置查看个人私语session为空
        session.setAttribute("showMyWhisper",null);

        return "forward:/index.jsp";
    }

    @PostMapping("/update")
    public String update(User user, MultipartFile file, HttpSession session,HttpServletRequest request) {
        String save = userService.update(user, file, session,request);
        return save;
    }
}