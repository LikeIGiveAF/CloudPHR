package com.phr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.phr.model.Login_details;
import com.phr.util.DBConnection;


public class Login_detailsDAO {
	public void register_intime(Login_details login) throws Exception{
		Connection con = null;
		try {
			con = DBConnection.connect();
			PreparedStatement ps = con.prepareStatement("insert into login_details values (?, ?)");
			ps.setString(1, login.getEmail());
			ps.setString(2, login.getLoginTime());
			ps.execute();
				
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		finally {
			con.close();
		}
	}
	public Login_details getLogin(String email) throws Exception{
		Connection con = null;
		try {
			con = DBConnection.connect();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select * FROM login_details WHERE email = '"+email+"' order by login_time desc LIMIT 1,1");
			Login_details login = new Login_details();
			login.setEmail(rs.getString("email"));
			login.setLoginTime(rs.getString("login_time"));
			return login;
			
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
			
		}finally {
			con.close();
		}
	}

}
