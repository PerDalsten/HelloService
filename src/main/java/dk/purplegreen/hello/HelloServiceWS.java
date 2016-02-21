package dk.purplegreen.hello;

import javax.inject.Inject;
import javax.jws.WebService;

@WebService(endpointInterface = "dk.purplegreen.hello.HelloService", targetNamespace = "http://www.purplegreen.dk/HelloService", serviceName = "HelloService", portName = "HelloServiceSOAP")
public class HelloServiceWS implements HelloService {

	@Inject
	HelloServiceBean hello;

	@Override
	public String hello(int id) {
		return hello.sayHello(id);
	}
}
