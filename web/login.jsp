<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<%
    String contextPath = request.getContextPath();
    pageContext.setAttribute("ctx",contextPath);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录界面</title>
    <!--设置标签图标-->
    <link href="images/title.ico" rel="shortcut icon">
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<div id="header">
    <div class="h_center">      
        <img src="images/title.jpg" >
        <p>图书管理系统登录界面</p>
    </div>
</div>
<div id="login_body">
    <div class="login_b_center">
        <div class="login_bg">
            <div class="err">
				<p>请登录</p>
                <c:if test="${!empty errors}" >
                    <span>用户名或密码错误</span>
                </c:if>
			</div>
           	<form action="${ctx}/LoginServlet" method="post" id="login">
            <div class="userName">
                <span>用户名</span>
                <input type="text" id="uname" placeholder="请输入用户名/密码" name="uname">
            </div>
            <div class="password">
                <span>密码</span>
                <input type="password" id="password" placeholder="请输入密码" name="password">
            </div>
            <div class="select">
                <span>类型</span>
                <select name="usertype" id="usertype">
                    <option value="0">普通用户</option>
                    <option value="1">管理员</option>
                </select>
            </div>
            <div class="hrh">
                <input type="button" value="登录" id="Button" >
            </div>
            </form>
            <div class="password_forget">
                <a href="#">忘记密码</a>
                <a href="${ctx}/regist.jsp">去注册</a>
            </div>
        </div>
    </div>
</div>

<script src="js/jquery.main.js"></script>
<script>

    $("#uname").val("${admin.uname}");
    $("#password").val("${admin.password}");

    if(${empty admin.type}){
        $("#usertype").val(0);
    }else{
        $("#usertype").val("${admin.type}");
    }

    $("#Button").click(function () {
        if($("#uname").val()==""){
            alert("请输入用户名");
        }
        else if($("#password").val()==""){
            alert("请输入密码");
        }
        else{
            $("#login").submit();
        }
    })


</script>

<!--通过include指令调用footer.jsp-->
<%@ include file="footer.jsp" %>

</body>


</html>