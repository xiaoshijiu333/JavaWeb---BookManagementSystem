package Services;

import Dao.adminDao;
import Model.admin;
import Model.book;
import Model.pageBean;
import Tools.Onename;
import Tools.isEmail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class adminServices {
    private adminDao adao = new adminDao();
    private pageBean pagebean = new pageBean();
    public admin loginServices(admin u) throws Exception {
        admin user=null;
        if(!isEmail.isemail(u.getUname())){
            user= adao.searchAdminByuname(u);
            return user;
        }
        else {
            user= adao.searchAdminByemail(u);
            return user;
        }
    }
    public int regeditServices(admin u) throws SQLException {
        //"邮箱格式错误"
        if(!isEmail.isemail(u.getEmail())){
            return 1;
        }
        //"该邮箱已经注册"
        else if (Onename.OneEmail(u.getEmail())){
            return 2;
        }
        //"用户名已存在"
        else if(Onename.OneName(u.getUname())){
             return 3;
        }
        //"注册成功"
        else{
            adao.addAdmin(u);
            return 4;
        }
    }

    public pageBean AllUserServices(int currentpage) throws SQLException {
        //当前页
        pagebean.setCurrentPage(currentpage);
        //一页显示多少条数据
        int count=7;
        pagebean.setCurrentCount(count);
        //总记录数
        int TotalCount= (int) adao.UserCount();
        pagebean.setTotalCount(TotalCount);
        //总页数
        int TotalPage= (int) Math.ceil(1.0*TotalCount/count);
        pagebean.setTotalPage(TotalPage);

        //当前页数据开始的角标
        int index=(currentpage-1)*count;

        //当前页数据
        List<admin> users = adao.SearchAllUser(index,count);
        pagebean.setPageListUser(users);


        return pagebean;
    }

    //将查询到的一条记录封装到集合当中，在封装成PageListUser,可以避免js出现的语法错误
    public pageBean PageBeanByName(String uname) throws SQLException {
        admin user= adao.searchByuname(uname);
        List<admin> admin = new ArrayList<>();
        //当前页
        pagebean.setCurrentPage(1);
        //一页显示多少条数据
        pagebean.setCurrentCount(7);
        //总记录数
        pagebean.setTotalCount(1);
        //总页数
        int TotalPage= (int) Math.ceil(1.0*1/7);
        pagebean.setTotalPage(TotalPage);

        if(user!=null){
            admin.add(user);
            pagebean.setPageListUser(admin);
            return pagebean;
        }
        else {
            pagebean.setPageListUser(null);
            return pagebean;
        }
    }

    //设置管理员业务
    public void SetToManager(int id) throws SQLException {
        adao.UpdateType(id);
    }
}
