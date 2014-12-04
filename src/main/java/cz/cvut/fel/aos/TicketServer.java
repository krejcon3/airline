package cz.cvut.fel.aos;

import cz.cvut.fel.aos.api.TicketService;

import javax.xml.ws.Endpoint;

/**
 * Created by krejcir on 3.12.14.
 */
public class TicketServer {

	public static void main(String[] args) {
		String bindingURI = "http://localhost:9898/printService";
		Endpoint.publish(bindingURI, new TicketService());
		System.out.println("Server started at: " + bindingURI);
	}
}
