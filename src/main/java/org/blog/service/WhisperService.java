package org.blog.service;

import org.blog.model.Whisper;
import org.blog.vo.PageVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface WhisperService {
    Integer dataCount(String userId);

    PageVo getWhisperList(Integer x, Integer y, String userId, String classCode);

    Whisper getById(String id);

    void save(Whisper whisper);

    void update(Whisper whisper);

    void delete(HttpSession session,String[] ids);

    PageVo getInitWhisperForWEB(Integer x, Integer y, String userId, String classCode);

    PageVo getWhisperListForAdmin(Integer x,Integer y);

    Whisper doAdd(Whisper whisper, MultipartFile file, HttpSession session, HttpServletRequest request);

    String forGoDetail(String id,String desc, HttpSession session);
}
