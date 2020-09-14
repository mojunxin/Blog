<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>Document</title>
    <link rel="stylesheet" href='<c:url value="/layui/res/layui/css/layui.css" ></c:url> '>
    <link rel="stylesheet" href='<c:url value="/layui/res/css/main.css" ></c:url> '>
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
        <a href='<c:url value="/layui/html/index.jsp" ></c:url> '>
            <span>MYBLOG</span>
            <img src='<c:url value="/layui/res/img/logo.png" ></c:url> '>
        </a>
    </h1>
    <div class="nav">
        <a href='<c:url value="/paperController/init" ></c:url> '>首页</a>
        <a href='<c:url value="/userController/loginOut"></c:url> '>${LoginUser.username}-退出</a>
    </div>
</div>
<div class="about-content">
    <form method="post" action='<c:url value="/userController/update"></c:url> ' enctype="multipart/form-data">
        <div class="w1000">
            <div class="item info">
                <div class="title">
                    <h3>我的介绍</h3>
                </div>
                <div class="cont">
                    <img src='<c:url value="${LoginUser.pic}"></c:url> '>
                    <div class="per-info">
                        <p>
                            头像：<span class="name"><input type="file" name="file" value="头像"></span><br/>
                            昵称：<span class="name"><input type="text" name="username" value="${LoginUser.username}" ></span><br/>
                            年龄：<span class="name"><input type="text" name="age" value="${LoginUser.age}" ></span><br/>
                            工作：<span class="name"><input type="text" name="work" value="${LoginUser.work}" ></span><br/>
                            喜好：<span class="name"><input type="text" name="hobby" value="${LoginUser.hobby}" ></span><br/>
                        </p>
                    </div>
                </div>
            </div>
            <div class="item contact">
                <div class="title">
                    <h3>联系方式</h3>
                </div>
                <div class="cont">
                    <div class="text">
                        <img src='<c:url value="/layui/res/img/erweima.jpg"></c:url> '>
                        <p class="WeChat">微信：<span><input type="text" name="wx" value="${LoginUser.wx}" ></span></p>
                        <p class="qq"> &nbsp qq  ：<span><input type="text" name="qq" value="${LoginUser.qq}" ></span></p>
                        <p class="iphone">电话：<span><input type="text" name="phone" value="${LoginUser.phone}" ></span></p>
                    </div>
                </div>
                <div class="title">
                    <h3>账号</h3>
                </div>
                <div class="cont">
                    <div class="text">
                        账号：<span class="name">${LoginUser.account}</span><br/>
                        密码：<span class="name"><input type="text" name="password" value="${LoginUser.password}" ></span><br/>
                        <input type="text" style="visibility: hidden;" name="id" value="${LoginUser.id}" >
                        <input type="text" style="visibility: hidden;" name="account" value="${LoginUser.account}" >
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block" style="text-align: left;">
                    <button class="layui-btn definite">確定</button>
                </div>
            </div>

        </div>
    </form>
    <br/>
    <br/>
    <br/>
</div>


<div class="footer-wrap">
    <div class="footer w1000">
        <div class="qrcode">
            <img src='<c:url value="/layui/res/img/erweima.jpg" ></c:url> ' style="width:200px;height:200px">
        </div>
        <div class="practice-mode">
            <img src='<c:url value="/layui/res/img/down_img.jpg"></c:url> '>
            <div class="text">
                <h4 class="title">我的联系方式</h4>
                <p>微信<span class="WeChat">1234567890</span></p>
                <p>手机<span class="iphone">1234567890</span></p>
                <p>邮箱<span class="email">1234567890@qq.com</span></p>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" href='<c:url value="/layui/res/layui/layui.js" ></c:url> '/>
<script type="text/javascript">
    layui.config({
        base: '../res/js/util/'
    }).use(['element', 'laypage', 'form', 'layer', 'menu'], function () {
        element = layui.element, laypage = layui.laypage, form = layui.form, layer = layui.layer, menu = layui.menu;
        menu.init();
    })
</script>
<div class="div" style="margin:0 auto; width:400px; height:100px; border:1px solid #FFF"><a href="http://beian.miit.gov.cn">备案：豫ICP备19045432号</a> </div>

</body>
</html>