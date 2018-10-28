package Services;

import Dao.bookDao;
import Dao.recordsDao;
import Model.book;
import Model.pageBean;
import Model.records;
import Tools.AddMonth;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class bookServices {
    private bookDao bookdao= new bookDao();
    private recordsDao recdao=new recordsDao();
    private pageBean pagebean = new pageBean();

    //将查询到的一条记录封装到集合当中，在封装成PageList,可以避免js出现的语法错误
    public pageBean PageBeanByName(String dname) throws SQLException {
        book book = bookdao.SearchBookByName(dname);
        List<book> books = new ArrayList<>();
        //当前页
        pagebean.setCurrentPage(1);
        //一页显示多少条数据
        pagebean.setCurrentCount(7);
        //总记录数
        pagebean.setTotalCount(1);
        //总页数
        int TotalPage= (int) Math.ceil(1.0*1/7);
        pagebean.setTotalPage(TotalPage);

        if(book!=null){
            books.add(book);
            pagebean.setPageList(books);

            return pagebean;
        }
        else {
            pagebean.setPageList(null);
            return pagebean;
        }
    }

    //设置查询所有的书籍PageBean
    public pageBean GetPageBean(int currentpage) throws SQLException {
        //当前页
        pagebean.setCurrentPage(currentpage);
        //一页显示多少条数据
        int count=7;
        pagebean.setCurrentCount(count);
        //总记录数
        int TotalCount= (int) bookdao.TotalCount();
        pagebean.setTotalCount(TotalCount);
        //总页数
        int TotalPage= (int) Math.ceil(1.0*TotalCount/count);
        pagebean.setTotalPage(TotalPage);

        //当前页数据开始的角标
        int index=(currentpage-1)*count;

        //当前页数据
        List<book> books = bookdao.SearchByIndex(index, count);
        pagebean.setPageList(books);

        //用于表示分类的cid,设置为0，避免继承之前的值
        pagebean.setCid(0);

        return pagebean;
    }

    //设置指定类型的PageBean
    public pageBean GetPageBeanByCid(int currentpage,int cid) throws SQLException {
        //当前页
        pagebean.setCurrentPage(currentpage);
        //一页显示多少条数据
        int count=7;
        pagebean.setCurrentCount(count);
        //总记录数
        int TotalCount= (int) bookdao.TotalCountCid(cid);
        pagebean.setTotalCount(TotalCount);
        //总页数
        int TotalPage= (int) Math.ceil(1.0*TotalCount/count);
        pagebean.setTotalPage(TotalPage);

        //当前页数据开始的角标
        int index=(currentpage-1)*count;

        //当前页数据
        List<book> books = bookdao.SearchByIndexCid(index,count,cid);
        pagebean.setPageList(books);

        //用于表示分类的cid
        pagebean.setCid(cid);

        return pagebean;
    }

    //根据名称查找相应图书，用于书本详情页的展示
    public book SearchByNameServ(String dname) throws SQLException {
       return bookdao.SearchBookByName(dname);
    }

    //借书业务
    public void lendBook(String uname,String dname) throws SQLException, ParseException {
        records record=new records();
        book onebook = bookdao.SearchBookByName(dname);
        //设置书的可借副本减1,借出次数加1
        onebook.setHave_count(onebook.getHave_count()-1);
        onebook.setLend_count(onebook.getLend_count()+1);
        //将书籍更新到数据库
        bookdao.UpdateBook(onebook);
        //在记录表中添加一条相关记录
        record.setDname(dname);
        record.setUname(uname);
        //获得当前时间
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //调用方法，获得比当前时间大一个月的时间
        String timemonth = AddMonth.addmonth(time);
        record.setLendtime(time);
        record.setShouldtime(timemonth);
        //将该记录添加到数据库当中
        recdao.addRec(record);
    }

    public void DeleteServices(int id) throws SQLException {
        bookdao.DeleteBookById(id);
    }

    public void UpdateServices(book onebook) throws SQLException {
        bookdao.UpdateBookById(onebook);
    }

    public void AddBookServices(book onebook) throws SQLException {
        bookdao.addBook(onebook);
    }
}
