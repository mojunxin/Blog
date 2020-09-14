package org.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.blog.model.Paper;

import java.util.List;

public interface PaperDao {
    Integer dataCount(@Param("userId") String userId, @Param("classCode") String classCode);

    List<Paper> getPaperList(@Param("x") Integer x, @Param("y") Integer y, @Param("userId") String userId, @Param("classCode") String classCode);

    Paper getById(@Param("id") String id);

    String getBestNewPaper();

    void save(Paper paper);

    void update(Paper paper);

    void delete(String[] ids);
}
