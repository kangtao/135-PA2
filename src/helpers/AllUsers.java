package helpers;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class AllUsers 
{
	 public static List<User> listAllUsers() 
	 {
		 Connection conn = null;
	     Statement stmt = null;
	     ResultSet rs = null;

	     List<User> userList = new ArrayList<User>();
	     try 
	     {
	    	 try 
	    	 {
	    		 conn = HelperUtils.connect();
	         } 
	    	 catch (Exception e) 
	    	 {
	    		 System.err.println("Internal Server Error. This shouldn't happen.");
	             return new ArrayList<User>();
	         }
	         stmt = conn.createStatement();
	         String query = "SELECT id, name, state, age FROM users WHERE role = 'customer'";
	         rs = stmt.executeQuery(query);
	         while (rs.next()) 
	         {
	        	 Integer id = rs.getInt(1);
	             String name = rs.getString(2);
	             String state = rs.getString(3);
	             Integer age = rs.getInt(4);
	             userList.add(new User(id, name, state, age));
	         }
	         return userList;
	     } 
	     catch (Exception e) 
	     {
	    	 System.err.println("Some error happened!<br/>" + e.getLocalizedMessage());
	         return new ArrayList<User>();
	     } 
	     finally 
	     {
	    	 try 
	    	 {
	    		 stmt.close();
	             conn.close();
	         } 
	    	 catch (SQLException e) 
	    	 {
	             e.printStackTrace();
	         }
	     }
	  } // end of listAllUsers method

	 
	 
	}

