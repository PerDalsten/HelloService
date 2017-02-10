package dk.purplegreen.hello;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PersonNotFoundMapper implements ExceptionMapper<PersonNotFoundException> {

	@Override
	public Response toResponse(PersonNotFoundException e) {	
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
