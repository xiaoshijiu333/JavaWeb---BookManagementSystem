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
    <link rel="stylesheet" href="css/book_desc.css">
</head>
<body>

<div id="admin_book_header">
    <div class="header_center">
        <img src="images/title.png">
        <p>欢迎${admin.uname}使用图书管理系统</p>
    </div>
</div>

<div id="book_desc_center">
    <div class="desc_center_center">
        <p>书本详情</p>
        <form action="${ctx}/AdminBookServlet?action=LendBook"  method="post" id="form_book">
            <div class="name_input">
                <label>书本名称:</label>
                <input type="text" value="${book.dname}" readonly="readonly" name="dname">
            </div>
            <div class="lend_count_input">
                <label>可借副本:</label>
                <input type="text" value="${book.have_count}" readonly="readonly" id="havecount">
            </div>
            <div class="desc_input">
                <label>内容简介:</label>
                <textarea readonly="readonly">${book.book_desc}</textarea>
            </div>
            <div class="desc_button">
                <input type="button" value="借书" id="lend_button">
                <input type="button" value="返回" onclick="Goback(-1)">
            </div>
        </form>

        <%--借书成功信息回显用的，避免js语法错误,并直接跳转到首页--%>
        <form action="${ctx}/AdminBookServlet?action=PageBooks&numble=1" id="form_success" method="post">
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

    //借书提交表单之前，对可借副本进行判断
    $("#lend_button").click(
        function () {
            if($("#havecount").val()==0){
                alert("该书没有多余的可以借了！！")
            }
            else {
                var set=confirm("是否确认借书？");
                if(set==true){
                    $("#form_book").submit();
                }
            }
    })

   //用js辅助可以完成很多事情，信息提示，跳转首页
    if($("#success").val()!=""){
        alert("${success}")
        $("#form_success").submit();
    }

</script>

</body>
</html>
