package bc.project;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Connections.AppConnect;
import Connections.FindAllByNames;
import gentrees.Person;

@RestController
public class FirstPage {
	
	public static AppConnect conn = new AppConnect();

	@RequestMapping("/")
	public String select() {
		StringBuilder sb = new StringBuilder();

		sb.append("<a href='/find'>Find By Name</a>");

		return sb.toString();
	}

	@RequestMapping("/find")
	@ResponseBody
	public String find(@RequestParam(value = "name", required = false) String name,
		      @RequestParam(value = "surname", required = false) String surname, HttpServletRequest request,
		      HttpServletResponse response) {
		FindAllByNames find = new FindAllByNames();
		StringBuilder sb = new StringBuilder();
		
		sb.append("<form action=''>");
		sb.append("<label for='fname'>First name:</label><br/>");
		sb.append("<input type='text' name='name' value=''><br/>");
		sb.append("<label for='lname'>Last name:</label><br/>");
		sb.append("<input type='text' name='surname'  value=''><br/>");
		sb.append("<input type='submit' value='Submit'>");
		sb.append("</form><br/>");
		
		 if (name != null && !"".equals(name) && surname != null && !"".equals(surname)) {
			 List<Person>persons = find.findAllByNames(name, surname);
			 sb.append("<table>");
			// sb.append("<style>table, th, td {border: 1px solid black;}</style>");
			 sb.append("<tr><td>Id</td><td>Name</td><td>Surname</td></tr>");
			 for(Person it:persons) {
				 sb.append("<tr><td>"+it.getId()+"</td><td>"+it.getFirstName()+"</td><td>"+it.getSurName()+"</td></tr>");
			 }
			 sb.append("</table>");
		 }

		sb.append("<a href='/'>Back</a>");
		return sb.toString();
	}

}
