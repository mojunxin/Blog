package org.blog.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.blog.model.User;
import org.blog.vo.ResponseVo;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("LoginUser");
        if (user == null){
            ResponseVo<String> vo = new ResponseVo<>(404001, "没有登录", null);
            // 手动转换 vo 为 json 格式数据 == @ResponseBody
            String json = new ObjectMapper().writeValueAsString(vo);
            // 说明返回给浏览器的数据是 json 格式，编码是 utf-8
            response.setContentType("application/json;character=utf-8");
            // 把 json 数据发送给给浏览器
            response.getWriter().println(json);
            return false;
        }else{
            if (!"admin".equals(user.getAccount())) {
                ResponseVo<String> vo = new ResponseVo<>(404002, "非管理员没有权限", null);
                // 手动转换 vo 为 json 格式数据 == @ResponseBody
                String json = new ObjectMapper().writeValueAsString(vo);
                // 说明返回给浏览器的数据是 json 格式，编码是 utf-8
                response.setContentType("application/json;character=utf-8");
                // 把 json 数据发送给给浏览器
                response.getWriter().println(json);
                return false;
            }
        }
        return true;
    }
}
