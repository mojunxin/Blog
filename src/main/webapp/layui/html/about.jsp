<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <title>我的</title>
  <link rel="stylesheet" type="text/css" href="../res/layui/css/layui.css">
  <link rel="stylesheet" type="text/css" href="../res/css/main.css">
<!--加载meta IE兼容文件-->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->
</head>
<body>
<div class="header">
  <div class="menu-btn">
    <div class="menu"></div>
  </div>
  <h1 class="logo">
    <c:if test="${LoginUser.username==null}">
      <a href='<c:url value="/admin/login/html.jsp"></c:url> '>
        <span>MYBLOG</span>
        <img src='<c:url value="/layui/res/img/logo.png" ></c:url> '>
      </a>
    </c:if>
    <c:if test="${LoginUser.username!=null}">
      <a href='<c:url value="/layui/html/index.jsp" ></c:url> '>
        <span>MYBLOG</span>
        <img src='<c:url value="/layui/res/img/logo.png" ></c:url> '>
      </a>
    </c:if>
  </h1>
  <div class="nav">
    <a href='<c:url value="/paperController/init" ></c:url> '>文章</a>
    <a href='<c:url value="/whisperController/init" ></c:url> '>微语</a>
    <a href='<c:url value="/messageController/init" ></c:url> '>留言</a>
    <a href='<c:url value="/albumController/init" ></c:url> '>相册</a>
    <c:if test="${LoginUser.username==null}">
      <a href='<c:url value="/layui/html/about.jsp" ></c:url> '  class="active" >关于</a>
    </c:if>
    <c:if test="${LoginUser.username!=null}">
      <a href='<c:url value="/admin/login/myself.jsp"></c:url> '>${LoginUser.username}-欢迎</a>
    </c:if>
  </div>
</div>
  <div class="about-content">
    <div class="w1000">
      <div class="item info">
        <div class="title">
          <h3>我的介绍</h3>
        </div>
        <div class="cont">
          <img src="../res/img/photo.jpg">
          <div class="per-info">
            <p>
              <span class="name">莫小贝</span><br />
              <span class="age">24岁</span><br />
              <span class="Career">大数据开发工程师</span><br />
              <span class="interest">只有音乐才是我的解药</span>
            </p>
          </div>
        </div>
      </div>
      <div class="item tool">
        <div class="title">
          <h3>我的技能</h3>
        </div>
        <div class="layui-fluid">
          <div class="layui-row">
            <div class="layui-col-xs6 layui-col-sm3 layui-col-md3">
              <div class="cont-box">
                <img src="../res/img/sy_img_bd.jfif" style="width:100px;height:100px">
                <p>80%</p>
              </div>
            </div>
            <div class="layui-col-xs6 layui-col-sm3 layui-col-md3">
              <div class="cont-box">
                <img src="../res/img/sy_img_java.png" style="width:100px;height:100px">
                <p>80%</p>
              </div>
            </div>
            <div class="layui-col-xs6 layui-col-sm3 layui-col-md3">
              <div class="cont-box">
                <img src="../res/img/sy_img_scala.jfif" style="width:100px;height:100px">
                <p>80%</p>
              </div>
            </div>
            <div class="layui-col-xs6 layui-col-sm3 layui-col-md3">
              <div class="cont-box">
                <img src="../res/img/sy_img_guitar.jfif" style="width:100px;height:100px">
                <p>80%</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="item contact">
        <div class="title">
          <h3>联系方式</h3>
        </div>
        <div class="cont">
          <img src="../res/img/erweima.jpg">
          <div class="text">
            <p class="WeChat">微信：<span>mjx_up</span></p>
            <p class="qq">qq：<span>820517963</span></p>
            <p class="iphone">电话：<span>188****1101</span></p>
          </div>
        </div>
      </div>
    </div>
  </div>
  
  

  <div class="footer-wrap">
    <div class="footer w1000">
      <div class="qrcode">
        <img src="../res/img/erweima.jpg"  style="width:200px;height:200px">
      </div>
      <div class="practice-mode">
        <img src="../res/img/down_img.jpg">
        <div class="text">
          <h4 class="title">我的联系方式</h4>
          <p>微信<span class="WeChat">mjx_up</span></p>
          <p>手机<span class="iphone">188****1101</span></p>
          <p>邮箱<span class="email">820517963@qq.com</span></p>
        </div>
      </div>
    </div>
  </div>
  <script type="text/javascript" src="../res/layui/layui.js"></script>
  <script type="text/javascript">
    layui.config({
      base: '../res/js/util/'
    }).use(['element','laypage','form','layer','menu'],function(){
      element = layui.element,laypage = layui.laypage,form = layui.form,layer = layui.layer,menu = layui.menu;
      menu.init();
    })
  </script>
<div class="div" style="margin:0 auto; width:400px; height:100px; border:1px solid #FFF"><a href="http://beian.miit.gov.cn">备案：豫ICP备19045432号</a> </div>

</body>
</html>