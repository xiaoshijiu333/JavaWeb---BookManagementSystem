package Web;

import Model.admin;
import Model.book;
import Model.pageBean;
import Services.bookServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet("/AdminBookServlet")
public class AdminBookServlet extends BaseServlet {
    private bookServices bookservices = new bookServices();

    public String PageBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

            return "admin_book.jsp";

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
            return "admin_book.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;

    }

    //进入书本详情页，按名称查找图书
    public String SearchByName(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        String dname = req.getParameter("dname");
        try {
            book book = bookservices.SearchByNameServ(dname);
            req.setAttribute("book",book);
            return "book_desc.jsp";

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //借书
    public String LendBook(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        admin user=(admin)req.getSession().getAttribute("admin");
        String dname = req.getParameter("dname");
        try {
            bookservices.lendBook(user.getUname(), dname);
            //标记存入域中，用于表示成功的信息回显
            req.setAttribute("success","借书成功，请在规定日期内归还！！");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "book_desc.jsp";
    }
}
