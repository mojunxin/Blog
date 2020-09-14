package org.blog.service;

import org.blog.model.Paper;
import org.blog.vo.PageVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface PaperService {
    Integer count();

    PageVo getPaperList(Integer x, Integer y, String userId, String classCode);

    Paper getById(String id);

    void save(Paper paper);

    void update(Paper paper);

    void delete(HttpSession session,String[] ids);

    String  details(String id, String desc, HttpSession session);

    String doUpdate(Paper paper, MultipartFile file, HttpSession session, HttpServletRequest request);

    String doAdd(Paper paper, MultipartFile file, HttpSession session, HttpServletRequest request);
}
