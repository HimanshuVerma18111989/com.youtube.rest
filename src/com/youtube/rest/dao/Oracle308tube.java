package com.youtube.rest.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This class returns the Oracle database connect object from
 * a CentOS Oracle Express Virtual Machine
 * 
 * The method and variable in this class are static to save resources
 * You only need one instance of this class running.
 * 
 * This was explained in Part 3 of the Java Rest Tutorial Series on YouTube
 * 
 * @author 308tube
 *
 */
public class Oracle308tube {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost:3306/tutorial1";

	//  Database credentials
	private static final String USER = "root";
	private static final String PASS = "Kat@1703";
	private static Connection conn = null; //used to lookup the database connection in weblogic
	
	/**
	 * This is a public method that will return the 308tube database connection.
	 * 
	 * @return Database object
	 * @throws Exception
	 */
	public static Connection Oracle308tubeConn() throws Exception {
		
		/**
		 * check to see if the database object is already defined...
		 * if it is, then return the connection, no need to look it up again.
		 */
		if (conn != null) {
			return conn;
		}
		
		try {
			
			/**
			 * This only needs to run one time to get the database object.
			 * context is used to lookup the database object in weblogic
			 * Oracle308tube will hold the database object
			 */
			  //STEP 2: Register JDBC driver
			  Class.forName(JDBC_DRIVER).newInstance();

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
			  
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
}

