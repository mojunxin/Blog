var baseURL = "http://111.229.42.116:80/blog";
// var baseURL = "http://localhost:8080/blog";
$(document).ready(function(){
    $("#UserTable").bootstrapTable({
        "toolbar" : "#UserToolbar",
        "url" : baseURL + "/user/list",
        "method" : "GET",
        "cache" : false,
        "contentType" : "application/json",
        "dataType" : "json",

        "pagination" : true,
        "sidePagination" : "server",
        "pageNumber" : 1,
        "pageSize" : 10,
        "pageList" : [4, 6, 50], // 可选页容量
        "queryParams" : function(params) {
            return {
                "pageIndex" : params.offset / params.limit + 1,
                "pageSize" : params.limit
            }
        },

        "responseHandler" : function(responseVo) {
            if (responseVo.code == 200) {

                return {
                    "rows" : responseVo.data.list,
                    "total" : responseVo.data.dateCount // 数据总条数
                };
            }else {
                alert(responseVo.message)
                window.location.href = baseURL+'/admin/login/html.jsp';
            }
        },
        "columns" : [
            {
                checkbox : true
            },{
                field : '',
                align : 'center',
                title : '序号',
                formatter: function (value, row, index) {
                    return index+1;
                }
            },
            {
                title : "昵称", // 列标题
                field : "username"
            },{
                title : "账号", // 列标题
                field : "account"
            },{
                title : "密码",
                field : "password"
            },{
                title : "性别",
                field : "sex"
            },{
                title : "年龄",
                field : "age"
            },{
                title : "工作",
                field : "work"
            },{
                title : "分类",
                field : "classCode"
            },
            {
                title : "微信",
                field : "wx"
            },{
                title : "qq",
                field : "qq"
            },{
                title : "爱好",
                field : "hobby"
            },{
                title : "电话",
                field : "phone"
            },{
                title : "照片",
                field : "pic"
            }
        ],
        onEditableSave : function(field, row, oldValue){
            $.ajax({
                url : baseURL + "/user/update",
                type : "PUT",
                cache : false,
                contentType : "application/json",
                dataType : "json",
                data : JSON.stringify(row), // row == model
                success : function(responseVo) {
                    if (responseVo.code == 200) {
                        sweetAlert("恭喜", "修改成功", "success");
                        $("#UserTable").bootstrapTable("refresh"); // 刷新表格
                    }
                },
            });
        },
    });

    $("#deleteUserButton").click(function(){
        // 获取选中行的所有数据
        var rows = $("#UserTable").bootstrapTable("getSelections");
        var ids = "";
        for(var i = 0; i < rows.length; i++) {
            if (i != 0) {
                ids += ",";
            }
            ids +=  rows[i].id;
        } // 1,2,3,4
        $.ajax({
            url : baseURL + "/user/delete?ids=" + ids,
            type : "PUT",
            cache : false,
            success : function(responseVo) {
                if (responseVo.code == 200) { // 删除成功
                    $("#UserTable").bootstrapTable("refresh"); // 刷新表格
                }
            },
        })
    });


    $("#AddButton").click(function(){
        //var formData = new FormData($("#addForm")[0]);
        $.ajax({
            url: baseURL + '/user/save',
            method: 'POST',
            cache: false,
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify({
                'account': $('#Account').val(),
                'password': $('#Password').val()
            }),
            success: function (responseVo) {
                if (responseVo.code == 200) {
                    // 1. 弹出提示框
                    sweetAlert("恭喜", "添加成功", "success");
                    // 2. 收回登录模态框
                    $('#SaveUserModal').modal('hide');
                    $("#UserTable").bootstrapTable("refresh");
                } else {
                    sweetAlert("抱歉", responseVo.message, "error");
                }
            }
        });
    });

});



























