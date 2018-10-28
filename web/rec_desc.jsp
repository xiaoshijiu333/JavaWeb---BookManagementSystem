<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String contextPath = request.getContextPath();
    pageContext.setAttribute("ctx",contextPath);
%>
<!DOCTYPE html>
<html>
<head>
    <title>${admin.uname}的主页</title>
    <link href="images/title.ico" rel="shortcut icon">
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/rec_desc.css">
</head>
<body>

<div id="admin_rec_header">
    <div class="header_center">
        <img src="images/title.png">
        <p>欢迎${admin.uname}使用图书管理系统</p>
    </div>
</div>

<div id="rec_desc_center">
    <div class="desc_center_center">
        <p>记录详情</p>
        <form action="${ctx}/AdminRecServlet?action=ReturnBook" method="post" id="form_rec">
            <div class="name_input">
                <label>书本名称:</label>
                <input type="text" value="${rec.dname}" readonly="readonly" name="dname">
            </div>
            <div class="lend_time_input">
                <label>借阅时间:</label>
                <input type="text" value="${rec.lendtime}" readonly="readonly" >
            </div>
            <div class="return_time_input">
                <label>应还时间:</label>
                <input type="text" readonly="readonly" value="${rec.shouldtime}">
            </div>
            <div class="is_return_input">
                <label>归还时间:</label>
                <input type="text" readonly="readonly" id="returntime" value="${rec.returntime}">
            </div>

            <%--隐藏一个input用于发送id--%>
            <input value="${rec.id}" name="id" style="display: none">

            <div class="desc_button">
                <input type="button" value="还书" id="return_button">
                <input type="button" value="返回" onclick="Goback(-1)">
            </div>
        </form>

        <%--借书成功信息回显用的，避免js语法错误,并直接跳转到首页--%>
        <form action="${ctx}/AdminRecServlet?action=AllRecords&numble=1" id="form_success" method="post">
            <input value="${success}" style="display: none" id="success">
        </form>

    </div>
</div>

<script src="js/jquery.main.js"></script>

<script>

    //返回按钮
    function Goback(num) {
        window.history.back(num);
    }

    //对归还时间显示的设定
    if($("#returntime").val()==""){
        $("#returntime").val("未归还");
    }

    //对是否需要归还的判定
    $("#return_button").click(
        function () {
            if($("#returntime").val()!="未归还"){
                alert("该书已经归还！！")
            }
            else{
                var set=confirm("是否确认还书？");
                if(set==true){
                    $("#form_rec").submit();
                }
            }
        }
    )

    //用js辅助信息提示，并跳转
    if($("#success").val()!=""){
        alert("${success}")
        $("#form_success").submit();
    }

</script>

</body>
</html>
