package Connections;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

//@Component
public class AppConnect 
{
	protected Connection conn;
	protected Environment env;
	String url,user,pass;
	//@Autowired
	public AppConnect(){
		 try {
	            File propertiesFile = new File("./" + "application.properties");
	            if (propertiesFile.exists()){
	            	System.out.println("file ok");
	            	}
	            } catch(Exception e) {
	            	System.err.println(e.toString());
	            }
		
		
		
		
	try {
			url = "jdbc:mysql://www.999.id.lv/?autoReconnect=true&serverTimezone=UTC&characterEncoding=utf8";
	      Class.forName("com.mysql.cj.jdbc.Driver"); // Load the driver class
	      conn = DriverManager.getConnection(
	          url, "root",
	          "Student007"); // Create connection
	      conn.setAutoCommit(false);
	    } catch (Exception e) {
	      System.err.println(e);
	    }
}
	
}