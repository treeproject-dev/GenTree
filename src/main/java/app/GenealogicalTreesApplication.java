package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import app.domain.Person;
import app.domain.Wedding;
import app.service.FindByFamiliesId;
import app.service.FindById;
import app.service.GetListsForWeddings;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class GenealogicalTreesApplication {
	 
	public static void main(String[] args) {
		SpringApplication.run(GenealogicalTreesApplication.class, args);
		
		/*GetListsForWeddings get = new GetListsForWeddings();
		List<Person>males = get.getByGenders("male");
		List<Person>females = get.getByGenders("female");
		System.out.println("males: ");
		for(Person it:males) {
			System.out.println(it.toString());
		}
		System.out.println("females: ");
		for(Person it:females) {
			System.out.println(it.toString());
		}*/
	}
	


}
