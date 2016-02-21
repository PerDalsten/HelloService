package dk.purplegreen.hello;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceRef;

@WebServlet(urlPatterns = "/HelloServlet", initParams = @WebInitParam(name = "wsendpoint", value = "http://localhost:9080/HelloService/HelloService") )
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HelloServlet() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);

		Map<String, Object> ctx = ((BindingProvider) helloService).getRequestContext();

		ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, config.getInitParameter("wsendpoint"));
	}

	@WebServiceRef(value = HelloService_Service.class)
	private HelloService helloService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = request.getParameter("id") == null ? 0 : Integer.valueOf(request.getParameter("id"));

		response.setContentType("text/html");

		response.getWriter().append("<html><head><title>HelloServlet</title></head><body><h1>")
				.append(helloService.hello(id)).append("<h1></body>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		throw new UnsupportedOperationException();
	}

}
