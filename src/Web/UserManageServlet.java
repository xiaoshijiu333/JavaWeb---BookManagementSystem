package Web;

import Model.pageBean;
import Services.adminServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/UserManageServlet")
public class UserManageServlet extends BaseServlet {

    private adminServices adminservices = new adminServices();

    public String SearchAllUser(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        String numble = req.getParameter("numble");
        try {
            pageBean pagebean = adminservices.AllUserServices(Integer.parseInt(numble));
            req.setAttribute("pagebean",pagebean);
            return "Manage_user.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public String SearchByuname(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        String uname = req.getParameter("uname");

        try {
            pageBean pagebean = adminservices.PageBeanByName(uname);
            if(pagebean.getPageListUser()==null){
                req.setAttribute("err_message","没有你要查找的用户");
            }
            req.setAttribute("pagebean",pagebean);
            return "Manage_user.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String ToManager(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        String id = req.getParameter("id");

        try {
            adminservices.SetToManager(Integer.parseInt(id));
            //标记存入域中，用于表示成功的信息回显
            req.setAttribute("success","添加管理员成功");
            return "/UserManageServlet?action=SearchAllUser&numble=1";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
