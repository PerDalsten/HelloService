package dk.purplegreen.hello;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class HelloServiceBean {

	@Inject
	private PersonDAO dao;

	public String sayHello(int id) {

		Person person = findPerson(id);
		if (person == null) {
			return "Hello, Stranger!";
		} else
			return "Hello, " + person.getName() + "!";
	}

	public Person findPerson(Integer id) {
		return dao.find(id);
	}

	public void createPerson(Person person) {
		dao.save(person);
	}
	
	public void deletePerson(Person person) {
		dao.delete(person);
	}

	public List<Person> getAllPersons() {
		return dao.getAllPersons();
	}
}
