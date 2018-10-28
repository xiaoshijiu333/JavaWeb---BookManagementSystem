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
    <link rel="stylesheet" href="css/admin_records.css">
    <link rel="stylesheet" href="css/pageStyle.css">
</head>
<body>
<div id="admin_records_header">
    <div class="header_center">
        <img src="images/title.png">
        <p>欢迎${admin.uname}使用图书管理系统</p>
    </div>
</div>

<div id="admin_records_search">
    <div class="admin_records_search_center">
        <form action="${ctx}/AdminRecServlet?action=AllRecords&numble=1" method="post" id="admin_form">
            <div class="button">
                <input type="button" value="查找" id="admin_button">
            </div>
            <div class="bname">
                <input type="text" placeholder="根据书名称查找记录" name="dname" id="admin_bname">
            </div>
        </form>
    </div>
</div>

<div id="admin_records_catagery">
    <div class="admin_records_catagery_center">
        <ul>
            <li><a href="${ctx}/AdminRecServlet?action=AllRecords&numble=1">全部记录</a></li>
            <li><a href="${ctx}/AdminRecServlet?action=AllRecords&numble=1&flag=1">未归还记录</a></li>
            <li><a href="${ctx}/AdminRecServlet?action=AllRecords&numble=1&flag=2">已归还记录</a></li>
        </ul>
    </div>
</div>

<div id="admin_records_context_header">
    <div class="context_header_center">
        <ul>
            <li>序号</li>
            <li>图书名称</li>
            <li>借阅时间</li>
            <li>应还时间</li>
            <li>归还时间</li>
        </ul>

        <%--用于显示多个数据--%>
        <c:forEach items="${pagebean.pageListRec}" var="recs" varStatus="status">
            <ul>
                <li>${status.index+1}</li>
                <li><a href="${ctx}/AdminRecServlet?action=RecSearch&id=${recs.id}">${recs.dname}</a></li>
                <li>${recs.lendtime}</li>
                <li>${recs.shouldtime}</li>
                <li>${recs.returntime}</li>
            </ul>
        </c:forEach>

        <%--用于显示没有查找结果信息--%>
        <p>${err_message}</p>

        <div class="pagepage">
            <div id="page" class="page_div"></div>
        </div>

        <div class="return_button">
            <form action="" method="post" name="button_form">
                <button id="return_but">返回欢迎页</button>
                <input type="text" value="1" style="display:none" name="numble">
                <button id="records_but">跳转书籍页</button>
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

    //分页
    $("#page").paging({
        pageNo:${pagebean.currentPage},  /*当前选中的是哪一页*/
        totalPage: ${pagebean.totalPage}, /*共多少页*/
        totalSize: ${pagebean.totalCount},/*共多少条记录*/
        callback: function(num) {
            $(window).attr('location',"${ctx}/AdminRecServlet?action=AllRecords&dname=${pagebean.dname}&flag=${pagebean.flag}&numble="+num);
        }
    })

    //监听返回页按钮，定义表单action
    $("#return_but").click(
        function () {
            button_form.action="${ctx}/admin_index.jsp";
        }
    )

    $("#records_but").click(
        function () {
            button_form.action="${ctx}/AdminBookServlet?action=PageBooks";
        }
    )


</script>

</body>
</html>
