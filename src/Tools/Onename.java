package Tools;


import java.sql.SQLException;

import Model.admin;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;


//用来判断用户名是否唯一
public class Onename {
	private static QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());

	public static boolean OneName(String name) {
		String sql="select * from admin where uname=?";
		try {
			admin u = runner.query(sql,new BeanHandler<>(admin.class),name);
			if(u!=null) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean OneEmail(String email){
		String sql="select * from admin where email=?";
		admin u = null;
		try {
			u = runner.query(sql,new BeanHandler<>(admin.class),email);
			if(u!=null) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
