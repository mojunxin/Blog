package org.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.blog.model.Album;

import java.util.List;

public interface AlbumDao {
    Integer dataCount(@Param("userId") String userId);

    List<Album> getAlbumList(@Param("x") Integer x, @Param("y") Integer y,@Param("userId") String userId);

    Album getById(@Param("id") String id);

    Album getBastNew();

    void save(Album album);

    void update(Album album);

    void delete(String[] ids);

}
