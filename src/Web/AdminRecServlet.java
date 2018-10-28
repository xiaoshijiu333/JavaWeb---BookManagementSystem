package Web;

import Model.admin;
import Model.book;
import Model.pageBean;
import Model.records;
import Services.recordsServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/AdminRecServlet")
public class AdminRecServlet extends BaseServlet {

    private recordsServices recservices = new recordsServices();

    public String AllRecords(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        admin user = (admin)req.getSession().getAttribute("admin");
        String numble = req.getParameter("numble");
        String flag = req.getParameter("flag");
        String dname = req.getParameter("dname");

        try {
            //以pageBean的形式查询指定用户所有记录
            if((flag==null||Integer.parseInt(flag)==0)&&(dname==null||dname.equals("null"))){
                pageBean pagebean = recservices.AllRecServices(user.getUname(), Integer.parseInt(numble));
                //把pagebean存到域中
                req.setAttribute("pagebean",pagebean);
            }

            //以pageBean的形式查询指定用户是否归还的所有记录
           else if(flag!=null&&Integer.parseInt(flag)!=0){
                pageBean pagebean = recservices.IsReturnServices(user.getUname(), Integer.parseInt(numble), Integer.parseInt(flag));
                //把pagebean存到域中
                req.setAttribute("pagebean",pagebean);
            }

            //根据书名搜索具体用户的记录
            else if(dname!=null&&dname!="null"){
                pageBean pagebean = recservices.RecByNameServices(user.getUname(), dname,Integer.parseInt(numble));
                if(pagebean.getPageListRec()==null){
                    req.setAttribute("err_message","没有你要查找的相关记录");
                }
                //pagebean必须要存到域中，避免js语法错误
                req.setAttribute("pagebean",pagebean);
            }
            return "admin_records.jsp";

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    //进入记录详情页，按记录对应的id进行查找，不要忘记了id，id具有唯一性
    public String RecSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        admin user=(admin)req.getSession().getAttribute("admin");
        try {
            records rec = recservices.RecByIdServices(Integer.parseInt(id));
            req.setAttribute("rec",rec);
            return "rec_desc.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //还书处理
    public String ReturnBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String dname = req.getParameter("dname");
        String id = req.getParameter("id");
        try {
            recservices.returnbook(dname,Integer.parseInt(id));
            //标记存入域中，用于表示成功的信息回显
            req.setAttribute("success","还书成功！！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "rec_desc.jsp";
    }
    }
