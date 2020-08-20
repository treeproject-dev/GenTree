package bc.project;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Connections.AppConnect;
import Connections.FindAllByNames;
import Connections.InsertPersons;
import gentrees.Person;
import utils.DateConverters;

@RestController
public class FirstPage {
	public static DateConverters conv = new DateConverters();
	public static AppConnect conn = new AppConnect();

	@RequestMapping("/")
	public String select() {
		StringBuilder sb = new StringBuilder();

		sb.append("<a href='/find'>Find By Name</a><br/>");
		sb.append("<a href='/save'>Save new person</a>");

		return sb.toString();
	}

	@RequestMapping("/save")
	@ResponseBody
	public String test(HttpServletRequest request, HttpServletResponse response) {

		StringBuilder sb = new StringBuilder();
		sb.append("<form action=''>                                                 ");
		sb.append("  <label for='fname'>First name:</label><br/>                    ");
		sb.append("  <input type='text' name='name' value=''><br/>                    ");
		sb.append("  <label for='lname'>Last name:</label><br/>                     ");
		sb.append("  <input type='text' name='surname'  value=''><br/>               ");
		sb.append("  <label for='lname'>Date of Birth:</label><br/>                 ");
		sb.append("	<input type='date' name='date'><br/><br/>                         ");
		sb.append("<input type='radio' name='gender' value='male'>        ");
		sb.append("  <label for='male'>Male</label><br>                             ");
		sb.append("  <input type='radio'  name='gender' value='female'>  ");
		sb.append("  <label for='female'>Female</label><br>                         ");
		sb.append("  <input type='radio'  name='gender' value='unknown'>");
		sb.append("  <label for='unknown'>Unknown</label>                           ");
		sb.append("<br/><br/>                                                       ");
		sb.append("  <input type='submit' value='Submit'>                           ");
		sb.append("</form>                                                          ");

		if (request.getParameter("gender") != (null)) {
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String date = request.getParameter("date");
			String gender = request.getParameter("gender");
			if (conv.isCorrectDate(date)) {
				Date dateOk = conv.stringToDate(date);
				Person person = new Person(name, surname, gender, dateOk);
				InsertPersons save = new InsertPersons();

				try {
					save.insertPerson(person);
					sb.append("<p style='color:green'>Person added in DB</p>");
				} catch (Exception e) {
					sb.append("<p style='color:red'>Try again with correct information</p>");
					System.err.println(e);
				}

			} else {
				sb.append("<p style='color:red'>Try again with correct information</p>");
			}
		}
		sb.append("<a href='/'>Back</a>");
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
			List<Person> persons = find.findAllByNames(name, surname);
			sb.append("<table>");
			// sb.append("<style>table, th, td {border: 1px solid black;}</style>");
			sb.append("<tr><td>Id</td><td>Name</td><td>Surname</td></tr>");
			for (Person it : persons) {
				String callF = "<a href=/findbyid?id=" + it.getId() + ">" + it.getId() + "</a>";
				sb.append("<tr><td>" + callF + "</td><td>" + it.getFirstName() + "</td><td>" + it.getSurName()
						+ "</td></tr>");
			}
			sb.append("</table><br/>");
		}

		sb.append("<a href='/'>Back</a>");
		return sb.toString();
	}

}
