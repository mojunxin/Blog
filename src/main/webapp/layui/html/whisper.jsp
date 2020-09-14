<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>微语</title>
    <link rel="stylesheet" href='<c:url value="/layui/res/layui/css/layui.css" ></c:url> '>
    <link rel="stylesheet" href='<c:url value="/layui/res/css/main.css" ></c:url> '>
    <link href='<c:url value="/admin/adminDate/css/bootstrap.min.css" ></c:url> '>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!--加载meta IE兼容文件-->
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
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
        <a href='<c:url value="/whisperController/init" ></c:url> ' class="active">微语</a>
        <a href='<c:url value="/messageController/init" ></c:url> '>留言</a>
        <a href='<c:url value="/albumController/init" ></c:url> '>相册</a>
        <c:if test="${LoginUser.username==null}">
            <a href='<c:url value="/layui/html/about.jsp" ></c:url> '>关于</a>
        </c:if>
        <c:if test="${LoginUser.username!=null}">
            <a href='<c:url value="/admin/login/myself.jsp"></c:url> '>${LoginUser.username}-欢迎</a>
        </c:if>
    </div>
</div>
<div class="content whisper-content">
    <div class="cont">
        <div class="layui-input-block" style="text-align: right;">
            <a href='<c:url value="/admin/user/whisper-add.jsp"></c:url> '>
                <button class="layui-btn definite">上传</button>
            </a>
        </div>


        <br/>
        <div class="whisper-list">
            <c:forEach items="${whisperResponseVo.data.list}" var="whisper">
                <div class="item-box">
                    <div class="item">
                        <div class="whisper-title">
                            <i class="layui-icon layui-icon-date"></i>
                            <span class="hour">${whisper.createDate.substring(11,16)}</span>
                            <span class="date">${whisper.createDate.substring(0,10)}</span>
                        </div>
                        <p class="text-cont">${whisper.content}</p>
                        <div class="img-box">
                            <a href='<c:url value="/whisperController/goDetail?id=${whisper.id}"></c:url> '>
                                <img src='<c:url value="${whisper.pic}"></c:url> ' style="width:310px;height:200px">
                            </a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <c:if test="${whisperResponseVo!=null}">
            <div id="layui-laypage-1" class="layui-box layui-laypage layui-laypage-default">
                <a href='<c:url value="/whisperController/init?pageIndex=${whisperResponseVo.data.pageIndex-1 }&pageSize=${whisperResponseVo.data.pageSize }"></c:url>'
                   class="layui-laypage-prev">
                    上一页
                </a>
                <c:forEach begin="1" end="${whisperResponseVo.data.pageCount }" var="i"> ${i}
                    <a href='<c:url value="/whisperController/init?pageIndex=${i}&pageSize=${whisperResponseVo.data.pageSize }"></c:url>'>
                            ${i}</a>
                </c:forEach>
                <a href='<c:url value="/whisperController/init?pageIndex=${whisperResponseVo.data.pageIndex+1 }&pageSize=${whisperResponseVo.data.pageSize }"></c:url>'
                   class="layui-laypage-next">
                    下一页
                </a>
            </div>
        </c:if>


    </div>
</div>

<script type="text/html" id="laytplCont">
    <div class="cont">
        <div class="img">
            <img src="{{d.avatar}}" alt="">
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
            <img src='<c:url value="/layui/res/img/erweima.jpg" ></c:url> ' style="width:200px;height:200px">
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
        menu.off();
        menu.submit()
    })
</script>
<script src='<c:url value="/admin/js/bootstrap.js" ></c:url> '></script>
<script src='<c:url value="/admin/js/jquery-3.3.1.min.js" ></c:url> '></script>
<div class="div" style="margin:0 auto; width:400px; height:100px; border:1px solid #FFF"><a href="http://beian.miit.gov.cn">备案：豫ICP备19045432号</a> </div>

</body>
</html>