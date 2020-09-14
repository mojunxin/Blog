package org.blog.controller;

import org.blog.utils.RestUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitDemo implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent context) {
    }
    @Override
    public void contextInitialized(ServletContextEvent context) {
        System.out.println("================>[ServletContextListener]自动加载启动开始.");
//        String strURL = "http://localhost:8080/blog/paperController/init";
//        RestUtil.restUtil(strURL);
    }
}
