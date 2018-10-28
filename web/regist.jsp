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
    <title>注册界面</title>
    <!--设置标签图标-->
     <link href="images/title.ico" rel="shortcut icon">
    <link rel="stylesheet" href="css/regist.css">
</head>
<body>
<!--头部-->
<div id="reg_header">
    <div class="reg_h_center">
        <div class="reg_h_left">
            <img src="images/title.jpg" alt="">
            <p>普通用户注册</p>
        </div>

        <div class="reg_h_right">
            <a href="login.jsp">去登录</a>
            <span>已有账户</span>

        </div>
    </div>
</div>
<!--表单内容-->
<div id="reg_content">
    <div class="reg_content_left">
        <c:if test="${!empty err}">
            <span>${err}</span>
        </c:if>

        <form action="${ctx}/RegeditServlet" method="post" id="regedit">
            <div>
                <label>用户名</label>
                <input type="text" id="uname" placeholder="请输入用户名" name="uname">            
            </div>
            <div>
                <label>密码</label>
                <input type="password" id="password" placeholder="请输入密码" name="password">
            </div>
            <div>
                <label>确认密码</label>
                <input type="password" id="checkpassword" placeholder="请再次输入密码" name="checkpassword">
            </div>
            <div>
                <label>邮箱</label>
                <input type="text" id="email" placeholder="请输入邮箱" name="email">
            </div>
            <div class="check_box">
                <label>验证码</label>
                <input id="checkcode" type="text" placeholder="请输入验证码" name="check">
                <img src="${ctx}/CheckCodeServlet" id="reg_img" >
            </div>
            <p>点击图片可刷新验证码</p>
            <div class="submit_button">
                <input type="button" value="立即注册" id="reg_button">
            </div>

        </form>
    </div>
</div>

<!--通过include指令调用footer.jsp-->
<%@ include file="footer.jsp" %>

<script src="js/jquery.main.js"></script>
<script>

    $("#uname").val("${admin.uname}");
    $("#password").val("${admin.password}");
    $("#checkpassword").val("${checkpassword}");
    $("#email").val("${admin.email}");
    $("#checkcode").val("${checkCode}");


    $("#reg_img").click(function () {
        this.src="${ctx}/CheckCodeServlet?time="+new Date().getTime();
    });

    $("#reg_button").click(function () {
        if($("#uname").val()==""){
            alert("用户名不能为空");
        }
        else if($("#password").val()==""){
            alert("密码不能为空");
        }
        else if($("#email").val()==""){
            alert("邮箱不能为空");
        }
        else if($("#checkpassword").val()==""){
            alert("确定密码不能为空");
        }
        else if($("#checkcode").val()==""){
            alert("验证码不能为空");
        }
        else if($("#checkpassword").val()!=$("#password").val()){
            alert("两次密码不一样");
        }
        else{
            $("#regedit").submit();
        }
    })

</script>
</body>
</html>