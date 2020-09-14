package org.blog.service;

import org.blog.model.Message;
import org.blog.vo.PageVo;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface MessageService {
    Integer dataCount(String classCode,String paperId);

    List<Message> getMessageList(String paperId);

    void save(Message message);

    PageVo getMsgList(Integer x, Integer y, String userId, String classCode);

    void update(Message message);

    void delete(HttpSession session, String[] ids);

}
