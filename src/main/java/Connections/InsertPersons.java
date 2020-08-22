package Connections;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import gentrees.Person;

public class InsertPersons {
//protected static AppConnect conn = new AppConnect();
	
public boolean insertPerson(Person person) {

    int i = 0;
    try {
    	AppConnect conn = new AppConnect();
    	java.sql.Date tmp,tmp2;
    	if(person.getDeath()==null){tmp = null;}else{tmp = new java.sql.Date(person.getDeath().getTime());}
    	if(person.getDateBirth()==null){tmp2 = null;}else{tmp2 = new java.sql.Date(person.getDateBirth().getTime());}
      String sql = "INSERT INTO gentrees.persons (person_name, person_surname, person_dateBirth, person_death, person_gender) VALUES (?,?,?,?,?)";
      PreparedStatement preparedStatement = conn.conn.prepareStatement(sql);
      preparedStatement.setString(1, person.getFirstName());
      preparedStatement.setString(2, person.getSurName());
      preparedStatement.setDate(3, tmp2);   
      preparedStatement.setDate(4, tmp);
      preparedStatement.setString(5, person.getGender().toString());
      i = preparedStatement.executeUpdate();
      conn.conn.commit();
      conn.conn.close();
    } catch (SQLException e) {
      //  Auto-generated catch block
      e.printStackTrace();
    }
  
    return true;
  }
	
}
