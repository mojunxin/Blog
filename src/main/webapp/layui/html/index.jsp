<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>文章</title>
    <link rel="stylesheet" href='<c:url value="/layui/res/layui/css/layui.css" ></c:url> '>
    <link rel="stylesheet" href='<c:url value="/layui/res/css/main.css" ></c:url> '>
    <!--加载meta IE兼容文件-->
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <!--[if lt IE 9]>

    <![endif]-->
</head>
<body>
<div class="header">
    <div class="menu-btn">
        <div class="menu"></div>
    </div>
    <h1 class="logo">
        <c:if test="${LoginUser==null}">
            <a href='<c:url value="/admin/login/html.jsp"></c:url> '>
                <span>MYBLOG</span>
                <img src='<c:url value="/layui/res/img/logo.png" ></c:url> '>
            </a>
        </c:if>
        <c:if test="${LoginUser!=null}">
            <a href='<c:url value="/layui/html/index.jsp" ></c:url> '>
                <span>MYBLOG</span>
                <img src='<c:url value="/layui/res/img/logo.png" ></c:url> '>
            </a>
        </c:if>
    </h1>
    <div class="nav">
        <a href='<c:url value="/paperController/init" ></c:url> ' class="active">文章</a>
        <a href='<c:url value="/whisperController/init" ></c:url> '>微语</a>
        <a href='<c:url value="/messageController/init" ></c:url> '>留言</a>
        <a href='<c:url value="/albumController/init" ></c:url> '>相册</a>
        <c:if test="${LoginUser.username==null}">
            <a href='<c:url value="/layui/html/about.jsp" ></c:url> '>关于</a>
        </c:if>
        <c:if test="${LoginUser!=null}">
            <a href='<c:url value="/admin/login/myself.jsp"></c:url> '>${LoginUser.username}-欢迎</a>
        </c:if>
    </div>
    <ul class="layui-nav header-down-nav">
     <li class="layui-nav-item"><a href="index.html" class="active">文章</a></li>
     <li class="layui-nav-item"><a href="whisper.html">微语</a></li>
     <li class="layui-nav-item"><a href="leacots.html">留言</a></li>
     <li class="layui-nav-item"><a href="album.html">相册</a></li>
     <li class="layui-nav-item"><a href="about.html">关于</a></li>
   </ul>
</div>

<div class="banner">
    <div class="cont w1000">
        <div class="title">
            <h3>MY<br/>BLOG</h3>
            <h4>well-balanced heart</h4>
        </div>
        <div class="amount">
            <div class="h">
                <%-- 记录网站访问次数 --%>
                <%
                    Integer counter = (Integer)application.getAttribute("counter"); //先从application里面获取计数器的key的值
                    if(counter==null){
                        //如果该值为null，说明第一次访问
                        application.setAttribute("counter",1);
                        //counter=(Integer)application.getAttribute("counter");
                    }else {
                        //如果该值不为空，取出来进行累加
                        int i = counter.intValue();
                        i++;
                        application.setAttribute("counter",i);//累加后再放进去
                    }
                %>
            </div>
            <p><span class="text">访问量</span><span class="access">${counter}</span></p>
            <p><span class="text">日志</span><span class="daily-record">${dataCount}</span></p>
        </div>
    </div>
</div>
<div class="content">
    <div class="cont w1000">
        <div class="list-item">
            <div class="layui-form-item">
                <div class="layui-input-block" style="text-align: right;">
                    <a href='<c:url value="/admin/user/paper-add.jsp"></c:url> '>
                        <button class="layui-btn definite">发布</button>
                    </a>
                </div>
            </div>
            <c:forEach items="${paperResponseVo.data.list}" var="paper">
                <div class="item">
                    <div class="layui-fluid">
                        <div class="layui-row">
                            <div class="layui-col-xs12 layui-col-sm4 layui-col-md5">
                                <div class="img">
                                    <img src='<c:url value="${paper.pic}" ></c:url> ' style="width:240px;height:240px">
                                </div>
                            </div>
                            <div class="layui-col-xs12 layui-col-sm8 layui-col-md7">
                                <div class="item-cont">
                                    <h3>${paper.title}
                                        <button class="layui-btn layui-btn-danger new-icon">
                                            <%
                                                Date date = new Date();
                                                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                                                String format = ft.format(date);
                                                request.setAttribute("date", format);
                                            %>
                                            <c:choose>
                                                <c:when test="${paper.createDate.startsWith(date)}">new</c:when>
                                                <c:when test="${!paper.createDate.startsWith(date)}">old</c:when>
                                            </c:choose>
                                        </button>
                                    </h3>

                                    <c:if test="${designPaper!=null&&LoginUser.id==paper.userId}">
                                        <h5><a href='<c:url value="/paperController/goUpdate?id=${paper.id}"></c:url>'>设计文章</a>
                                        </h5>
                                        <h5><a href='<c:url value="/paperController/doDelete?id=${paper.id}"></c:url>'>删除</a>
                                        </h5>
                                    </c:if>
                                    <c:if test="${designPaper==null}">
                                        <h5>设计文章</h5>
                                    </c:if>

                                    <p>${paper.content}...</p>
                                    <p align="right">${paper.createDate.substring(0,10)}</p>
                                    <a href='<c:url value="/paperController/detail?id=${paper.id}"></c:url> '
                                       class="go-icon"></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <div id="demo" style="text-align: center;"></div>
        </div>
    </div>

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
</div>
<script type="text/javascript" href='<c:url value="/layui/res/layui/layui.js" ></c:url> '></script>

<script type="text/javascript">
    layui.config({
        base: '../res/js/util/'
    }).use(['element', 'laypage', 'jquery', 'menu'], function () {
        element = layui.element, laypage = layui.laypage, $ = layui.$, menu = layui.menu;
        laypage.render({
            elem: 'demo'
            , count: 70 //数据总数，从服务端得到
        });
        menu.init();
    })
</script>
<div class="div" style="margin:0 auto; width:400px; height:100px; border:1px solid #FFF"><a href="http://beian.miit.gov.cn">备案：豫ICP备19045432号</a> </div>

</body>
</html>