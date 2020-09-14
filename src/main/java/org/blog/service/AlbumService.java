package org.blog.service;

import org.blog.model.Album;
import org.blog.vo.PageVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface AlbumService {
    Integer dataCount(String userId);

    Album getById(String id);

    void save(Album album);

    void update(Album album);

    void delete(HttpSession session,String[] ids);

    PageVo getAlbumList(Integer x, Integer y, String userId);

    Album getBastNew();

    String doAdd(Album album, MultipartFile file, HttpSession session, HttpServletRequest request);
}
