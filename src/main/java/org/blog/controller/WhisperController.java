package org.blog.controller;

import org.blog.model.Message;
import org.blog.model.User;
import org.blog.model.Whisper;
import org.blog.service.MessageService;
import org.blog.service.WhisperService;
import org.blog.vo.PageVo;
import org.blog.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@CrossOrigin
@Controller
@RequestMapping("/whisperController")
public class WhisperController {
    @Autowired
    private WhisperService whisperService;

    @GetMapping("/init")
    public String init(Integer pageIndex, Integer pageSize, String userId, String classCode, HttpSession session) {
        //数据初始化
//        String showMyWhisper = (String) session.getAttribute("showMyWhisper");
//        if (showMyWhisper != null) {
//            if (showMyWhisper.equals("Y")) {
//                User loginUser = (User) session.getAttribute("LoginUser");
//                userId = loginUser.getId();
//            }
//        }

        pageIndex = pageIndex == null ? 1 : pageIndex <= 0 ? 1 : pageIndex;
        pageSize = pageSize == null ? 3 : pageSize;
        PageVo paperList = whisperService.getWhisperList(pageIndex, pageSize, userId, classCode);
        ResponseVo<PageVo> succ = new ResponseVo<>(200, "succ", paperList);
        session.setAttribute("whisperResponseVo", succ);
        return "forward:/layui/html/whisper.jsp";
    }

    @PostMapping("/doAdd")
    public String add(Whisper whisper, MultipartFile file, HttpSession session, HttpServletRequest request) {
        Whisper wh = whisperService.doAdd(whisper, file, session, request);
//        session.setAttribute("addWhisper",wh);
//        return "forward:/admin/user/whisper-add.jsp";

        PageVo paperList = whisperService.getWhisperList(1, 3, null, null);

        ResponseVo<PageVo> succ = new ResponseVo<>(200, "succ", paperList);
        session.setAttribute("whisperResponseVo", succ);
        return "forward:/layui/html/whisper.jsp";

    }

    @GetMapping("/goDetail")
    public String details(String id,String desc, HttpSession session) {
        return whisperService.forGoDetail(id,desc, session);
    }

}
