package gentrees;

import java.util.Date;
import java.time.LocalDate;

public class Person {
	public int id;
	public String firstName, surName;
	public Date dateBirth, death;
	public Person myFather;
	public Person myMother;
	public String gender="Unknown";
	public int age;
	public int mid;

	public Person() {
		this.firstName = "First Name";
		this.surName = "Surname";

	}

//constructers
	public Person(String firstName, String surName, String gender, Date dateBirth, int mid) {
		this.firstName = firstName;
		this.surName = surName;
		this.gender = gender;
		this.dateBirth = dateBirth;
		this.mid = mid;

	}

	public int getId() {
		return id;
	}

	public Person(int id, String firstName, String surName) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.surName = surName;
}

	public Person(String firstName, String surName, String gender, Date dateBirth, Date death, Person father,
			Person mother) {
		this.firstName = firstName;
		this.surName = surName;
		this.gender = gender;
		this.dateBirth = dateBirth;
		this.death = death;
		this.myFather = father;
		this.myMother = mother;

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {

		this.dateBirth = dateBirth;
	}

	public Date getDeath() {
		return death;
	}

	public void setDeath(Date death) {

		this.death = death;
	}

	public Person getMyFather() {
		return myFather;
	}

	public void setMyFather(Person myFather) {
		this.myFather = myFather;
	}

	public Person getMyMother() {
		return myMother;
	}

	public void setMyMother(Person myMother) {
		this.myMother = myMother;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
//(man.getAge() >= 18) ? "Все в порядке, проходите!" : "Этот фильм не подходит для вашего возраста!";
		return firstName + " " + surName + " " + gender + " " + ((dateBirth==null)?"":dateBirth) + " ";
	}

	public String sendJSON(Person person) {
		String message = "{\"id\":\"" + person.id + ",\r\n" + 
				" \"name\":\"" + person.firstName + ",\r\n" + 
				" \"surname\":\"" + person.surName + ",\r\n" + 
				" \"gender\":\"" + person.gender + ",\r\n" + 
				" \"birth\":\"" + person.dateBirth + ",\r\n" + 
				" \"death\":\"" + person.death + ",\r\n" + 
				" \"family\":null,\r\n" + 
				" \"x\":100,\r\n" + 
				" \"y\":100,\r\n" + 
				" \"active\":false,\r\n" + 
				" \"hiden\":false,\r\n" + 
				" \"move\":false}";

		
		return message;
	}

}

