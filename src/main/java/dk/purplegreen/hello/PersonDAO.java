package dk.purplegreen.hello;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Model
public class PersonDAO {

	@PersistenceContext(unitName = "HelloService")
	EntityManager em;

	public void save(Person person) {
		if (person.getId() == null) {
			em.persist(person);
		} else {
			person = em.merge(person);
		}
	}

	public Person find(Integer id) {
		return em.find(Person.class, id);
	}

	public void delete(Person person) {
		em.remove(em.merge(person));
	}

	public List<Person> getAllPersons() {
		TypedQuery<Person> query = em.createNamedQuery("findAllPersons", Person.class);
		return query.getResultList();
	}
}
