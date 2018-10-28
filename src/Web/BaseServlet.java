package Web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String action = req.getParameter("action");
        //获取当前对象的字节码
        Class<? extends BaseServlet> aClass = this.getClass();
        try {
            Method method = aClass.getMethod(action,HttpServletRequest.class,HttpServletResponse.class);

            //如果方法存在，再执行下一步
            if(method!=null){
                //执行方法的时候一定要注意申明是哪个对象
                String  position = (String)method.invoke(this,req, resp);
                req.getRequestDispatcher(position).forward(req,resp);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
