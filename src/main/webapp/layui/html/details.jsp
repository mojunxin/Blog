<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>详情</title>
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
            <a href='<c:url value="/layui/html/about.jsp"  ></c:url> ' class="active">关于</a>
        </c:if>
        <c:if test="${LoginUser.username!=null}">
            <a href='<c:url value="/admin/login/myself.jsp"></c:url> '>${LoginUser.username}-欢迎</a>
        </c:if>
    </div>
</div>
<div class="content whisper-content leacots-content details-content">
    <div class="cont w1000">
        <div class="whisper-list">
            <div class="item-box">
                <div class="review-version">
                    <div class="form-box">
                        <div class="article-cont" align="center">
                            <div class="title">
                                <h3>${paper.title}</h3>
                                <p class="cont-info"><span class="data">${paper.createDate.substring(0,10)}</span><span
                                        class="types">${paper.classCode}</span></p>
                            </div>
                            <p>${paper.content}</p>
                            <img src='<c:url value="${paper.pic}"></c:url> ' style="width:700px;height:600px" >
                            <div class="btn-box">

                                <c:if test="${firstPage==null}">
                                    <a href='<c:url value="/paperController/detail?id=previous"></c:url> '
                                       class="layui-btn layui-btn-primary">上一篇</a>
                                </c:if>
                                <c:if test="${firstPage!=null}">
                                    <button class="layui-btn layui-btn-primary">${firstPage}</button>
                                </c:if>
                                <c:if test="${lastPage==null}">
                                    <a href='<c:url value="/paperController/detail"></c:url> '
                                       class="layui-btn layui-btn-primary">下一篇</a>
                                </c:if>
                                <c:if test="${lastPage!=null}">
                                    <button class="layui-btn layui-btn-primary">${lastPage}</button>
                                </c:if>

                            </div>
                        </div>
                        <div class="form">
                            <form class="layui-form" action="">
                                <div class="layui-form-item layui-form-text">
                                    <div class="layui-input-block">
                                        <textarea name="desc" placeholder="既然来了，就说几句" class="layui-textarea"></textarea>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-input-block" style="text-align: right;">
                                        <button class="layui-btn definite">確定</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="volume">全部留言 <span>${msgDataCount}</span>
                    </div>
                    <div class="list-cont">

                        <c:forEach items="${messageList}" var="message">
                            <div class="cont">
                                <div class="img">
                                    <img src='<c:url value="${message.pic}"></c:url> ' style="width:50px;height:50px" >
                                </div>
                                <div class="text">
                                    <p class="tit"><span class="name">${message.username}</span><span
                                            class="data">${message.createDate}</span></p>
                                    <p class="ct">${message.content}</p>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>
            </div>
        </div>
        <div id="demo" style="text-align: center;"></div>
    </div>
</div>
<script type="text/html" id="laytplCont">
    <div class="cont">
        <div class="img">
            {{# if(d.avatar){ }}
            <img src="{{d.avatar}}" alt="">
            {{# } else { }}
            <img src="../res/img/header.png" alt="">
            {{# } }}
        </div>
        <div class="text">
            <p class="tit"><span class="name">{{d.name}}</span><span class="data">2018/06/06</span></p>
            <p class="ct">{{d.cont}}</p>
        </div>
    </div>
</script>
<div class="footer-wrap">
    <div class="footer w1000">
        <div class="qrcode">
            <img src='<c:url value="/layui/res/img/erweima.jpg" ></c:url> '  style="width:200px;height:200px">
        </div>
        <div class="practice-mode">
            <img src='<c:url value="/layui/res/img/down_img.jpg" ></c:url> '/>
            <div class="text">
                <h4 class="title">我的联系方式</h4>
                <p>微信<span class="WeChat">mjx_up</span></p>
                <p>手机<span class="iphone">188****1101</span></p>
                <p>邮箱<span class="email">820517963@qq.com</span></p>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" href='<c:url value="/layui/res/layui/layui.js" ></c:url> '/>
<script type="text/javascript">
    layui.config({
        base: '../res/js/util/'
    }).use(['element', 'laypage', 'form', 'menu'], function () {
        element = layui.element, laypage = layui.laypage, form = layui.form, menu = layui.menu;
        laypage.render({
            elem: 'demo'
            , count: 70 //数据总数，从服务端得到
        });
        menu.init();
        menu.submit();
    })

    function click() {
        alert("123")
    }

</script>
<div class="div" style="margin:0 auto; width:400px; height:100px; border:1px solid #FFF"><a href="http://beian.miit.gov.cn">备案：豫ICP备19045432号</a> </div>

</body>
</html>