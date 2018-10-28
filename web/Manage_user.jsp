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
    <link rel="stylesheet" href="css/Manage_user.css">
    <link rel="stylesheet" href="css/pageStyle.css">
</head>
<body>
<div id="admin_book_header">
    <div class="header_center">
        <img src="images/title.png">
        <p>欢迎${admin.uname}进入图书管理系统后台管理员界面</p>
    </div>
</div>
<div id="admin_book_search">
    <div class="admin_book_search_center">
        <form action="${ctx}/UserManageServlet?action=SearchByuname" method="post" id="admin_form">
            <div class="button">
                <input type="button" value="查找" id="admin_button">
            </div>
            <div class="bname">
                <input type="text" placeholder="输入用户名称查询" name="uname" id="admin_bname">
            </div>
        </form>
    </div>
</div>
<div id="admin_book_context_header">
    <div class="context_header_center">
        <ul class="first_ul">
            <li>序号</li>
            <li>用户名称</li>
            <li>用户密码</li>
            <li>用户邮箱</li>
            <li>设为管理员</li>
        </ul>

        <%--用于显示多个数据--%>
        <c:forEach items="${pagebean.pageListUser}" var="user" varStatus="status">
            <ul>
                <li>${status.index+1}</li>
                <li>${user.uname}</li>
                <li>${user.password}</li>
                <li>${user.email}</li>
                <li><a href="${ctx}/UserManageServlet?action=ToManager&id=${user.id}" id="a_manager"><img src="images/user.png"></a></li>
            </ul>
        </c:forEach>

        <%--用于显示没有查找结果信息--%>
        <p>${err_message}</p>

        <div class="pagepage">
            <div id="page" class="page_div"></div>
        </div>
        <div class="return_button">
            <form action="${ctx}/manager_index.jsp" method="post" name="button_form">
                <button id="return_but">返回欢迎页</button>
            </form>
        </div>

    </div>
</div>


<script src="js/jquery.main.js"></script>
<script type="text/javascript" src="js/paging.js"></script>
<script>

    $("#admin_button").click(
        function () {
            if($("#admin_bname").val()==""){
                alert("要查询的内容不能为空");
            }
            else{
                $("#admin_form").submit();
            }
        }
    )

    //设定管理员之前的判断确认
    $("#a_manager").click(
        function () {
            var set=confirm("是否确认设定为管理员?");
            if(set==true){
                return true;
            }else{
                return false;
            }
        }
    )
    //分页
    $("#page").paging({
        pageNo:${pagebean.currentPage},  /*当前选中的是哪一页*/
        totalPage: ${pagebean.totalPage}, /*共多少页*/
        totalSize: ${pagebean.totalCount},/*共多少条记录*/
        callback: function(num) {
            $(window).attr('location',"${ctx}/UserManageServlet?action=SearchAllUser&numble="+num);
        }
    })

</script>

</body>
</html>
