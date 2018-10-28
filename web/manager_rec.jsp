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
    <link rel="stylesheet" href="css/manager_rec.css">
    <link rel="stylesheet" href="css/pageStyle.css">
</head>
<body>
<div id="manager_records_header">
    <div class="header_center">
        <img src="images/title.png">
        <p>欢迎${admin.uname}进入图书管理系统后台管理员界面</p>
    </div>
</div>

<div id="manager_records_search">
    <div class="manager_records_search_center">
        <form action="${ctx}/ManagerRecServlet?action=ManaAllRecords&numble=1" method="post" id="form_dname">
            <div class="button">
                <input type="button" value="查找" id="admin_button">
            </div>
            <div class="bname">
                <input type="text" placeholder="查找书被借记录" name="dname" id="admin_bname">
            </div>
        </form>
        <form action="${ctx}/ManagerRecServlet?action=ManaAllRecords&numble=1" id="form_uname" method="post">
            <div class="uname">
                <input type="text" placeholder="查找用户借阅记录" name="uname" id="admin_uname">
            </div>
        </form>
    </div>
</div>

<div id="manager_records_catagery">
    <div class="manager_records_catagery_center">
        <ul>
            <li><a href="${ctx}/ManagerRecServlet?action=ManaAllRecords&numble=1">全部记录</a></li>
            <li><a href="${ctx}/ManagerRecServlet?action=ManaAllRecords&numble=1&flag=1">未归还记录</a></li>
            <li><a href="${ctx}/ManagerRecServlet?action=ManaAllRecords&numble=1&flag=2">已归还记录</a></li>
        </ul>
    </div>
</div>

<div id="manager_records_context_header">
    <div class="context_header_center">
        <ul>
            <li class="first_li">序号</li>
            <li class="second_li">借阅人名称</li>
            <li class="second_li">图书名称</li>
            <li>借阅时间</li>
            <li>应还时间</li>
            <li>归还时间</li>
        </ul>

        <%--用于显示多个数据--%>
        <c:forEach items="${pagebean.pageListRec}" var="recs" varStatus="status">
            <ul>
                <li class="first_li">${status.index+1}</li>
                <li class="second_li">${recs.uname}</li>
                <li class="second_li">${recs.dname}</li>
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
            if($("#admin_bname").val()==""&&$("#admin_uname").val()==""){
                alert("要查询的内容不能为空");
            }else if($("#admin_bname").val()!=""){
                $("#form_dname").submit();
            }
            else if($("#admin_uname").val()!=""){
                $("#form_uname").submit();
            }
        }
    )

    //分页
    $("#page").paging({
        pageNo:${pagebean.currentPage},  /*当前选中的是哪一页*/
        totalPage: ${pagebean.totalPage}, /*共多少页*/
        totalSize: ${pagebean.totalCount},/*共多少条记录*/
        callback: function(num) {
            $(window).attr('location',"${ctx}/ManagerRecServlet?action=ManaAllRecords&uname=${pagebean.uname}&dname=${pagebean.dname}&flag=${pagebean.flag}&numble="+num);
        }
    })

    //监听返回页按钮，定义表单action
    $("#return_but").click(
        function () {
            button_form.action="${ctx}/manager_index.jsp";
        }
    )

    $("#records_but").click(
        function () {
            button_form.action="${ctx}/ManagerBookServlet?action=ManaPageBooks";
        }
    )


</script>

</body>
</html>