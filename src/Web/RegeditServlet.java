package Web;

import Model.admin;
import Services.adminServices;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/RegeditServlet")
public class RegeditServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        adminServices adminservices = new adminServices();
        Map<String, String[]> parameterMap = req.getParameterMap();
        admin user = new admin();

        try {
            BeanUtils.populate(user, parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将对象保存到域中，用于错误信息时候的数据回显
        req.getSession().setAttribute("admin", user);


        //获取确认密码存到域中
        String checkpassword = req.getParameter("checkpassword");
        req.getSession().setAttribute("checkpassword",checkpassword);

        //首先判断验证码是否正确
        String checkcode = req.getParameter("check");
        String checkcode2 = (String) req.getSession().getAttribute("checkcode_session");
        if (!checkcode.equals(checkcode2)) {
            req.setAttribute("err", "验证码错误");
            req.setAttribute("checkCode",checkcode);
            req.getRequestDispatcher("/regist.jsp").forward(req, resp);

        } else {
            try {
                int flag = adminservices.regeditServices(user);
                if (flag == 1) {
                    req.setAttribute("err", "邮箱格式错误");
                    req.getRequestDispatcher("/regist.jsp").forward(req, resp);
                } else if (flag == 2) {
                    req.setAttribute("err", "该邮箱已经注册");
                    req.getRequestDispatcher("/regist.jsp").forward(req, resp);
                } else if (flag == 3) {
                    req.setAttribute("err", "用户名已存在");
                    req.getRequestDispatcher("/regist.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("/BookManagement/login.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


