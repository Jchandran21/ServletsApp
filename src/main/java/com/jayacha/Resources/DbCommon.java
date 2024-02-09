package com.jayacha.Resources;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class DbCommon {
	private static Connection con;
	private static Logger logger=LogManager.getLogger();;
	public static void createConnection() throws  ClassNotFoundException, SQLException {
		String dbName = "test_db";
		String dbUser = "root";
		String dbPass = "0000";
		String url = "jdbc:mysql://localhost:3306/" + dbName;
		Class.forName("com.mysql.cj.jdbc.Driver");
		DbCommon.con = DriverManager.getConnection(url,dbUser,dbPass);
		logger.info("New Connection is created");
	}
	 
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if(con==null) {
			DbCommon.createConnection();
		}
		logger.info("Database is accessed");
		return con;
	}
	
}