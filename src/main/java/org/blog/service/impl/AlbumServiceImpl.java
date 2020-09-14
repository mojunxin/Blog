package org.blog.service.impl;

import org.apache.commons.io.FilenameUtils;
import org.blog.dao.AlbumDao;
import org.blog.model.Album;
import org.blog.model.User;
import org.blog.service.AlbumService;
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
@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;

    private Utils utils = new Utils();

    @Override
    public Integer dataCount(String userId) {
        return albumDao.dataCount(userId);
    }

    @Override
    public Album getById(String id) {
        return albumDao.getById(id);
    }

    @Override
    public void save(Album album) {
        albumDao.save(album);
    }

    @Override
    public void update(Album album) {
        albumDao.update(album);
    }

    @Override
    public void delete(HttpSession session,String[] ids) {
        for (int i = 0; i < ids.length; i++) {
            String pic = albumDao.getById(ids[i]).getPic();
            utils.deletePic(session,pic);
        }
        albumDao.delete(ids);
    }

    @Override
    public PageVo getAlbumList(Integer x, Integer y, String userId) {
        //数据总条数
        int dataCount = albumDao.dataCount(userId);
        if (dataCount == 0) {
            return new PageVo<>(x, y, null, null, null);
        } else {
            //总页数
            Integer pageCount = (dataCount + y - 1) / y;
            x = x < 0 ? 0 : x;
            x = x > pageCount ? pageCount : x;
            List<Album> paperList = albumDao.getAlbumList((x-1)*y, y,userId);
            return new PageVo<>(x, y, pageCount, dataCount, paperList);
        }
    }

    @Override
    public Album getBastNew() {
        return albumDao.getBastNew();
    }

    @Override
    public String doAdd(Album album, MultipartFile file, HttpSession session, HttpServletRequest request) {
        String[] split = file.getOriginalFilename().split("");
        if (split.length > 1) {
            String filename = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(filename);

            String picName = utils.getUuid() + "." + extension;
            album.setPic("/admin/pic/" +picName);
            String img = request.getServletContext().getRealPath("/admin/pic");
            //设置保存文件的路径
            String path = img + "/" + picName;
            try {
                file.transferTo(new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        album.setCreateDate(utils.getDate());
        User loginUser = (User) session.getAttribute("LoginUser");
        if (loginUser != null) {
            album.setUserId(loginUser.getId());
            album.setCreateBy(loginUser.getUsername());
        } else {
            album.setCreateBy("游客");
        }
        albumDao.save(album);

        return "forward:/layui/html/album.jsp";
    }

}
