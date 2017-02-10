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
		person.setId(null);
		dao.save(person);
	}

	public void updatePerson(Person person) throws PersonNotFoundException {
		Person p = findPerson(person.getId());
		if (p == null) {
			throw new PersonNotFoundException("Person with id " + person.getId() + " does not exist");
		}

		dao.save(person);
	}

	public void deletePerson(Person person) throws PersonNotFoundException {
		Person p = findPerson(person.getId());
		if (p == null) {
			throw new PersonNotFoundException("Person with id " + person.getId() + " does not exist");
		}

		dao.delete(person);
	}

	public List<Person> getAllPersons() {
		return dao.getAllPersons();
	}
}
