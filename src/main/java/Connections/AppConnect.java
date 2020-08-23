package Connections;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
//@Configuration
//@PropertySource("application.properties")
@Component
public class AppConnect 
{
	@Autowired
    private Environment env;
	
	
	@Value("${my.name}")private String name;
	
	public Connection conn;
	
	public AppConnect(){
		
		//System.out.println("PRINT FROM ENV: "+ env.getProperty("spring.datasource.username"));
	try {
		
		
		String url = "jdbc:mysql://www.999.id.lv/?autoReconnect=true&serverTimezone=UTC&characterEncoding=utf8";
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