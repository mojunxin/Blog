<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>Document</title>

    <link rel="stylesheet" href="../layui/css/layui.css" media="all">
    <link rel="stylesheet" href='<c:url value="/layui/res/css/main.css" ></c:url> '>
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

<div class="content whisper-content leacots-content">
    <div class="cont w1000">
        <div class="whisper-list">
            <div class="item-box">
                <div class="review-version">
                    <table id="ipAction" lay-filter="test1"></table>
                </div>
                <div class="review-version">
                    <table id="ipActionCount" lay-filter="test"></table>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="../layui/layui.js"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
            elem: '#ipAction'
            ,height: 312
            ,url: '/blog/ipAction/list' //数据接口
            ,response: {
                statusCode: 200 //规定成功的状态码，默认：0
            }
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
                ,{field: 'ip', title: '访问ip', width:180}
                ,{field: 'action', title: '访问路劲', width:180}
                ,{field: 'code', title: '状态码', width:80}
                ,{field: 'place', title: 'ip地理位置', width: 180}
                ,{field: 'createDate', title: '创建时间', width: 180}
            ]]
        });
    });

    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
            elem: '#ipActionCount'
            ,height: 312
            ,url: '/blog/ipActionCount/list' //数据接口
            ,response: {
                statusCode: 200 //规定成功的状态码，默认：0
            }
            ,page: true //开启分页
            ,cols: [[
                ,{field: 'ip', title: '访问ip', width:127}
                ,{field: 'count', title: '点击次数', width:180}
                ,{field: 'create_date', title: '统计日期', width:180}
            ]]
        });
    });
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
<div class="div" style="margin:0 auto; width:400px; height:100px; border:1px solid #FFF"><a href="http://beian.miit.gov.cn">备案：豫ICP备19045432号</a> </div>

</body>
</html>