package cz.cvut.fel.aos;

import cz.cvut.fel.aos.service.TicketService;

import javax.xml.ws.Endpoint;

/**
 * PrintService server
 *
 * @author Ondřej Krejčíř
 */
public class TicketServer {

	/**
	 * Publishing interface of PrintService
	 *
	 * @param args undefined for now
	 */
	public static void main(String[] args) {
		String bindingURI = "http://localhost:9898/printService";
		Endpoint.publish(bindingURI, new TicketService());
		System.out.println("Server started at: " + bindingURI);
	}
}
