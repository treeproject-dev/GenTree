package gentrees;

import java.util.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import tree.Tree;
import tree.Kleisli;
import tree.TreeInterface;
import utils.DateConverters;

public class Person {
	public String firstName, surName;
	public Date dateBirth, death;
	public Person myFather;				// <- obsolete;
	public Person myMother;				// <- 		
	public Wedding myFamily;	// <- new: myFamily :: Wedding;
	public String gender;
	public int age;
	
	//technical:
	public int pid;	// <- Person's ID from database;
	public int mid;	// <- ID of Persons Family (Alma Mater);
	

//constructors	
	public Person() {
		this.firstName = "First Name";
		this.surName = "Surname";
        this.gender = "unknown";
	}

	public Person(String name, String surname, String gender) {
		this.firstName = name;
		this.surName = surname;
        this.gender = gender;
	}
	
	public Person(String firstName, String surName, String gender, Date dateBirth) {
		this.firstName = firstName;
		this.surName = surName;
		this.gender = gender;
		this.dateBirth = dateBirth;

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
		return this.getFamily().getHusband();
	}

	public void setMyFather(Person myFather) {
		this.getFamily().setHusband(myFather);
	}

	public Person getMyMother() {
		return this.getFamily().getWife();
	}

	public void setMyMother(Person myMother) {
		this.getFamily().setWife(myMother);
	}

	//??
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

	
	/* ARS */

	//New:
	
	// p -> w
	public Wedding getFamily() {
		return myFamily;
	}

	public void setFamily(Wedding myFamily) {
		this.myFamily = myFamily;
	}

				/* ---==[Navigation]==--- */
	
	//Start Stream of Searching;
	public TreeInterface search() {
		ArrayList<Person> ps = new ArrayList<Person>();
		ArrayList<Wedding> ws = new ArrayList<Wedding>();
		
		ps.add(this);
		
		Tree t = new Tree(ps, ws);
		return t;
	}
	
	// if null - returns []
	public List<Person> getParents() {
		List<Person> res = new ArrayList<Person>();
		if (this.getFamily() != null)
			if (this.getFamily().getSpouses() != null)
				res = this.getFamily().getSpouses(); 
		return res;
	};	
	
	
	//TODO Check;
	public List<Wedding> getWeddings(Tree t) {
		List<Wedding> result = t.weddings.stream()
		    .filter( w -> this.equals(w.getHusband())
		    		   || this.equals(w.getWife()) )
		    .collect(Collectors.toList());
		return result;
	};
	
	

	//TODO Check;
	public List<Person> getChildren(Tree t) {
		return this.search()
			.p_w( (p) -> {return this.getWeddings(t);} )
			.w_p( (w) -> {return w.getChildren(t);} )
			.toPersons();
/*		
		List<Person> result = 
				t.p_w( (Person p)  -> {return this.getWeddings(t);} )
				 .w_p( (Wedding w) -> {return w.getChildren(t);} )
				 .getPersons();
		return result;
*/
		
	};	
	
	//TODO Check;
	public List<Person> getSpouses(Tree t) {
		return this.search()
				.p_w( (Person p)  -> {return p.getWeddings(t);} )
				.w_p( (Wedding w) -> {return w.getSpouses();} ) //<- "this" in [p]; 
				.p_p( (Person p)  -> {ArrayList<Person> ps = new ArrayList<Person>();
												if (!this.equals(p)) {ps.add(p);};
				    	 					  return ps;} ).toPersons();
	};	

	//Returns List of Brother and Sisters, that includes (step-) brother & sisters. 
	public List<Person> getSiblings(Tree t) {
		return this.search()
			.p_p( (p) -> { return p.getParents();   }  )
			.p_p( (p) -> { return p.getChildren(t); }  )
			.toPersons();
	}

	// TODO
	//format "yyyy/mm/dd" or "dd.mm.yyyy" or smth else.
	public String getDateBirthString(String format) {
		
		String res = "";
		
		int dd   = this.getDateBirth().getDay();
		int mm   = this.getDateBirth().getMonth();
		int yyyy = this.getDateBirth().getYear();
		
		switch (format) {
		case "yyyy/mm/dd":
			res = yyyy+"/"+mm+"/"+dd;
		break;
		case "dd.mm.yyyy":
			res = dd+"."+mm+"."+yyyy; 
		break;
		default:
			res = "";
		}
		return res;
	}

	// TODO read BirthDate from String
	//format "yyyy/mm/dd"
	public void setBirthDateFromString(String date, String format) {
		/* EMPTY*/
	}
	
	// TODO the same date convertions for Date of death. 
	
	//TEST:
	public static void main(String[] args) {
		Person pers = new Person();
		pers.firstName = "Charles";
		pers.surName   = "Darwin";
		pers.gender    = "male";
		pers.dateBirth = new Date(1809,02,12);
		pers.death	   = new Date(1882,04,19);

		System.out.println(pers.toString());
		System.out.println(pers.toJSONStr());
		
		
/*
 		pers.firstName = "Alexander";
		pers.surName   = "Pushkin";
		pers.gender    = "male";
		pers.dateBirth =  new Date(1799,05,26);
		pers.death     =  new Date(1837,01,29);
*/
		
//		pers.birth         =LocalDate()  
//				new LocalDate(1799,05,26);
//		pers.dateDeath     =  new LocalDate(1837,01,29);

		System.out.println(pers.toString());
		
		System.out.println("PersonFrameConstructor:");
		
	}
	
	/* CONVERTORS */
	
	public String convertGender() {
		String gender = "U";
		if ("male".equals(this.gender))
           gender = "M";
		else gender = "F";
		return gender;
	}
	
	
	/* SAVE & LOAD */
	
	public String toJSON() {
		
		String b = "";
		String d = "";
		
		if (this.dateBirth != null)
			b = DateConverters.dateToString(this.dateBirth);
		if (this.death != null)
			d = DateConverters.dateToString(this.death);
		
		return  "{\"id\":\"" + this.pid + "\",\r\n" + 
				" \"name\":\"" + this.firstName + "\",\r\n" + 
				" \"surname\":\"" + this.surName + "\",\r\n" + 
				" \"gender\":\"" + this.convertGender() + "\",\r\n" + 
				" \"birth\":\"" + b + "\",\r\n" + 
				" \"death\":\"" + d + "\",\r\n" + 
				" \"family\":null,\r\n" + 
				" \"x\":100,\r\n" + 
				" \"y\":100,\r\n" + 
				" \"active\":false,\r\n" + 
				" \"hiden\":false,\r\n" + 
				" \"move\":false}";
		
	}

	public String toJSONStr() {
		
		String b = "";
		String d = "";
		
		if (this.dateBirth != null)
			b = DateConverters.dateToString(this.dateBirth);
		if (this.death != null)
			d = DateConverters.dateToString(this.death);
		
		return "{\"id\":\"" + this.pid + "\"," + 
				" \"name\":\"" + this.firstName + "\"," + 
				" \"surname\":\"" + this.surName + "\"," + 
				" \"gender\":\"" + this.convertGender() + "\"," + 
				" \"birth\":\"" + b + "\"," + 
				" \"death\":\"" + d + "\"," + 
				" \"family\":null," + 
				" \"x\":100," + 
				" \"y\":100," + 
				" \"active\":false," + 
				" \"hiden\":false," + 
				" \"move\":false}";
		
	}

	
}
