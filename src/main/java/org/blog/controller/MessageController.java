package org.blog.controller;

import org.blog.model.Message;
import org.blog.model.User;
import org.blog.service.MessageService;
import org.blog.utils.Utils;
import org.blog.vo.PageVo;
import org.blog.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@CrossOrigin
@Controller
@RequestMapping("/messageController")
public class MessageController {
    @Autowired
    private MessageService messageService;

    private Utils utils = new Utils();

    @GetMapping("/init")
    public String init(Integer pageIndex, Integer pageSize,String desc, HttpSession session) {
        if (desc != null) {
            Message message = new Message();
            message.setCreateDate(utils.getDate());
            message.setContent(desc);
            message.setClassCode("message");
            User loginUser = (User) session.getAttribute("LoginUser");
            if (loginUser == null) {
                message.setUsername("游客");
                message.setPic("/layui/res/img/header.png");
            } else {
                message.setUsername(loginUser.getUsername());
                message.setUserId(loginUser.getId());
//                message.setPic(loginUser.getPic());
                message.setPic("/layui/res/img/header.png");
            }
            messageService.save(message);
        }

        pageIndex = pageIndex == null ? 1 : pageIndex <= 0 ? 1 : pageIndex;
        pageSize = pageSize == null ? 20 : pageSize;
        PageVo paperList = messageService.getMsgList(pageIndex, pageSize, null, "message");
        ResponseVo<PageVo> succ = new ResponseVo<>(200, "succ", paperList);
        session.setAttribute("paperResponseVo", succ);
        session.setAttribute("leacotsCount", messageService.dataCount("message", null));
        return "forward:/layui/html/leacots.jsp";
    }

}
