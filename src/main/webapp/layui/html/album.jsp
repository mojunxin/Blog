<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>相册</title>
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
        <a href='<c:url value="/albumController/init" ></c:url> ' class="active">相册</a>
        <c:if test="${LoginUser.username==null}">
            <a href='<c:url value="/layui/html/about.jsp" ></c:url> '>关于</a>
        </c:if>
        <c:if test="${LoginUser.username!=null}">
            <a href='<c:url value="/admin/login/myself.jsp"></c:url> '>${LoginUser.username}-欢迎</a>
        </c:if>
    </div>
</div>
<div class="album-content w1000" id="layer-photos-demo" class="layer-photos-demo">
    <div class="img-info">
        <img src='<c:url value="${bastNewAlbum.pic}" ></c:url> ' style="width:620px;height:400px" >
        <div class="title">
            <p class="data">今日上传<span>${bastNewAlbum.createDate.substring(0,10)}</span></p>
            <p class="text">${bastNewAlbum.title}</p>
        </div>
    </div>
    <div class="layui-input-block" style="text-align: right;">
        <a href='<c:url value="/admin/user/album-add.jsp"></c:url> '><button class="layui-btn definite">上传</button></a>
    </div>
    <div class="img-list">
        <div class="layui-fluid" style="padding:0">
            <div class="layui-row layui-col-space30 space">

                <c:forEach items="${albumResponseVo.data.list}" var="album">
                    <div class="layui-col-xs12 layui-col-sm4 layui-col-md4">
                        <div class="item">
                            <img src='<c:url value="${album.pic}" ></c:url> ' style="width:310px;height:200px">
                            <div class="cont-text">
                                <div class="data">${album.createDate}</div>
                                <p class="address"><i
                                        class="layui-icon layui-icon-location"></i><span>${album.place}</span></p>
                                <p class="briefly">${album.title}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </div>
    </div>

    <div id="demo">
        <div id="layui-laypage-1" class="layui-box layui-laypage layui-laypage-default">
            <a href='<c:url value="/albumController/init?pageIndex=${albumResponseVo.data.pageIndex-1 }&pageSize=${albumResponseVo.data.pageSize }"></c:url>'
               class="layui-laypage-prev">
                上一页
            </a>
            <c:forEach begin="1" end="${albumResponseVo.data.pageCount }" var="i"> ${i}
                <a href='<c:url value="/albumController/init?pageIndex=${i}&pageSize=${albumResponseVo.data.pageSize }"></c:url>'>
                        ${i}</a>
            </c:forEach>
            <a href='<c:url value="/albumController/init?pageIndex=${albumResponseVo.data.pageIndex+1 }&pageSize=${albumResponseVo.data.pageSize }"></c:url>'
               class="layui-laypage-next">
                下一页
            </a>
        </div>
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
<script type="text/javascript" href='<c:url value="/layui/res/layui/layui.js" ></c:url> '/>
<script type="text/javascript">
    layui.config({
        base: '../res/js/util/'
    }).use(['element', 'laypage', 'form', 'layer', 'menu'], function () {
        element = layui.element, laypage = layui.laypage, form = layui.form, layer = layui.layer, menu = layui.menu;
        laypage.render({
            elem: 'demo'
            , count: 70 //数据总数，从服务端得到
        });
        layer.photos({
            photos: '#layer-photos-demo'
            , anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
            , tab: function (pic, layero) {
                console.log(pic, layero)
            }
        });
        menu.init();
    })
</script>
<div class="div" style="margin:0 auto; width:400px; height:100px; border:1px solid #FFF"><a href="http://beian.miit.gov.cn">备案：豫ICP备19045432号</a> </div>

</body>
</html>