package dk.purplegreen.hello;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "personController")
@RequestScoped
public class PersonController {

	@Inject
	private HelloServiceBean helloService;

	private Person person = new Person();

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String addPerson() {
		helloService.savePerson(person);
		return "index.xhtml";
	}

	public List<Person> getPersonList() {
		return helloService.getAllPersons();
	}
	
	public String deletePerson(Person p){		
		helloService.deletePerson(p);		
		return "list.xhtml";
	}
	
}
