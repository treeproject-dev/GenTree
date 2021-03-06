package bc.project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Connections.FindById;
import gentrees.Person;
import tree.*;

@RestController
public class SecondPage {

	@RequestMapping("/findbyid")
	public String findById(@RequestParam(value = "id", required = false) String id, 
			HttpServletRequest request, 
			HttpServletResponse response) 
					throws FileNotFoundException {
		int ids=0;
		StringBuilder sb = new StringBuilder();
		String header = "<html><head><title>FindByID page</title><meta charset=\"utf-8\"><link rel=\"stylesheet\" href=\"/css/design.css\"></head><body>";	
		sb.append(header);
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
		List <Person> list =findId.findById(ids);
		//Person person=
		
		System.out.println(list.toString());
		
		String path = "/";
		
		//Save Person -> File "persons.js"
		Tree t = new Tree(list,null);
		
		try {
		t.personsToJSONFile(path + "persons.js");
		
		BufferedReader reader = new BufferedReader(new FileReader(path+"PersonDrawer.html")); 
		while (reader.ready()) {									    
			String str = reader.readLine();
			sb.append(str);
			};
		reader.close();	
		}
		 catch (IOException x) { System.err.print(x); }
		}
		/*
		//sb.append("<canvas id=\"myCanvas\" width=\"1000\" height=\"550\" style=\"margin: 0px; border:2px solid #d3d3d3;\"> ");
		//sb.append("Your browser does not support the HTML5 canvas tag.</canvas>                                    ");
		//sb.append("<input type=\"file\" id=\"jsonFile\" name=\"jsonFile\" />                                             ");
		//sb.append("<p id=\"screen-log\" style=\"\"></p>                                                                ");
		//sb.append("<script src=\"/prototypeI.js\"></script>                                                        ");
		//sb.append(person.toString());
		//sb.append("<br/><br/>");
				//person.toPersonFrame(ids);
		}
		//sb.append("<a href='/'>Back</a></p>");
		//sb.append("</body></html>");
		*/
		
		return sb.toString();
		
	}
}
