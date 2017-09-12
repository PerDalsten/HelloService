package dk.purplegreen.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HelloServiceBeanTest {

	@Mock
	private PersonDAO dao;

	@InjectMocks
	private HelloServiceBean helloService;

	@Test
	public void testSayHelloKnown() {

		when(dao.find(1)).thenReturn(new Person("Homer"));

		String result = helloService.sayHello(1);

		assertEquals("Hello, Homer!", result);
	}

	@Test
	public void testSayHelloUnknown() {

		String result = helloService.sayHello(99);

		assertEquals("Hello, Stranger!", result);
	}
}
