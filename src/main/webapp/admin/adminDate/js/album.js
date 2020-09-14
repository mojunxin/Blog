var baseURL = "http://111.229.42.116:80/blog";
// var baseURL = "http://localhost:8080/blog";
$(document).ready(function(){

    $("#AlbumTable").bootstrapTable({
        "toolbar" : "#AlbumToolbar",
        "url" : baseURL + "/album/list",
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
                title : "标题", // 列标题
                field : "title", // 属性值
                editable: {
                    type : "text",
                    title : "标题",
                    validate : function(v) {
                        if (v ==null) {
                            return "标题不能为空";
                        }
                    }
                }
            },
            {
                title : "地点", // 列标题
                field : "place"
            },
            {
                title : "创建者", // 列标题
                field : "createBy" // 属性值
            },
            {
                title : "创建时间",
                field : "createDate"
            },
            {
                title : "照片",
                field : "pic"
            }
        ],

        onEditableSave : function(field, row, oldValue){
            $.ajax({
                url : baseURL + "/album/update",
                type : "PUT",
                cache : false,
                contentType : "application/json",
                dataType : "json",
                data : JSON.stringify(row), // row == model
                success : function(responseVo) {
                    if (responseVo.code == 200) {
                        sweetAlert("恭喜", "修改成功", "success");
                        $("#AlbumTable").bootstrapTable("refresh"); // 刷新表格
                    }
                },
            });
        },
    });
    $("#deleteAlbumButton").click(function(){
        // 获取选中行的所有数据
        var rows = $("#AlbumTable").bootstrapTable("getSelections");
        var ids = "";
        for(var i = 0; i < rows.length; i++) {
            if (i != 0) {
                ids += ",";
            }
            ids +=  rows[i].id;
        } // 1,2,3,4
        $.ajax({
            url : baseURL + "/album/delete?ids=" + ids,
            type : "PUT",
            cache : false,
            success : function(responseVo) {
                if (responseVo.code == 200) { // 删除成功
                    $("#AlbumTable").bootstrapTable("refresh"); // 刷新表格
                }
            },
        })
    });


    $("#AddButton").click(function(){
        $.ajax({
            url: baseURL + '/album/save',
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
                    $('#SaveAlbumModal').modal('hide');
                    $("#AlbumTable").bootstrapTable("refresh");
                } else {
                    sweetAlert("抱歉", responseVo.message, "error");
                }
            }
        });
    });

});



























