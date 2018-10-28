package Services;

import Dao.bookDao;
import Dao.recordsDao;
import Model.book;
import Model.pageBean;
import Model.records;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class recordsServices {

    private recordsDao recDao = new recordsDao();
    private pageBean pagebean = new pageBean();
    private bookDao bookdao= new bookDao();

    //设置查询指定用户所有的记录PageBean
    public pageBean AllRecServices(String uname,int currentpage) throws SQLException {
        //当前页
        pagebean.setCurrentPage(currentpage);
        //一页显示多少条数据
        int count=7;
        pagebean.setCurrentCount(count);
        //总记录数
        int TotalCount= (int) recDao.TotalCountByName(uname);
        pagebean.setTotalCount(TotalCount);
        //总页数
        int TotalPage= (int) Math.ceil(1.0*TotalCount/count);
        pagebean.setTotalPage(TotalPage);

        //当前页数据开始的角标
        int index=(currentpage-1)*count;

        //当前页数据
        List<records> record = recDao.SearchAllRec(uname, index, count);
        pagebean.setPageListRec(record);

        //设置用于区别的flag和dname
        pagebean.setFlag(0);
        pagebean.setDname("null");

        return pagebean;
    }

    //根据指定用户是否归还进行记录查找的pageBean
    public pageBean IsReturnServices(String uname,int currentpage,int flag) throws SQLException {
        //当前页
        pagebean.setCurrentPage(currentpage);
        //一页显示多少条数据
        int count=7;
        pagebean.setCurrentCount(count);
        //总记录数
        int TotalCount= (int) recDao.IsReturnCount(uname,flag);
        pagebean.setTotalCount(TotalCount);
        //总页数
        int TotalPage= (int) Math.ceil(1.0*TotalCount/count);
        pagebean.setTotalPage(TotalPage);

        //当前页数据开始的角标
        int index=(currentpage-1)*count;

        //当前页数据
        List<records> record = recDao.IsReturnRec(uname,flag,index,count);
        pagebean.setPageListRec(record);

        //设置用于区别的flag和dname
        pagebean.setFlag(flag);
        pagebean.setDname("null");

        return pagebean;
    }

    //根据指定用户的指定书名称查找相应的记录,这里可能一个用户会借同一本书多次，所以将查询到的
    // 结果封装成PageBean
    public pageBean RecByNameServices(String uname,String dname,int currentpage) throws SQLException {
        //当前页
        pagebean.setCurrentPage(currentpage);
        //一页显示多少条数据
        int count=7;
        pagebean.setCurrentCount(count);
        //总记录数
        int TotalCount= (int) recDao.RecByNameCount(uname,dname);
        pagebean.setTotalCount(TotalCount);
        //总页数
        int TotalPage= (int) Math.ceil(1.0*TotalCount/count);
        pagebean.setTotalPage(TotalPage);

        //当前页数据开始的角标
        int index=(currentpage-1)*count;

        //当前页数据
        List<records> recordsList  = recDao.RecByName(uname,dname,index,count);

        if(recordsList.size()!=0){
            pagebean.setPageListRec(recordsList);
        }else {
            pagebean.setPageListRec(null);
        }
        //设置用于区别的flag和dname
        pagebean.setFlag(0);
        pagebean.setDname(dname);

        return pagebean;
    }

    public records RecByIdServices(int id) throws SQLException {
        return recDao.SearchRecById(id);
    }

    //还书业务
    public void returnbook(String dname,int id) throws SQLException {
        //设置书的可借副本加1
        book book = bookdao.SearchBookByName(dname);
        book.setHave_count(book.getHave_count()+1);
        //将书籍更新到数据库
        bookdao.UpdateBook(book);
        //根据id获取相对应的记录,设置归还时间
        records record = recDao.SearchRecById(id);
        record.setReturntime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //将记录更新到数据库
        recDao.UpdateRec(record);
    }

    //管理员用于查询所有用户的所有记录
    public pageBean ManaAllRecServices(int currentpage) throws SQLException {
        //当前页
        pagebean.setCurrentPage(currentpage);
        //一页显示多少条数据
        int count=7;
        pagebean.setCurrentCount(count);
        //总记录数
        int TotalCount= (int) recDao.TotalCount();
        pagebean.setTotalCount(TotalCount);
        //总页数
        int TotalPage= (int) Math.ceil(1.0*TotalCount/count);
        pagebean.setTotalPage(TotalPage);

        //当前页数据开始的角标
        int index=(currentpage-1)*count;

        //当前页数据
        List<records> record = recDao.SearchAllRec(index, count);
        pagebean.setPageListRec(record);

        //设置用于区别的flag、dname和uname
        pagebean.setFlag(0);
        pagebean.setDname("null");
        pagebean.setUname("null");

        return pagebean;
    }

    //查询所有用户是否归还进行记录查找的pageBean
    public pageBean ManaIsReturnServices(int currentpage,int flag) throws SQLException {
        //当前页
        pagebean.setCurrentPage(currentpage);
        //一页显示多少条数据
        int count=7;
        pagebean.setCurrentCount(count);
        //总记录数
        int TotalCount= (int) recDao.AllIsReturnCount(flag);
        pagebean.setTotalCount(TotalCount);
        //总页数
        int TotalPage= (int) Math.ceil(1.0*TotalCount/count);
        pagebean.setTotalPage(TotalPage);

        //当前页数据开始的角标
        int index=(currentpage-1)*count;

        //当前页数据
        List<records> record = recDao.AllIsReturnRec(flag,index,count);
        pagebean.setPageListRec(record);

        //设置用于区别的flag、dname和uname
        pagebean.setFlag(flag);
        pagebean.setDname("null");
        pagebean.setUname("null");

        return pagebean;
    }

    //查询所有用户的指定书名称相应的记录,将查询到的结果封装成PageBean
    public pageBean ManaRecByDnameServices(String dname,int currentpage) throws SQLException {
        //当前页
        pagebean.setCurrentPage(currentpage);
        //一页显示多少条数据
        int count=7;
        pagebean.setCurrentCount(count);
        //总记录数
        int TotalCount= (int) recDao.RecByDnameCount(dname);
        pagebean.setTotalCount(TotalCount);
        //总页数
        int TotalPage= (int) Math.ceil(1.0*TotalCount/count);
        pagebean.setTotalPage(TotalPage);

        //当前页数据开始的角标
        int index=(currentpage-1)*count;

        //当前页数据
        List<records> recordsList  = recDao.RecByDname(dname,index,count);

        if(recordsList.size()!=0){
            pagebean.setPageListRec(recordsList);
        }else {
            pagebean.setPageListRec(null);
        }
        //设置用于区别的flag、dname和uname
        pagebean.setFlag(0);
        pagebean.setDname(dname);
        pagebean.setUname("null");

        return pagebean;
    }

    //查询指定用户的所有记录,将查询到的结果封装成PageBean
    public pageBean ManaRecByUnameServices(String uname,int currentpage) throws SQLException {
        //当前页
        pagebean.setCurrentPage(currentpage);
        //一页显示多少条数据
        int count=7;
        pagebean.setCurrentCount(count);
        //总记录数
        int TotalCount= (int) recDao.RecByUnameCount(uname);
        pagebean.setTotalCount(TotalCount);
        //总页数
        int TotalPage= (int) Math.ceil(1.0*TotalCount/count);
        pagebean.setTotalPage(TotalPage);

        //当前页数据开始的角标
        int index=(currentpage-1)*count;

        //当前页数据
        List<records> recordsList  = recDao.RecByUname(uname,index,count);

        if(recordsList.size()!=0){
            pagebean.setPageListRec(recordsList);
        }else {
            pagebean.setPageListRec(null);
        }
        //设置用于区别的flag、dname和uname
        pagebean.setFlag(0);
        pagebean.setDname("null");
        pagebean.setUname(uname);

        return pagebean;
    }
}
