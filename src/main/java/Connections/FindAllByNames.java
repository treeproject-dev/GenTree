package Connections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gentrees.Person;

public class FindAllByNames {
	protected static AppConnect conn = new AppConnect();
	
	public List<Person> findAllByNames(String firstName, String lastName) {

	    List<Person> persons = new ArrayList<Person>();
	    try {
	    	AppConnect conn = new AppConnect();
	      String sql = "SELECT * FROM gentrees.persons where person_name LIKE ? AND person_surname LIKE ?";
	      PreparedStatement preparedStatement = conn.conn.prepareStatement(sql);
	      preparedStatement.setString(1, "%" + firstName + "%");
	      preparedStatement.setString(2, "%" + lastName + "%");
	      
	      ResultSet rs = preparedStatement.executeQuery();
	      
	      while (rs.next()) {
	    	Person x = new Person();
	    	       x.pid = rs.getInt(1);
	    	       x.firstName = rs.getString(2);
	    	       x.surName = rs.getString(3);
	        persons.add(x);
	      }
	      conn.conn.close();

	    } catch (SQLException e) {
	      //  Auto-generated catch block
	      e.printStackTrace();
	    }
	    
	    return persons;

	  }
	
}
