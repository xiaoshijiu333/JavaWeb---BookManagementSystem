package Dao;

import Model.records;
import Tools.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class recordsDao {

    private QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());

    //从指定角标、用户，查找特定数量的数据
    public List<records> SearchAllRec(String uname,int index,int count ) throws SQLException {
        String sql="select * from records where uname=? order by id desc limit ?,?";
        return runner.query(sql, new BeanListHandler<records>(records.class), uname,index,count);
    }

    //查询指定用户的记录个数
    public long TotalCountByName(String uname) throws SQLException {
        String sql="select count(*) from records where uname=?";
        long count=(long) runner.query(sql,new ScalarHandler(),uname);
        return count;
    }

    //查询指定用户、指定角标，查找特定数量是否归还的记录
    public List<records> IsReturnRec(String uname,int flag,int index,int count) throws SQLException {
        String sql=null;
        if(flag==1){
            sql="select * from records where uname=? and returntime is null order by id desc limit ?,?";
        }else{
            sql="select * from records where uname=? and returntime is not null order by id desc limit ?,?";
        }
        return runner.query(sql,new BeanListHandler<records>(records.class),uname,index,count);

    }

    //查询指定用户是否归还的记录的个数
    public long IsReturnCount(String uname,int flag) throws SQLException {
        String sql=null;
        if(flag==1){
            sql="select count(*) from records where uname=? and returntime is null";
        }else{
            sql="select count(*) from records where uname=? and returntime is not null";
        }
        long count=(long) runner.query(sql,new ScalarHandler(),uname);
        return count;
    }

    //根据角标，指定用户的指定书名称查找相应的记录，记录可能会有多条
    public List<records> RecByName(String uname,String dname,int index,int count) throws SQLException {
        String sql="select * from records where uname=? and dname=? order by id desc limit ?,?";
        List<records> recordsList = runner.query(sql, new BeanListHandler<records>(records.class), uname, dname, index, count);
        return recordsList;
    }

    //查询指定用户的指定书名称的记录的个数
    public long RecByNameCount(String uname,String dname) throws SQLException {
        String sql="select count(*) from records where uname=? and dname=?";
        long count =(long)runner.query(sql,new ScalarHandler(),uname,dname);
        return count;
    }

    //添加一条记录
    public void addRec(records record) throws SQLException {
        String sql="insert into records(dname,uname,lendtime,returntime,shouldtime) values(?,?,?,?,?)";
        runner.update(sql,record.getDname(),record.getUname(),record.getLendtime(),record.getReturntime(),record.getShouldtime());
    }

    //根据Id进行查询
    public records SearchRecById(int id) throws SQLException {
        String sql="select * from records where id=?";
        return runner.query(sql,new BeanHandler<records>(records.class),id);
    }

    //更新记录信息
    public void UpdateRec(records record) throws SQLException {
        String sql="update records set returntime=? where id=?";
        runner.update(sql,record.getReturntime(),record.getId());
    }

    //查询所有用户的所有记录个数
    public long TotalCount() throws SQLException {
        String sql="select count(*) from records";
        long count =(long)runner.query(sql,new ScalarHandler());
        return count;
    }

    //从指定角标，查找特定数量的数据
    public List<records> SearchAllRec(int index,int count ) throws SQLException {
        String sql="select * from records order by id desc limit ?,?";
        return runner.query(sql, new BeanListHandler<records>(records.class),index,count);
    }

    //查询所有用户是否归还的记录的个数
    public long AllIsReturnCount(int flag) throws SQLException {
        String sql=null;
        if(flag==1){
            sql="select count(*) from records where returntime is null";
        }else{
            sql="select count(*) from records where returntime is not null";
        }
        long count=(long) runner.query(sql,new ScalarHandler());
        return count;
    }

    //查询所有用户、指定角标，查找特定数量是否归还的记录
    public List<records> AllIsReturnRec(int flag,int index,int count) throws SQLException {
        String sql=null;
        if(flag==1){
            sql="select * from records where returntime is null order by id desc limit ?,?";
        }else{
            sql="select * from records where returntime is not null order by id desc limit ?,?";
        }
        return runner.query(sql,new BeanListHandler<records>(records.class),index,count);

    }

    //根据角标，所有用户的指定书名称查找相应的记录
    public List<records> RecByDname(String dname,int index,int count) throws SQLException {
        String sql="select * from records where dname=? order by id desc limit ?,?";
        List<records> recordsList = runner.query(sql, new BeanListHandler<records>(records.class), dname, index, count);
        return recordsList;
    }

    //查询所有用户的指定书名称的记录的个数
    public long RecByDnameCount(String dname) throws SQLException {
        String sql="select count(*) from records where dname=?";
        long count =(long)runner.query(sql,new ScalarHandler(),dname);
        return count;
    }

    //查询指定用户的所有记录的个数
    public long RecByUnameCount(String uname) throws SQLException {
        String sql="select count(*) from records where uname=?";
        long count =(long)runner.query(sql,new ScalarHandler(),uname);
        return count;
    }

    //根据角标，指定用户查找所有的记录
    public List<records> RecByUname(String uname,int index,int count) throws SQLException {
        String sql="select * from records where uname=? order by id desc limit ?,?";
        List<records> recordsList = runner.query(sql, new BeanListHandler<records>(records.class), uname, index, count);
        return recordsList;
    }
}

