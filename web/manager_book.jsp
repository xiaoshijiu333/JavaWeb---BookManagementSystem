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
    <link rel="stylesheet" href="css/manager_book.css">
    <link rel="stylesheet" href="css/pageStyle.css">
</head>
<body>

<div id="manager_book_header">
    <div class="header_center">
        <img src="images/title.png">
        <p>欢迎${admin.uname}进入图书管理系统后台管理员界面</p>
    </div>
</div>

<div id="manager_book_search">
    <div class="manager_book_search_center">
        <form action="${ctx}/ManagerBookServlet?action=bookByName" method="post" id="admin_form">
            <div class="button">
                <input type="button" value="查找" id="admin_button">
            </div>
            <div class="bname">
                <input type="text" placeholder="输入你想查询的书目" name="bname" id="admin_bname">
            </div>
        </form>
        <form action="add_book.jsp">
            <button class="add_button">添加书籍</button>
        </form>
    </div>
</div>
<div id="manager_book_catagery">
    <div class="manager_book_catagery_center">
        <ul>
            <li><a href="${ctx}/ManagerBookServlet?action=ManaPageBooks&numble=1">全部</a></li>
            <li><a href="${ctx}/ManagerBookServlet?action=ManaPageBooks&cid=1&numble=1">自然科学</a></li>
            <li><a href="${ctx}/ManagerBookServlet?action=ManaPageBooks&cid=2&numble=1">程序人生</a></li>
            <li><a href="${ctx}/ManagerBookServlet?action=ManaPageBooks&cid=3&numble=1">文学诗歌</a></li>
            <li><a href="${ctx}/ManagerBookServlet?action=ManaPageBooks&cid=4&numble=1">经典名著</a></li>
            <li><a href="${ctx}/ManagerBookServlet?action=ManaPageBooks&cid=5&numble=1">其他</a></li>
        </ul>
    </div>
</div>
<div id="manager_book_context_header">
    <div class="context_header_center">
        <ul>
            <li>序号</li>
            <li>名称</li>
            <li class="long_li">作者/出版社</li>
            <li>可借副本</li>
            <li>被借次数</li>
            <li>馆藏地</li>
            <li>更新</li>
            <li>删除</li>
        </ul>

        <%--用于显示多个数据--%>
        <c:forEach items="${pagebean.pageList}" var="bks" varStatus="status">
            <ul>
                <li>${status.index+1}</li>
                <li>${bks.dname}</li>
                <li class="long_li">${bks.writer}</li>
                <li>${bks.have_count}</li>
                <li>${bks.lend_count}</li>
                <li>${bks.position}</li>
                <li><a href="${ctx}/ManagerBookServlet?action=ToUpdateBook&dname=${bks.dname}"><img src="images/update.png"></a></li>
                <li><a href="${ctx}/ManagerBookServlet?action=DeleteBook&id=${bks.id}" id="a_delete"><img src="images/delete.png"></a></li>
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
                <button id="records_but">跳转记录页</button>
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

    //删除之前的确定
    $("#a_delete").click(
        function () {
            var set=confirm("是否确认删除?");
            if(set==true){
                return true;
            }else{
                return false;
            }
        }
    )

    //监听返回页按钮，定义表单action
    $("#return_but").click(
        function () {
            button_form.action="${ctx}/manager_index.jsp";
        }
    )

    $("#records_but").click(
        function () {
            button_form.action="${ctx}/ManagerRecServlet?action=ManaAllRecords";
        }
    )


    //分页
    $("#page").paging({
        pageNo:${pagebean.currentPage},  /*当前选中的是哪一页*/
        totalPage: ${pagebean.totalPage}, /*共多少页*/
        totalSize: ${pagebean.totalCount},/*共多少条记录*/
        callback: function(num) {
            $(window).attr('location',"${ctx}/ManagerBookServlet?action=ManaPageBooks&cid=${pagebean.cid}&numble="+num);
        }
    })


</script>

</body>
</html>