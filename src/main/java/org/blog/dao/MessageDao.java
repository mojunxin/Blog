package org.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.blog.model.Message;

import java.util.List;

public interface MessageDao {
    Integer dataCount(@Param("classCode") String classCode, @Param("paperId") String paperId);

    List<Message> getMessageList(@Param("paperId") String paperId);

    void save(Message message);

    List<Message> getMsgList(@Param("x") Integer x, @Param("y") Integer y, @Param("userId") String userId, @Param("classCode") String classCode);

    //查询私语中的留言
    List<Message> getMessageListByWhisperId(@Param("whisperId") String whisperId);

    void update(Message message);

    void delete(String[] ids);

    Message getById(@Param("id") String id);

    Integer getDataCountByWhisperId(@Param("whisperId") String whisperId);

}
