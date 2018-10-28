package Web;


import Model.book;
import Model.pageBean;
import Services.bookServices;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;


@WebServlet("/ManagerBookServlet")
public class ManagerBookServlet extends BaseServlet {

    private bookServices bookservices = new bookServices();

    public String ManaPageBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String numble = req.getParameter("numble");
            String cid = req.getParameter("cid");
            if(cid==null||Integer.parseInt(cid)==0){
                pageBean pagebean = bookservices.GetPageBean(Integer.parseInt(numble));
                //将查询到的所有书籍信息以PageBean的形式存到域中,返回类型定义需要转发到的地方，在baseServlet中处理
                req.setAttribute("pagebean",pagebean);
            }
            else{
                //分类查询
                pageBean pagebean = bookservices.GetPageBeanByCid(Integer.parseInt(numble),Integer.parseInt(cid));
                req.setAttribute("pagebean",pagebean);
            }

            return "manager_book.jsp";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String bookByName(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        try {
            String bname = req.getParameter("bname");
            pageBean pagebean = bookservices.PageBeanByName(bname);
            if(pagebean.getPageList()==null){
                req.setAttribute("err_message","没有你要查找的书籍");
            }
            req.setAttribute("pagebean",pagebean);
            return "manager_book.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    //删除书籍
    public String DeleteBook(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        String id = req.getParameter("id");
        try {
            bookservices.DeleteServices(Integer.parseInt(id));
            //删除成功，跳转到首页
            return "/ManagerBookServlet?action=ManaPageBooks&numble=1";

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据书名称查询书，进入更新页详情
    public String ToUpdateBook(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        String dname = req.getParameter("dname");
        try {
            book book = bookservices.SearchByNameServ(dname);
            req.setAttribute("book",book);
            return "ManaBook_desc.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //更新书籍信息
    public String UpdateBook(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{

        book onebook=new book();
        Map<String, String[]> map = req.getParameterMap();

        try {
            //使用BeanUtils封装数据
            BeanUtils.populate(onebook,map);

            bookservices.UpdateServices(onebook);
            //标记存入域中，用于表示成功的信息回显
            req.setAttribute("success","更新成功");

            return "ManaBook_desc.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //添加书籍
    public String AddBook(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        book onebook=new book();
        Map<String, String[]> map = req.getParameterMap();

        try {
            //使用BeanUtils封装数据
            BeanUtils.populate(onebook,map);

            bookservices.AddBookServices(onebook);
            //标记存入域中，用于表示成功的信息回显
            req.setAttribute("success","添加成功");

            return "add_book.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
