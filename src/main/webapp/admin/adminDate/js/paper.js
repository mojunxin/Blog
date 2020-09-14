var baseURL = "http://111.229.42.116:80/blog";
// var baseURL = "http://localhost:8080/blog";
$(document).ready(function(){

    $("#PaperTable").bootstrapTable({
        "toolbar" : "#PaperToolbar",
        "url" : baseURL + "/paper/list",
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
                title : "创建者", // 列标题
                field : "createBy" // 属性值
            },
            {
                title : "创建时间",
                field : "createDate"
            },
            {
                title : "内容",
                field : "content",
                editable: {
                    type : "text",
                    title : "内容",
                    validate : function(v) {
                        if (v ==null) {
                            return "内容不能为空";
                        }
                    }
                }
            },
            {
                title : "分类",
                field : "classCode"
            },
            {
                title : "是否显示",
                field : "isShow",
                editable: {
                    type : "text",
                    title : "是否显示",
                    validate : function(v) {
                        if (v ==null) {
                            return "不能为空";
                        }
                    }
                }
            },
            {
                title : "照片",
                field : "pic"
            }
        ],

        onEditableSave : function(field, row, oldValue){
            $.ajax({
                url : baseURL + "/paper/update",
                type : "PUT",
                cache : false,
                contentType : "application/json",
                dataType : "json",
                data : JSON.stringify(row), // row == model
                success : function(responseVo) {
                    if (responseVo.code == 200) {
                        sweetAlert("恭喜", "修改成功", "success");
                        $("#PaperTable").bootstrapTable("refresh"); // 刷新表格
                    }
                },
            });
        },
    });
    $("#deletePaperButton").click(function(){
        // 获取选中行的所有数据
        var rows = $("#PaperTable").bootstrapTable("getSelections");
        var ids = "";
        for(var i = 0; i < rows.length; i++) {
            if (i != 0) {
                ids += ",";
            }
            ids +=  rows[i].id;
        } // 1,2,3,4
        $.ajax({
            url : baseURL + "/paper/delete?ids=" + ids,
            type : "PUT",
            cache : false,
            success : function(responseVo) {
                if (responseVo.code == 200) { // 删除成功
                    $("#PaperTable").bootstrapTable("refresh"); // 刷新表格
                }
            },
        })
    });

});



























