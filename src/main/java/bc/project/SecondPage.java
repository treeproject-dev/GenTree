package bc.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Connections.FindById;
import gentrees.Person;

@RestController
public class SecondPage {

	@RequestMapping("/findbyid")
	public String findById(@RequestParam(value = "id", required = false) String id, HttpServletRequest request, HttpServletResponse response) {
		int ids=0;
		String result="";
		try {
			   ids = Integer.parseInt(id);
			}
			catch (NumberFormatException e)
			{
			   ids = 0;
			}
		if (ids!=0) {
		FindById findId = new FindById();
		Person person=findId.findById(ids);
		result = person.toString();
		//person.toPersonFrame(ids);
		}
		return result;
		
	}
}
