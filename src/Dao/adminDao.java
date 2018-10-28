package Dao;

import Model.admin;
import Tools.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class adminDao {
    private QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
    private admin user=null;
    public admin searchAdminByuname(admin u) throws Exception {
        String sql="select * from admin where uname=? and password=? and type=?";
        user = runner.query(sql, new BeanHandler<admin>(admin.class), u.getUname(), u.getPassword(),u.getType());
        return user;
    }
    public admin searchAdminByemail(admin u) throws Exception {
        String sql="select * from admin where email=? and password=? and type=?";
        user = runner.query(sql, new BeanHandler<admin>(admin.class), u.getEmail(), u.getPassword(),u.getType());
        return user;
    }
    public void addAdmin(admin u) throws SQLException {
        String sql="insert into admin(uname,password,email,type) values(?,?,?,?)";
        runner.update(sql, u.getUname(), u.getPassword(), u.getEmail(), u.getType());
    }
    //根据角标和特定数量要求查询所有普通用户
    public List<admin> SearchAllUser(int index,int count) throws SQLException {
        String sql="select * from admin where type=0 order by id desc limit ?,?";
        return runner.query(sql,new BeanListHandler<admin>(admin.class),index,count);
    }
    //查询普通用户数量
    public long UserCount() throws SQLException {
        String sql="select count(*) from admin where type=0";
        long count =(long)runner.query(sql,new ScalarHandler());
        return count;
    }
    public admin searchByuname(String uname) throws SQLException {
        String sql="select * from admin where uname=? and type=0";
        user = runner.query(sql, new BeanHandler<admin>(admin.class),uname);
        return user;
    }

    //根据Id更新用户的状态
    public void UpdateType(int id) throws SQLException {
        String sql="update admin set type=1 where id=?";
        runner.update(sql,id);
    }
}
