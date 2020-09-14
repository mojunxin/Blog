package org.blog.service.impl;

import org.apache.commons.io.FilenameUtils;
import org.blog.dao.UserDao;
import org.blog.model.User;
import org.blog.service.UserService;
import org.blog.utils.Utils;
import org.blog.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    private Utils utils = new Utils();

    @Override
    public User getByAccount(String account) {
        return userDao.getByAccount(account);
    }

    @Override
    public PageVo getListUser(Integer x, Integer y) {
        //数据总条数
        int dataCount = userDao.dataCount();
        if (dataCount == 0) {
            return new PageVo<>(x, y, null, null, null);
        } else {
            //总页数
            Integer pageCount = (dataCount + y - 1) / y;
            x = x < 0 ? 0 : x;
            x = x > pageCount ? pageCount : x;
            List<User> paperList = userDao.getListUser((x-1)*y, y);
            return new PageVo<>(x, y, pageCount, dataCount, paperList);
        }
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(HttpSession session,String[] ids) {
//        for (int i = 0; i < ids.length; i++) {
//            String pic = userDao.getById(ids[i]).getPic();
//            utils.deletePic(session,pic);
//        }
        userDao.delete(ids);
    }

    @Override
    public User getById(String id) {
        return userDao.getById(id);
    }

    @Override
    public User getByAccountAndPassword(String account, String password) {
        return userDao.getByAccountAndPassword(account,password);
    }

    @Override
    public String update(User user, MultipartFile file, HttpSession session,HttpServletRequest request) {
        String[] split = file.getOriginalFilename().split("");
        if (split.length > 1) {
            User loginUser = (User) session.getAttribute("LoginUser");
            if (loginUser.getPic() != null) {
                String realPath = request.getServletContext().getRealPath("");
                File oldPicPath = new File(realPath+loginUser.getPic());
                oldPicPath.delete();
            }
            String filename = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(filename);
            UUID uuid = UUID.randomUUID();
            String picName = uuid + "." + extension;
            user.setPic("/admin/pic/" +picName);
            String img = request.getServletContext().getRealPath("/admin/pic");
            //设置保存文件的路径
            String path = img + "/" + picName;
            try {
                file.transferTo(new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
            userDao.update(user);
            session.setAttribute("LoginUser", userDao.getByAccount(user.getAccount()));
            return "forward:/admin/login/myself.jsp";
        }
        userDao.update(user);
        session.setAttribute("LoginUser", userDao.getByAccount(user.getAccount()));
        return "forward:/admin/login/myself.jsp";
    }
}
