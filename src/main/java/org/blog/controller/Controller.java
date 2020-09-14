package org.blog.controller;

import net.sf.json.JSONObject;
import org.blog.model.*;
import org.blog.service.*;
import org.blog.vo.PageVo;
import org.blog.vo.ResponseVo;
import org.demo.utils.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class Controller {

    @Autowired
    private UserService userService;

    @GetMapping("/user/list")
    public ResponseVo userList(Integer pageIndex, Integer pageSize) {
        pageIndex = pageIndex == null ? 1 : pageIndex <= 0 ? 1 : pageIndex;
        pageSize = pageSize == null ? 10 : pageSize;
        PageVo<User> UserPageVo = userService.getListUser(pageIndex, pageSize);
        ResponseVo success = new ResponseVo(200, "success", UserPageVo);
        return success;
    }

    @PutMapping("/user/update")
    public ResponseVo userUpdate(@RequestBody User user) {
        userService.update(user);
        return new ResponseVo(200, "success", null);
    }

    @PutMapping("/user/delete")
    public ResponseVo userDelete(HttpSession session, String ids) {
        String[] split = ids.split(",");
        userService.delete(session, split);
        return new ResponseVo(200, "success", null);
    }

    @PostMapping("/user/save")
    public ResponseVo userSave(@RequestBody User user) {
        userService.save(user);
        return new ResponseVo(200, "success", null);

    }


    //文章
    @Autowired
    private PaperService paperService;

    @GetMapping("/paper/list")
    public ResponseVo paperList(Integer pageIndex, Integer pageSize) {
        pageIndex = pageIndex == null ? 1 : pageIndex <= 0 ? 1 : pageIndex;
        pageSize = pageSize == null ? 10 : pageSize;
        PageVo PaperPageVo = paperService.getPaperList(pageIndex, pageSize, null, null);
        return new ResponseVo(200, "success", PaperPageVo);
    }

    @PutMapping("/paper/update")
    public ResponseVo upaperUdate(@RequestBody Paper paper) {
        paperService.update(paper);
        return new ResponseVo(200, "success", null);
    }

    @PutMapping("/paper/delete")
    public ResponseVo paperDelete(HttpSession session, String ids) {
        String[] split = ids.split(",");
        paperService.delete(session, split);
        return new ResponseVo(200, "success", null);
    }

    @PostMapping("/paper/save")
    public ResponseVo paperSave(@RequestBody Paper paper) {
        paperService.save(paper);
        return new ResponseVo(200, "success", null);
    }


    //留言
    @Autowired
    private MessageService messageService;

    @GetMapping("/message/list")
    public ResponseVo messageList(Integer pageIndex, Integer pageSize) {
        pageIndex = pageIndex == null ? 1 : pageIndex <= 0 ? 1 : pageIndex;
        pageSize = pageSize == null ? 10 : pageSize;
        PageVo messagePageVo = messageService.getMsgList(pageIndex, pageSize, null, null);
        return new ResponseVo(200, "success", messagePageVo);
    }

    @PutMapping("/message/update")
    public ResponseVo messagePageVoUpdate(@RequestBody Message message) {
        messageService.update(message);
        return new ResponseVo(200, "success", null);
    }

    @PutMapping("/message/delete")
    public ResponseVo messagePageVoDelete(HttpSession session, String ids) {
        String[] split = ids.split(",");
        messageService.delete(session, split);
        return new ResponseVo(200, "success", null);
    }

    @PostMapping("/message/save")
    public ResponseVo messageSave(@RequestBody Message message) {
        messageService.save(message);
        return new ResponseVo(200, "success", null);
    }


    //相册
    @Autowired
    private AlbumService albumService;

    @GetMapping("/album/list")
    public ResponseVo albumList(Integer pageIndex, Integer pageSize) {
        pageIndex = pageIndex == null ? 1 : pageIndex <= 0 ? 1 : pageIndex;
        pageSize = pageSize == null ? 10 : pageSize;
        PageVo albumPageVo = albumService.getAlbumList(pageIndex, pageSize, null);
        return new ResponseVo(200, "success", albumPageVo);
    }

    @PutMapping("/album/update")
    public ResponseVo albumUpdate(@RequestBody Album album) {
        albumService.update(album);
        return new ResponseVo(200, "success", null);
    }

    @PutMapping("/album/delete")
    public ResponseVo albumDelete(HttpSession session, String ids) {
        String[] split = ids.split(",");
        albumService.delete(session, split);
        return new ResponseVo(200, "success", null);
    }

    @PostMapping("/album/save")
    public ResponseVo albumSave(@RequestBody Album album) {
        albumService.save(album);
        return new ResponseVo(200, "success", null);
    }


    //微语
    @Autowired
    private WhisperService whisperService;

    @GetMapping("/whisper/list")
    public ResponseVo whisperList(Integer pageIndex, Integer pageSize) {
        pageIndex = pageIndex == null ? 1 : pageIndex <= 0 ? 1 : pageIndex;
        pageSize = pageSize == null ? 10 : pageSize;
        PageVo albumPageVo = whisperService.getWhisperListForAdmin(pageIndex, pageSize);
        return new ResponseVo(200, "success", albumPageVo);
    }

    @PutMapping("/whisper/update")
    public ResponseVo whisperUpdate(@RequestBody Whisper whisper) {
        whisperService.update(whisper);
        return new ResponseVo(200, "success", null);
    }

    @PutMapping("/whisper/delete")
    public ResponseVo whisperDelete(HttpSession session, String ids) {
        String[] split = ids.split(",");
        whisperService.delete(session, split);
        return new ResponseVo(200, "success", null);
    }

    @PostMapping("/whisper/save")
    public ResponseVo whisperSave(@RequestBody Whisper whisper) {
        whisperService.save(whisper);
        return new ResponseVo(200, "success", null);
    }


    @Autowired
    private IpActionService ipActionService;

    @GetMapping("/ipAction/list")
    public JSONObject ipActionList() {
        List<IpAction> list = ipActionService.list(null);
        Integer count = ipActionService.count();
        List<Map<String,Object>> lists = new ArrayList();
        for (IpAction ipAction:list) {
            Map<String, Object> ipMap = utils.convertBeanToMap(ipAction);
            lists.add(ipMap);
        }
        JSONObject dataMap = utils.getMapReturn(lists, "success", 200, count);
        return dataMap;
    }

    @GetMapping("/ipActionCount/list")
    public JSONObject ipActionCountList() {
        List<Map<String, Object>> maps = ipActionService.ipActionCount(null);
        Integer count = ipActionService.count();
        JSONObject dataMap = utils.getMapReturn(maps, "success", 200, count);
        return dataMap;
    }

}
