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
		// If saving a previously persisted Person make sure it is in context
		// (or a new Person will be created)
		if (person.getId() != null && !em.contains(person)) {
			person = em.merge(person);
		} else {
			em.persist(person);
		}
	}

	public Person find(Integer id) {
		return em.find(Person.class, id);
	}

	public void delete(Person person) {
		em.remove(find(person.getId()));
	}

	public List<Person> getAllPersons() {
		TypedQuery<Person> query = em.createNamedQuery("findAllPersons", Person.class);
		return query.getResultList();
	}
}
