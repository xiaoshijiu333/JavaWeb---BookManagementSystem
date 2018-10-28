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
        <form action="${ctx}/ManagerBookServlet?action=AddBook" method="post" id="form_book">
            <div class="name_input">
                <label>书本名称:</label>
                <input type="text" name="dname" id="dname" >
            </div>

            <div class="writer_input">
                <label>作者/出版社:</label>
                <input type="text" name="writer" id="writer" >
            </div>

            <div class="position_input">
                <label>馆藏地:</label>
                <input type="text"  name="position" id="position">
            </div>

            <div class="canlend_input">
                <label>可借副本:</label>
                <input type="text"  name="have_count" id="have_count">
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

            <div class="desc_input" >
                <label>内容简介:</label>
                <textarea name="book_desc" id="book_desc"></textarea>
            </div>

            <div class="desc_button">
                <input type="button" value="添加" id="add_button">
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


    //提交之前的一系列判断
    $("#add_button").click(
        function () {
            if($("#dname").val()==""){
                alert("请输入书本名称");
            }
            else if($("#writer").val()==""){
                alert("请输入作者/出版社");
            }
            else if($("#position").val()==""){
                alert("请输入馆藏地");
            }
            else if($("#have_count").val()==""){
                alert("请输入可借副本");
            }
            else if($("#book_desc").val()==""){
                alert("请输入书本详情");
            }else {
                var set=confirm("是否确认添加？");
                if(set==true){
                    $("#form_book").submit();
                }
            }
        }
    )

    //用js辅助可以完成很多事情，信息提示，跳转首页
    if($("#success").val()!=""){
        alert("${success}")
        $("#form_success").submit();
    }

</script>

</body>
</html>