package Web;

import Model.admin;
import Model.pageBean;
import Services.recordsServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ManagerRecServlet")
public class ManagerRecServlet extends BaseServlet {
    private recordsServices recservices = new recordsServices();

    public String ManaAllRecords(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String numble = req.getParameter("numble");
        String flag = req.getParameter("flag");
        String dname = req.getParameter("dname");
        String uname = req.getParameter("uname");

        try {
            //以pageBean的形式查询所有用户所有记录
            if((flag==null||Integer.parseInt(flag)==0)&&(dname==null||dname.equals("null"))&&(uname==null||uname.equals("null"))){
                pageBean pagebean = recservices.ManaAllRecServices(Integer.parseInt(numble));
                //把pagebean存到域中
                req.setAttribute("pagebean",pagebean);
            }

            //以pageBean的形式查询所有用户是否归还的所有记录
            else if(flag!=null&&Integer.parseInt(flag)!=0){
                pageBean pagebean = recservices.ManaIsReturnServices(Integer.parseInt(numble), Integer.parseInt(flag));
                //把pagebean存到域中
                req.setAttribute("pagebean",pagebean);
            }

            //根据书名搜索所有用户的记录
            else if(dname!=null&&!dname.equals("null")){
                pageBean pagebean = recservices.ManaRecByDnameServices(dname,Integer.parseInt(numble));
                if(pagebean.getPageListRec()==null){
                    req.setAttribute("err_message","没有你要查找的相关记录");
                }
                //pagebean必须要存到域中，避免js语法错误
                req.setAttribute("pagebean",pagebean);
            }

            //根据用户名搜索所有记录
            else if(uname!=null&&!uname.equals("null")){
                pageBean pagebean = recservices.ManaRecByUnameServices(uname,Integer.parseInt(numble));
                if(pagebean.getPageListRec()==null){
                    req.setAttribute("err_message","没有你要查找的相关记录");
                }
                //pagebean必须要存到域中，避免js语法错误
                req.setAttribute("pagebean",pagebean);
            }
            return "manager_rec.jsp";

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
