package Connections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gentrees.Person;

public class FindById {
	//protected static AppConnect conn = new AppConnect();
	protected static Person person;
	
	public static List<Person> findById(int id) {
		List<Person>persons = new ArrayList<>();
	try {
		AppConnect conn = new AppConnect();
	      String sql = "SELECT * FROM gentrees.persons where person_id=?";
	      PreparedStatement preparedStatement = conn.conn.prepareStatement(sql);
	      preparedStatement.setInt(1, id);
	      ResultSet rs = preparedStatement.executeQuery();
	      rs.next();
	      
	      person = new Person(rs.getString(2), rs.getString(3), rs.getString(6), rs.getDate(4), rs.getInt(7));
	      conn.conn.close();
	      
	      persons.add(person);
	    } catch (SQLException e) {
	      // Auto-generated catch block
	      e.printStackTrace();
	    }
	return persons;
	}
}
