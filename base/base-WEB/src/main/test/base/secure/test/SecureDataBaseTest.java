package base.secure.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import junit.framework.Assert;

public class SecureDataBaseTest {

	@Test
	public void TestJDBC(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String u = "root";
		String p = "root";
		String url = "jdbc:mysql://localhost:3306/secure_rbac?serverTimezone=UTC&characterEncoding=utf-8";
		
		Connection conn = null;
		java.sql.PreparedStatement statment;
		
		try {
			conn = DriverManager.getConnection(url,u,p);
			statment = conn.prepareStatement("SELECT password FROM user WHERE username = ?");
			statment.setString(1, "test");
			ResultSet res = statment.executeQuery();
			while(res.next())
			Assert.assertEquals(true, "test".equals(res.getString("password")));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
