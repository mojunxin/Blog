<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <title>博客后台监控</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-table.min.css" rel="stylesheet">
    <link href="css/bootstrap-editable.css" rel="stylesheet">
    <link href="css/sweetalert2.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>博客
            <small>后台管理</small>
        </h1>
    </div>
    <div class="btn-group" role="group" aria-label="...">
        <button type="button" class="btn btn-default"><a href='<c:url value="/index.jsp" ></c:url> '>首页</a></button>
    </div>
    <p></p>
    <ul class="nav nav-tabs">
        <li role="presentation"><a href='<c:url value="/admin/adminDate/user.jsp" ></c:url> '>用户</a></li>
        <li role="presentation" class="active"><a href='<c:url value="/admin/adminDate/paper.jsp" ></c:url> '>文章</a></li>
        <li role="presentation"><a href='<c:url value="/admin/adminDate/whisper.jsp" ></c:url> '>微语</a></li>
        <li role="presentation"><a href='<c:url value="/admin/adminDate/message.jsp" ></c:url> '>留言</a></li>
        <li role="presentation"><a href='<c:url value="/admin/adminDate/album.jsp" ></c:url> '>相册</a></li>
    </ul>

    <table id="PaperTable"></table>
    <div class="btn-group" role="group" aria-label="..." id="PaperToolbar">
        <div class="btn-group" role="group" aria-label="...">
            <button type="button" class="btn btn-default" id="deletePaperButton">删除</button>
        </div>
    </div>
</div>

<script src='<c:url value="/admin/js/jquery-3.3.1.min.js" ></c:url> '></script>
<script src='<c:url value="/admin/js/bootstrap.js" ></c:url> '></script>
<script src='<c:url value="/admin/js/bootstrap-table.min.js" ></c:url> '></script>
<script src='<c:url value="/admin/js/bootstrap-table-zh-CN.min.js" ></c:url> '></script>
<script src='<c:url value="/admin/js/bootstrap-editable.min.js" ></c:url> '></script>
<script src='<c:url value="/admin/js/bootstrap-table-editable.min.js" ></c:url> '></script>
<script src='<c:url value="/admin/js/sweetalert2.all.js" ></c:url> '></script>
<script src='<c:url value="/admin/adminDate/js/paper.js" ></c:url> '></script>
</body>
</html>