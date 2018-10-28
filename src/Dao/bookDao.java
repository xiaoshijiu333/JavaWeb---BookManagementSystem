package Dao;

import Model.book;
import Tools.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class bookDao {

    private QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());

    //根据书名称进行查找
    public book SearchBookByName(String dname) throws SQLException {
        String sql="select * from book where dname=?";
        book book = runner.query(sql, new BeanHandler<book>(book.class), dname);
        return book;
    }

    //查询数据库总记录数
    public long TotalCount() throws SQLException {
        String sql="select count(*) from book";
        long count=(long) runner.query(sql,new ScalarHandler());
        return count;
    }

    //限定类型，查询数据库总记录数
    public long TotalCountCid(int cid) throws SQLException {
        String sql="select count(*) from book where cid=?";
        long count=(long) runner.query(sql,new ScalarHandler(),cid);
        return count;
    }

    //从指定角标，查找特定数量的数据
    public List<book> SearchByIndex(int index,int count) throws SQLException {
        String sql="select * from book order by id desc limit ?,?";
        List<book> bookListByIndex = runner.query(sql, new BeanListHandler<book>(book.class), index,count);
        return bookListByIndex;
    }

    //限定类型并从指定角标，查找特定数量的数据
    public List<book> SearchByIndexCid(int index,int count,int cid) throws SQLException {
        String sql="select * from book where cid=? order by id desc limit ?,?";
        List<book> bookListByIndex = runner.query(sql, new BeanListHandler<book>(book.class), cid,index,count);
        return bookListByIndex;
    }

    //更新书籍信息
    public void UpdateBook(book onebook) throws SQLException {
        String sql="update book set have_count=?,lend_count=? where dname=?";
        runner.update(sql,onebook.getHave_count(),onebook.getLend_count(),onebook.getDname());
    }

    //删除书籍
    public void DeleteBookById(int id) throws SQLException {
        String sql="delete from book where id=?";
        runner.update(sql,id);
    }

    //更新书籍
    public void UpdateBookById(book onebook) throws SQLException {
        String sql="update book set dname=?,writer=?,position=?,have_count=?,book_desc=?,cid=? where id=?";
        runner.update(sql,onebook.getDname(),onebook.getWriter(),onebook.getPosition(),onebook.getHave_count(),onebook.getBook_desc(),onebook.getCid(),onebook.getId());
    }

    //添加书籍
    public void addBook(book onebook) throws SQLException {
        String sql="insert into book(dname,writer,position,have_count,book_desc,cid) values(?,?,?,?,?,?)";
        runner.update(sql,onebook.getDname(),onebook.getWriter(),onebook.getPosition(),onebook.getHave_count(),onebook.getBook_desc(),onebook.getCid());

    }
}
