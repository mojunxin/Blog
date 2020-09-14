var baseURL = "http://111.229.42.116:80/blog";
// var baseURL = "http://localhost:8080/blog";
$(document).ready(function () {

    $("#MessageTable").bootstrapTable({
        "toolbar": "#MessageToolbar",
        "url": baseURL + "/message/list",
        "method": "GET",
        "cache": false,
        "contentType": "application/json",
        "dataType": "json",

        "pagination": true,
        "sidePagination": "server",
        "pageNumber": 1,
        "pageSize": 10,
        "pageList": [4, 6, 50], // 可选页容量
        "queryParams": function (params) {
            return {
                "pageIndex": params.offset / params.limit + 1,
                "pageSize": params.limit
            }
        },

        "responseHandler": function (responseVo) {
            if (responseVo.code == 200) {
                return {
                    "rows": responseVo.data.list,
                    "total": responseVo.data.dateCount // 数据总条数
                };
            } else {
                alert(responseVo.message)
                window.location.href = baseURL + '/admin/login/html.jsp';
            }
        },
        "columns": [
            {
                checkbox: true
            }, {
                field: '',
                align: 'center',
                title: '序号',
                formatter: function (value, row, index) {
                    return index + 1;
                }
            },
            {
                title: "创建者", // 列标题
                field: "username" // 属性值
            },
            {
                title: "创建时间",
                field: "createDate"
            },
            {
                title: "微语Id",
                field: "whisperId"
            },
            {
                title: "文章Id",
                field: "paperId"
            },
            {
                title: "分类",
                field: "classCode"
            },
            {
                title: "点赞数",
                field: "num"
            },
            {
                title: "照片",
                field: "pic"
            },
            {
                title: "内容",
                field: "content"
            }
        ],

        onEditableSave: function (field, row, oldValue) {
            $.ajax({
                url: baseURL + "/message/update",
                type: "PUT",
                cache: false,
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(row), // row == model
                success: function (responseVo) {
                    if (responseVo.code == 200) {
                        sweetAlert("恭喜", "修改成功", "success");
                        $("#MessageTable").bootstrapTable("refresh"); // 刷新表格
                    }
                },
            });
        },
    });
    $("#deleteMessageButton").click(function () {
        // 获取选中行的所有数据
        var rows = $("#MessageTable").bootstrapTable("getSelections");
        var ids = "";
        for (var i = 0; i < rows.length; i++) {
            if (i != 0) {
                ids += ",";
            }
            ids += rows[i].id;
        } // 1,2,3,4
        $.ajax({
            url: baseURL + "/message/delete?ids=" + ids,
            type: "PUT",
            cache: false,
            success: function (responseVo) {
                if (responseVo.code == 200) { // 删除成功
                    $("#MessageTable").bootstrapTable("refresh"); // 刷新表格
                }
            },
        })
    });


    $("#AddButton").click(function () {
        //var formData = new FormData($("#addForm")[0]);
        $.ajax({
            url: baseURL + '/message/save',
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
                    $('#SaveMessageModal').modal('hide');
                    $("#MessageTable").bootstrapTable("refresh");
                } else {
                    sweetAlert("抱歉", responseVo.message, "error");
                }
            }
        });
    });

});

