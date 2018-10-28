<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="css/manager_index.css">
</head>
<body>
<div id="manager_index_header">
    <div class="header_center">
        <img src="images/title.png">
        <p>欢迎${admin.uname}进入图书管理系统后台管理员界面</p>
    </div>
</div>
<div id="manager_index_center">
    <div class="center_center">
        <img src="images/index.png">

        <%--每一次跳转的时候都加上一个action参数--%>
        <%--使用按钮可以利用表单发送一些参数，简化地址栏的繁琐程度--%>

        <form action="${ctx}/ManagerBookServlet?action=ManaPageBooks" method="post">
            <input type="text" value="1" style="display:none" name="numble">
            <button class="first_button">书目管理</button>
        </form>

        <form action="${ctx}/ManagerRecServlet?action=ManaAllRecords" method="post">
            <input type="text" value="1" style="display:none" name="numble">
            <button class="second_button">记录管理</button>
        </form>

        <form action="${ctx}/UserManageServlet?action=SearchAllUser" method="post">
            <input type="text" value="1" style="display:none" name="numble">
            <button class="four_button">用户管理</button>
        </form>


        <form action="${ctx}/login.jsp" method="post" id="exit_form">
            <button class="third_button" id="exit_button" type="button">退出</button>
        </form>

        <div class="tips">
            <p>管理员功能区，请点击</p>
        </div>

    </div>
</div>

<script src="js/jquery.main.js"></script>
<script>

    $("#exit_button").click(
        function () {
            var set=confirm("是否确认退出？");
            if(set==true){
                $("#exit_form").submit();
            }
        }
    )

</script>

<!--通过include指令调用footer.jsp-->
<%@ include file="footer.jsp" %>

</body>
</html>