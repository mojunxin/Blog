package org.blog.service.impl;

import org.blog.dao.MessageDao;
import org.blog.model.Message;
import org.blog.service.MessageService;
import org.blog.utils.Utils;
import org.blog.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    private Utils utils = new Utils();

    @Override
    public Integer dataCount(String classCode, String paperId) {
        return messageDao.dataCount(classCode, paperId);
    }

    @Override
    public List<Message> getMessageList(String paperId) {
        return messageDao.getMessageList(paperId);
    }

    @Override
    public void save(Message message) {
        messageDao.save(message);
    }

    @Override
    public PageVo getMsgList(Integer x, Integer y, String userId, String classCode) {
        int dataCount = messageDao.dataCount(classCode, null);
        if (dataCount == 0) {
            return new PageVo<>(x, y, null, null, null);
        } else {
            //总页数
            Integer pageCount = (dataCount + y - 1) / y;
            x = x < 0 ? 0 : x;
            x = x > pageCount ? pageCount : x;
            List<Message> paperList = messageDao.getMsgList((x - 1) * y, y, userId, classCode);
            return new PageVo<>(x, y, pageCount, dataCount, paperList);
        }
    }

    @Override
    public void update(Message message) {
        messageDao.update(message);
    }

    @Override
    public void delete(HttpSession session, String[] ids) {
        for (int i = 0; i < ids.length; i++) {
            messageDao.getById(ids[i]).getPic();
//            utils.deletePic(session,pic);
        }
        messageDao.delete(ids);
    }
}
