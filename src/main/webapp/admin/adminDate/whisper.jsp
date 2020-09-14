<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
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
        <li role="presentation"><a href='<c:url value="/admin/adminDate/paper.jsp" ></c:url> '>文章</a></li>
        <li role="presentation" class="active"><a href='<c:url value="/admin/adminDate/whisper.jsp" ></c:url> '>微语</a></li>
        <li role="presentation"><a href='<c:url value="/admin/adminDate/message.jsp" ></c:url> '>留言</a></li>
        <li role="presentation"><a href='<c:url value="/admin/adminDate/album.jsp" ></c:url> '>相册</a></li>
    </ul>

    <table id="WhisperTable"></table>
    <div class="btn-group" role="group" aria-label="..." id="WhisperToolbar">
        <div class="btn-group" role="group" aria-label="...">
            <button type="button" class="btn btn-default" id="deleteWhisperButton">删除</button>
            <button type="button" class="btn btn-default"
                    data-toggle="modal" data-target="#SaveWhisperModal">添加
            </button>
        </div>
    </div>
</div>


<div class="modal fade" id="SaveWhisperModal" tabindex="-1" role="dialog" aria-labelledby="SaveWhisperModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">添加</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="Account" class="control-label">账号:</label>
                        <input type="text" class="form-control" id="Account">
                    </div>
                    <div class="form-group">
                        <label for="Password" class="control-label">密码:</label>
                        <input type="password" class="form-control" id="Password">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="AddButton">添加</button>
            </div>
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
<script src='<c:url value="/admin/adminDate/js/whisper.js" ></c:url> '></script>
</body>
</html>