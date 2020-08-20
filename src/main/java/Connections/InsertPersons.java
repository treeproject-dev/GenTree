package Connections;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import gentrees.Person;

public class InsertPersons {
protected static AppConnect conn = new AppConnect();
	
public boolean insertPerson(Person person) {

    int i = 0;
    try {
    	java.sql.Date tmp;
    	if(person.getDeath()==null) { tmp = null;} else {tmp = new java.sql.Date(person.getDeath().getTime());}
      String sql = "INSERT INTO gentrees.persons (person_name, person_surname, person_date, person_date_death, person_gender) VALUES (?,?,?,?,?)";
      PreparedStatement preparedStatement = conn.conn.prepareStatement(sql);
      preparedStatement.setString(1, person.getFirstName());
      preparedStatement.setString(2, person.getSurName());
      preparedStatement.setDate(3, new java.sql.Date(person.getDateBirth().getTime()));   
      preparedStatement.setDate(4, tmp);
      preparedStatement.setString(5, person.getGender().toString());
      i = preparedStatement.executeUpdate();
      conn.conn.commit();
      
    } catch (SQLException e) {
      //  Auto-generated catch block
      e.printStackTrace();
    }
  
    return true;
  }
	
}
