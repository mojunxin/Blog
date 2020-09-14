package org.blog.service.impl;

import org.apache.commons.io.FilenameUtils;
import org.blog.dao.MessageDao;
import org.blog.dao.WhisperDao;
import org.blog.model.Message;
import org.blog.model.User;
import org.blog.model.Whisper;
import org.blog.service.WhisperService;
import org.blog.utils.Utils;
import org.blog.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class WhisperServiceImpl implements WhisperService {
    @Autowired
    private WhisperDao whisperDao;

    @Autowired
    private MessageDao messageDao;

    private Utils utils = new Utils();

    @Override
    public Integer dataCount(String userId) {
        return whisperDao.dataCount(userId);
    }

    @Override
    public PageVo getWhisperList(Integer x, Integer y, String userId, String classCode) {
        //数据总条数
        int dataCount = whisperDao.dataCount(userId);
        if (dataCount == 0) {
            return new PageVo<>(x, y, null, null, null);
        } else {
            //总页数
            Integer pageCount = (dataCount + y - 1) / y;
            x = x < 0 ? 0 : x;
            x = x > pageCount ? pageCount : x;
            List<Whisper> paperList = whisperDao.getWhisperList((x - 1) * y, y, userId, classCode);
            return new PageVo<>(x, y, pageCount, dataCount, paperList);
        }
    }

    @Override
    public Whisper getById(String id) {
        return whisperDao.getById(id);
    }

    @Override
    public void save(Whisper whisper) {
        whisperDao.save(whisper);
    }

    @Override
    public void update(Whisper whisper) {
        whisperDao.update(whisper);
    }

    @Override
    public void delete(HttpSession session,String[] ids) {
        for (int i = 0; i < ids.length; i++) {
            String pic = whisperDao.getById(ids[i]).getPic();
            utils.deletePic(session,pic);
        }
        whisperDao.delete(ids);
    }

    @Override
    public PageVo getInitWhisperForWEB(Integer x, Integer y, String userId, String classCode) {
        //数据总条数
        int dataCount = whisperDao.dataCount(userId);
        if (dataCount == 0) {
            return new PageVo<>(x, y, null, null, null);
        } else {
            x--;
            //总页数
            Integer pageCount = (dataCount + y - 1) / y;
            x = x < 0 ? 0 : x;
            x = x > pageCount ? pageCount : x;
            //获取所有的 私语
            List<Whisper> paperList = whisperDao.getWhisperList(x, y, userId, classCode);
            ArrayList<Whisper> whispers = new ArrayList<>();

            for (Whisper whisper : paperList) {
                List<Message> messageListByWhisperId = messageDao.getMessageListByWhisperId(whisper.getId());
                whisper.setMessages(messageListByWhisperId);
                whispers.add(whisper);
            }
            return new PageVo<>(x, y, pageCount, dataCount, whispers);
        }
    }

    @Override
    public PageVo getWhisperListForAdmin(Integer x, Integer y) {
        //数据总条数
        int dataCount = whisperDao.dataCount(null);
        if (dataCount == 0) {
            return new PageVo<>(x, y, null, null, null);
        } else {
            x--;
            //总页数
            Integer pageCount = (dataCount + y - 1) / y;
            x = x < 0 ? 0 : x;
            x = x > pageCount ? pageCount : x;
            //获取所有的 私语
            List<Whisper> whisperList = whisperDao.getWhisperListForAdmin(x, y);

            return new PageVo<>(x, y, pageCount, dataCount, whisperList);
        }
    }

    @Override
    public Whisper doAdd(Whisper whisper, MultipartFile file, HttpSession session, HttpServletRequest request) {
        String[] split = file.getOriginalFilename().split("");
        if (split.length > 1) {
            String filename = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(filename);

            String picName = utils.getUuid() + "." + extension;
            whisper.setPic("/admin/pic/" + picName);
            String img = request.getServletContext().getRealPath("/admin/pic");
            //设置保存文件的路径
            String path = img + "/" + picName;
            try {
                file.transferTo(new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        whisper.setCreateDate(utils.getDate());
        whisper.setClassCode("whisper");
        whisper.setIsShow(whisper.getIsShow() == null ? "Y" : whisper.getIsShow());
        User loginUser = (User) session.getAttribute("LoginUser");
        if (loginUser != null) {
            whisper.setUserId(loginUser.getId());
            whisper.setUsername(loginUser.getUsername());
        } else {
            whisper.setUsername("游客");
        }
        whisperDao.save(whisper);

        return whisper;
    }

    @Override
    public String forGoDetail(String id,String desc, HttpSession session) {
        if (desc == null) {
            Whisper whisper = whisperDao.getById(id);
            whisper.setMessages(messageDao.getMessageList(id));
            session.setAttribute("goDetailWhisper", whisper);

            Integer count = messageDao.getDataCountByWhisperId(id);
            session.setAttribute("whisperMessageDataCount", count);
            return "forward:/admin/user/whisper-detail.jsp";
        } else {
            Whisper detailWhisper = (Whisper) session.getAttribute("goDetailWhisper");
            String deId = detailWhisper.getId();

            Message message = new Message();
            message.setWhisperId(deId);
            message.setCreateDate(utils.getDate());
            message.setContent(desc);
            message.setPic("/layui/res/img/header.png");

            messageDao.save(message);

            Whisper whisper = whisperDao.getById(deId);
            whisper.setMessages(messageDao.getMessageListByWhisperId(deId));
            session.setAttribute("goDetailWhisper", whisper);

            Integer count = messageDao.getDataCountByWhisperId(deId);
            session.setAttribute("whisperMessageDataCount", count);
            return "forward:/admin/user/whisper-detail.jsp";
        }

    }
}
