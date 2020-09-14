<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>Document</title>
    <link rel="stylesheet" href='<c:url value="/admin/layui/css/layui.css" ></c:url> '>
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
        <a href='<c:url value="/admin/login/myself.jsp"></c:url> '>${LoginUser.username}-欢迎</a>
    </div>
</div>

<div class="layui-form-item">
    <label class="layui-form-label"></label>
    <div class="layui-input-block">
        <form method="post" action='<c:url value="/paperController/doUpdate"></c:url> '
              enctype="multipart/form-data">
            <input type="text" style="visibility: hidden;" name="id" value="${updatePaper.id}">
            <input type="text" style="visibility: hidden;" name="createDate" value="${updatePaper.createDate}">
            <input type="text" style="visibility: hidden;" name="classCode" value="${updatePaper.classCode}">

            <div class="layui-form-item">
                <label class="layui-form-label">标题</label>
                <div class="layui-input-block">
                    <input type="text" name="title" required lay-verify="required" placeholder="请输入标题"
                           value="${updatePaper.title}" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">创建时间</label>
                <div class="layui-input-block">
                    <br/>
                    ${updatePaper.createDate}
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">分类</label>
                <div class="layui-input-block">
                    <br/>
                    ${updatePaper.classCode}
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">是否显示</label>
                <div class="layui-input-block">
                <br/>
                    <select name="isShow">
                        <option value="Y">是</option>
                        <option value="N">否</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">文本域</label>
                <div class="layui-input-block">
                    <textarea name="content" placeholder="请输入内容"
                              class="layui-textarea">${updatePaper.content}</textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">图片</label>
                <div class="layui-input-block">
                    <img src='<c:url value="${updatePaper.pic}"></c:url> ' style="width:300px;higth:300px">
                    <input type="file" name="file" >
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
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
            <img src='<c:url value="/layui/res/img/erweima.jpg" ></c:url> ' style="width:200px;height:200px">
        </div>
        <div class="practice-mode">
            <img src='<c:url value="/layui/res/img/down_img.jpg" ></c:url> '/>
            <div class="text">
                <h4 class="title">我的联系方式</h4>
                <p>微信<span class="WeChat">1234567890</span></p>
                <p>手机<span class="iphone">1234567890</span></p>
                <p>邮箱<span class="email">1234567890@qq.com</span></p>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" href='<c:url value="/admin/layui/layui.js" ></c:url> '/>
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