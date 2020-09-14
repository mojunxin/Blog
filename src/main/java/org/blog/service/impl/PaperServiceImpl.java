package org.blog.service.impl;

import org.apache.commons.io.FilenameUtils;
import org.blog.dao.PaperDao;
import org.blog.model.Message;
import org.blog.model.Paper;
import org.blog.model.User;
import org.blog.service.MessageService;
import org.blog.service.PaperService;
import org.blog.utils.Utils;
import org.blog.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    private PaperDao paperDao;
    @Autowired
    private MessageService messageService;

    private Utils utils = new Utils();

    @Override
    public Integer count() {
        return paperDao.dataCount(null, null);
    }

    @Override
    public PageVo getPaperList(Integer x, Integer y, String userId, String classCode) {
        //数据总条数
        int dataCount = paperDao.dataCount(userId, classCode);
        if (dataCount == 0) {
            return new PageVo<>(x, y, null, null, null);
        } else {
            //总页数
            Integer pageCount = (dataCount + y - 1) / y;
            x = x < 0 ? 0 : x;
            x = x > pageCount ? pageCount : x;
            List<Paper> paperList = paperDao.getPaperList((x-1)*y, y, userId, classCode);
            return new PageVo<>(x, y, pageCount, dataCount, paperList);
        }
    }

    @Override
    public Paper getById(String id) {
        return paperDao.getById(id);
    }

    @Override
    public void save(Paper paper) {
        paperDao.save(paper);
    }

    @Override
    public void update(Paper paper) {
        paperDao.update(paper);
    }

    @Override
    public void delete(HttpSession session,String[] ids) {
        for (int i = 0; i < ids.length; i++) {
            String pic = paperDao.getById(ids[i]).getPic();
            utils.deletePic(session,pic);
        }
        paperDao.delete(ids);
    }

    @Override
    public String details(String id, String desc, HttpSession session) {
        if (desc == null) {
            if (id != null) {//点击详情
                if ("previous".equals(id)) {//点击上一篇
                    //点击上一页的时候 lastPage则为空
                    session.setAttribute("lastPage", null);
                    Integer pageIndex = (Integer) session.getAttribute("nextNum");
                    if (pageIndex - 1<=0) {
                        session.setAttribute("firstPage", "首页");
                        pageIndex++;
                    }
                    Paper paper = this.getPaper(pageIndex - 1);
                    session.setAttribute("paper", paper);
                    session.setAttribute("nextNum", pageIndex - 1);
                    //获取留言
                    session.setAttribute("messageList", messageService.getMessageList(paper.getId()));
                    session.setAttribute("msgDataCount",messageService.dataCount("paper",paper.getId()));
                    return "forward:/layui/html/details.jsp";
                } else {
                    session.setAttribute("nextNum", 1);
                    if (id.equals(paperDao.getBestNewPaper())) {
                        session.setAttribute("firstPage", "首页");
                    }
                    //获取目标文章
                    Paper paper = this.getById(id);
                    session.setAttribute("paper", paper);
                    //获取留言
                    session.setAttribute("messageList", messageService.getMessageList(id));
                    session.setAttribute("msgDataCount",messageService.dataCount("paper",paper.getId()));
                    return "forward:/layui/html/details.jsp";
                }
            } else {//点击下一篇
                session.setAttribute("firstPage", null);
                Integer pageIndex = (Integer) session.getAttribute("nextNum");
                if (this.count() == pageIndex + 1) {
                    session.setAttribute("lastPage", "最后一页了！");
                }
                Paper paper = this.getPaper(pageIndex+1);

                session.setAttribute("nextNum", pageIndex + 1);
                session.setAttribute("paper", paper);
                //获取留言
                session.setAttribute("messageList", messageService.getMessageList(paper.getId()));
                session.setAttribute("msgDataCount",messageService.dataCount("paper",paper.getId()));
                return "forward:/layui/html/details.jsp";
            }
        } else {
            Paper  paper = (Paper) session.getAttribute("paper");
            Message message = new Message();
            message.setPaperId(paper.getId());
            message.setCreateDate(utils.getDate());
            message.setContent(desc);
            message.setClassCode("paper");
            User loginUser = (User) session.getAttribute("LoginUser");
            if (loginUser == null) {
                message.setUsername("游客");
                message.setPic("/layui/res/img/header.png");
            } else {
                message.setUsername(loginUser.getUsername());
                message.setUserId(loginUser.getId());
//                message.setPic(loginUser.getPic());
                message.setPic("/layui/res/img/header.png");
            }

            messageService.save(message);
            //获取留言
            session.setAttribute("messageList", messageService.getMessageList(paper.getId()));
            session.setAttribute("msgDataCount",messageService.dataCount("paper",paper.getId()));
            return "forward:/layui/html/details.jsp";
        }
    }

    @Override
    public String doUpdate(Paper paper, MultipartFile file, HttpSession session, HttpServletRequest request) {
        String[] split = file.getOriginalFilename().split("");
        if (split.length > 1) {

            if (paper.getPic() != null) {
                String realPath = request.getServletContext().getRealPath("");
                File oldPicPath = new File(realPath+paper.getPic());
                oldPicPath.delete();
            }
            String filename = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(filename);
            UUID uuid = UUID.randomUUID();
            String picName = uuid + "." + extension;
            paper.setPic("/admin/pic/" +picName);
            String img = request.getServletContext().getRealPath("/admin/pic");
            //设置保存文件的路径
            String path = img + "/" + picName;
            try {
                file.transferTo(new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        paperDao.update(paper);
        session.setAttribute("updatePaper", paperDao.getById(paper.getId()));
        return "forward:/admin/user/paper-update.jsp";
    }

    @Override
    public String doAdd(Paper paper, MultipartFile file, HttpSession session, HttpServletRequest request) {
        String[] split = file.getOriginalFilename().split("");
        if (split.length > 1) {
            String filename = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(filename);

            String picName = utils.getUuid() + "." + extension;
            paper.setPic("/admin/pic/" +picName);
            String img = request.getServletContext().getRealPath("/admin/pic");
            //设置保存文件的路径
            String path = img + "/" + picName;
            try {
                file.transferTo(new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        paper.setCreateDate(utils.getDate());
        paper.setClassCode("paper");
        User loginUser = (User) session.getAttribute("LoginUser");
        if (loginUser != null) {
            paper.setUserId(loginUser.getId());
            paper.setCreateBy(loginUser.getUsername());
        } else {
            paper.setCreateBy("游客");
        }
        paperDao.save(paper);

        return "forward:/index.jsp";
    }

    public Paper getPaper(Integer pageIndex) {
        PageVo paperList = this.getPaperList(pageIndex, 1, null, null);
        List<Paper> papers = paperList.getList();
        return papers.size() > 0 ? papers.get(0) : null;
    }
}
