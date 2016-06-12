
package com.youtube.rest.status;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.sql.*;

import com.youtube.rest.dao.*;

/**
 * This is the root path for our restful api service
 * In the web.xml file, we specified that /api/* need to be in the URL to
 * get to this class.
 * 
 * We are versioning the class in the URL path.  This is the first version v1.
 * Example how to get to the root of this api resource:
 * http://localhost:7001/com.youtube.rest/api/v1/status
 * 
 * @author 308tube
 *
 */
@Path("/v1/status/")
public class v1_status {
	
	private static final String api_version = "00.02.00"; //version of the api
	
	/**
	 * This method sits at the root of the api.  It will return the name
	 * of this api.
	 * 
	 * @return String - Title of the api
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>its not that difficult</p>";
	}
	
	/**
	 * This method will return the version number of the api
	 * Note: this is nested one down from the root.  You will need to add version
	 * into the URL path.
	 * 
	 * Example:
	 * http://localhost:7001/com.youtube.rest/api/v1/status/version
	 * 
	 * @return String - version number of the api
	 */
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version:</p>" + api_version;
	}
	
	/**
	 * This method will connect to the oracle database and return the date/time stamp.
	 * It will then return the date/time to the user in String format
	 * 
	 * This was explained in Part 3 of the Java Rest Tutorial Series on YouTube
	 * 
	 * @return String -  returns the database date/time stamp
	 * @throws Exception
	 */
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception {
		
		String myString = null;
		String returnString = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			
			//STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      conn = Oracle308tube.Oracle308tubeConn();
		      stmt = conn.createStatement();
		      String sql;
		      sql = "select *" +
					"from users";
		      ResultSet rs = stmt.executeQuery(sql);

			
			
			//loops through the results and save it into myString
			while (rs.next()) {
				// /*Debug*/ System.out.println(rs.getString("DATETIME"));
				myString = rs.getString("username");
			}
			
			rs.close();
		    stmt.close();
		    conn.close(); //close connection
			
			returnString = "<p>Database Status</p> " +
				"<p>Database username return: " + myString + "</p>";
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * The finally cause will always run. Even if the the method get a error.
		 * You want to make sure the connection to the database is closed.
		 */
		finally {
			//finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		}
		
		return returnString; 
	}
	
}
