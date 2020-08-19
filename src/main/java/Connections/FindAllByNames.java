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
	      String sql = "SELECT * FROM gentrees.persons where person_name LIKE ? AND person_surname LIKE ?";
	      PreparedStatement preparedStatement = conn.conn.prepareStatement(sql);
	      preparedStatement.setString(1, "%" + firstName + "%");
	      preparedStatement.setString(2, "%" + lastName + "%");
	      
	      ResultSet rs = preparedStatement.executeQuery();
	      
	      while (rs.next()) {
	        persons.add(new Person(rs.getInt(1), rs.getString(2), rs.getString(3)));
	      }

	    } catch (SQLException e) {
	      //  Auto-generated catch block
	      e.printStackTrace();
	    }
	    
	    return persons;

	  }
	
}
