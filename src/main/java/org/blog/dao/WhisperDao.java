package org.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.blog.model.Whisper;

import java.util.List;

public interface WhisperDao {
    Integer dataCount(@Param("userId") String userId);

    List<Whisper> getWhisperList(@Param("x") Integer x, @Param("y") Integer y, @Param("userId") String userId, @Param("classCode") String classCode);

    Whisper getById(@Param("id") String id);

    void save(Whisper whisper);

    void update(Whisper whisper);

    void delete(String[] ids);

    List<Whisper> getWhisperListForAdmin(@Param("x") Integer x, @Param("y") Integer y);
}
