package org.blog.controller;

import org.blog.model.Paper;
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

@CrossOrigin
@Controller
@RequestMapping("/paperController")
public class PaperController {
    @Autowired
    private PaperService paperService;
    @Autowired
    private UserService userService;

    @GetMapping("/init")
    public String init(Integer pageIndex, Integer pageSize, String createBy, String createCode, HttpSession session) {
        session.setAttribute("designPaper",null);
        pageIndex = pageIndex == null ? 1 : pageIndex <= 0 ? 1 : pageIndex;
        pageSize = pageSize == null ? 20 : pageSize;
        PageVo paperList = paperService.getPaperList(pageIndex, pageSize, createBy, createCode);
        ResponseVo<PageVo> succ = new ResponseVo<>(200, "succ", paperList);
        session.setAttribute("paperResponseVo", succ);
        session.setAttribute("dataCount", paperService.count());
        session.setAttribute("paperNumber", null);
        return "forward:/layui/html/index.jsp";
    }
    @GetMapping("/detail")
    public String details(String id, String desc, HttpSession session) {
        String details = paperService.details(id, desc, session);
        return details;
    }

    @GetMapping("/userPaper")
    public String userPaperDetails(String userId, HttpSession session) {
        PageVo paperList = paperService.getPaperList(1, 100, userId, null);
        ResponseVo<PageVo> succ = new ResponseVo<>(200, "succ", paperList);
        session.setAttribute("paperResponseVo", succ);
        //设置文章可编辑
        session.setAttribute("designPaper","Y");
        return "forward:/layui/html/index.jsp";
    }

    @GetMapping("/goUpdate")
    public String update(String id, HttpSession session) {
        Paper paper = paperService.getById(id);
        session.setAttribute("updatePaper",paper);
        return "forward:/admin/user/paper-update.jsp";
    }

    @PostMapping("/doUpdate")
    public String update(Paper paper, MultipartFile file, HttpSession session, HttpServletRequest request) {
        String s = paperService.doUpdate(paper, file, session, request);
        return s;
    }

    @PostMapping("/doAdd")
    public String add(Paper paper, MultipartFile file, HttpSession session, HttpServletRequest request) {
        String s = paperService.doAdd(paper, file, session, request);
        return s;
    }

    @GetMapping("/doDelete")
    public String doDelete(String id, HttpSession session) {
        paperService.delete(session,id.split(","));
        return "forward:/index.jsp";
    }

}
