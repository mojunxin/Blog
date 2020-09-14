package org.blog.controller;

import org.blog.model.Album;
import org.blog.service.AlbumService;
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
@RequestMapping("/albumController")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @GetMapping("/init")
    public String init(Integer pageIndex, Integer pageSize,String userId,HttpSession session) {
        pageIndex = pageIndex == null ? 1 : pageIndex <= 0 ? 1 : pageIndex;
        pageSize = pageSize == null ? 6 : pageSize;
        PageVo albumList = albumService.getAlbumList(pageIndex, pageSize, userId);
        ResponseVo<PageVo> succ = new ResponseVo<>(200, "succ", albumList);
        session.setAttribute("albumResponseVo", succ);

        session.setAttribute("bastNewAlbum", albumService.getBastNew());
        return "forward:/layui/html/album.jsp";
    }

    @PostMapping("/doAdd")
    public String add(Album album, MultipartFile file, HttpSession session, HttpServletRequest request) {
        String s = albumService.doAdd(album, file, session, request);
        this.init(1, 6, null, session);
        return s;
    }

}
