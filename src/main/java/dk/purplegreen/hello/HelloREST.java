package dk.purplegreen.hello;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
@RequestScoped
public class HelloREST {

	@Inject
	HelloServiceBean hello;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response hello(@PathParam("id") String id) {

		StringBuilder result = new StringBuilder();
		result.append("{ \"message\" : \"");

		result.append(hello.sayHello(Integer.valueOf(id)));
		result.append("\" }");

		return Response.ok(result.toString()).build();
	}
}
