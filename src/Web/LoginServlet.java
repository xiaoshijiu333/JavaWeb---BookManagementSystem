package Web;



import Model.admin;
import Services.adminServices;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        admin u=new admin();
        adminServices adminservices = new adminServices();
        String uname = req.getParameter("uname");
        String password = req.getParameter("password");
        String usertype = req.getParameter("usertype");
            u.setUname(uname);
            u.setPassword(password);
            u.setEmail(uname);
            u.setType(Integer.parseInt(usertype));

            //将u存到域中，用于信息回显时候数据还保存着
            req.setAttribute("admin",u);

            try {
                admin adm=adminservices.loginServices(u);
                if(adm!=null){
                    //存到Session域中，方便后续取数据
                    req.getSession().setAttribute("admin",adm);
                    if(adm.getType()==0){
                        //重定向不能写成/admin_index.jsp，因为重定向并不是服务器内部的转发
                        resp.sendRedirect("admin_index.jsp");
                    }
                    else {
                        resp.sendRedirect("manager_index.jsp");
                    }
                }
                else {
                    //登录失败信息回显，转发到原登陆界面
                    req.setAttribute("errors","用户名或密码错误");
                    req.getRequestDispatcher("login.jsp").forward(req,resp);
                }
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
    }
