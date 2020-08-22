package Connections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gentrees.Person;

public class FindById {
	//protected static AppConnect conn = new AppConnect();
	protected Person person;
	
	public Person findById(int id) {
	try {
			AppConnect conn = new AppConnect();
	      String sql = "SELECT * FROM gentrees.persons where person_id=?";
	      PreparedStatement preparedStatement = conn.conn.prepareStatement(sql);
	      preparedStatement.setInt(1, id);
	      ResultSet rs = preparedStatement.executeQuery();
	      rs.next();
	      
	      person = new Person(rs.getString(2), rs.getString(3), rs.getString(6), rs.getDate(4));
	      conn.conn.close();
	    } catch (SQLException e) {
	      // Auto-generated catch block
	      e.printStackTrace();
	    }
	return person;
	}
}
