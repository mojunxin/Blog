package org.blog.service;

import org.blog.model.User;
import org.blog.vo.PageVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    User getByAccount(String account);

    PageVo getListUser(Integer x, Integer y);

    User getById(String id);

    User getByAccountAndPassword(String account, String password);

    String update(User user, MultipartFile file, HttpSession session,HttpServletRequest request);

    void save(User user);

    void update(User user);

    void delete(HttpSession session,String[] ids);
}
