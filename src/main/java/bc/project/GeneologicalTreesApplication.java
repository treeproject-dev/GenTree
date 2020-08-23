package bc.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import Connections.FindByFamiliesId;
import Connections.FindById;
import gentrees.Person;
import gentrees.Wedding;

@SpringBootApplication
public class GeneologicalTreesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneologicalTreesApplication.class, args);
		
		FindByFamiliesId ff = new FindByFamiliesId();
		FindById fid = new FindById();
		List<Person>persons = fid.findById(32);
		System.out.println(persons.get(0).toString());
		List<Wedding>list = ff.findByFamiliesId(persons.get(0).mid);
		System.out.println(list.get(0).toString());
		
	}

}
