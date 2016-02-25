package dk.purplegreen.hello;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/person")
@RequestScoped
public class PersonREST {

	@Inject
	HelloServiceBean hello;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPerson(@PathParam("id") String id) {

		Person p = hello.findPerson(Integer.valueOf(id));
		if (p != null) {
			return Response.ok(p).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPerson(Person person) {

		person.setId(null);
		hello.savePerson(person);
		return Response.ok(person).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePerson(@PathParam("id") String id, Person person) {

		Person p = hello.findPerson(Integer.valueOf(id));

		if (p != null) {
			p.setName(person.getName());
			hello.savePerson(p);
			return Response.ok(p).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response deletePerson(@PathParam("id") String id) {

		Person p = hello.findPerson(Integer.valueOf(id));
		if (p != null) {
			hello.deletePerson(p);

			return Response.ok().build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}
