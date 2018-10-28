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
    <link rel="stylesheet" href="css/ManaBook_desc.css">
</head>
<body>

<div id="ManaBook_book_header">
    <div class="header_center">
        <img src="images/title.png">
        <p>欢迎${admin.uname}进入图书管理系统后台管理员界面</p>
    </div>
</div>

<div id="book_desc_center">
    <div class="desc_center_center">
        <p>更新操作</p>
        <form action="${ctx}/ManagerBookServlet?action=UpdateBook" method="post" id="form_book">
            <div class="name_input">
                <label>书本名称:</label>
                <input type="text" value="${book.dname}" name="dname">
            </div>

            <div class="writer_input">
                <label>作者/出版社:</label>
                <input type="text" value="${book.writer}" name="writer">
            </div>

            <div class="position_input">
                <label>馆藏地:</label>
                <input type="text"  value="${book.position}" name="position">
            </div>

            <div class="canlend_input">
                <label>可借副本:</label>
                <input type="text" id="" value="${book.have_count}" name="have_count">
            </div>

            <div class="cid_input">
                <label>所属类型:</label>
                <select name="cid" id="booktype">
                    <option value="1">自然科学</option>
                    <option value="2">程序人生</option>
                    <option value="3">文学诗歌</option>
                    <option value="4">经典名著</option>
                    <option value="5">其他</option>
                </select>
            </div>

            <div class="desc_input">
                <label>内容简介:</label>
                <textarea name="book_desc">${book.book_desc}</textarea>
            </div>

            <input value="${book.id}" name="id" style="display: none">

            <div class="desc_button">
                <input type="button" value="更新" id="update_button">
                <input type="button" value="返回" onclick="Goback(-1)">
            </div>

        </form>

        <%--借书成功信息回显用的，避免js语法错误,并直接跳转到首页--%>
        <form action="${ctx}/ManagerBookServlet?action=ManaPageBooks&numble=1" id="form_success" method="post">
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

    //更新提交
    $("#update_button").click(
        function () {
            var set=confirm("是否确认更新？");
            if (set==true){
                $("#form_book").submit();
            }
        }
    )

    //对类别的设定
    $("#booktype").val("${book.cid}");


    //用js辅助可以完成很多事情，信息提示，跳转首页
    if($("#success").val()!=""){
        alert("${success}")
        $("#form_success").submit();
    }

</script>

</body>
</html>