var baseURL = "http://111.229.42.116:80/blog";
// var baseURL = "http://localhost:8080/blog";
$(document).ready(function () {

    $("#WhisperTable").bootstrapTable({
        "toolbar": "#WhisperToolbar",
        "url": baseURL + "/whisper/list",
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
                title: "昵称", // 列标题
                field: "username"
            },
            {
                title: "创建时间",
                field: "createDate"
            },
            {
                title: "分类",
                field: "classCode"
            },
            {
                title: "内容",
                field: "content",
                editable: {
                    type: "text",
                    title: "内容",
                    validate: function (v) {
                        if (v == null) {
                            return "内容不能为空";
                        }
                    }
                }
            },
            {
                title: "是否显示",
                field: "isShow",
                editable: {
                    type: "text",
                    title: "是否显示",
                    validate: function (v) {
                        if (v == null) {
                            return "不能为空";
                        }
                    }
                }
            },
            {
                title: "点赞数",
                field: "clickNum"
            },
            {
                title: "留言数",
                field: "msgNum"
            },
            {
                title: "照片",
                field: "pic"
            }
        ],

        onEditableSave: function (field, row, oldValue) {
            $.ajax({
                url: baseURL + "/whisper/update",
                type: "PUT",
                cache: false,
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(row), // row == model
                success: function (responseVo) {
                    if (responseVo.code == 200) {
                        sweetAlert("恭喜", "修改成功", "success");
                        $("#WhisperTable").bootstrapTable("refresh"); // 刷新表格
                    }
                },
            });
        },
    });
    $("#deleteWhisperButton").click(function () {
        // 获取选中行的所有数据
        var rows = $("#WhisperTable").bootstrapTable("getSelections");
        var ids = "";
        for (var i = 0; i < rows.length; i++) {
            if (i != 0) {
                ids += ",";
            }
            ids += rows[i].id;
        } // 1,2,3,4
        $.ajax({
            url: baseURL + "/whisper/delete?ids=" + ids,
            type: "PUT",
            cache: false,
            success: function (responseVo) {
                if (responseVo.code == 200) { // 删除成功
                    $("#WhisperTable").bootstrapTable("refresh"); // 刷新表格
                }
            },
        })
    });


    $("#AddButton").click(function () {
        //var formData = new FormData($("#addForm")[0]);
        $.ajax({
            url: baseURL + '/whisper/save',
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
                    $('#SaveWhisperModal').modal('hide');
                    $("#WhisperTable").bootstrapTable("refresh");
                } else {
                    sweetAlert("抱歉", responseVo.message, "error");
                }
            }
        });
    });

});



























