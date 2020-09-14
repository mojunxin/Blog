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
        <c:if test="${LoginUser.account=='admin'}">
            <a href='<c:url value="/admin/adminDate/user.jsp" ></c:url> '>后台</a>
        </c:if>
        <a href='<c:url value="/userController/loginOut"></c:url> '>${LoginUser.username}-退出</a>
    </div>
</div>
<div class="about-content">
    <div class="w1000">
        <div class="item info">
            <div class="title">
                <h3>我的介绍</h3>
            </div>
            <div class="cont">
                <img src='<c:url value="${LoginUser.pic}"></c:url> '>
                <div class="per-info">
                    <p>
                        <span class="name">${LoginUser.username}</span><br/>
                        <span class="age">${LoginUser.age}岁</span><br/>
                        <span class="Career">${LoginUser.work}</span><br/>
                        <span class="interest">${LoginUser.hobby}</span>
                    </p>
                </div>
            </div>
        </div>
        <div class="item tool">
            <div class="title">
                <h3>我的</h3>
            </div>
            <div class="layui-fluid">
                <div class="layui-row">
                    <div class="layui-col-xs6 layui-col-sm3 layui-col-md3">
                        <div class="cont-box">
                            <img src='<c:url value="/layui/res/img/gr_img2.jpg"></c:url> '>
                            <p>
                                <a href='<c:url value="/paperController/userPaper?userId=${LoginUser.id}"></c:url> '>文章</a>
                            </p>
                        </div>
                    </div>
                    <div class="layui-col-xs6 layui-col-sm3 layui-col-md3">
                        <div class="cont-box">
                            <img src='<c:url value="/layui/res/img/gr_img3.jpg"></c:url> '>
                            <p>
                                <a href='<c:url value="/whisperController/init?userId=${LoginUser.id}"></c:url> '>微语</a>
                            </p>
                        </div>
                    </div>
                    <div class="layui-col-xs6 layui-col-sm3 layui-col-md3">
                        <div class="cont-box">
                            <img src='<c:url value="/layui/res/img/gr_img4.jpg"></c:url> '>
                            <p>
                                <a href='<c:url value="/albumController/init?userId=${LoginUser.id}"></c:url> '>相册</a>
                            </p>
                        </div>
                    </div>
                    <div class="layui-col-xs6 layui-col-sm3 layui-col-md3">
                        <div class="cont-box">
                            <img src='<c:url value="/layui/res/img/gr_img5.jpg"></c:url> '>
                            <p><a href='<c:url value="/admin/user/user-update.jsp" ></c:url> '>资料</a></p>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <div class="item tool">
            <div class="title">
                <h3>拓展</h3>
            </div>
            <div class="layui-fluid">
                <div class="layui-row">
                    <div class="layui-col-xs6 layui-col-sm3 layui-col-md3">
                        <div class="cont-box">
                            <img src='<c:url value="/layui/res/img/gr_img2.jpg"></c:url> '>
                            <p>
                                <a href='<c:url value="/fileload/html/file-add.jsp"></c:url> '>文件</a>
                            </p>
                        </div>
                    </div>
                    <div class="layui-col-xs6 layui-col-sm3 layui-col-md3">
                        <div class="cont-box">
                            <img src='<c:url value="/layui/res/img/gr_img3.jpg"></c:url> '>
                            <p>
                                <a href='<c:url value="/fileload/html/show-ip_action.jsp"></c:url> '>统计</a>
                            </p>
                        </div>
                    </div>
                    <div class="layui-col-xs6 layui-col-sm3 layui-col-md3">
                        <div class="cont-box">
                            <img src='<c:url value="/layui/res/img/gr_img4.jpg"></c:url> '>
                            <p>
                                <a href='<c:url value="/albumController/init?userId=${LoginUser.id}"></c:url> '>相册</a>
                            </p>
                        </div>
                    </div>
                    <div class="layui-col-xs6 layui-col-sm3 layui-col-md3">
                        <div class="cont-box">
                            <img src='<c:url value="/layui/res/img/gr_img5.jpg"></c:url> '>
                            <p><a href='<c:url value="/admin/user/user-update.jsp" ></c:url> '>资料</a></p>
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
                <img src='<c:url value="/layui/res/img/erweima.jpg"></c:url> '>
                <div class="text">
                    <p class="WeChat">微信：<span>${LoginUser.wx}</span></p>
                    <p class="qq">qq：<span>${LoginUser.qq}</span></p>
                    <p class="iphone">电话：<span>${LoginUser.phone}</span></p>
                </div>
            </div>
        </div>
    </div>
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
    $(document).ready(function () {
        $("#myWhisperButton").click(function () {
            alert("asdsf")
        });
    });
</script>
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